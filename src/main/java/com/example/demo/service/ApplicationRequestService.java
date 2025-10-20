package com.example.demo.service;

import com.example.demo.entity.ApplicationRequest;
import com.example.demo.entity.Course;
import com.example.demo.entity.Operator;
import com.example.demo.repository.ApplicationRequestRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ApplicationRequestService {

    private final ApplicationRequestRepository requestRepository;
    private final CourseRepository courseRepository;
    private final OperatorRepository operatorRepository;

    public List<ApplicationRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    public Optional<ApplicationRequest> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    public List<ApplicationRequest> getRequestsByHandledStatus(boolean handled) {
        return requestRepository.findByHandled(handled);
    }

    public List<ApplicationRequest> getRequestsByCourseId(Long courseId) {
        return requestRepository.findByCourseId(courseId);
    }

    @Transactional
    public ApplicationRequest createRequest(String userName, String commentary,
                                            String phone, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        ApplicationRequest request = new ApplicationRequest();
        request.setUserName(userName);
        request.setCommentary(commentary);
        request.setPhone(phone);
        request.setCourse(course);
        request.setHandled(false);

        return requestRepository.save(request);
    }

    @Transactional
    public ApplicationRequest assignOperators(Long requestId, Set<Long> operatorIds) {
        ApplicationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.isHandled()) {
            throw new RuntimeException("Request is already handled. Cannot assign operators.");
        }

        Set<Operator> operators = request.getOperators();
        for (Long operatorId : operatorIds) {
            Operator operator = operatorRepository.findById(operatorId)
                    .orElseThrow(() -> new RuntimeException("Operator not found: " + operatorId));
            operators.add(operator);
        }

        request.setHandled(true);

        return requestRepository.save(request);
    }

    @Transactional
    public ApplicationRequest removeOperator(Long requestId, Long operatorId) {
        ApplicationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        Operator operator = operatorRepository.findById(operatorId)
                .orElseThrow(() -> new RuntimeException("Operator not found"));

        request.getOperators().remove(operator);

        return requestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }
}