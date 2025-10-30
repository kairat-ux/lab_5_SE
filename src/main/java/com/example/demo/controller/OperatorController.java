package com.example.demo.controller;

import com.example.demo.entity.ApplicationRequest;
import com.example.demo.entity.Operator;
import com.example.demo.service.ApplicationRequestService;
import com.example.demo.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/operators")
@CrossOrigin(origins = "*")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private ApplicationRequestService requestService;

    @GetMapping
    public ResponseEntity<List<Operator>> getAllOperators() {
        List<Operator> operators = operatorService.getAllOperators();
        return ResponseEntity.ok(operators);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operator> getOperatorById(@PathVariable Long id) {
        return operatorService.getOperatorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Operator> createOperator(@RequestBody Operator operator) {
        Operator created = operatorService.createOperator(operator);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operator> updateOperator(
            @PathVariable Long id,
            @RequestBody Operator operator) {
        try {
            Operator updated = operatorService.updateOperator(id, operator);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOperator(@PathVariable Long id) {
        try {
            operatorService.deleteOperator(id);
            return ResponseEntity.ok("Operator deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting operator");
        }
    }

    @PutMapping("/{id}/assign/{requestId}")
    public ResponseEntity<ApplicationRequest> assignOperatorToRequest(
            @PathVariable Long id,
            @PathVariable Long requestId) {
        try {
            ApplicationRequest updated = requestService.assignOperator(id, requestId);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<Operator>> getActiveOperators() {
        List<Operator> operators = operatorService.getActiveOperators();
        return ResponseEntity.ok(operators);
    }
}