package dreamdev.anthony.services;

import dreamdev.anthony.data.models.Election;
import dreamdev.anthony.data.repositories.ElectionRepository;
import dreamdev.anthony.dtos.requests.CreateElectionRequest;
import dreamdev.anthony.dtos.requests.UpdateElectionRequest;
import dreamdev.anthony.dtos.responses.ElectionResponse;
import dreamdev.anthony.exceptions.EntityNotFoundException;
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
        return Mapper.toElectionResponse(electionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Election not found with id: "+id)
        ));
    }

    @Override
    public ElectionResponse updateElection(String id, UpdateElectionRequest request) {
        Election election = electionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Election not found with id: " + id)
        );
        Election updatedElection = Mapper.updateElection(election, request);
        return Mapper.toElectionResponse(electionRepository.save(updatedElection));
    }

    @Override
    public Page<ElectionResponse> getAllElections(int page, int size, String sortBy) {
        return electionRepository.findAll();
    }

    @Override
    public void deleteElection(String id) {
    }
}
