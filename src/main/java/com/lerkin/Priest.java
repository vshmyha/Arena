package com.lerkin;

import java.util.Objects;

public class Priest extends com.lerkin.Magician {
    protected int recoveryPercent;

    public Priest(int attack, int health, String name, int manaPoint, int spellAttack, int recoveryPercent) {
        super(attack, health, name, manaPoint, spellAttack);
        this.recoveryPercent = recoveryPercent;
    }

    public Priest() {
    }

   @Override
   protected int calculateAttack() {
       int attack;
       int minManaPoint = 2;
       int maxManaPoint = 5;
       int halfHealth = health * 50 / 100;

       if (health > halfHealth) {

           if (tryUseMana(minManaPoint)) {
               attack = this.spellAttack;

           } else {
               attack = this.attack;
           }
       } else {
           if (tryUseMana(maxManaPoint)) {
               treatment();
               attack = 0;
           } else {
               if (tryUseMana(minManaPoint)) {
                   attack = this.spellAttack;
               } else {
                   attack = this.attack;
               }
           }
       }
       return  attack;
   }

   public boolean tryUseMana(int manaPoint) {
       if (magicAttackPossible(manaPoint)) {
           this.manaPoint = this.manaPoint - manaPoint;
           return true;
       } else {
           return false;
       }
   }

    public void treatment() {
        health = health + recoveryPercent;
    }

    public int getRecoveryPercent() {
        return recoveryPercent;
    }

    public void setRecoveryPercent(int recoveryPercent) {
        this.recoveryPercent = this.health * recoveryPercent / 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Priest priest = (Priest) o;
        return recoveryPercent == priest.recoveryPercent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), recoveryPercent);
    }

    @Override
    public String toString() {
        return "Priest{" +
                "name='" + name + '\'' +
                ", recoveryPercent=" + recoveryPercent +
                ", manaPoint=" + manaPoint +
                ", spellAttack=" + spellAttack +
                ", attack=" + attack +
                ", health=" + health +
                '}';
    }
}
