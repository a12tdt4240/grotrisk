package com.testgame.Models;

public class TestRunClass {
	public static void main(String[] args) {
		Category catOne = new Category("Historie");
		Alternative alt1 = new Alternative("17.mai 1905", false);
		Alternative alt2 = new Alternative("17.mai 1814", false);
		Alternative alt3 = new Alternative("7.juni 1905", true);
		Alternative alt4 = new Alternative("7.mai 1945", false);
		Quiz questionOne = new Quiz(catOne,
				"Når ble det moderne Norge uavhengig?", alt1, alt2, alt3, alt4);

		System.out.println("Kategori: " + questionOne.getCategory().getName());
		System.out.println("Spørsmål: " + questionOne.getQuestionText());
		System.out.println("Alternativ 1: " + questionOne.getAlt1().getName() + "\nAlternativ 2: "
				+ questionOne.getAlt2().getName() + "\nAlternativ 3: " + questionOne.getAlt3().getName() + "\nAlternativ 4: " + questionOne.getAlt4().getName());
		System.out.println("Er alternativ 1 riktig? " + questionOne.getAlt1().isCorrectAnswer());
		System.out.println("Er alternativ 3 riktig? " + questionOne.getAlt3().isCorrectAnswer());
		
	}
}
