package com.example.demo.controller;

import com.example.demo.entity.ApplicationRequest;
import com.example.demo.service.ApplicationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "*")
public class ApplicationRequestController {

    @Autowired
    private ApplicationRequestService requestService;

    @GetMapping
    public ResponseEntity<List<ApplicationRequest>> getAllRequests() {
        List<ApplicationRequest> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationRequest> getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApplicationRequest> createRequest(@RequestBody ApplicationRequest request) {
        ApplicationRequest created = requestService.createRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationRequest> updateRequest(
            @PathVariable Long id,
            @RequestBody ApplicationRequest request) {
        try {
            ApplicationRequest updated = requestService.updateRequest(id, request);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        try {
            requestService.deleteRequest(id);
            return ResponseEntity.ok("Request deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting request");
        }
    }

    @GetMapping("/unhandled")
    public ResponseEntity<List<ApplicationRequest>> getUnhandledRequests() {
        List<ApplicationRequest> requests = requestService.getUnhandledRequests();
        return ResponseEntity.ok(requests);
    }
}