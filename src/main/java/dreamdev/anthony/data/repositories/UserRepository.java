package dreamdev.anthony.data.repositories;

import dreamdev.anthony.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> getUserById(String id);
}
