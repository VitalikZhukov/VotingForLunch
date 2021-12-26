package ru.vote.web.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.vote.web.abstractTest.AbstractRestaurantRestControllerTest;

import static ru.vote.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaRestaurantRestControllerTest extends AbstractRestaurantRestControllerTest {
}
