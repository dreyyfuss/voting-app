package dreamdev.anthony.utils;

import dreamdev.anthony.data.models.Election;
import dreamdev.anthony.dtos.requests.CreateElectionRequest;
import dreamdev.anthony.dtos.responses.ElectionResponse;

public class Mapper {
    public static Election toElection(CreateElectionRequest request) {
        Election election = new Election();

        election.setElectionName(request.getElectionName());
        election.setElectionStatus(request.getElectionStatus());
        election.setStartTime(request.getStartTime());
        election.setEndTime(request.getEndTime());

        return election;
    }

    public static ElectionResponse toElectionResponse(Election election) {
        ElectionResponse response = new ElectionResponse();

        response.setId(election.getId());
        response.setElectionName(election.getElectionName());
        response.setElectionStatus(election.getElectionStatus());
        response.setStartTime(election.getStartTime());
        response.setEndTime(election.getEndTime());

        return response;
    }
}
