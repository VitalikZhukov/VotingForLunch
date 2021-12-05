package ru.vote.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vote.model.User;
import ru.vote.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    // ID for DB in memory
    private final AtomicInteger inMemoryUserID = new AtomicInteger(0);

    // DB in memory
    private final Map<Integer, User> inMemoryUserDB = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(inMemoryUserID.incrementAndGet());
            inMemoryUserDB.put(user.getId(), user);
            return user;
        }
        //update
        //action with value in Map, if the value there
        return inMemoryUserDB.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return inMemoryUserDB.remove(id) != null;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return inMemoryUserDB.get(id);
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return inMemoryUserDB.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return inMemoryUserDB.values().stream()
                .sorted(Comparator.comparing(User::getLogin).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }
}
