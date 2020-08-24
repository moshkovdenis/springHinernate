package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);
        Car car = new Car(456, "Honda",
                new User("Vasy", "Petrov", "vasy@mail.ru"));
        Car car2 = new Car(4356, "Mazda",
                new User("Max", "Ivanov", "max@mail.ru"));
        carService.add(car);
        carService.add(car2);
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("User = " + user.getCar());
            System.out.println();
        }
        User getUser = carService.getUser(456, "Honda");
        System.out.println(getUser);
        context.close();
    }
}
