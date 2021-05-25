package br.com.petz.pet.domain.service;

import br.com.petz.pet.integration.entity.PetEntity;
import br.com.petz.pet.rest.model.Pet;

public class PetMapper {
    public static Pet entityToModel(PetEntity petEntity){
        return Pet.builder()
                .idPet(petEntity.getIdPet())
                .idCliente(petEntity.getIdCliente())
                .idade(petEntity.getIdade())
                .nome(petEntity.getNome())
                .cor(petEntity.getCor())
                .peso(petEntity.getPeso())
                .raca(petEntity.getRaca())
                .build();
    }

    public static PetEntity modelToEntity(Pet pet){
        return PetEntity.builder()
                .idPet(pet.getIdPet())
                .idCliente(pet.getIdCliente())
                .idade(pet.getIdade())
                .nome(pet.getNome())
                .cor(pet.getCor())
                .peso(pet.getPeso())
                .raca(pet.getRaca())
                .build();
    }
}
