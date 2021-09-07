package com.example.myapplication.controller;

import com.example.myapplication.domain.Message;
import com.example.myapplication.domain.User;
import com.example.myapplication.repos.MessageRepo;
import com.example.myapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model, @AuthenticationPrincipal User user) {
        model.put("name", user == null ? "unknown" : user.getUsername());
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam (required = false, defaultValue = "") String filter, Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTextContainingIgnoreCase(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages", messages);
        model.put("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,//ошибки валидации всегда перед моделью
            Model model
    )  {
        message.setAuthor(user);
        if (bindingResult.hasErrors()) {

            Map<String, String> errorsMap = UtilsController.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            model.addAttribute("message", null);
            messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }


}