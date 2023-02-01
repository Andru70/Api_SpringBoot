package com.api.mysql.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Date;
import java.text.SimpleDateFormat;

@SpringBootTest
@AutoConfigureMockMvc
public class CancionControllerTest {

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


    @DisplayName("Obtenemos todas las canciones y esperamos una respuesta positiva")
    @Test
    public void testgetCancions() throws Exception {

        MvcResult peticion = mockMvc.perform(get("/cancions"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }
    @DisplayName("Obtenemos una cancion y esperamos una respuesta positiva")
    @Test
    public void testgetCancion() throws Exception {

        MvcResult peticion = mockMvc.perform(get("/cancion/5"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }

    @DisplayName("Guardamos una cancion y esperamos una respuesta positiva")
    @Test
    public void testsaveCancion() throws Exception {

        String dateString = "1976-06-14";

        Date fecha = Date.valueOf(dateString);

                                                                                                                                                                                   //Sin el T00.... se hace el registro pero el test da error ya que espera la fecha sin el time y la response retorna la fecha junto con su time
        String json = "{\"nombre\":\"Períodico de ayer\",\"genero\":\"salsa\",\"compositor\":\"Héctor Lavoe\",\"artista\":\"Héctor Lavoe\",\"album\":\"De ti depende\",\"date\":\"1976-06-14T00:00:00.000+00:00\",\"playlist\":{\"playlistID\":11}}";

        MvcResult peticion = mockMvc.perform(post("/newcancion")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json))
                                    .andExpect(status().isOk())
                                    .andExpect(content().json(json))
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }

    @DisplayName("Eliminamos una cancion y esperamos una respuesta positiva")
    @Test
    public void testdeleteCancion() throws Exception {

        MvcResult peticion = mockMvc.perform(delete("/cancion/19"))
                                    .andExpect(status().isOk())
                                    .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }

    @DisplayName("Actualizamos una cancion y esperamos una respuesta positiva")
    @Test
    public void testupdateCancion() throws Exception {

//        String dateString = "1976-06-14";

//        Date fecha = Date.valueOf(dateString);


                                                                                                                                                                                        //Sin el T00.... se hace el registro pero el test da error ya que espera la fecha sin el time y la response retorna la fecha junto con su time
        String json = "{\"cancionId\":9,\"nombre\":\"Working For It\",\"genero\":\"Pop\",\"compositor\":\"Skrillex\",\"artista\":\"Skrillex\",\"album\":\"Generationwhy\",\"date\":\"2016-11-20T00:11:00.000+00:00\"}";

        MvcResult peticion = mockMvc.perform(put("/cancion/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isOk())
                        .andReturn();

        String data = peticion.getResponse().getContentAsString();

        System.out.println(data);

    }



}
