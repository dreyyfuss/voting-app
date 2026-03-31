package dreamdev.anthony.data.models;

import org.springframework.data.annotation.Id;

public class Voter {
    @Id
    private String id;
    private String userId;
    private String electionId;
}
