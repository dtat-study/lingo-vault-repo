package lingo.vault.server.dao;

import lingo.vault.server.entity.AppUser;

public interface AppUserDao {

    public String getPassword(String userName);
    
    public AppUser findByUsername(String userName);
}
