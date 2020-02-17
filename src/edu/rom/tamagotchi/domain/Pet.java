package edu.rom.tamagotchi.domain;

import edu.rom.tamagotchi.config.Config;

public abstract class Pet {

    private String url;
    private String name;
    private int age;
    private int fullness;
    private boolean happy;
    private boolean dead;

    protected String MIN_AGE = Config.getProp("minAge");
    protected String MAX_AGE = Config.getProp("maxAge");
    protected String MIN_FULLNESS = Config.getProp("minFullness");
    protected String MAX_FULLNESS = Config.getProp("maxFullness");


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isHappy() {
        return happy;
    }

    public void setHappy(boolean happy) {
        this.happy = happy;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}