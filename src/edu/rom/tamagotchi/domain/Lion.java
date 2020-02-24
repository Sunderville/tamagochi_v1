package edu.rom.tamagotchi.domain;

public class Lion extends Pet {

    public Lion() {
    }

    public Lion(long bornMoment, long dieMoment, int age, int fullness, int happiness, boolean dead) {
        super(bornMoment, dieMoment, age, fullness, happiness, dead);
    }

    @Override
    public String toString() {
        return "Lion" + "\n" +
                bornMoment + "\n" +
                shuttingDownMoment + "\n" +
                age + "\n" +
                fullness + "\n" +
                happiness + "\n" +
                isDead + "\n" +
                deathMoment;
    }

}
