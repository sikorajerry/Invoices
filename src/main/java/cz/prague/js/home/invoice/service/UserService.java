package cz.prague.js.home.invoice.service;

import cz.prague.js.home.invoice.dto.UserDto;
import cz.prague.js.home.invoice.model.Authority;
import cz.prague.js.home.invoice.model.AuthorityType;
import cz.prague.js.home.invoice.model.User;
import cz.prague.js.home.invoice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserDto userDto) throws Exception {

        User userByUsername = userRepository.findUserByUsername(userDto.getUsername());
        if (userByUsername != null) {
            throw new Exception("USername exist not possible add another one");
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setUsername(userDto.getUsername());
        String encodedPassword = new BCryptPasswordEncoder().encode(userDto.getPassword());
        user.setPassword(encodedPassword);
        user.setReadablePassword(userDto.getPassword());

        Set<Authority> authorities = new HashSet<>();
        Authority authority=  new Authority();
        authority.setName(AuthorityType.ROLE_USER);
        authorities.add(authority);

        user.setAuthorities(authorities);

        logger.info("User save({})", user);



        userRepository.save(user);
    }

    public List<UserDto> findAll() {
        List<User> all = userRepository.findAll();
        List<UserDto> output = new ArrayList<>();
        for (User user : all) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setSurname(user.getSurname());
            userDto.setPassword(user.getPassword());
            userDto.setUsername(user.getUsername());

            output.add(userDto);
        }

        return output;
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }


}
