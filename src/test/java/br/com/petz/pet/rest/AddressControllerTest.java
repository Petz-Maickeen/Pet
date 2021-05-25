package br.com.petz.pet.rest;

import br.com.petz.pet.JsonUtil;
import br.com.petz.pet.domain.port.PetPort;
import br.com.petz.pet.rest.model.DataResponse;
import br.com.petz.pet.rest.model.Pet;
import br.com.petz.pet.rest.model.PetEmpty;
import br.com.petz.pet.rest.util.Constantes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(PetController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetPort petPort;

    @Test
    public void shouldFindClientByID() throws Exception {
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

        when(petPort.findPetByIdPet(idPet,idCliente)).thenReturn(clienteE);
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cliente/" + idCliente + "/pet/" + idPet))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(clienteE).build())));
    }

    @Test
    public void shouldNotFindClientByID() throws Exception {
        UUID idCliente = UUID.randomUUID();
        UUID idPet = UUID.randomUUID();

        when(petPort.findPetByIdPet(idPet,idCliente)).thenThrow(new NullPointerException());
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cliente/" + idCliente + "/pet/" + idPet))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.PET_NAO_ENCONTRADO).build())));
    }

    @Test
    public void shouldInsertClient() throws Exception {
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

        when(petPort.insertPet(clienteS)).thenReturn(clienteS);
        mockMvc
                .perform(MockMvcRequestBuilders.post("/cliente/" + idCliente + "/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(clienteE)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(clienteS).build())));
    }

    @Test
    public void shouldNotInsertClient() throws Exception {
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

        when(petPort.insertPet(clienteE)).thenReturn(new PetEmpty());
        mockMvc
                .perform(MockMvcRequestBuilders.post("/cliente/" + idCliente + "/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(clienteE)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.PET_JA_CADASTRADO).build())));
    }

    @Test
    public void shouldUpdateClient() throws Exception {
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

        when(petPort.updatePet(clienteE)).thenReturn(clienteE);
        mockMvc
                .perform(MockMvcRequestBuilders.patch("/cliente/"+idCliente+"/pet/" + idPet)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(clienteE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(clienteE).build())));
    }

    @Test
    public void shouldNotUpdateClient() throws Exception {
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

        when(petPort.updatePet(clienteE)).thenReturn(new PetEmpty());
        mockMvc
                .perform(MockMvcRequestBuilders.patch("/cliente/"+idCliente+"/pet/"+idPet)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(clienteE)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.PET_NAO_ENCONTRADO).build())));
    }

    @Test
    public void shouldDeleteClient() throws Exception {
        UUID idCliente = UUID.randomUUID();
        UUID idPet = UUID.randomUUID();
        when(petPort.deletePetByIdPet(idPet,idCliente)).thenReturn(true);
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/cliente/" + idCliente + "/pet/" + idPet))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.PET_EXCLUIDO_SUCESSO).build())));
    }

    @Test
    public void shouldNotDeleteClient() throws Exception {
        UUID idCliente = UUID.randomUUID();
        UUID idPet = UUID.randomUUID();
        when(petPort.deletePetByIdPet(idPet,idCliente)).thenReturn(false);
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/cliente/" + idCliente + "/pet/" + idPet))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.PET_NAO_ENCONTRADO).build())));
    }
}