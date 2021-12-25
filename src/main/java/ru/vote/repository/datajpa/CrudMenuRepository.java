package ru.vote.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vote.model.Menu;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
}
