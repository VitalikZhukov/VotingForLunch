package ru.vote;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.vote.model.Restaurant;
import ru.vote.model.Role;
import ru.vote.model.User;
import ru.vote.web.restaurant.RestaurantRestController;
import ru.vote.web.user.AdminRestController;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class SpringMain {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant(null,"Лидбир Темный", Map.of("Первое", 6.80, "Второе", 13.50, "Компот", 4.00), 5);
        User user = new User(null, "Vaska", "qwe@ewq.by", "123", null, restaurant, null, Role.USER);

        try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml")) {
            System.out.println("Bean names: " + Arrays.toString(context.getBeanDefinitionNames()));

            RestaurantRestController rest = context.getBean(RestaurantRestController.class);
            rest.create(restaurant);

            AdminRestController admin = context.getBean(AdminRestController.class);
            admin.create(user);

            System.out.println("--------------------------");

            rest.getAll().forEach(System.out::println);
            System.out.println(admin.get(1));
        }

    }
}
