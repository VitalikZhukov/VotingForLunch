package ru.vote.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vote.model.Restaurant;
import ru.vote.repository.RestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepository implements RestaurantRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            manager.persist(restaurant);
            return restaurant;
        }
        return manager.merge(restaurant);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return manager.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Restaurant get(int id) {
        return manager.find(Restaurant.class, id);
    }

    @Override
    public List<Restaurant> getAll() {
        return manager.createNamedQuery(Restaurant.ALL_SORTED, Restaurant.class).getResultList();
    }

    @Override
    @Transactional
    public boolean incrementVoteCounter(int id) {
        int counter = getVoteCounter(id);
        counter++;
        return manager.createNamedQuery(Restaurant.INCREMENT_VOTE_COUNTER)
                .setParameter("id", id)
                .setParameter("counter", counter)
                .executeUpdate() != 0;
    }

    @Override
    public int getVoteCounter(int id) {
        return (int) manager.createNamedQuery(Restaurant.GET_VOTE_COUNTER)
                .setParameter("id", id)
                .getSingleResult();
    }
}
