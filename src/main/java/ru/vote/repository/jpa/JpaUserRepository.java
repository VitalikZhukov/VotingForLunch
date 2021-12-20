package ru.vote.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vote.model.User;
import ru.vote.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            manager.persist(user);
            return user;
        }
        return manager.merge(user);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return manager.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return manager.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = manager.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return manager.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }
}
