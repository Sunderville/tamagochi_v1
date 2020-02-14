package edu.rom.tamagotchi.domain;

import edu.rom.tamagotchi.controllers.StarterScreenController;

import java.io.*;

public class Lifecycle {





    public void savePet(Pet pet) {

        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(
                             new FileOutputStream("save.ser"))) {

            objectOutputStream.writeObject(pet);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pet loadPet() {

        File saveFile = new File("save.ser");

        if (saveFile.exists()) {
            try (ObjectInputStream objectInputStream =
                         new ObjectInputStream(
                                 new FileInputStream("save.ser"))) {

                return (Pet) objectInputStream.readObject();

            } catch (IOException e) {
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Pet createNewPet(Pet pet) {
        if (pet instanceof Lion) {
            pet = new Lion();
        } else if (pet instanceof Duck) {
            pet = new Duck();
        } else if (pet instanceof Homer) {
            pet = new Homer();
        } else if (pet instanceof Terminator) {
            pet = new Terminator();
        }
        return pet;
    }
}


