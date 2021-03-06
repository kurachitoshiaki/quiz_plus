package com.example.demo;

public class Quiz {
	
	/**
	 * 問題文
	 */
	private String question;
	
	/**
	 * クイズの正解
	 */
	private boolean answer;
	private int fourAnswer;
	
	public Quiz(String question, boolean answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public Quiz(String question, int fourAnswer) {
		this.question = question;
		this.fourAnswer = fourAnswer;
	}
	
	public String getQuestion() {
		return question;
	}
	// 二択問題
	public boolean isAnswer() {
		return answer;
	}
	// 四択問題
	public int isFourAnswer(){
		return fourAnswer;
	}
	
	@Override
	public String toString() {
		String marubatsu = answer ? "○" : "×";
		return question + "" + marubatsu;
	}
}
