package dreamdev.anthony.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Election {
    @Id
    private String id;
    private String electionName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String electionStatus;
}
