package dreamdev.anthony.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Document
public class Candidate {
    @Id
    private String id;
    private String userId;
    private String electionId;
}
