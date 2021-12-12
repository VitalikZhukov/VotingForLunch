package ru.vote;

import ru.vote.model.Role;
import ru.vote.model.User;

import static ru.vote.model.AbstractModel.START_SEQ;

public class UserTestData {

    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("registered", "roles", "checkTimeVote");

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 2;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "User1", "1@tut.by", "Password1", 1, Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@tut.by", "Password", null, Role.ADMIN);

}
