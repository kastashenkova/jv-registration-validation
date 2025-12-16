package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.exception.RegistrationException;
import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RegistrationServiceImplTest {
    private static RegistrationService service;
    private static User validUser1;
    private static User validUser2;
    private static User shortLoginAndPasswordUser;
    private static User youngUser;

    @BeforeAll
    static void setUp() {
        service = new RegistrationServiceImpl();
        validUser1 = new User();
        validUser1.setId(1L);
        validUser1.setLogin("kastashenkova");
        validUser1.setPassword("123456");
        validUser1.setAge(18);
        service.register(validUser1);
        validUser2 = new User();
        validUser2.setId(2L);
        validUser2.setLogin("k.astashenkova123");
        validUser2.setPassword("secure");
        validUser2.setAge(40);
        shortLoginAndPasswordUser = new User();
        shortLoginAndPasswordUser.setId(3L);
        shortLoginAndPasswordUser.setLogin("sheva");
        shortLoginAndPasswordUser.setPassword("abc1");
        shortLoginAndPasswordUser.setAge(22);
        youngUser = new User();
        youngUser.setId(4L);
        youngUser.setLogin("ivanenko");
        youngUser.setPassword("password1");
        youngUser.setAge(10);
    }

    @Test
    void register_nullUser_notOk() {
        assertThrows(RegistrationException.class,
                () -> service.register(null));
    }

    @Test
    void register_existingUser_notOk() {
        assertThrows(RegistrationException.class,
                () -> service.register(validUser1));
    }

    @Test
    void register_shortLogin_notOk() {
        assertThrows(RegistrationException.class,
                () -> service.register(shortLoginAndPasswordUser));
    }

    @Test
    void register_shortPassword_notOk() {
        assertThrows(RegistrationException.class,
                () -> service.register(shortLoginAndPasswordUser));
    }

    @Test
    void register_underAge_notOk() {
        assertThrows(RegistrationException.class,
                () -> service.register(youngUser));
    }

    @Test
    void register_normalUser_Ok() {
        assertNotNull(service.register(validUser2));
    }
}
