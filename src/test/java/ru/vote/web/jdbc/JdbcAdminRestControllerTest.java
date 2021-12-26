package ru.vote.web.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.vote.web.abstractTest.AbstractAdminRestControllerTest;

import static ru.vote.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcAdminRestControllerTest extends AbstractAdminRestControllerTest {
}
