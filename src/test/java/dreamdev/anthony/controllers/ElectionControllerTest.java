package dreamdev.anthony.controllers;

import dreamdev.anthony.dtos.requests.CreateElectionRequest;
import dreamdev.anthony.dtos.requests.UpdateElectionRequest;
import dreamdev.anthony.dtos.responses.ElectionResponse;
import dreamdev.anthony.exceptions.EntityNotFoundException;
import dreamdev.anthony.services.ElectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ElectionController.class)
class ElectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ElectionService electionService;

    private ElectionResponse electionResponse;
    private CreateElectionRequest createElectionRequest;
    private UpdateElectionRequest updateElectionRequest;

    @BeforeEach
    void setUp() {
        electionResponse = new ElectionResponse();
        electionResponse.setId("randomstringid");
        electionResponse.setElectionName("Presidential Election");
        electionResponse.setStartTime(LocalDateTime.now().plusDays(1));
        electionResponse.setEndTime(LocalDateTime.now().plusDays(3));

        createElectionRequest = new CreateElectionRequest();
        createElectionRequest.setElectionName("Presidential Election");
        createElectionRequest.setStartTime(LocalDateTime.now().plusDays(1));
        createElectionRequest.setEndTime(LocalDateTime.now().plusDays(3));

        updateElectionRequest = new UpdateElectionRequest();
        updateElectionRequest.setElectionName("Updated Election");
    }

    @Test
    void createElection_shouldReturn201AndElectionResponse() throws Exception {
        when(electionService.createElection(any(CreateElectionRequest.class))).thenReturn(electionResponse);

        mockMvc.perform(post("/api/v1/elections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createElectionRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value("randomstringid"))
                .andExpect(jsonPath("$.data.electionName").value("Presidential Election"));

        verify(electionService, times(1)).createElection(any(CreateElectionRequest.class));
    }

    @Test
    void getElectionById_shouldReturn200AndElectionResponse_whenIdExists() throws Exception {
        when(electionService.getElectionById("randomstringid")).thenReturn(electionResponse);

        mockMvc.perform(get("/api/v1/elections/randomstringid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value("randomstringid"))
                .andExpect(jsonPath("$.data.electionName").value("Presidential Election"));

        verify(electionService, times(1)).getElectionById("randomstringid");
    }

    @Test
    void getElectionById_shouldReturn404_whenIdDoesNotExist() throws Exception {
        when(electionService.getElectionById("wrongId")).thenThrow(new EntityNotFoundException("Election not found with id: wrongId"));

        mockMvc.perform(get("/api/v1/elections/wrongId"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void getAllElections_shouldReturn200AndPageOfElectionResponses() throws Exception {
        Page<ElectionResponse> electionPage = new PageImpl<>(List.of(electionResponse));
        when(electionService.getAllElections(0, 10, "electionName")).thenReturn(electionPage);

        mockMvc.perform(get("/api/v1/elections")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sortBy", "electionName"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content[0].electionName").value("Presidential Election"));

        verify(electionService, times(1)).getAllElections(0, 10, "electionName");
    }

    @Test
    void updateElection_shouldReturn200AndUpdatedElectionResponse_whenIdExists() throws Exception {
        electionResponse.setElectionName("Updated Election");
        when(electionService.updateElection(eq("randomstringid"), any(UpdateElectionRequest.class))).thenReturn(electionResponse);

        mockMvc.perform(patch("/api/v1/elections/randomstringid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateElectionRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.electionName").value("Updated Election"));

        verify(electionService, times(1)).updateElection(eq("randomstringid"), any(UpdateElectionRequest.class));
    }

    @Test
    void updateElection_shouldReturn404_whenIdDoesNotExist() throws Exception {
        when(electionService.updateElection(eq("wrongId"), any(UpdateElectionRequest.class)))
                .thenThrow(new EntityNotFoundException("Election not found with id: wrongId"));

        mockMvc.perform(patch("/api/v1/elections/wrongId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateElectionRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void deleteElection_shouldReturn200_whenIdExists() throws Exception {
        doNothing().when(electionService).deleteElection("randomstringid");

        mockMvc.perform(delete("/api/v1/elections/randomstringid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        verify(electionService, times(1)).deleteElection("randomstringid");
    }
}