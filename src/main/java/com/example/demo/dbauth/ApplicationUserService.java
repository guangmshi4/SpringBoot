package com.example.demo.dbauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserDao applicationUserDao;

    @Autowired
    public ApplicationUserService(ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserRole> user = applicationUserDao.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found" + username));

        return user.map(ApplicationUser::new).get();
    }
}
