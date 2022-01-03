package ru.vote.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.vote.service.abstractTest.AbstractUserServiceTest;

import static ru.vote.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
}
