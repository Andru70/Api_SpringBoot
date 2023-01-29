package com.api.mysql.repositories;

import com.api.mysql.entities.AccountEntity;
import com.api.mysql.entities.PlaylistEntity;
import com.api.mysql.entities.UserEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlaylistRepository {

    @Autowired
    private IPlaylistRepository playlistRepository;

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

    @DisplayName("Guardamos una playlist y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testsavePlaylist(){

        UserEntity user = new UserEntity(15, "Yamal Torrente");
        AccountEntity account = new AccountEntity(50, "Fabian33", "Fabian33",
                "Premium", user);
        PlaylistEntity playlist = new PlaylistEntity(account, "Relajate y disfruta");

        PlaylistEntity playlistsaved = playlistRepository.save(playlist);

        assertNotNull(playlistsaved);

    }

    @DisplayName("Actualizamos una playlist y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testupdatePlaylist(){

        UserEntity user = new UserEntity(15, "Yamal Torrente");
        AccountEntity account = new AccountEntity(50, "Fabian33", "Fabian33",
                "Premium", user);
        PlaylistEntity playlist = new PlaylistEntity(7,account, "Rumba a tope");

        playlistRepository.save(playlist);

        Optional<PlaylistEntity> playlistget = playlistRepository.findById(7);

        if(playlistget.isPresent()){

            PlaylistEntity playlistupdate = playlistget.get();

            playlistupdate.setNombre("De chill");

            playlistRepository.save(playlistupdate);

        }else{
            System.out.println("Playlist no encontrado");
        }

        Optional<PlaylistEntity> playlistgetu = playlistRepository.findById(7);

        assertEquals("De chill", playlistgetu.get().getNombre());

    }

    @DisplayName("Obtenemos todas las playlists y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testgetPlaylits(){

        UserEntity user = new UserEntity(14, "Juan Magallanes");
        AccountEntity account = new AccountEntity(22, "Hilton33", "Hilton33",
                "Free", user);
        PlaylistEntity playlist = new PlaylistEntity(account, "Rumba a tope");

        UserEntity user2 = new UserEntity(1, "Juan Fer");
        AccountEntity account2 = new AccountEntity(49, "Gomez471", "Gomez471",
                "Free", user2);
        PlaylistEntity playlist2 = new PlaylistEntity(account, "Rumba a tope");

        playlistRepository.save(playlist);
        playlistRepository.save(playlist2);

        List<PlaylistEntity> playlists = playlistRepository.findAll();

        assertEquals(7, playlists.size());

    }


}
