package br.com.petz.pet.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet extends PetEmpty{

    @JsonProperty(value = "idPet")
    private UUID idPet;

    @JsonProperty(value = "idCliente")
    private UUID idCliente;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "raca")
    private String raca;

    @JsonProperty(value = "cor")
    private String cor;

    @JsonProperty(value = "idade")
    private int idade;

    @JsonProperty(value = "peso")
    private int peso;

}
