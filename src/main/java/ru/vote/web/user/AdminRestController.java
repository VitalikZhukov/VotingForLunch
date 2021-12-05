package ru.vote.web.user;

import org.springframework.stereotype.Controller;
import ru.vote.model.User;
import ru.vote.repository.UserRepository;

import java.util.List;

@Controller
public class AdminRestController extends AbstractUserController{

    protected AdminRestController(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User get(int id) {
        return super.get(id);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(User user, int id) {
        super.update(user, id);
    }

    @Override
    public User getByEmail(String email) {
        return super.getByEmail(email);
    }
}
