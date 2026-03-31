package dreamdev.anthony.data.repositories;

import dreamdev.anthony.data.models.Voter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VoterRepository extends MongoRepository<Voter, String> {
    List<Voter> getVotersByElectionId(String electionId);
}
