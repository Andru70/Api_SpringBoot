package com.api.mysql.repositories;

import com.api.mysql.dto.AccountDTO;
import com.api.mysql.entities.AccountEntity;
import com.api.mysql.entities.UserEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class AccountRepositoryTest {

    @Autowired
    private IAccountRepository accountRepository;

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

    @DisplayName("Guardamos una cuenta y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testsaveAccount(){

        UserEntity user = new UserEntity(14, "Juan Magallanes");
        AccountEntity account = new AccountEntity(21, "Hilton33", "Hilton33",
                "Free", user);

        AccountEntity accountsaved = accountRepository.save(account);

        assertNotNull(accountsaved);
        //assertEquals(account, accountsaved);

    }

    @DisplayName("Actualizamos una cuenta y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testupdateAccount(){

        UserEntity user = new UserEntity(3, "Faber Olmedo");
        AccountEntity account = new AccountEntity(30, "Oscar44", "Oscar44",
                "Free", user);

        accountRepository.save(account);

        Optional<AccountEntity> accountget = accountRepository.findById((long) 30);
        System.out.println(accountget);

        if(accountget.isPresent()){

            AccountEntity accountupdate = accountget.get();

            accountupdate.setUserName("Julian33");
            accountupdate.setPassword("Julian33");
            accountupdate.setType_Account("Premium");

            accountRepository.save(accountupdate);

        }else{
            System.out.println("Cuenta no encontrada");
        }

        Optional<AccountEntity> accountgetu = accountRepository.findById( (long) 30);

        assertEquals("Julian33", accountgetu.get().getUserName());

    }

    @DisplayName("Obtenemos todas las cuentas y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testgetAccounts(){

        UserEntity user1 = new UserEntity(1, "Juan Fer");
        AccountEntity account1 = new AccountEntity(49, "Gomez471", "Gomez471",
                "Free", user1);

        UserEntity user2 = new UserEntity(15, "Yamal Torrente");
        AccountEntity account2 = new AccountEntity(50, "Fabian33", "Fabian33",
                "Premium", user2);

        accountRepository.save(account1);
        accountRepository.save(account2);

        List<AccountEntity> accounts = accountRepository.findAll();

        System.out.println(accounts.size());
        assertEquals(8, accounts.size()); //En este caso 8 ya que tengo 6 registros en la tabla antes de ejecutar el test
        //assertNotNull(accounts);
    }

    @DisplayName("Obtenemos una account por su id mostrandola con un DTO y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testsgetDetailsAccount(){

        Optional<AccountEntity> account = accountRepository.findById( (long) 18);

        AccountDTO details = new AccountDTO();

        details.setCuentaID( account.get().getCuentaID() );
        details.setFullNameuser( account.get().getIdUsuario().getFullName() );
        details.setUserName(account.get().getUserName() );
        details.setPassword( account.get().getPassword() );
        details.setType_Account( account.get().getType_Account() );

        assertEquals(account.get().getIdUsuario().getFullName(), details.getFullNameuser());

    }

    @DisplayName("Eliminamos una account y esperamos una respuesta positiva")
    @Test
    @Rollback(false)
    public void testdeleteAccount(){

        UserEntity user = new UserEntity(16, "Kike Vega");
        AccountEntity account = new AccountEntity(52, "Gomez471", "Gomez471",
                "Free", user);

        accountRepository.save(account);

        Optional<AccountEntity> accountget = accountRepository.findById( (long) 52);

        System.out.println(accountget.get());

        accountRepository.delete(account);

        assertFalse(accountRepository.findById( (long) 52).isPresent());

    }

}
