package lingo.vault.server.config;

public class UserContextHolder {
    private static final ThreadLocal<String> userHolder = new ThreadLocal<>();

    public static void setUser(String username) {
        userHolder.set(username);
    }

    public static String getUser() {
        return userHolder.get();
    }

    public static void clear() {
        userHolder.remove();
    }
}
