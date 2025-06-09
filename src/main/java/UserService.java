import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class UserService {
    protected Map<String, User> users = new HashMap<>();

    // Регистрация пользователя
    public String register(String username, String email, String password) {
        if (users.containsKey(username)) {
            return "Пользователь с таким именем уже существует.";
        }

        User newUser = new User(username, email, password);
        users.put(username, newUser);
        sendConfirmationEmail(email);
        return "Регистрация успешна. Проверьте свою электронную почту для подтверждения.";
    }

    // Аутентификация пользователя
    public String login(String username, String password) {
        User user = users.get(username);
        if (user == null) {
            return "Пользователь не найден.";
        }

        if (!user.isActive()) {
            return "Аккаунт не подтвержден. Проверьте свою электронную почту.";
        }

        if (!user.getPassword().equals(password)) {
            return "Неверный пароль.";
        }

        return "Добро пожаловать, " + username + "!";
    }

    // Восстановление пароля
    public String recoverPassword(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                String newPassword = UUID.randomUUID().toString().substring(0, 8); // Генерация нового пароля
                user.password = newPassword;
                sendRecoveryEmail(email, newPassword);
                return "Новый пароль отправлен на вашу электронную почту.";
            }
        }
        return "Пользователь с такой электронной почтой не найден.";
    }

    private void sendConfirmationEmail(String email) {
        System.out.println("Подтверждение отправлено на " + email);
    }

    private void sendRecoveryEmail(String email, String newPassword) {
        System.out.println("Новый пароль отправлен на " + email + ": " + newPassword);
    }
}