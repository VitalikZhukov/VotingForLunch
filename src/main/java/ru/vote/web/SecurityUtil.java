package ru.vote.web;

// заглушка, пока не внедрю Spring Security
// возвращает 1 в качестве проверки ID пользователя

public class SecurityUtil {

    public static int authUserId() {
        return 1;
    }
}
