package br.com.petz.pet.domain.adapter;

import br.com.petz.pet.integration.entity.PetEntity;
import br.com.petz.pet.rest.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, String> {
    PetEntity findByIdPetAndIdCliente(UUID idPet, UUID idCliente);
    List<PetEntity> findAllByIdCliente(UUID idCliente);
    boolean existsByIdCliente(UUID idCliente);
    boolean existsByNomeAndAndIdCliente(String nome, UUID idCliente);
    boolean existsByIdPetAndIdCliente(UUID idPet, UUID idCliente);
}
