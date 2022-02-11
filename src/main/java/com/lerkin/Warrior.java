package com.lerkin;

import java.util.Objects;

public class Warrior {
    protected int attack;
    protected int health;
    protected String name;

    public Warrior(int attack, int health, String name) {
        this.attack = attack;
        this.health = health;
        this.name = name;
    }

    public Warrior() {
    }

    public void attack(Warrior warrior) {
        warrior.takeDamage(calculateAttack());
        printAttack(warrior);
    }

    protected int calculateAttack() {
        return attack;
    }

    protected void printAttack(Warrior warrior) {
        String opponentName = warrior.getName();
        System.out.println(this.name + " attack " + opponentName + ". Now he has " + warrior.getHealth() + "hp");
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warrior warrior = (Warrior) o;
        return attack == warrior.attack &&
                health == warrior.health &&
                Objects.equals(name, warrior.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attack, health, name);
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", health=" + health +
                '}';
    }
}
