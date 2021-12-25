package ru.vote.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vote.model.Restaurant;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
