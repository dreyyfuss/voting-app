package dreamdev.anthony.services;

import dreamdev.anthony.data.models.Election;
import dreamdev.anthony.data.repositories.ElectionRepository;
import dreamdev.anthony.dtos.requests.CreateElectionRequest;
import dreamdev.anthony.dtos.responses.ElectionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ElectionServiceTest {
    @Mock
    private ElectionRepository electionRepository;

    @InjectMocks
    private ElectionServiceImpl electionServiceImpl;

    private ElectionService electionService;

    private Election election;
    private CreateElectionRequest createElectionRequest;

    @BeforeEach
    void setUp() {
        electionService = electionServiceImpl;

        election = new Election();
        election.setId("randomstringid");
        election.setElectionName("Presidential Election");
        election.setStartTime(LocalDateTime.now().plusDays(1));
        election.setEndTime(LocalDateTime.now().plusDays(3));

        createElectionRequest = new CreateElectionRequest();
        createElectionRequest.setElectionName("Presidential Election");
        createElectionRequest.setStartTime(LocalDateTime.now().plusDays(1));
        createElectionRequest.setEndTime(LocalDateTime.now().plusDays(3));
    }

    @Test
    void createElection_shouldSaveAndReturnElectionResponse() {
        when(electionRepository.save(any(Election.class))).thenReturn(election);

        ElectionResponse response = electionService.createElection(createElectionRequest);

        assertNotNull(response);
        assertEquals("Presidential Election", response.getElectionName());
        verify(electionRepository, times(1)).save(any(Election.class));
    }
}