/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.dtos;

import java.io.Serializable;

/**
 *
 * @author 99hai
 */
public class PetDTO implements Serializable {

    private String petID, petName, petType, petImage;
    private boolean isActive;
    private int petAge;
    private String owner;

    public PetDTO(String petID, String petName, String petType, String petImage, boolean isActive, int petAge) {
        this.petID = petID;
        this.petName = petName;
        this.petType = petType;
        this.petImage = petImage;
        this.isActive = isActive;
        this.petAge = petAge;
    }

    public PetDTO(String petID, String petName, String petImage, int petAge) {
        this.petID = petID;
        this.petName = petName;
        this.petImage = petImage;
        this.petAge = petAge;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetImage() {
        return petImage;
    }

    public void setPetImage(String petImage) {
        this.petImage = petImage;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }

}
