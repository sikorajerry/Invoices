package cz.prague.js.home.invoice.repository;

import cz.prague.js.home.invoice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface  UserRepository extends MongoRepository<User, String> {
    public User findUserByUsername(String username);
}
