package com.api.mysql.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.api.mysql.entities.UserEntity;
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
public class UserControllerTest {

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


    @DisplayName("Obtenemos todos los usuarios y esperamos una respuesta positiva")
    @Test
    public void testGetUsers() throws Exception {

        MvcResult peticion = mockMvc.perform(get("/users"))
                        .andExpect(status().isOk())
                        .andReturn();

        System.out.println(peticion.getResponse().getStatus());
        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);
    }

    @DisplayName("Obtenemos un usuario y esperamos una respuesta positiva")
    @Test
    public void testGetUser() throws Exception {

        MvcResult peticion = mockMvc.perform(get("/user/24"))
                .andExpect(status().isOk())
                .andReturn();


        String data = peticion.getResponse().getContentAsString();
        UserEntity user = objectMapper.readValue(data, UserEntity.class);

        System.out.println(data);
        System.out.println(user.getFullName());

    }

    @DisplayName("Guardamos un usuario y esperamos una respuesta positiva")
    @Test
    public void testSaveUser() throws Exception {
        String json = "{\"fullName\":\"Victor Pereira\"}";

        mockMvc.perform(post("/newuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isOk())
                        .andExpect(content().json(json));
    }

    @DisplayName("Eliminamos un usuario y esperamos una respuesta positiva")
    @Test
    public void testDeleteUser() throws Exception{

        mockMvc.perform(delete("/user/30"))
                .andExpect(status().isOk());

    }

    @DisplayName("Actualizamos un usuario y esperamos una respuesta positiva")
    @Test
    public void testUpdateUser() throws Exception {
        String json = "{\"fullName\":\"Juan Medina\",\"usuarioId\":\"29\"}";
        mockMvc.perform(put("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }



}