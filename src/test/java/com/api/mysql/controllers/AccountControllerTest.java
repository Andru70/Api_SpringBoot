package com.api.mysql.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.mysql.entities.AccountEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void initAll(){
        System.out.println("----- Se iniciará la ejecución de los tests -----");
    }

    @AfterAll
    static void finishAll(){
        System.out.println("----- Todos los tests han sido ejecutados -----");
    }

    @BeforeEach
    public void initAllEach(){
        System.out.println("----- Este test será ejecutado -----");
    }

    @AfterEach
    public void finishAllEach(){
        System.out.println("----- Este test ha terminado de ejecutarse -----");
    }


    @DisplayName("Obtenemos todas las cuentas y esperamos una respuesta positiva")
    @Test
    public void testgetAccounts() throws Exception {

        MvcResult peticion = mockMvc.perform(get("/accounts"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }

    @DisplayName("Obtenemos una account por su id y esperamos una respuesta positiva")
    @Test
    public void testsgetAccount() throws Exception {

        MvcResult peticion = mockMvc.perform(get("/account/49"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();
        AccountEntity account = objectMapper.readValue(data, AccountEntity.class);

        System.out.println(data);
        System.out.println(account.getUserName());

    }

    @DisplayName("Obtenemos una account por su id mostrandola con un DTO y esperamos una respuesta positiva")
    @Test
    public void testsgetDetailsAccount() throws Exception {

        MvcResult peticion = mockMvc.perform(get("/accountdetail/49"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }

    @DisplayName("Guardamos una cuenta y esperamos una respuesta positiva")
    @Test
    public void testsaveAccount() throws Exception {

        String json = "{\"idUsuario\":{\"fullName\":\"Juan Medina\",\"usuarioId\":29},\"userName\":\"Tolu22\",\"password\":\"Tolu22\",\"type_Account\":\"Free\"}";

        MvcResult peticion = mockMvc.perform(post("/newaccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isOk())
                        .andExpect(content().json(json))
                        .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }
    @DisplayName("Eliminamos una account y esperamos una respuesta positiva")
    @Test
    public void testdeleteAccount() throws Exception {

        MvcResult peticion = mockMvc.perform(delete("/account/55"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }

    @DisplayName("Actualizamos una cuenta y esperamos una respuesta positiva")
    @Test
    public void testupdateAccount() throws Exception {

        String json = "{\"idUsuario\":{\"fullName\":\"Juan Magallanes\",\"usuarioId\":14},\"userName\":\"Mag33\",\"password\":\"Mag33\",\"type_Account\":\"Premium\",\"cuentaID\":22}";

        MvcResult peticion = mockMvc.perform(put("/account/update")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json))
                                    .andExpect(status().isOk())
                                    .andExpect(content().json(json))
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }



}
