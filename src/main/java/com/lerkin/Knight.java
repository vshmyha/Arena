package com.lerkin;

public class Knight extends Warrior {

    protected int armor;

    public Knight(int attack, int health, String name, int armor) {
        super(attack, health, name);
        this.armor = armor;
    }

    public Knight() {
    }

    @Override
    public void takeDamage(int damage) {
        if (armor > 0) {
            armor -= damage;
            if (armor < 0) {
                health = health + armor;
            }
        } else {
            health -= damage;
        }
    }
}
