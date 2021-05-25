package br.com.petz.pet.domain.port;

import br.com.petz.pet.rest.model.Pet;
import br.com.petz.pet.rest.model.PetEmpty;

import java.util.List;
import java.util.UUID;

public interface PetPort {
    Pet findPetByIdPet(UUID idPet, UUID idCliente);
    List<Pet> findPetByIdCliente(UUID idCliente);
    PetEmpty insertPet(Pet pet);
    PetEmpty updatePet(Pet pet);
    boolean deletePetByIdPet(UUID idPet, UUID idCliente);
}
