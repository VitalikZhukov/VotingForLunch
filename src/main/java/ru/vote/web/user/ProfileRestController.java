package ru.vote.web.user;

import org.springframework.stereotype.Controller;
import ru.vote.model.User;
import ru.vote.repository.UserRepository;

import static ru.vote.web.SecurityUtil.authUserId;

@Controller
public class ProfileRestController extends AbstractUserController {

    protected ProfileRestController(UserRepository userRepository) {
        super(userRepository);
    }

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}
