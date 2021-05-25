package br.com.petz.pet.rest;

import br.com.petz.pet.domain.port.PetPort;
import br.com.petz.pet.rest.model.DataResponse;
import br.com.petz.pet.rest.model.Pet;
import br.com.petz.pet.rest.model.PetEmpty;
import br.com.petz.pet.rest.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/cliente/{idCliente}/pet")
public class PetController {
    @Autowired
    private PetPort petPort;

    @GetMapping("/{idPet}")
    private ResponseEntity<?> findPet(@Valid @PathVariable(value = "idCliente") UUID idClient,
                                         @Valid @PathVariable(value = "idPet") UUID idPet){
        try {
            return ResponseEntity.ok().body(DataResponse.builder().data(petPort.findPetByIdPet(idPet, idClient)).build());
        } catch (NullPointerException ex){
            return ResponseEntity.status(404).body(DataResponse.builder().data(Constantes.PET_NAO_ENCONTRADO).build());
        }
    }

    @PatchMapping("/{idPet}")
    private ResponseEntity<?> updatePet(@Valid @RequestBody Pet pet,
                                        @Valid @PathVariable(value = "idCliente") UUID idClient,
                                        @Valid @PathVariable(value = "idPet") UUID idPet){
        pet.setIdPet(idPet);
        pet.setIdCliente(idClient);
        PetEmpty petEmpty = petPort.updatePet(pet);
        return petEmpty instanceof Pet ?
                ResponseEntity.ok().body(DataResponse.builder().data(petEmpty).build()):
                ResponseEntity.status(404).body(DataResponse.builder().data(Constantes.PET_NAO_ENCONTRADO).build());
    }

    @PostMapping
    private ResponseEntity<?> insertPet(@Valid @PathVariable(value = "idCliente") UUID idClient,
                                           @Valid @RequestBody Pet pet){
        pet.setIdCliente(idClient);
        PetEmpty petEmpty = petPort.insertPet(pet);
        return petEmpty instanceof Pet ?
                ResponseEntity.status(201).body(DataResponse.builder().data(petEmpty).build()):
                ResponseEntity.status(400).body(DataResponse.builder().data(Constantes.PET_JA_CADASTRADO).build());
    }

    @DeleteMapping("/{idPet}")
    private ResponseEntity<?> removePet(@Valid @PathVariable(value = "idCliente") UUID idClient,
                                           @Valid @PathVariable(value = "idPet")UUID idPet){
        return petPort.deletePetByIdPet(idPet, idClient)?
                ResponseEntity.ok().body(DataResponse.builder().data(Constantes.PET_EXCLUIDO_SUCESSO).build()):
                ResponseEntity.status(404).body(DataResponse.builder().data(Constantes.PET_NAO_ENCONTRADO).build());
    }
}
