package dreamdev.anthony.dtos.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateElectionRequest {
    private String electionName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String electionStatus;
}
