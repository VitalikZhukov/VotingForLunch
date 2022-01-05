package ru.vote.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.vote.service.abstractTest.AbstractRestaurantServiceTest;

import static ru.vote.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
class DataJpaRestaurantServiceTest extends AbstractRestaurantServiceTest {
}
