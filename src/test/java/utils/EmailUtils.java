package utils;

import java.util.UUID;

public class EmailUtils {
    public static String generateRandomEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@qa.com.br";
    }
}