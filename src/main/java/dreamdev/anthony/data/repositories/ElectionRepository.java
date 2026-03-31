package dreamdev.anthony.data.repositories;

import dreamdev.anthony.data.models.Election;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ElectionRepository extends MongoRepository<Election, String> {
}
