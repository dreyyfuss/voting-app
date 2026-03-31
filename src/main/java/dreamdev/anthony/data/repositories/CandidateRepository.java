package dreamdev.anthony.data.repositories;

import dreamdev.anthony.data.models.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CandidateRepository extends MongoRepository<Candidate, String> {
    List<Candidate> getCandidatesByElectionId(String electionId);
}
