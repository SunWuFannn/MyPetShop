/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.models;

import haipm.daos.PetDAO;
import haipm.dtos.PetDTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 99hai
 */
public class ProcessPet implements Serializable {

    private PetDTO pet;
    private String petID;
    private String nameSearch;
    private String username;
    private List<String> listID;
    public ProcessPet() {
    }

    public PetDTO getPet() {
        return pet;
    }

    public void setPet(PetDTO pet) {
        this.pet = pet;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getListID() {
        return listID;
    }

    public void setListID(List<String> listID) {
        this.listID = listID;
    }
    
    
    //add
    public boolean addPet() throws Exception {
        PetDAO dao = new PetDAO();
        return dao.addPet(pet);
    }

    public boolean addToUser() throws Exception{
        PetDAO dao = new PetDAO();
        return dao.addToUser(pet, username);
    }
    //delete
    public boolean deletePet() throws Exception {
        PetDAO dao = new PetDAO();
        return dao.deletePet(petID);
    }
    
    //update
    public boolean updatePet() throws Exception{
        PetDAO dao = new PetDAO();
        return dao.updatePet(pet);
    }
    
    //getAll
    
    public List<PetDTO> getAllPet() throws Exception{
        PetDAO dao = new PetDAO();
        return dao.getAllPet();
    }
    
    //search pet by name
    
    public List<PetDTO> searchPetByName() throws Exception{
        PetDAO dao = new PetDAO();
        return dao.searchPetByName(nameSearch);
    }
    
    
    //search by key
    
    public PetDTO finByPK() throws Exception{
        PetDAO dao = new PetDAO();
        return dao.searchPetByID(petID);
    }
    
    // get list id pet of 1 user
    public List<String> findListIdPet() throws Exception{
        PetDAO dao = new PetDAO();
        return dao.findPetOfUser(username);
    }
    
    //get list pet of 1 user
    public List<PetDTO> getListPetOfUSer() throws Exception{
        PetDAO dao = new PetDAO();
        return dao.getListPetOfUser(listID);
    }
}
