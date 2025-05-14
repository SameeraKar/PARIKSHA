package com.example.quiz.controller;

import com.example.quiz.model.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class QuizController {

    private final List<Question> quiz = List.of(
        new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Rome"}, "Paris"),
        new Question("2 + 2 = ?", new String[]{"3", "4", "5", "6"}, "4"),
        new Question("Which language is used for Android development?", new String[]{"Java", "Python", "C#", "Ruby"}, "Java")
    );

    @GetMapping("/")
    public String showQuiz(Model model) {
        model.addAttribute("questions", quiz);
        return "index";
    }

    @PostMapping("/submit")
    public String submitQuiz(@RequestParam Map<String, String> responses, Model model) {
        int score = 0;
        for (int i = 0; i < quiz.size(); i++) {
            String userAnswer = responses.get("q" + i);
            if (userAnswer != null && userAnswer.equals(quiz.get(i).getAnswer())) {
                score++;
            }
        }
        model.addAttribute("score", score);
        model.addAttribute("total", quiz.size());
        return "result";
    }
}
