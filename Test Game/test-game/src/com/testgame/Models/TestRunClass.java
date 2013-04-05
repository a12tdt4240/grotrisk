package com.testgame.Models;

public class TestRunClass {
	public static void main(String[] args) {
		Category catOne = new Category("Historie");
		Alternative alt1 = new Alternative("17.mai 1905", false);
		Alternative alt2 = new Alternative("17.mai 1814", false);
		Alternative alt3 = new Alternative("7.juni 1905", true);
		Alternative alt4 = new Alternative("7.mai 1945", false);
		Question questionOne = new Question(catOne,
				"Når ble det moderne Norge uavhengig?", alt1, alt2, alt3, alt4);

		System.out.println("Kategori: " + questionOne.category.category);
		System.out.println("Spørsmål: " + questionOne.questionText);
		System.out.println("Alternativ 1: " + questionOne.alt1.text + "\nAlternativ 2: "
				+ questionOne.alt2.text + "\nAlternativ 3: " + questionOne.alt3.text + "\nAlternativ 4: " + questionOne.alt4.text);
		System.out.println("Er alternativ 1 riktig? " + questionOne.alt1.isCorrectAnswer);
		System.out.println("Er alternativ 3 riktig? " + questionOne.alt3.isCorrectAnswer);
		
	}
}
