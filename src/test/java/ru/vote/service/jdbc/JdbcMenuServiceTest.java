package ru.vote.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.vote.service.abstractTest.AbstractMenuServiceTest;

import static ru.vote.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcMenuServiceTest extends AbstractMenuServiceTest {
}
