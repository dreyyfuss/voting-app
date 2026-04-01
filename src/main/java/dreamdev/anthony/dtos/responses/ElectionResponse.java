package dreamdev.anthony.dtos.responses;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class ElectionResponse {
    @Id
    private String id;
    private String electionName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String electionStatus;
}
