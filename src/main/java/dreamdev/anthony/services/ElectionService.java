package dreamdev.anthony.services;

import dreamdev.anthony.dtos.requests.CreateElectionRequest;
import dreamdev.anthony.dtos.requests.UpdateElectionRequest;
import dreamdev.anthony.dtos.responses.ElectionResponse;
import org.springframework.data.domain.Page;

public interface ElectionService {
    ElectionResponse createElection(CreateElectionRequest request);
    ElectionResponse updateElection(String id, UpdateElectionRequest request);
    ElectionResponse getElectionById(String electionId);
    Page<ElectionResponse> getAllElections(int page, int size, String sortBy);
    void deleteElection(String id);
}
