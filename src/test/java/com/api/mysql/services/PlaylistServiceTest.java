package com.api.mysql.services;


import com.api.mysql.entities.PlaylistEntity;
import com.api.mysql.repositories.IPlaylistRepository;
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
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlaylistServiceTest {

    @Mock
    private IPlaylistRepository playlistRepository;

    @InjectMocks
    private PlaylistService playlistService;

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

    @DisplayName("Guardamos una playlist y esperamos una respuesta positiva")
    @Test
    public void testsavePlaylist(){

        PlaylistEntity playlist = new PlaylistEntity("Las 100 más sonadasss");

        Mockito.when(playlistRepository.save(playlist)).thenReturn(playlist);

        PlaylistEntity playlist2 = new PlaylistEntity("Rumba play");

        PlaylistEntity playlistsaved = playlistService.createPlaylist(playlist);

        System.out.println(playlistsaved.getNombre());

        assertEquals(playlist, playlistsaved);
    }

    @DisplayName("Actualizamos una playlist y esperamos una respuesta positiva")
    @Test
    public void testupdatePlaylist(){

        PlaylistEntity playlist = new PlaylistEntity(3,"Las 100 más sonadasss");

        Mockito.when(playlistRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(playlist));

        PlaylistEntity playlistedit = playlist;
        playlistedit.setNombre("Top 100 de la semana");

        Mockito.when(playlistRepository.save(ArgumentMatchers.any())).thenReturn(playlistedit);

        PlaylistEntity playlistupdate = playlistService.updatePlaylist(playlistedit);
//        playlistService.createPlaylist(playlistedit);
        // System.out.println(playlist.getNombre() + " " + playlistupdate.getNombre());
        assertEquals(playlist.getNombre(), playlistupdate.getNombre());

    }

    @DisplayName("Obtenemos todas las playlists y esperamos una respuesta positiva")
    @Test
    public void testgetPlaylists(){

        PlaylistEntity playlist = new PlaylistEntity(3,"Las 100 más sonadasss");
        PlaylistEntity playlist2 = new PlaylistEntity(9,"Cantadas con el corazón");

        Mockito.when(playlistRepository.findAll()).thenReturn(Arrays.asList(playlist, playlist2));

        List<PlaylistEntity> playlists = playlistService.getAllPlaylists();

        for(int i = 0 ; i < playlists.size() ; i++){

            System.out.println( "Playlist with id " + playlists.get(i).getPlaylistID() + " and name " +
                    playlists.get(i).getNombre());

        }

        assertNotNull(playlists);
    }

    @DisplayName("Obtenemos una playlist y esperamos una respuesta positiva")
    @Test
    public void testgetPlaylist(){

        PlaylistEntity playlist = new PlaylistEntity(3,"Las 20 más sonadas de la semana");

        Mockito.when(playlistRepository.findById(3)).thenReturn(Optional.of(playlist));

        PlaylistEntity playlistget = playlistService.getPlaylistById(3);
//        System.out.println(playlistget.getNombre());
        assertNotNull(playlistget);

    }

    @DisplayName("Eliminamos una playlist y esperamos una respuesta positiva")
    @Test
    public void testdeletePlaylist(){

        PlaylistEntity playlist = new PlaylistEntity(3,"Las 100 más sonadasss");
        PlaylistEntity playlist2 = new PlaylistEntity(9,"Cantadas con el corazón");

        Mockito.when(playlistRepository.findById(( 3 ) )).thenReturn(Optional.of(playlist)).thenReturn(null);
        Mockito.when(playlistRepository.findById(( 9 ) )).thenReturn(Optional.of(playlist2));

        playlistService.deletePlaylist(playlist.getPlaylistID());

        Optional<PlaylistEntity> playlistbuscada = playlistRepository.findById(playlist.getPlaylistID());

        if(playlistbuscada == null){
            assertNull(playlistbuscada);
        }else{
            System.out.println(playlistbuscada.get().getNombre());
            assertNull(playlistbuscada);

        }

    }


}
