package com.api.mysql.repositories;

import com.api.mysql.entities.AccountEntity;
import com.api.mysql.entities.PodcastEntity;
import com.api.mysql.entities.UserEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PodcastRepositoryTest {

    @Autowired
    private IPodcastRepository podcastRepository;

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

    @DisplayName("Guardamos un podcast y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testsavePodcast(){

        UserEntity user = new UserEntity(15, "Yamal Torrente");
        AccountEntity account = new AccountEntity(50, "Fabian33", "Fabian33",
                "Premium", user);
        PodcastEntity podcast = new PodcastEntity(account, "Charlando en el atardecer",
                "Juan Carlos Ferril", 6);

        PodcastEntity podcastsaved = podcastRepository.save(podcast);

        System.out.println(podcastsaved.getAccount().getUserName());

        assertNotNull(podcastsaved);

    }

    @DisplayName("Actualizamos un podcast y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testupdatePodcast(){

        UserEntity user = new UserEntity(3, "Faber Olmedo");
        AccountEntity account = new AccountEntity(30, "Julian33", "Julian33",
                "Premium", user);
        PodcastEntity podcast = new PodcastEntity(11, account, "El arte poco notorio",
                "Carlos Figueredo", 16);

        UserEntity user2 = new UserEntity(9, "Juan Fermini");
        AccountEntity account2 = new AccountEntity(4, "Juanchi99", "Juanchi99",
                "Free", user2);

        podcastRepository.save(podcast);

        Optional<PodcastEntity> podcastget = podcastRepository.findById(11);

        if(podcastget.isPresent()){

            PodcastEntity podcastupdate = podcastget.get();

            podcastupdate.setAccount(account2);
            podcastupdate.setEpisodios(17);

            podcastRepository.save(podcastupdate);
        }else{
            System.out.println("Podcast no encontrado");
        }

        Optional<PodcastEntity> podcastgetu = podcastRepository.findById(11);

        assertEquals("Juanchi99", podcastgetu.get().getAccount().getUserName());

    }

    @DisplayName("Obtenemos todos los podcasts y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testgetPodcasts(){

        UserEntity user = new UserEntity(3, "Faber Olmedo");
        AccountEntity account = new AccountEntity(30, "Julian33", "Julian33",
                "Premium", user);
        PodcastEntity podcast = new PodcastEntity(account, "Charlando en el atardecer",
                "Juan Carlos Ferril", 6);

        podcastRepository.save(podcast);

        List<PodcastEntity> podcasts = podcastRepository.findAll();

        assertEquals(7, podcasts.size()); //En este caso 7 ya que tengo 6 registros en la tabla antes de ejecutar el test

    }

    @DisplayName("Obtenemos un podcast por su id y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testsgetPodcast(){

        UserEntity user = new UserEntity(3, "Faber Olmedo");
        AccountEntity account = new AccountEntity(30, "Julian33", "Julian33",
                "Premium", user);
        PodcastEntity podcast = new PodcastEntity(13, account, "Experiencias complejas",
                "Arthur Jaimes", 18);

        podcastRepository.save(podcast);

        Optional<PodcastEntity> podcastget = podcastRepository.findById(13);

        assertNotNull(podcastget);

    }

    @DisplayName("Eliminamos un podcast y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testdeletePodcast(){

        UserEntity user = new UserEntity(3, "Faber Olmedo");
        AccountEntity account = new AccountEntity(30, "Julian33", "Julian33",
                "Premium", user);
        PodcastEntity podcast = new PodcastEntity(14, account, "Un dia a la vez",
                "Alfonso Torres Vega", 20);

        podcastRepository.save(podcast);

        Optional<PodcastEntity> podcastget = podcastRepository.findById(14);

        System.out.println(podcastget.get().getNombre());

        podcastRepository.delete(podcast);

        Optional<PodcastEntity> podcastgetu = podcastRepository.findById(14);

        assertFalse(podcastgetu.isPresent());

    }


}
