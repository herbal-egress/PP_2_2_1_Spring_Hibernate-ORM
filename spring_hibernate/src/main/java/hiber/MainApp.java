package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru")); // юзер без машины
        userService.addUserWithCar(new User("User2", "Lastname2", "user2@mail.ru")
                , new Car("Koenigsegg", 850)); // юзер с машиной
        userService.addUserWithCar(new User("User3", "Lastname3", "user3@mail.ru")
                , new Car("Ferrari", 488)); // юзер с машиной

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.println("\n---------- UserByCar: " + "На " + userService.getUserByCar("Ferrari", 488)
                .getCar().getModel() + " гоняет " + userService.getUserByCar("Ferrari", 488)
                .getFirstName() + " ----------\n");
        System.out.println("\n---------- UserById: " + userService.getUserById(2L).getFirstName() + " гоняет на "
                + userService.getUserById(2L).getCar().getModel() + " ----------\n");
        context.close();
    }
}