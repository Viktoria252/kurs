import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void testRegisterNewUser() {
        String result = userService.register("Лена", "lena@example.com", "password123");
        assertEquals("Регистрация успешна. Проверьте свою электронную почту для подтверждения.", result);
    }

    @Test
    void testRegisterExistingUser() {
        userService.register("Лена", "lena@example.com", "password123");
        String result = userService.register("Лена", "lena@example.com", "password456");
        assertEquals("Пользователь с таким именем уже существует.", result);
    }

    @Test
    void testLoginWithActiveUser() {
        userService.register("Лена", "lena@example.com", "password123");
        userService.users.get("Лена").activate();
        String result = userService.login("Лена", "password123");
        assertEquals("Добро пожаловать, Лена!", result);
    }

    @Test
    void testLoginWithInactiveUser() {
        userService.register("Лена", "lena@example.com", "password123");
        String result = userService.login("Лена", "password123");
        assertEquals("Аккаунт не подтвержден. Проверьте свою электронную почту.", result);
    }

    @Test
    void testLoginWithWrongPassword() {
        userService.register("Лена", "lena@example.com", "password123");
        userService.users.get("Лена").activate();
        String result = userService.login("Лена", "wrongpassword");
        assertEquals("Неверный пароль.", result);
    }

    @Test
    void testRecoverPasswordWithExistingEmail() {
        userService.register("Лена", "lena@example.com", "password123");
        String result = userService.recoverPassword("lena@example.com");
        assertEquals("Новый пароль отправлен на вашу электронную почту.", result);
    }

    @Test
    void testRecoverPasswordWithNonExistingEmail() {
        String result = userService.recoverPassword("nonexistent@example.com");
        assertEquals("Пользователь с такой электронной почтой не найден.", result);
    }
}
