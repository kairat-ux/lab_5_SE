package com.example.demo.service;

import com.example.demo.entity.ApplicationRequest;
import com.example.demo.entity.Operator;
import com.example.demo.repository.ApplicationRequestRepository;
import com.example.demo.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicationRequestService {

    @Autowired
    private ApplicationRequestRepository requestRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    public List<ApplicationRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    public Optional<ApplicationRequest> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    public ApplicationRequest createRequest(ApplicationRequest request) {
        return requestRepository.save(request);
    }

    public ApplicationRequest updateRequest(Long id, ApplicationRequest updatedRequest) {
        ApplicationRequest request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setUserName(updatedRequest.getUserName());
        request.setPhone(updatedRequest.getPhone());
        request.setCommentary(updatedRequest.getCommentary());
        request.setHandled(updatedRequest.getHandled());

        if (updatedRequest.getCourse() != null) {
            request.setCourse(updatedRequest.getCourse());
        }

        return requestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    public ApplicationRequest assignOperator(Long operatorId, Long requestId) {
        Operator operator = operatorRepository.findById(operatorId)
                .orElseThrow(() -> new RuntimeException("Operator not found"));

        ApplicationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setOperator(operator);
        return requestRepository.save(request);
    }

    public List<ApplicationRequest> getUnhandledRequests() {
        return requestRepository.findByHandled(false);
    }
}