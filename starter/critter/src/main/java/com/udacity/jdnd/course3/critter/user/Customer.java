package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer extends User {
    private String phoneNumber;
    private String notes;
    @OneToMany(targetEntity = Pet.class)
    Set<Pet> petIds;

    public void addPet(Pet pet) {
        petIds.add(pet);
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Pet> getPetIds() {
        return petIds;
    }

    public void setPetIds(Set<Pet> petIds) {
        this.petIds = petIds;
    }
}
