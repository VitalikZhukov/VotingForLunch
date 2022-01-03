package ru.vote.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.vote.service.abstractTest.AbstractRestaurantServiceTest;

import static ru.vote.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcRestaurantServiceTest extends AbstractRestaurantServiceTest {
}
