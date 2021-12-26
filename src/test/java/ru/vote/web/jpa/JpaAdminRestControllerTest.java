package ru.vote.web.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.vote.web.abstractTest.AbstractAdminRestControllerTest;

import static ru.vote.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaAdminRestControllerTest extends AbstractAdminRestControllerTest {
}
