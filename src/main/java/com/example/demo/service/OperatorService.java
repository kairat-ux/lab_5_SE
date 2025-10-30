package com.example.demo.service;

import com.example.demo.entity.Operator;
import com.example.demo.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;

    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }

    public Optional<Operator> getOperatorById(Long id) {
        return operatorRepository.findById(id);
    }

    public Operator createOperator(Operator operator) {
        return operatorRepository.save(operator);
    }

    public Operator updateOperator(Long id, Operator updatedOperator) {
        Operator operator = operatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operator not found"));

        operator.setName(updatedOperator.getName());
        operator.setEmail(updatedOperator.getEmail());
        operator.setPhone(updatedOperator.getPhone());
        operator.setActive(updatedOperator.getActive());

        return operatorRepository.save(operator);
    }

    public void deleteOperator(Long id) {
        operatorRepository.deleteById(id);
    }

    public List<Operator> getActiveOperators() {
        return operatorRepository.findByActive(true);
    }
}