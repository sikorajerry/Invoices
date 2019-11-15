package cz.prague.js.home.invoice.service;

import cz.prague.js.home.invoice.security.domain.PdfUserDetails;
import cz.prague.js.home.invoice.model.User;
import cz.prague.js.home.invoice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByUsername = userRepository.findUserByUsername(username);

        return new PdfUserDetails(userByUsername);
    }
}
