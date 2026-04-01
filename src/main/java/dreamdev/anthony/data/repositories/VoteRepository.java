package dreamdev.anthony.data.repositories;

import dreamdev.anthony.data.models.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepository extends MongoRepository<Vote, String> {
}
