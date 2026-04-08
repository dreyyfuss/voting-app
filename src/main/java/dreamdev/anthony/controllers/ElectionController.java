package dreamdev.anthony.controllers;

import dreamdev.anthony.dtos.requests.CreateElectionRequest;
import dreamdev.anthony.dtos.requests.UpdateElectionRequest;
import dreamdev.anthony.dtos.responses.ApiResponse;
import dreamdev.anthony.dtos.responses.ElectionResponse;
import dreamdev.anthony.services.ElectionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/elections")
public class ElectionController {
    private final ElectionService electionService;

    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ElectionResponse>> createElection(@RequestBody CreateElectionRequest request) {
        ElectionResponse electionResponse = electionService.createElection(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, electionResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ElectionResponse>> getElectionById(@PathVariable String id) {
        ElectionResponse electionResponse = electionService.getElectionById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, electionResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ElectionResponse>>> getAllElections(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "electionName") String sortBy) {
        Page<ElectionResponse> electionResponsePage = electionService.getAllElections(page, size, sortBy);
        return ResponseEntity.ok(new ApiResponse<>(true, electionResponsePage));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ElectionResponse>> updateElection(
            @PathVariable String id,
            @RequestBody UpdateElectionRequest request) {
        ElectionResponse electionResponse = electionService.updateElection(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, electionResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteElection(
            @PathVariable String id) {
        electionService.deleteElection(id);
        return ResponseEntity.ok(new ApiResponse<>(true, null));
    }
}
