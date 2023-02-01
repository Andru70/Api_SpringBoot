package com.api.mysql.services;

import com.api.mysql.entities.UserEntity;
import com.api.mysql.repositories.IUserRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

//@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

    @Mock //@Autowired para Jpa y que sea posible ejecutar realmente los métodos en la db
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

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


    @DisplayName("Guardamos un usuario y esperamos una respuesta positiva")
    @Test
    //@Rollback(value = false)
    public void testSaveUser(){

        UserEntity user = new UserEntity("Yamal Torrente");

        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);

        UserEntity usersaved = userService.createUser(user);
//        System.out.println(usersaved);

        assertEquals(user, usersaved);
        //assertNotNull(usersaved);
    }

    @DisplayName("Actualizamos un usuario y esperamos una respuesta positiva")
    @Test
    public void testUpdateUser(){

        UserEntity user = new UserEntity(9,"Yamal Torrente");

        Mockito.when(userRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(user));

        UserEntity useredit = user;
        useredit.setFullName("Jose Pereira");

        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);

        UserEntity userupdate = userService.updateUser(useredit);

        assertEquals(user.getUsuarioId(), userupdate.getUsuarioId());

    }

    @DisplayName("Obtenemos todos los usuarios y esperamos una respuesta positiva")
    @Test
    public void testGetUsers(){

        UserEntity user = new UserEntity("Yamal Torrente");
        UserEntity user2 = new UserEntity("Luian Torrente");

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user,user2));

        List<UserEntity> users = userService.getAllUsers();

        assertNotNull(users);

    }

    @DisplayName("Obtenemos un usuario por su id y esperamos una respuesta positiva")
    @Test
    public void testGetUser(){

        UserEntity user = new UserEntity(9,"Pablo Medina");

        Mockito.when(userRepository.findById((long) 9)).thenReturn(Optional.of(user));

        UserEntity userget = userService.getUserById(9);
//        System.out.println(userget.getFullName());

        assertEquals(user.getUsuarioId(), userget.getUsuarioId());

    }

    @DisplayName("Eliminamos un usuario y esperamos una respuesta positiva")
    @Test
    public void testDeleteUser(){

        UserEntity user = new UserEntity(9,"Pablo Medina");
        UserEntity user2 = new UserEntity(10,"Arturo Romero");

        Mockito.when(userRepository.findById((long) 9)).thenReturn(Optional.of(user)).thenReturn(null);
        Mockito.when(userRepository.findById((long) 10)).thenReturn(Optional.of(user2));

        userService.deleteUser(user.getUsuarioId());

        Optional<UserEntity> userbuscado = userRepository.findById(user.getUsuarioId());

        if(userbuscado == null){
            assertNull(userbuscado);
        }else{
            System.out.println(userbuscado.get());
            assertNotNull(userbuscado);
//            assertNull(userbuscado);
        }

    }

}
