package dreamdev.anthony.services;

import dreamdev.anthony.data.models.Election;
import dreamdev.anthony.data.repositories.ElectionRepository;
import dreamdev.anthony.dtos.requests.CreateElectionRequest;
import dreamdev.anthony.dtos.requests.UpdateElectionRequest;
import dreamdev.anthony.dtos.responses.ElectionResponse;
import dreamdev.anthony.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

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
    private UpdateElectionRequest updateElectionRequest;

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

        updateElectionRequest = new UpdateElectionRequest();
        updateElectionRequest.setElectionName("Updated Election");
    }

    @Test
    void createElection_shouldSaveAndReturnElectionResponse() {
        when(electionRepository.save(any(Election.class))).thenReturn(election);

        ElectionResponse response = electionService.createElection(createElectionRequest);

        assertNotNull(response);
        assertEquals("Presidential Election", response.getElectionName());
        verify(electionRepository, times(1)).save(any(Election.class));
    }

    @Test
    void getElectionById_shouldReturnElectionResponse_whenIdExists() {
        when(electionRepository.findById("randomstringid")).thenReturn(Optional.of(election));

        ElectionResponse response = electionService.getElectionById("randomstringid");

        assertNotNull(response);
        assertEquals("randomstringid", response.getId());
        assertEquals("Presidential Election", response.getElectionName());
        verify(electionRepository, times(1)).findById("randomstringid");
    }

    @Test
    void getElectionById_shouldThrowException_whenIdDoesNotExist() {
        when(electionRepository.findById("wrongId")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> electionService.getElectionById("wrongId"));
        verify(electionRepository, times(1)).findById("wrongId");
    }

    @Test
    void updateElection_shouldUpdateAndReturnElectionResponse_whenIdExists() {
        when(electionRepository.findById("randomstringid")).thenReturn(Optional.of(election));
        when(electionRepository.save(any(Election.class))).thenReturn(election);
        ElectionResponse response = electionService.updateElection("randomstringid", updateElectionRequest);
        assertNotNull(response);
        assertEquals("Updated Election", response.getElectionName());
        verify(electionRepository, times(1)).save(any(Election.class));
    }

    @Test
    void updateElection_shouldThrowException_whenIdDoesNotExist() {
        when(electionRepository.findById("wrongId")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> electionService.updateElection("wrongId", updateElectionRequest));
        verify(electionRepository, never()).save(any(Election.class));
    }
}