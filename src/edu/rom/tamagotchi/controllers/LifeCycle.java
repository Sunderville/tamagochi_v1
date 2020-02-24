package edu.rom.tamagotchi.controllers;

import edu.rom.tamagotchi.domain.Duck;
import edu.rom.tamagotchi.domain.Homer;
import edu.rom.tamagotchi.domain.Lion;
import edu.rom.tamagotchi.domain.Pet;
import edu.rom.tamagotchi.domain.Terminator;

import java.io.*;

class LifeCycle {

    Pet loadPet() {
        File saveFile = new File("save.txt");
        Pet pet;
        if (saveFile.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(saveFile))) {
                String className = bufferedReader.readLine();
                pet = createPetForClassName(className);
                pet.setBornMoment(Long.parseLong(bufferedReader.readLine()));
                pet.setShuttingDownMoment(Long.parseLong(bufferedReader.readLine()));
                pet.setAge(Integer.parseInt(bufferedReader.readLine()));
                pet.setFullness(Integer.parseInt(bufferedReader.readLine()));
                pet.setHappiness(Integer.parseInt(bufferedReader.readLine()));
                pet.setIsDead(Boolean.parseBoolean(bufferedReader.readLine()));
                pet.setDeathMoment(Long.parseLong(bufferedReader.readLine()));
                return pet;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The save file is absent.");
        }
        return new Pet() {
        };
    }

    void savePet(Pet pet) {
        File saveFile = new File("save.txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(saveFile))){
            bufferedWriter.write(pet.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void deletingSaveFile() {
        File saveFile = new File("save.txt");
        if (saveFile.exists()) {
            if (saveFile.delete()) {
                System.out.println("Save file is successfully deleted.");
            } else {
                System.out.println("Save file deleting has failed.");
            }
        } else {
            System.out.println("There is no any save file.");
        }
    }

    long calculateTheoreticalDeathTime(Pet pet) {
        if (pet.getDeathMoment() == 0) {
            pet.setAge((int) (pet.getAge() + (System.currentTimeMillis() - pet.getShuttingDownMoment()) / (pet.getAGING_STEP()*1000)));
            pet.setFullness((int) (pet.getFullness() - (System.currentTimeMillis() - pet.getShuttingDownMoment()) / (pet.getHUNGRINESS_STEP()*1000)));
            pet.setHappiness((int) (pet.getHappiness() - (System.currentTimeMillis() - pet.getShuttingDownMoment()) / (pet.getHUNGRINESS_STEP()*1000)));
            long ageDeathInTheory = pet.getShuttingDownMoment() + ((pet.getMAX_AGE() - pet.getAge()) * (pet.getAGING_STEP() * 1000));
            long hungryDeathInTheory = pet.getShuttingDownMoment() + (pet.getFullness() * (pet.getHUNGRINESS_STEP() * 1000));
            long sadDeathInTheory = pet.getShuttingDownMoment() + (pet.getHappiness() * (pet.getHUNGRINESS_STEP() * 1000));
            long temp = ageDeathInTheory < hungryDeathInTheory ? ageDeathInTheory : hungryDeathInTheory;
            long theoreticalDeathMoment = temp < sadDeathInTheory ? temp : sadDeathInTheory;
            return theoreticalDeathMoment;
        } else {
            return pet.getDeathMoment();
        }
    }

    private Pet createPetForClassName(String className) {
        Pet pet;
        switch (className) {
            case "Lion":
                pet = new Lion();
                break;
            case "Duck":
                pet = new Duck();
                break;
            case "Homer":
                pet = new Homer();
                break;
            default:
                pet = new Terminator();
                break;
        }
        return pet;
    }

}
