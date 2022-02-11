package com.lerkin;

import java.util.List;
import java.util.Random;

public class Arena {
    private List<Warrior> warriors;
    private final Random RANDOM = new Random(System.currentTimeMillis());

    public Arena(List<Warrior> warriors) {
        this.warriors = warriors;
    }

    public Warrior startBattle() throws InterruptedException {
        System.out.println("Our gladiators: \n" + warriors);
        Thread.sleep(3000);
        System.out.println("Battle begins");
        Thread.sleep(1000);
        System.out.println("3");
        Thread.sleep(1000);
        System.out.println("2");
        Thread.sleep(1000);
        System.out.println("1");
        Thread.sleep(1000);
        System.out.println("FIGHT!!!!");


        int warriorsCount = warriors.size();
        if (warriorsCount > 1) {
            while (true) {
                int warriorNumber1 = RANDOM.nextInt(warriorsCount);
                int warriorNumber2 = RANDOM.nextInt(warriorsCount);

                Warrior warrior1 = warriors.get(warriorNumber1);
                Warrior warrior2 = warriors.get(warriorNumber2);


                boolean selfFighter = warrior1 == warrior2;

                Warrior winner = fight(warrior1, warrior2, selfFighter);

                if (winner == null) {
                    if (!selfFighter) {
                        warriorsCount -= 2;
                        System.out.println("Both are dead. Idiots....");
                    } else {
                        warriorsCount--;
                        System.out.println(warrior1.getName() + " kill himself...");
                    }
                } else {
                    String winnerName = winner.getName();
                    if (!selfFighter) {
                        warriorsCount--;
                        System.out.println(winnerName + " is a winner.");
                    } else {
                        System.out.println(winnerName + " damaged himself but still alive.");
                    }
                }

                if (warriorsCount == 1) {
                    if (selfFighter) {
                        for (Warrior warrior : warriors) {
                            if (warrior.isAlive()) {
                                winner = warrior;
                                break;
                            }
                        }
                    }
                    return winner;
                } else if (warriorsCount == 0) {
                    return null;
                }
            }

        } else {
            Warrior warrior = warriors.get(0);
            while (warrior.isAlive()) {
                warrior.attack(warrior);
            }
            return warrior;
        }
    }

    private Warrior fight(Warrior warrior1, Warrior warrior2, boolean isSelfFighter) throws InterruptedException {
        Warrior winner;
        if (!isSelfFighter) {
            warrior1.attack(warrior2);
            warrior2.attack(warrior1);
            Thread.sleep(3200);

            boolean firstDead = !warrior1.isAlive();
            boolean secondDead = !warrior2.isAlive();
            System.out.println();
            if (firstDead && !secondDead) {
                winner = warrior2;
            } else if (!firstDead && secondDead) {
                winner = warrior1;
            } else if (secondDead && firstDead) {
                winner = null;
            } else {
                winner = fight(warrior1, warrior2, false);
            }
        } else {
            warrior1.attack(warrior2);
            if (warrior1.isAlive()) {
                winner = warrior1;
            } else {
                winner = null;
            }
            System.out.println();
        }
        System.out.println();
        return winner;
    }
}

