package com.example.demo.repository;

import com.example.demo.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {
    List<Operator> findByDepartment(String department);
}