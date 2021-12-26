package ru.vote.web.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.vote.web.abstractTest.AbstractRestaurantRestControllerTest;

import static ru.vote.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcRestaurantRestControllerTest extends AbstractRestaurantRestControllerTest {
}
