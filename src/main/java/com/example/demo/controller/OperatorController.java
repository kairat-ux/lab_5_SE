package com.example.demo.controller;

import com.example.demo.entity.Operator;
import com.example.demo.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OperatorController {

    private final OperatorService operatorService;

    @GetMapping
    public ResponseEntity<List<Operator>> getAllOperators() {
        return ResponseEntity.ok(operatorService.getAllOperators());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operator> getOperatorById(@PathVariable Long id) {
        return operatorService.getOperatorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<Operator>> getOperatorsByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(operatorService.getOperatorsByDepartment(department));
    }

    @PostMapping
    public ResponseEntity<Operator> createOperator(@RequestBody Operator operator) {
        Operator createdOperator = operatorService.createOperator(operator);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOperator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operator> updateOperator(@PathVariable Long id,
                                                   @RequestBody Operator operator) {
        try {
            Operator updatedOperator = operatorService.updateOperator(id, operator);
            return ResponseEntity.ok(updatedOperator);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperator(@PathVariable Long id) {
        operatorService.deleteOperator(id);
        return ResponseEntity.noContent().build();
    }
}