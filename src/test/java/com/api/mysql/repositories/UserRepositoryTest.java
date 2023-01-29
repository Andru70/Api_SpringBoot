package com.api.mysql.repositories;

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
public class UserRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

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
//        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void finishAllEach(){
        System.out.println("----- Este test ha terminado de ejecutarse -----");
    }

    @DisplayName("Guardamos un usuario y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testsaveUser(){

        UserEntity user = new UserEntity("Martin Lopez");

        UserEntity usersaved = userRepository.save(user);

        assertNotNull(usersaved);

    }

    @DisplayName("Actualizamos un usuario y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testupdateUser(){

        UserEntity user = new UserEntity(19,"Karla Perez");

        UserEntity usersaved = userRepository.save(user);

        Optional<UserEntity> userget = userRepository.findById( (long) 19 );

        if(userget.isPresent()){

            UserEntity userupdate = userget.get();

            userupdate.setFullName("Maria Franco");

            userRepository.save(userupdate);

        }else{
            System.out.println("Usuario no encontrado");
        }

        Optional<UserEntity> usergetu = userRepository.findById( (long) 19 );

        assertEquals("Maria Franco", usergetu.get().getFullName());

    }

    @DisplayName("Obtenemos todos los usuarios y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testgetUsers(){

        UserEntity user = new UserEntity("Roberto Gil");
        UserEntity user2 = new UserEntity("Pablo Lopez");

        userRepository.save(user);
        userRepository.save(user2);

        List<UserEntity> users = userRepository.findAll();

        assertEquals(13, users.size()); //En este caso 13 ya que tengo 11 registros en la tabla antes de ejecutar el test

    }

    @DisplayName("Obtenemos un usuario por su id y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testsgetUser(){

        UserEntity user = new UserEntity(22,"Carlos Navarro");

        userRepository.save(user);

        Optional<UserEntity> userget = userRepository.findById( (long) 22);

        assertNotNull(userget);

    }

    @DisplayName("Eliminamos un usuario y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testdeleteUser(){

        UserEntity user = new UserEntity(25,"Daniela Ortega");

        userRepository.save(user);

        Optional<UserEntity> userget = userRepository.findById( (long) 25);

        System.out.println(userget.get().getFullName());

        userRepository.delete(user);

        Optional<UserEntity> usergetu = userRepository.findById( (long) 25);

        System.out.println(usergetu.isPresent());

        assertFalse(usergetu.isPresent());

    }

}
