package com.api.mysql.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.mysql.entities.PodcastEntity;
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
public class PodcastControllerTest {

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


    @DisplayName("Obtenemos todos los podcasts y esperamos una respuesta positiva")
    @Test
    public void testgetPodcasts() throws Exception {

        MvcResult peticion = mockMvc.perform(get("/podcasts"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }

    @DisplayName("Obtenemos un podcast por su id y esperamos una respuesta positiva")
    @Test
    public void testsgetPodcast() throws Exception {

        MvcResult peticion = mockMvc.perform(get("/podcast/6"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();
        PodcastEntity podcast = objectMapper.readValue(data, PodcastEntity.class);

        System.out.println(data);
        System.out.println(podcast.getNombre());

    }

    @DisplayName("Guardamos un podcast y esperamos una respuesta positiva")
    @Test
    public void testsavePodcast() throws Exception {

        String json = "{\"account\":{\"cuentaID\":25},\"nombre\":\"Cautivate aqui\",\"autor\":\"Felipe Tabasco\",\"episodios\":12}";

        MvcResult peticion = mockMvc.perform(post("/newpodcast")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isOk())
                        .andExpect(content().json(json))
                        .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }

    @DisplayName("Eliminamos un podcast y esperamos una respuesta positiva")
    @Test
    public void testdeletePodcast() throws Exception {

        MvcResult peticion = mockMvc.perform(delete("/podcast/3"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }

    @DisplayName("Actualizamos un podcast y esperamos una respuesta positiva")
    @Test
    public void testupdatePodcast() throws Exception {

        String json = "{\"nombre\":\"Negocios en forma\",\"autor\":\"Gabriel Mejia\",\"episodios\":20,\"podcastID\":8}";

        MvcResult peticion = mockMvc.perform(put("/podcast/update")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json))
                                    .andExpect(status().isOk())
                                    .andExpect(content().json(json))
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }



}
