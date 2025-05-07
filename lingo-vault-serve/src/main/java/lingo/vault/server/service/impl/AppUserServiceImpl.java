
package lingo.vault.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lingo.vault.server.dao.AppUserDao;
import lingo.vault.server.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    @Autowired
    AppUserDao appUserDao;

    @Override
    public boolean login(String username, String password) {
        // Get pasword from DB
        String encodedPasword = appUserDao.getPassword(username);
        // check if user is exist
        if (encodedPasword == null) {
            return false;
        }

        // check password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, encodedPasword);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserDao.findByUsername(username);

    }

}
