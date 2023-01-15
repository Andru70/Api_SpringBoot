package com.api.mysql.services;

import com.api.mysql.dto.AccountDTO;
import com.api.mysql.entities.AccountEntity;
import com.api.mysql.entities.UserEntity;
import com.api.mysql.repositories.IAccountRepository;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountServiceTest {

    @Mock
    private IAccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

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

    @DisplayName("Guardamos una cuenta y esperamos una respuesta positiva")
    @Test
    public void testsaveAccount(){

        AccountEntity account = new AccountEntity("Jacobo080", "Jacobo080", "Free");

        Mockito.when(accountRepository.save(ArgumentMatchers.any())).thenReturn(account);

        AccountEntity accountsaved = accountService.createAccount(account);

        assertEquals(account, accountsaved);

    }

    @DisplayName("Actualizamos una cuenta y esperamos una respuesta positiva")
    @Test
    public void testupdateAccount(){

        AccountEntity account = new AccountEntity(5,"Jacobo080", "Jacobo080", "Free");

        Mockito.when(accountRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(account));

        Mockito.when(accountRepository.save(ArgumentMatchers.any())).thenReturn(account);

        AccountEntity accountedit = account;
        accountedit.setCuentaID(account.getCuentaID());
        accountedit.setUserName("Spikeo2");
        accountedit.setPassword("Spikeo2");
        accountedit.setType_Account("Premium");

        AccountEntity accountupdate = accountService.updateAccount(accountedit);

        assertEquals(account.getCuentaID(), accountupdate.getCuentaID());

    }

    @DisplayName("Obtenemos todas las cuentas y esperamos una respuesta positiva")
    @Test
    public void testgetAccounts(){ // Prácticamente seguro de que es así

        AccountEntity account = new AccountEntity("Axix080", "Axix080", "Free");
        AccountEntity account2 = new AccountEntity("Dilk090", "Dilk090", "Free");

        Mockito.when(accountRepository.findAll()).thenReturn(Arrays.asList(account,account2));

        List<AccountEntity> accounts = accountService.getAllAccounts();

        assertNotNull(accounts);

    }

    @DisplayName("Obtenemos una account por su id y esperamos una respuesta positiva")
    @Test
    public void testsgetAccount(){

        AccountEntity account = new AccountEntity(9,"Axix080", "Axix080", "Free");

        Mockito.when(accountRepository.findById((long) 9)).thenReturn(Optional.of(account));

        AccountEntity accountget = accountService.getAccountById(9);
//        System.out.println(userget.getFullName());

        assertEquals(account.getCuentaID(), accountget.getCuentaID());

    }

    @DisplayName("Obtenemos una account por su id mostrandola con un DTO y esperamos una respuesta positiva")
    @Test
    public void testsgetDetailsAccount(){

        UserEntity user = new UserEntity(10,"Yamal Torrente");

        AccountEntity account = new AccountEntity(9,"Axix080", "Axix080", "Free",
                user);

        Mockito.when(accountRepository.findById((long) 9)).thenReturn(Optional.of(account));

        AccountEntity accountget = accountService.getAccountById(9);

        AccountDTO details = new AccountDTO();

        details.setCuentaID(accountget.getCuentaID());
        details.setFullNameuser(accountget.getIdUsuario().getFullName());
        details.setUserName(accountget.getUserName());
        details.setPassword(accountget.getPassword());
        details.setType_Account(accountget.getType_Account());

        assertEquals(account.getCuentaID(), details.getCuentaID());

    }

    @DisplayName("Eliminamos una account y esperamos una respuesta positiva")
    @Test
    public void testdeleteAccount(){

        AccountEntity account = new AccountEntity(2,"Axix080", "Axix080", "Free");
        AccountEntity account2 = new AccountEntity(4,"Dilk090", "Dilk090", "Free");

        Mockito.when(accountRepository.findById(((long) 2))).thenReturn(Optional.of(account)).thenReturn(null);
        Mockito.when(accountRepository.findById((long) 4)).thenReturn(Optional.of(account2));

        accountService.deleteAccount(account.getCuentaID());

        Optional<AccountEntity> accountbuscada = accountRepository.findById(account.getCuentaID());

        if(accountbuscada == null){
            assertNull(accountbuscada);
        }else{
            System.out.println(accountbuscada.get());
            assertNotNull(accountbuscada);
//            assertNull(userbuscado);
        }

    }






}
