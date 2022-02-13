package ru.vote;

import ru.vote.model.Role;
import ru.vote.model.User;
import ru.vote.web.json.JsonUtil;

import java.util.Collections;

import static ru.vote.model.AbstractModel.START_SEQ;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "checkTimeVote", "password");

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int NOT_FOUND = 10;
    public static final int WITH_REST_ID = 10002;

    public static final User user = new User(USER_ID, "User", "1@tut.by", "Password1", null, Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@tut.by", "Password", null, Role.ADMIN, Role.USER);

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

    public static User getWithResetRestID1() {
        User updated = new User(user);
        updated.setRestaurantId(0);
        return updated;
    }

    public static User getWithResetRestID2() {
        User updated = new User(admin);
        updated.setRestaurantId(0);
        return updated;
    }

    public static User getWithRestaurantId() {
        User withRestId = new User(user);
        withRestId.setRestaurantId(WITH_REST_ID);
        return withRestId;
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
