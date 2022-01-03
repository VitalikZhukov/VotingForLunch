package ru.vote.web.jdbc;

import org.junit.Ignore;
import org.springframework.test.context.ActiveProfiles;
import ru.vote.web.abstractTest.AbstractAdminRestControllerTest;

import static ru.vote.Profiles.JDBC;

@ActiveProfiles(JDBC)
@Ignore
public class JdbcAdminRestControllerTest extends AbstractAdminRestControllerTest {
}
