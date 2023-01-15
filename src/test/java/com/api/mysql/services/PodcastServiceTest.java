package com.api.mysql.services;


import com.api.mysql.entities.PodcastEntity;
import com.api.mysql.repositories.IPodcastRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PodcastServiceTest {

    @Mock
    private IPodcastRepository podcastRepository;

    @InjectMocks
    private PodcastService podcastService;

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
//        System.out.println("----- Este test será ejecutado -----");
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void finishAllEach(){
        System.out.println("----- Este test ha terminado de ejecutarse -----");
    }


    @DisplayName("Guardamos un podcast y esperamos una respuesta positiva")
    @Test
    public void testsavePodcast(){

        PodcastEntity podcast = new PodcastEntity("Charlando en el atardecer","Juan Carlos Ferril",6);

        Mockito.when(podcastRepository.save(ArgumentMatchers.any())).thenReturn(podcast);

        PodcastEntity podcastsaved = podcastService.createPodcast(podcast);

        assertEquals(podcast, podcastsaved);
    }

    @DisplayName("Actualizamos un podcast y esperamos una respuesta positiva")
    @Test
    public void testupdatePodcast(){

        PodcastEntity podcast = new PodcastEntity(7,"Charlando en el atardecer","Juan Carlos Ferril"
                ,6);

        Mockito.when(podcastRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(podcast));

        PodcastEntity podcastedit = new PodcastEntity();
        podcastedit.setPodcastID(podcast.getPodcastID());
        podcastedit.setNombre("La necesidad de hacer aquello");
        podcastedit.setAutor("Francisco Almanza");
        podcastedit.setEpisodios(15);

        Mockito.when(podcastRepository.save(ArgumentMatchers.any())).thenReturn(podcastedit);

        PodcastEntity podcastupdate = podcastService.updatePodcast(podcastedit);

        assertEquals(podcast.getPodcastID(), podcastedit.getPodcastID());

    }

    @DisplayName("Obtenemos todos los podcasts y esperamos una respuesta positiva")
    @Test
    public void testgetPodcasts(){

        PodcastEntity podcast = new PodcastEntity("Charlando en el atardecer","Juan Carlos Ferril",6);
        PodcastEntity podcast2 = new PodcastEntity("Fábrica de experiencias","Arnold Vega",18);

        Mockito.when(podcastRepository.findAll()).thenReturn(Arrays.asList(podcast,podcast2));

        List<PodcastEntity> podcasts = podcastService.getAllPodcasts();

        for(int i = 0 ; i < podcasts.size() ; i++){

            System.out.println(podcasts.get(i).getAutor());

        }

        assertNotNull(podcasts);
    }

    @DisplayName("Obtenemos un podcast por su id y esperamos una respuesta positiva")
    @Test
    public void testsgetPodcast(){

        PodcastEntity podcast = new PodcastEntity(7,"Charlando en el atardecer","Juan Carlos Ferril"
                ,6);

        Mockito.when(podcastRepository.findById(7)).thenReturn(Optional.of(podcast));

        PodcastEntity podcastget = podcastService.getPodcastById(7);

        assertNotNull(podcastget);

    }

    @DisplayName("Eliminamos un podcast y esperamos una respuesta positiva")
    @Test
    public void testdeletePodcast(){

        PodcastEntity podcast = new PodcastEntity(7,"Charlando en el atardecer","Juan Carlos Ferril"
                ,6);
        PodcastEntity podcast2 = new PodcastEntity(9,"Fábrica de experiencias","Arnold Vega",
                18);

        Mockito.when(podcastRepository.findById(( 7 ) )).thenReturn(Optional.of(podcast)).thenReturn(null);
        Mockito.when(podcastRepository.findById(( 9 ) )).thenReturn(Optional.of(podcast2));

        podcastService.deletePodcast(podcast.getPodcastID());

        Optional<PodcastEntity> podcastbuscado = podcastRepository.findById(podcast.getPodcastID());

        if(podcastbuscado == null){
            assertNull(podcastbuscado);
        }else{
            System.out.println(podcastbuscado.get().getNombre());
            assertNull(podcastbuscado);

        }

    }


}
