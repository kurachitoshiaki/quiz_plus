package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizAppApplication {
	private List<Quiz> quizzes = new ArrayList<>();
	private QuizFileDao quizFileDao = new QuizFileDao();
	
	@GetMapping("/quiz")
	public Quiz quiz() {
		int index = new Random().nextInt(quizzes.size());
		
		return quizzes.get(index);
	}
	// クイズ一覧
	@GetMapping("/show")
	public List<Quiz> show() {
		return quizzes;
	}
	
	// クイズ作成
	@PostMapping("/create")
	public void create(@RequestParam String question, @RequestParam int answer) {
		Quiz quiz = new Quiz(question, answer);
		quizzes.add(quiz);
	}
	
	// クイズの解答
	@GetMapping("/check")
	public String check(@ RequestParam String question,@RequestParam boolean answer) {
		for(Quiz quiz: quizzes) {
			if(quiz.getQuestion().equals(question)) {
				if(quiz.isAnswer() == answer) {
					return "正解！";
				}else {
					return "不正解！";
				}
			}else {
				return "見つからなかった" + quiz.getQuestion();
			}
		}
		return "問題がありません";
	}
	
	// クイズの解答
//	@GetMapping("/check")
//	public String for_check(@ RequestParam String question,@RequestParam int answer) {
//		for(Quiz quiz: quizzes) {
//			if(quiz.getQuestion().equals(question)) {
//				if(quiz.isFourAnswer() == answer) {
//					return "正解！";
//				}else {
//					return "不正解！";
//				}
//			}else {
//				return "見つからなかった" + quiz.getQuestion();
//			}
//		}
//		return "問題がありません";
//	}
	@PostMapping("/save")
	public String save() {
		try {
			quizFileDao.write(quizzes);
			return "ファイルに保存しました";
		} catch (IOException e) {
			return "ファイルの保存に失敗しました。";
		}
	}
	
	@GetMapping("/load")
	public String load() {
		try {
			quizzes = quizFileDao.read();
			return "ファイルを読み込みました";
		} catch (Exception e) {
			e.printStackTrace();
			return "ファイルの読み込みに失敗しました";
		}
	}
}
