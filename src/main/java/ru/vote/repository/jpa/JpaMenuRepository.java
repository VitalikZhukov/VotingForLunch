package ru.vote.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vote.model.Menu;
import ru.vote.repository.MenuRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMenuRepository implements MenuRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Menu save(Menu menu) {
        if (menu.isNew()) {
            manager.persist(menu);
            return menu;
        }
        return manager.merge(menu);
    }

    @Override
    public Menu get(int id) {
        return manager.find(Menu.class, id);
    }

    @Override
    public List<Menu> getListByRestaurantId(int restaurantId) {
        return manager.createNamedQuery(Menu.GET_LIST_BY_RESTAURANT_ID, Menu.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }

    @Override
    public List<Menu> getAll() {
        return manager.createNamedQuery(Menu.ALL_SORTED, Menu.class).getResultList();
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return manager.createNamedQuery(Menu.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public boolean deleteAllByRestaurantId(int restaurantId) {
        return manager.createNamedQuery(Menu.DELETE_ALL_BY_RESTAURANT_ID)
                .setParameter("restaurantId", restaurantId)
                .executeUpdate() != 0;
    }
}
