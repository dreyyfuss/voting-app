package dreamdev.anthony.services;

import dreamdev.anthony.data.models.Election;
import dreamdev.anthony.data.repositories.ElectionRepository;
import dreamdev.anthony.dtos.requests.CreateElectionRequest;
import dreamdev.anthony.dtos.requests.UpdateElectionRequest;
import dreamdev.anthony.dtos.responses.ElectionResponse;
import dreamdev.anthony.utils.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ElectionServiceImpl implements ElectionService {

    private final ElectionRepository electionRepository;

    public ElectionServiceImpl(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    @Override
    public ElectionResponse createElection(CreateElectionRequest request) {
        Election savedElection = electionRepository.save(Mapper.toElection(request));
        return Mapper.toElectionResponse(savedElection);
    }

    @Override
    public ElectionResponse getElectionById(String id) {
    }

    @Override
    public ElectionResponse updateElection(String id, UpdateElectionRequest request) {
    }

    @Override
    public Page<ElectionResponse> getAllElections(int page, int size, String sortBy) {
    }

    @Override
    public void deleteElection(String id) {
    }
}
