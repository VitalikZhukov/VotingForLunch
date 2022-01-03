package ru.vote.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.vote.service.abstractTest.AbstractMenuServiceTest;

import static ru.vote.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaMenuServiceTest extends AbstractMenuServiceTest {
}
