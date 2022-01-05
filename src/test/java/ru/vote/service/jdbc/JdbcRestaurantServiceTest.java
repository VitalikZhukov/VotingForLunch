package ru.vote.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.vote.service.abstractTest.AbstractRestaurantServiceTest;

import static ru.vote.Profiles.JDBC;

@ActiveProfiles(JDBC)
class JdbcRestaurantServiceTest extends AbstractRestaurantServiceTest {
}
