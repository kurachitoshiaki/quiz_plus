package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizAppController {
	private List<Quiz> quizzes = new ArrayList<>();
	
	// クイズ一覧
	@GetMapping("/show")
	public List<Quiz> show() {
		return quizzes;
	}
	
	//　クイズ作成
	@PostMapping("/create")
	public void create(@RequestParam String question, @RequestParam boolean answer) {
		Quiz quiz = new Quiz(question, answer);
		quizzes.add(quiz);
	}
}
