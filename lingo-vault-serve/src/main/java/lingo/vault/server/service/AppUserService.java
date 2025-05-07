package lingo.vault.server.service;

public interface AppUserService {
    public boolean login(String userName, String encodedPassword);

}
