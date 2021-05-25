package br.com.petz.pet.domain.service;

import br.com.petz.pet.domain.adapter.PetRepository;
import br.com.petz.pet.domain.port.PetPort;
import br.com.petz.pet.integration.entity.PetEntity;
import br.com.petz.pet.rest.model.Pet;
import br.com.petz.pet.rest.model.PetEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PetService implements PetPort {
    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet findPetByIdPet(UUID idPet, UUID idCliente) {
        return PetMapper.entityToModel(petRepository.findByIdPetAndIdCliente(idPet, idCliente));
    }

    @Override
    public List<Pet> findPetByIdCliente(UUID idCliente) {
        if(petRepository.existsByIdCliente(idCliente)){
            List<Pet> result = new ArrayList<>();
            List<PetEntity> aux = petRepository.findAllByIdCliente(idCliente);
            for(PetEntity entity : aux){
                result.add(PetMapper.entityToModel(entity));
            }
            return result;
        }else {
            return new ArrayList<Pet>();
        }
    }

    @Override
    public PetEmpty insertPet(Pet pet) {
        try {
            if (petRepository.existsByNomeAndAndIdCliente(pet.getNome(), pet.getIdCliente())) {
                return new PetEmpty();
            } else {
                return PetMapper.entityToModel(petRepository.save(PetMapper.modelToEntity(pet)));
            }
        } catch (NullPointerException ex){
            return new PetEmpty();
        }
    }

    @Override
    public PetEmpty updatePet(Pet pet) {
        try {
            if (petRepository.existsByNomeAndAndIdCliente(pet.getNome(), pet.getIdCliente())) {
                return PetMapper.entityToModel(petRepository.save(PetMapper.modelToEntity(pet)));
            } else {
                return new PetEmpty();
            }
        } catch (NullPointerException ex){
            return new PetEmpty();
        }
    }

    @Override
    public boolean deletePetByIdPet(UUID idPet, UUID idCliente) {
        if(petRepository.existsByIdPetAndIdCliente(idPet, idCliente)){
            petRepository.delete(petRepository.findByIdPetAndIdCliente(idPet, idCliente));
            return true;
        }else {
            return false;
        }
    }
}
