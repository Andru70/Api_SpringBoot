package com.api.mysql.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.mysql.entities.PlaylistEntity;
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
public class PlaylistControllerTest {

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


    @DisplayName("Obtenemos todas las playlists y esperamos una respuesta positiva")
    @Test
    public void testgetPlaylists() throws Exception {

        MvcResult peticion = mockMvc.perform(get("/playlists"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }

    @DisplayName("Obtenemos una playlist y esperamos una respuesta positiva")
    @Test
    public void testgetPlaylist() throws Exception {

        MvcResult peticion = mockMvc.perform(get("/playlist/7"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();
        PlaylistEntity playlist = objectMapper.readValue(data, PlaylistEntity.class);

        System.out.println(data);
        System.out.println(playlist.getNombre());

    }

    @DisplayName("Guardamos una playlist y esperamos una respuesta positiva")
    @Test
    public void testsavePlaylist() throws Exception {

        String json = "{\"nombre\":\"Actualidad aqui\",\"account\":{\"cuentaID\":30}}";

        MvcResult peticion = mockMvc.perform(post("/newplaylist")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json))
                                    .andExpect(status().isOk())
                                    .andExpect(content().json(json))
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }

    @DisplayName("Eliminamos una playlist y esperamos una respuesta positiva")
    @Test
    public void testdeletePlaylist() throws Exception {

        MvcResult peticion = mockMvc.perform(delete("/playlist/9"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }

    @DisplayName("Actualizamos una playlist y esperamos una respuesta positiva")
    @Test
    public void testupdatePlaylist() throws Exception {

        String json = "{\"playlistID\":11,\"nombre\":\"Canciones que perduran\",\"account\":{\"cuentaID\":56}}";

        MvcResult peticion = mockMvc.perform(put("/playlist/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isOk())
                        .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }



}
