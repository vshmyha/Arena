package com.lerkin;

import java.util.Random;

public class Gladiator extends Warrior{
    private Random random = new Random();

    public Gladiator(int attack, int health, String name) {
        super(attack, health, name);
    }

    public Gladiator() {
    }

    @Override
    protected int calculateAttack() {
        int randomNum = random.nextInt(100) + 1;
        int attack = this.attack;
        if (randomNum > 80) {
            attack *= 3;
            System.out.println("KRIT x3");
        } else if (randomNum > 55) {
            attack *= 2;
            System.out.println("KRIT x2");
        }
        return attack;
    }
}
