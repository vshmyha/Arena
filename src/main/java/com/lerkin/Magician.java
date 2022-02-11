package com.lerkin;

import java.util.Objects;
import java.util.Random;

public class Magician extends Warrior {
    private Random random = new Random();
    protected int manaPoint;
    protected int spellAttack;

    public Magician(int attack, int health, String name, int manaPoint, int spellAttack) {
        super(attack, health, name);
        this.manaPoint = manaPoint;
        this.spellAttack = spellAttack;
    }

    public Magician() {
    }

    @Override
    public void attack(Warrior warrior) {
        super.attack(warrior);
        manaPoint = manaPoint++;
    }

    @Override
    protected int calculateAttack() {
        int randomNum = random.nextInt(100) + 1;
        int attack;
        int minManaPoints = 2;
        MagicAttack magicAttack;
        if (magicAttackPossible(minManaPoints)) {
            if (randomNum > 80) {
                magicAttack = attackTryingKrit(6, 3);
            } else if (randomNum > 55) {
                magicAttack = attackTryingKrit(4, 2);
            } else {
                magicAttack = calculateMagicAttack(minManaPoints, 1);
            }
            if (magicAttack != null) {
                int spellAttack = magicAttack.calculate();
                manaPoint = manaPoint - magicAttack.manaPoint;
                attack = spellAttack;
                printMagicAttack(magicAttack);
            } else {
                attack = this.attack;
            }
        } else {
            attack = this.attack;
        }
        return attack;
    }

    private MagicAttack attackTryingKrit(int requiredManaPoints, int krit) {
        MagicAttack magicAttack = calculateMagicAttack(requiredManaPoints, krit);
        if (magicAttack == null) {
            magicAttack = calculateMagicAttack(2,1);
        }
        return magicAttack;
    }

    private void printMagicAttack(MagicAttack magicAttack) {
        String kritStr = "";
        if (magicAttack.isAttackRaised()) {
            kritStr = "KRIT x" + magicAttack.currentKrit + ". ";
        }
        System.out.println(kritStr + "Left " + this.manaPoint + " manapoint");
    }

    private MagicAttack calculateMagicAttack(int requiredManaPoints, int krit) {
        if (magicAttackPossible(requiredManaPoints)) {
            MagicAttack magicAttack = new MagicAttack(requiredManaPoints);
            magicAttack.setCurrentKrit(krit);
            return magicAttack;
        }
        return null;
    }

    public boolean magicAttackPossible(int manaPoint) {
        return this.manaPoint - manaPoint > 0;
    }

    protected class MagicAttack {
        protected int manaPoint;
        protected int spellAttack;
        protected int currentKrit = 1;

        public MagicAttack(int manaPoint) {
            this.manaPoint = manaPoint;
            this.spellAttack = Magician.this.spellAttack;
        }

        public void setCurrentKrit(int currentKrit) {
            this.currentKrit = currentKrit;
        }

        protected int calculate() {
            return spellAttack * currentKrit;
        }

        protected boolean isAttackRaised() {
            return currentKrit > 1;
        }
    }

    public void setManaPoint(int manaPoint) {
        this.manaPoint = manaPoint;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public int getSpellAttack() {
        return spellAttack;
    }

    public void setSpellAttack(int spellAttack) {
        this.spellAttack = spellAttack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magician magician = (Magician) o;
        return manaPoint == magician.manaPoint &&
                spellAttack == magician.spellAttack;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), manaPoint, spellAttack);
    }

    @Override
    public String toString() {
        return "Magician{" +
                "name='" + name + '\'' +
                ", manaPoint=" + manaPoint +
                ", attackSpell=" + spellAttack +
                ", attack=" + attack +
                ", health=" + health +
                '}';
    }
}
