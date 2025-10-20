package com.example.demo.controller;

import com.example.demo.entity.ApplicationRequest;
import com.example.demo.entity.Course;
import com.example.demo.entity.Operator;
import com.example.demo.service.ApplicationRequestService;
import com.example.demo.service.CourseService;
import com.example.demo.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final CourseService courseService;
    private final OperatorService operatorService;
    private final ApplicationRequestService requestService;

    // Главная страница - список заявок
    @GetMapping("/")
    public String index(Model model) {
        List<ApplicationRequest> requests = requestService.getAllRequests();
        model.addAttribute("requests", requests);
        return "index";
    }

    // Страница списка курсов
    @GetMapping("/courses")
    public String courses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    // Страница создания заявки
    @GetMapping("/requests/new")
    public String newRequestForm(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "request-form";
    }

    // Создание заявки
    @PostMapping("/requests/create")
    public String createRequest(@RequestParam String userName,
                                @RequestParam String phone,
                                @RequestParam String commentary,
                                @RequestParam Long courseId) {
        requestService.createRequest(userName, commentary, phone, courseId);
        return "redirect:/";
    }

    // Страница назначения операторов
    @GetMapping("/requests/{id}/assign")
    public String assignOperatorsForm(@PathVariable Long id, Model model) {
        ApplicationRequest request = requestService.getRequestById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.isHandled()) {
            return "redirect:/?error=already_handled";
        }

        List<Operator> operators = operatorService.getAllOperators();
        model.addAttribute("request", request);
        model.addAttribute("operators", operators);
        return "assign-operators";
    }

    // Назначение операторов
    @PostMapping("/requests/{id}/assign")
    public String assignOperators(@PathVariable Long id,
                                  @RequestParam(required = false) List<Long> operatorIds) {
        if (operatorIds != null && !operatorIds.isEmpty()) {
            requestService.assignOperators(id, Set.copyOf(operatorIds));
        }
        return "redirect:/";
    }

    // Просмотр деталей заявки
    @GetMapping("/requests/{id}")
    public String viewRequest(@PathVariable Long id, Model model) {
        ApplicationRequest request = requestService.getRequestById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        model.addAttribute("request", request);
        return "request-details";
    }

    // Удаление оператора из заявки
    @PostMapping("/requests/{requestId}/remove-operator/{operatorId}")
    public String removeOperator(@PathVariable Long requestId,
                                 @PathVariable Long operatorId) {
        requestService.removeOperator(requestId, operatorId);
        return "redirect:/requests/" + requestId;
    }

    // Страница списка операторов
    @GetMapping("/operators")
    public String operators(Model model) {
        List<Operator> operators = operatorService.getAllOperators();
        model.addAttribute("operators", operators);
        return "operators";
    }
}