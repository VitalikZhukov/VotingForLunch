package ru.vote.web.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.vote.web.abstractTest.AbstractMenuRestControllerTest;

import static ru.vote.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcMenuRestControllerTest extends AbstractMenuRestControllerTest {
}
