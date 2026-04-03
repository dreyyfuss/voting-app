package dreamdev.anthony.utils;

import dreamdev.anthony.data.models.Election;
import dreamdev.anthony.dtos.requests.CreateElectionRequest;
import dreamdev.anthony.dtos.requests.UpdateElectionRequest;
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

    public static Election updateElection(Election election, UpdateElectionRequest request) {

        if (request.getElectionName() != null) election.setElectionName(request.getElectionName());
        if (request.getElectionStatus() != null) election.setElectionStatus(request.getElectionStatus());
        if (request.getStartTime() != null) election.setStartTime(request.getStartTime());
        if (request.getEndTime() != null) election.setEndTime(request.getEndTime());

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
