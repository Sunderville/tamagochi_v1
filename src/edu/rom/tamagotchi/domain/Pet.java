package edu.rom.tamagotchi.domain;

import edu.rom.tamagotchi.config.Config;

public abstract class Pet {

    protected volatile long bornMoment;
    protected volatile long shuttingDownMoment;
    protected volatile int age;
    protected volatile int fullness;
    protected volatile int happiness;
    protected volatile boolean isDead;
    protected volatile long deathMoment;

    protected final int MAX_AGE = Integer.parseInt(Config.getProp("maxAge"));
    protected final int MAX_FULLNESS = Integer.parseInt(Config.getProp("maxFullness"));
    protected final int MAX_HAPPINESS = Integer.parseInt(Config.getProp("maxHappiness"));
    protected final int AGING_STEP = Integer.parseInt(Config.getProp("ageIncrementSteps"));
    protected final int HUNGRINESS_STEP = Integer.parseInt(Config.getProp("hungrinessDecrementSteps"));
    protected final int FREEZE_AFTER_DEATH = Integer.parseInt(Config.getProp("freezeArterDeath"));

    protected Pet() {
    }

    Pet(long bornMoment, long dieMoment, int age, int fullness, int happiness, boolean isDead) {
        this.bornMoment = bornMoment;
        this.shuttingDownMoment = dieMoment;
        this.age = age;
        this.fullness = fullness;
        this.happiness = happiness;
        this.isDead = isDead;
    }

    public long getBornMoment() {
        return bornMoment;
    }

    public void setBornMoment(long bornMoment) {
        this.bornMoment = bornMoment;
    }

    public long getShuttingDownMoment() {
        return shuttingDownMoment;
    }

    public void setShuttingDownMoment(long shuttingDownMoment) {
        this.shuttingDownMoment = shuttingDownMoment;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFullness() {
        return fullness;
    }

    public void setFullness(int fullness) {
        this.fullness = fullness;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public long getDeathMoment() {
        return deathMoment;
    }

    public void setDeathMoment(long deathMoment) {
        this.deathMoment = deathMoment;
    }


    public int getMAX_AGE() {
        return MAX_AGE;
    }

    public int getMAX_FULLNESS() {
        return MAX_FULLNESS;
    }

    public int getMAX_HAPPINESS() {
        return MAX_HAPPINESS;
    }

    public int getAGING_STEP() {
        return AGING_STEP;
    }

    public int getHUNGRINESS_STEP() {
        return HUNGRINESS_STEP;
    }

    public int getFREEZE_AFTER_DEATH() {
        return FREEZE_AFTER_DEATH;
    }

    @Override
    public String toString() {
        return  bornMoment + "\n" +
                shuttingDownMoment + "\n" +
                age + "\n" +
                fullness + "\n" +
                happiness + "\n" +
                isDead + "\n" +
                deathMoment;
    }
}