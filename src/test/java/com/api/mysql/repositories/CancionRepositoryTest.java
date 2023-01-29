package com.api.mysql.repositories;

import com.api.mysql.entities.AccountEntity;
import com.api.mysql.entities.CancionEntity;
import com.api.mysql.entities.PlaylistEntity;
import com.api.mysql.entities.UserEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CancionRepositoryTest {

    @Autowired
    private ICancionRepository cancionRepository;

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

    @DisplayName("Guardamos una cancin y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testsaveCancion(){

        String dateString = "2016-12-02";

        Date fecha = Date.valueOf(dateString);

        UserEntity user = new UserEntity(10, "Faber Olmedo");
        AccountEntity account = new AccountEntity(18, "Ferbin101", "Ferbin101",
                "Free", user);
        PlaylistEntity playlist = new PlaylistEntity(3, account, "Top Mundial");

        CancionEntity cancion = new CancionEntity(playlist,"Gangnam Style", "Psy", "Pop",
                "Psy", "Pop music", fecha);

        CancionEntity cancionsaved = cancionRepository.save(cancion);

        System.out.println(cancionsaved.getAlbum());
        System.out.println(cancionsaved.getDate());

        assertNotNull(cancionsaved);

    }

    @DisplayName("Actualizamos una cancion y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testupdateCancion(){

        String dateString = "2015-12-18";

        Date fecha = Date.valueOf(dateString);

        UserEntity user = new UserEntity(10, "Faber Olmedo");
        AccountEntity account = new AccountEntity(18, "Ferbin101", "Ferbin101",
                "Free", user);
        PlaylistEntity playlist = new PlaylistEntity(3, account, "Top Mundial");

        CancionEntity cancion = new CancionEntity(8, playlist,"Faded", "Alan Walker", "Pop",
                "Alan Walker", "Pop music", fecha);

        String dateString2 = "2016-11-18";

        Date fecha2 = Date.valueOf(dateString2);


        cancionRepository.save(cancion);

        Optional<CancionEntity> cancionget = cancionRepository.findById(8);

        if(cancionget.isPresent()){

            CancionEntity cancionupdate = cancionget.get();

            cancionupdate.setGenero("Electrónica");
            cancionupdate.setDate(fecha2);

            cancionRepository.save(cancionupdate);

        }else{
            System.out.println("Cancion no encontrada");
        }

        Optional<CancionEntity> canciongetu = cancionRepository.findById(8);

        assertEquals("Electrónica", canciongetu.get().getGenero());

    }

    @DisplayName("Obtenemos todas las canciones y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testgetCancions(){

        String dateString = "2015-12-18";

        Date fecha = Date.valueOf(dateString);

        UserEntity user = new UserEntity(10, "Faber Olmedo");
        AccountEntity account = new AccountEntity(18, "Ferbin101", "Ferbin101",
                "Free", user);
        PlaylistEntity playlist = new PlaylistEntity(3, account, "Top Mundial");

        CancionEntity cancion = new CancionEntity(playlist,"Faded", "Alan Walker", "Pop",
                "Alan Walker", "Pop music", fecha);

        cancionRepository.save(cancion);

        List<CancionEntity> cancions = cancionRepository.findAll();

        assertEquals(6, cancions.size()); //En este caso 6 ya que tengo 5 registros en la tabla antes de ejecutar el test

    }

    @DisplayName("Obtenemos una cancion por su id y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testsgetCancion(){

        String dateString = "2011-06-11";

        Date fecha = Date.valueOf(dateString);

        UserEntity user = new UserEntity(10, "Faber Olmedo");
        AccountEntity account = new AccountEntity(18, "Ferbin101", "Ferbin101",
                "Free", user);
        PlaylistEntity playlist = new PlaylistEntity(3, account, "Top Mundial");

        CancionEntity cancion = new CancionEntity(10, playlist,"El Malo", "Aventura",
                "Bachata","Aventura", "Bachata por siempre", fecha);

        cancionRepository.save(cancion);

        Optional<CancionEntity> cancionget = cancionRepository.findById(10);

        assertNotNull(cancionget);

    }

    @DisplayName("Eliminamos una cancion y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testdeletePodcast(){

        String dateString = "2011-06-11";

        Date fecha = Date.valueOf(dateString);

        UserEntity user = new UserEntity(10, "Faber Olmedo");
        AccountEntity account = new AccountEntity(18, "Ferbin101", "Ferbin101",
                "Free", user);
        PlaylistEntity playlist = new PlaylistEntity(3, account, "Top Mundial");

        CancionEntity cancion = new CancionEntity( 11,playlist,"El Malo", "Aventura",
                "Bachata","Aventura", "Bachata por siempre", fecha);

        cancionRepository.save(cancion);

        Optional<CancionEntity> cancionget = cancionRepository.findById(11);

        System.out.println(cancionget.get().getCancionId() + " " + cancionget.get().getArtista());

        cancionRepository.deleteById(11);

        Optional<CancionEntity> canciongetu = cancionRepository.findById(11);

        assertFalse(canciongetu.isPresent());

    }



}
