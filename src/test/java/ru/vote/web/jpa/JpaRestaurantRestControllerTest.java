package ru.vote.web.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.vote.web.abstractTest.AbstractRestaurantRestControllerTest;


import static ru.vote.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaRestaurantRestControllerTest extends AbstractRestaurantRestControllerTest {
}
