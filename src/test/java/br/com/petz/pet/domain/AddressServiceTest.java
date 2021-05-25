package br.com.petz.pet.domain;

import br.com.petz.pet.domain.adapter.PetRepository;
import br.com.petz.pet.domain.service.PetMapper;
import br.com.petz.pet.domain.service.PetService;
import br.com.petz.pet.integration.entity.PetEntity;
import br.com.petz.pet.rest.model.Pet;
import br.com.petz.pet.rest.model.PetEmpty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private PetService petService;

    @MockBean
    private PetRepository petRepository;

    @Test
    public void shouldFindPhoneByClientID(){

        List<Pet> listaPhone = new ArrayList<>();
        List<PetEntity> listaEntity = new ArrayList<>();
        UUID idCliente = UUID.randomUUID();
        UUID idPet = UUID.randomUUID();
        Pet clienteE = Pet.builder()
                .idCliente(idCliente)
                .idPet(idPet)
                .raca("SRD")
                .nome("Beta")
                .idade(11)
                .peso(8)
                .cor("Caramelo")
                .build();

        listaPhone.add(clienteE);
        listaEntity.add(PetMapper.modelToEntity(clienteE));
        when(petRepository.existsByIdCliente(idCliente)).thenReturn(true);
        when(petRepository.findAllByIdCliente(idCliente)).thenReturn(listaEntity);

        assertEquals(petService.findPetByIdCliente(idCliente), listaPhone);
    }

    @Test
    public void shouldNotFindPhoneByClientID(){

        List<Pet> listaPhone = new ArrayList<>();
        List<PetEntity> listaEntity = new ArrayList<>();
        UUID idCliente = UUID.randomUUID();
        UUID idPet = UUID.randomUUID();
        Pet clienteE = Pet.builder()
                .idCliente(idCliente)
                .idPet(idPet)
                .raca("SRD")
                .nome("Beta")
                .idade(11)
                .peso(8)
                .cor("Caramelo")
                .build();

        listaPhone.add(clienteE);
        listaEntity.add(PetMapper.modelToEntity(clienteE));
        when(petRepository.existsByIdCliente(idCliente)).thenReturn(false);
        when(petRepository.findAllByIdCliente(idCliente)).thenReturn(listaEntity);

        assertTrue(petService.findPetByIdCliente(idCliente).isEmpty());
    }

    @Test
    public void shouldInserClient(){
        UUID idCliente = UUID.randomUUID();
        UUID idPet = UUID.randomUUID();
        Pet clienteE = Pet.builder()
                .idCliente(idCliente)
                .idPet(idPet)
                .raca("SRD")
                .nome("Beta")
                .idade(11)
                .peso(8)
                .cor("Caramelo")
                .build();
        Pet clienteS = Pet.builder()
                .idCliente(idCliente)
                .idPet(idPet)
                .raca("SRD")
                .nome("Beta")
                .idade(11)
                .peso(8)
                .cor("Caramelo")
                .build();

        when(petRepository.existsByNomeAndAndIdCliente(clienteE.getNome(),clienteE.getIdCliente())).thenReturn(false);
        when(petRepository.save(PetMapper.modelToEntity(clienteE))).thenReturn(PetMapper.modelToEntity(clienteS));

        assertEquals(petService.insertPet(clienteE),clienteS);
    }

    @Test
    public void shouldNotInserExistingPhone(){
        UUID idCliente = UUID.randomUUID();
        UUID idPet = UUID.randomUUID();
        Pet clienteE = Pet.builder()
                .idCliente(idCliente)
                .idPet(idPet)
                .raca("SRD")
                .nome("Beta")
                .idade(11)
                .peso(8)
                .cor("Caramelo")
                .build();

        when(petRepository.existsByNomeAndAndIdCliente(clienteE.getNome(), clienteE.getIdCliente())).thenReturn(true);
        assertTrue(petService.insertPet(clienteE) instanceof PetEmpty);
    }

    @Test
    public void shouldUpdateClient(){
        UUID idCliente = UUID.randomUUID();
        UUID idPet = UUID.randomUUID();
        Pet clienteE = Pet.builder()
                .idCliente(idCliente)
                .idPet(idPet)
                .raca("SRD")
                .nome("Beta")
                .idade(11)
                .peso(8)
                .cor("Caramelo")
                .build();

        when(petRepository.existsByNomeAndAndIdCliente(clienteE.getNome(),clienteE.getIdCliente())).thenReturn(true);
        when(petRepository.save(PetMapper.modelToEntity(clienteE))).thenReturn(PetMapper.modelToEntity(clienteE));

        assertEquals(petService.updatePet(clienteE),clienteE);
    }

    @Test
    public void shouldNotUpdateInvalidPhone(){
        UUID idCliente = UUID.randomUUID();
        UUID idPet = UUID.randomUUID();
        Pet clienteE = Pet.builder()
                .idCliente(idCliente)
                .idPet(idPet)
                .raca("SRD")
                .nome("Beta")
                .idade(11)
                .peso(8)
                .cor("Caramelo")
                .build();

        when(petRepository.existsByNomeAndAndIdCliente(clienteE.getNome(), idCliente)).thenReturn(false);

        assertTrue(petService.updatePet(clienteE) instanceof PetEmpty);
    }

    @Test
    public void shouldDeleteClient(){
        UUID idCliente = UUID.randomUUID();
        UUID idPet = UUID.randomUUID();

        when(petRepository.existsByIdPetAndIdCliente(idPet,idCliente)).thenReturn(true);

        assertTrue(petService.deletePetByIdPet(idPet,idCliente));
    }

    @Test
    public void shouldNotDeleteInvalidClient(){
        UUID idCliente = UUID.randomUUID();
        UUID idPet = UUID.randomUUID();

        when(petRepository.existsByIdPetAndIdCliente(idPet,idCliente)).thenReturn(false);

        assertFalse(petService.deletePetByIdPet(idPet,idCliente));
    }
}
