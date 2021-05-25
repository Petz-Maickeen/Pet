package br.com.petz.pet.integration.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "pet")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetEntity {
    @Id
    @NotNull
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_pet",columnDefinition = "BINARY(16)")
    private UUID idPet;

    @NotNull
    @Column(name = "id_cliente",columnDefinition = "BINARY(16)")
    private UUID idCliente;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "raca")
    private String raca;

    @NotNull
    @Column(name = "cor")
    private String cor;

    @NotNull
    @Column(name = "idade")
    private int idade;

    @NotNull
    @Column(name = "peso")
    private int peso;
}