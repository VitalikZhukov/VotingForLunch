package ru.vote.web;

// stub until I deploy Spring Security
// returns SEQUENCE as validation for user ID

import ru.vote.model.AbstractUser;

public class SecurityUtil {
    private static int id = AbstractUser.START_SEQ;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }
}
