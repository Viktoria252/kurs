public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Регистрация пользователя
        System.out.println(userService.register("Лена", "lena@example.com", "password123"));

        // Аутентификация
        System.out.println(userService.login("Лена", "password123"));

        // Восстановление пароля
        System.out.println(userService.recoverPassword("lena@example.com"));
    }
}