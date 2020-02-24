package edu.rom.tamagotchi.domain;

public class Duck extends Pet {

    public Duck() {
    }

    public Duck(long bornMoment, long dieMoment, int age, int fullness, int happiness, boolean dead) {
        super(bornMoment, dieMoment, age, fullness, happiness, dead);
    }

    @Override
    public String toString() {
        return "Duck" + "\n" +
                bornMoment + "\n" +
                shuttingDownMoment + "\n" +
                age + "\n" +
                fullness + "\n" +
                happiness + "\n" +
                isDead + "\n" +
                deathMoment;
    }
}
