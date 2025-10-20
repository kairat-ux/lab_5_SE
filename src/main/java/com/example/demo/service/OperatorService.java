package com.example.demo.service;

import com.example.demo.entity.Operator;
import com.example.demo.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperatorService {

    private final OperatorRepository operatorRepository;

    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }

    public Optional<Operator> getOperatorById(Long id) {
        return operatorRepository.findById(id);
    }

    public List<Operator> getOperatorsByDepartment(String department) {
        return operatorRepository.findByDepartment(department);
    }

    public Operator createOperator(Operator operator) {
        return operatorRepository.save(operator);
    }

    public Operator updateOperator(Long id, Operator operatorDetails) {
        Operator operator = operatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operator not found"));

        operator.setName(operatorDetails.getName());
        operator.setSurname(operatorDetails.getSurname());
        operator.setDepartment(operatorDetails.getDepartment());

        return operatorRepository.save(operator);
    }

    public void deleteOperator(Long id) {
        operatorRepository.deleteById(id);
    }
}