package ru.vote.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.vote.service.abstractTest.AbstractUserServiceTest;

import static ru.vote.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaAdminServiceTest extends AbstractUserServiceTest {
}
