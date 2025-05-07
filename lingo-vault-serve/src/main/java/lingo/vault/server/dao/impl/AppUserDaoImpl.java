package lingo.vault.server.dao.impl;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lingo.vault.server.dao.AppUserDao;
import lingo.vault.server.entity.AppUser;

@Component
public class AppUserDaoImpl implements AppUserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public String getPassword(String userName) {
        String sql = "select password_hash from app_user where username = ?";
        try {
            return (String) entityManager.createNativeQuery(sql).setParameter(1, userName).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public AppUser findByUsername(String userName) {
        try {
            return  entityManager.find(AppUser.class, userName);
        } catch (NoResultException e) {
            return null;
        }
    }
}
