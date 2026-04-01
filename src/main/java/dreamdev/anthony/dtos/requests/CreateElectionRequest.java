package dreamdev.anthony.dtos.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateElectionRequest {
    private String electionName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String electionStatus;
}
