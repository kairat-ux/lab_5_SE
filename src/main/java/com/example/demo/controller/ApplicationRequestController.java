package com.example.demo.controller;

import com.example.demo.entity.ApplicationRequest;
import com.example.demo.service.ApplicationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ApplicationRequestController {

    private final ApplicationRequestService requestService;

    @GetMapping
    public ResponseEntity<List<ApplicationRequest>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationRequest> getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/handled/{handled}")
    public ResponseEntity<List<ApplicationRequest>> getRequestsByHandledStatus(
            @PathVariable boolean handled) {
        return ResponseEntity.ok(requestService.getRequestsByHandledStatus(handled));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ApplicationRequest>> getRequestsByCourseId(
            @PathVariable Long courseId) {
        return ResponseEntity.ok(requestService.getRequestsByCourseId(courseId));
    }

    @PostMapping
    public ResponseEntity<ApplicationRequest> createRequest(
            @RequestBody Map<String, Object> requestData) {
        try {
            String userName = (String) requestData.get("userName");
            String commentary = (String) requestData.get("commentary");
            String phone = (String) requestData.get("phone");
            Long courseId = Long.valueOf(requestData.get("courseId").toString());

            ApplicationRequest createdRequest = requestService.createRequest(
                    userName, commentary, phone, courseId);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{requestId}/assign-operators")
    public ResponseEntity<ApplicationRequest> assignOperators(
            @PathVariable Long requestId,
            @RequestBody Map<String, Set<Long>> body) {
        try {
            Set<Long> operatorIds = body.get("operatorIds");
            ApplicationRequest updatedRequest = requestService.assignOperators(requestId, operatorIds);
            return ResponseEntity.ok(updatedRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{requestId}/operators/{operatorId}")
    public ResponseEntity<ApplicationRequest> removeOperator(
            @PathVariable Long requestId,
            @PathVariable Long operatorId) {
        try {
            ApplicationRequest updatedRequest = requestService.removeOperator(requestId, operatorId);
            return ResponseEntity.ok(updatedRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}