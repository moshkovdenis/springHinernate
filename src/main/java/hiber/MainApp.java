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
        User user1 = new User("Roman", "Petrov", "roma@mail.ru");
        user1.setCar(new Car("Honda", user1));
        userService.add(user1);
        User user2 = new User("Max", "Ivanov", "roma@mail.ru");
        user2.setCar(new Car("Mazda", user2));
        userService.add(user2);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("User car = " + user.getCar());
            System.out.println();
        }
        User getUserSeries = userService.getUserBySeries(user1.getCar().getSeries());
        System.out.println(getUserSeries);
        context.close();
    }
}
