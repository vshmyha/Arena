package com.lerkin;

import java.util.Arrays;

public class ArenaMain {

	public static void main(String[] args) throws InterruptedException {

		Warrior dania = new Magician(3, 50, "Dania", 10, 9);
		Warrior lerka = new Priest(3, 23, "Lerka", 30, 5, 7);

		Arena arena = new Arena(Arrays.asList(dania, lerka));
		Warrior winner = arena.startBattle();

		if (winner != null) {
			System.out.println("Absolute winner dominator 3002 IS AAAAAAAAAAA " + winner.getName());
		}

	}
}
