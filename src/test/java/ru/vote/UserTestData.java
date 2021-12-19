package ru.vote;

import ru.vote.model.Role;
import ru.vote.model.User;

import java.util.Collections;

import static ru.vote.model.AbstractUser.START_SEQ;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("registered", "roles", "checkTimeVote");

    public static final int USER_ID = START_SEQ;
    public static final int USER_ID_SECOND = START_SEQ + 1;
    public static final int ADMIN_ID = START_SEQ + 2;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "User1", "1@tut.by", "Password1", 1, Role.USER);
    public static final User user2 = new User(USER_ID_SECOND, "User2", "2@tut.by", "Password2", 2, Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@tut.by", "Password", null, Role.ADMIN);

    public static User getNew() {
        return new User(null, "NewName", "new@tut.by", "NewPass", 50, Role.USER);
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setId(USER_ID);
        updated.setEmail("update@tut.by");
        updated.setLogin("UpdateName");
        updated.setPassword("updatePass");
        updated.setRestaurantId(60);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
