package ru.vote.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vote.model.Restaurant;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Restaurant r SET r.voteCounter=:counter WHERE r.id=:id")
    int incrementVoteCounter(@Param("counter") int counter, @Param("id") int id);

    @Query("SELECT r.voteCounter FROM Restaurant r WHERE r.id=:id")
    int getVoteCounter(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Restaurant r SET r.voteCounter=0")
    void resetAllVoteCounter();
}
