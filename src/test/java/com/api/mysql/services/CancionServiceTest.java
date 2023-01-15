package com.api.mysql.services;


import com.api.mysql.entities.CancionEntity;
import com.api.mysql.repositories.ICancionRepository;
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

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CancionServiceTest {

    @Mock
    private ICancionRepository cancionRepository;

    @InjectMocks
    private CancionService cancionService;

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

    @DisplayName("Guardamos una cancion y esperamos una respuesta positiva")
    @Test
    public void testsaveCancion(){

        String dateString = "2022-12-02";

        Date fecha = Date.valueOf(dateString);

        CancionEntity cancion = new CancionEntity("Me enseñaste", "Duki", "Reguetón",
                "Duki", "Adiós", fecha);

        Mockito.when(cancionRepository.save(cancion)).thenReturn(cancion);

        CancionEntity cancionsaved = cancionService.createCancion(cancion);

        System.out.println(cancionsaved.getGenero());

        assertEquals(cancion, cancionsaved);
    }

    @DisplayName("Actualizamos una cancion y esperamos una respuesta positiva")
    @Test
    public void testupdateCancion(){

        String dateString = "2022-12-02";

        Date fecha = Date.valueOf(dateString);

        String dateString2 = "2022-12-22";

        Date fecha2 = Date.valueOf(dateString2);

        CancionEntity cancion = new CancionEntity("Me enseñaste", "Duki", "Reguetón",
                "Duki", "Adiós", fecha);

        Mockito.when(cancionRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(cancion));

        CancionEntity cancionedit = cancion;
        cancionedit.setAlbum("Sin ti");
        cancionedit.setDate(fecha2);

        Mockito.when(cancionRepository.save(ArgumentMatchers.any())).thenReturn(cancionedit);

        CancionEntity cancionupdate = cancionService.updateCancion(cancionedit);

        assertEquals(cancion.getNombre(), cancionupdate.getNombre());

    }

    @DisplayName("Obtenemos todas las canciones y esperamos una respuesta positiva")
    @Test
    public void testgetCancions(){

        String dateString = "2021-12-02";

        Date fecha = Date.valueOf(dateString);

        String dateString2 = "2022-5-21";

        Date fecha2 = Date.valueOf(dateString2);

        CancionEntity cancion = new CancionEntity(1,"Me enseñaste", "Duki", "Reguetón",
                "Duki", "Adiós", fecha);

        CancionEntity cancion2 = new CancionEntity(5,"Nuevamente", "Slim", "Trap",
                "Slim", "Again", fecha2);

        Mockito.when(cancionRepository.findAll()).thenReturn(Arrays.asList(cancion, cancion2));

        List<CancionEntity> cancions = cancionService.getAllCancions();

        for(int i = 0 ; i < cancions.size() ; i++){

            System.out.println( "Cancion with id " + cancions.get(i).getCancionId() + " name " +
                    cancions.get(i).getNombre() + " and artist " + cancions.get(i).getArtista());

        }

        assertNotNull(cancions);
    }

    @DisplayName("Obtenemos una cancion y esperamos una respuesta positiva")
    @Test
    public void testgetCancion(){

        String dateString = "2022-12-02";

        Date fecha = Date.valueOf(dateString);

        CancionEntity cancion = new CancionEntity(3,"Me enseñaste", "Duki", "Reguetón",
                "Duki", "Adiós", fecha);

        Mockito.when(cancionRepository.findById((3))).thenReturn(Optional.of(cancion));

        CancionEntity cancionget = cancionService.getCancionById(3);

        assertNotNull(cancionget);

    }

    @DisplayName("Eliminamos una cancion y esperamos una respuesta positiva")
    @Test
    public void testdeleteCancion(){

        String dateString = "2021-12-02";

        Date fecha = Date.valueOf(dateString);

        String dateString2 = "2022-5-21";

        Date fecha2 = Date.valueOf(dateString2);

        CancionEntity cancion = new CancionEntity(1,"Me enseñaste", "Duki", "Reguetón",
                "Duki", "Adiós", fecha);

        CancionEntity cancion2 = new CancionEntity(5,"Nuevamente", "Slim", "Trap",
                "Slim", "Again", fecha2);

        Mockito.when(cancionRepository.findById(( 1 ) )).thenReturn(Optional.of(cancion)).thenReturn(null);
        Mockito.when(cancionRepository.findById(( 5 ) )).thenReturn(Optional.of(cancion2));

        cancionService.deleteCancion(cancion.getCancionId());

        Optional<CancionEntity> cancionbuscada = cancionRepository.findById(cancion.getCancionId());

        if(cancionbuscada == null){
            assertNull(cancionbuscada);
        }else{
            System.out.println(cancionbuscada.get().getNombre());
            assertNull(cancionbuscada);

        }

    }
    

}
