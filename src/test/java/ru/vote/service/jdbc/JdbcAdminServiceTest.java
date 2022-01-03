package ru.vote.service.jdbc;

import org.junit.Ignore;
import org.springframework.test.context.ActiveProfiles;
import ru.vote.service.abstractTest.AbstractUserServiceTest;

import static ru.vote.Profiles.JDBC;

@ActiveProfiles(JDBC)
@Ignore
public class JdbcAdminServiceTest extends AbstractUserServiceTest {
}
