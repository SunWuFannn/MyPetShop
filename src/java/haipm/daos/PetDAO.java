/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.daos;

import haipm.db.MyConnection;
import haipm.dtos.PetDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 99hai
 */
public class PetDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public PetDAO() {
    }

    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<PetDTO> getAllPet() throws Exception {
        String petID, petName, petType, petImage;
        int petAge;
        boolean isActive;
        List<PetDTO> mylist = null;
        PetDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select PetID,PetName,PetType,PetAge,Image,isActive from tbl_Pet";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            mylist = new ArrayList<PetDTO>();
            while (rs.next()) {
                petID = rs.getString("PetID");
                petName = rs.getString("PetName");
                petType = rs.getString("PetType");
                petImage = rs.getString("Image");
                petAge = rs.getInt("PetAge");
                isActive = rs.getString("isActive").equals("1") ? true : false;
                dto = new PetDTO(petID, petName, petType, petImage, isActive, petAge);
                mylist.add(dto);
            }
        } finally {
            closeConnection();
        }
        return mylist;
    }

    public boolean addPet(PetDTO pet) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Insert into tbl_Pet(PetID,PetName,PetAge,PetType,Image,isActive) "
                    + " values(?,?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, pet.getPetID());
            preStm.setString(2, pet.getPetName());
            preStm.setInt(3, pet.getPetAge());
            preStm.setString(4, pet.getPetType());
            preStm.setString(5, pet.getPetImage());
            preStm.setString(6, pet.isIsActive() ? "1" : "0");
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    
    //add pet to user
    
    public boolean addToUser(PetDTO pet , String username) throws Exception{
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Insert into tbl_UserPet(Username,PetID) values(?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, pet.getPetID());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        
        return check;
    }
    
    public boolean updatePet(PetDTO pet) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_Pet set PetName= ? , PetAge = ? , Image = ? where PetID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, pet.getPetName());
            preStm.setInt(2, pet.getPetAge());
            preStm.setString(3, pet.getPetImage());
            preStm.setString(4, pet.getPetID());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean deletePet(String petID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update tbl_Pet set isActive = ? where PetID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "0");
            preStm.setString(2, petID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public PetDTO searchPetByID(String petID) throws Exception {
        PetDTO pet = null;
        String petName,petImage,petType;
        int petAge;
        boolean isActive;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select PetID, PetName, PetAge, Image, PetType, isActive from tbl_Pet  "
                    + " where PetID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, petID);
            rs = preStm.executeQuery();
            if(rs.next()){
                petName = rs.getString("PetName");
                petAge = rs.getInt("PetAge");
                petImage = rs.getString("Image");
                petType = rs.getString("PetType");
                isActive = rs.getString("isActive").equals("1") ? true : false;
                pet = new PetDTO(petID, petName, petType, petImage, isActive, petAge);
            }
        } finally {
            closeConnection();
        }

        return pet;
    }
    
    public List<PetDTO> searchPetByName(String name) throws Exception{
        List<PetDTO> listPet = null;
        String petID,petName,petImage,petType;
        int petAge;
        boolean isActive;
        PetDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select PetID , PetName, PetAge,PetType,Image,isActive from tbl_Pet where PetName like ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%"+name+"%");
            rs = preStm.executeQuery();
            listPet = new ArrayList<PetDTO>();
            while(rs.next()){
                petID = rs.getString("PetID");
                petName = rs.getString("PetName");
                petType = rs.getString("PetType");
                petAge = rs.getInt("PetAge");
                petImage = rs.getString("Image");
                isActive = rs.getString("isActive").equals("1") ? true : false;
                dto = new PetDTO(petID, petName, petType, petImage, isActive, petAge);
                listPet.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listPet;
    }
    
    public List<String> findPetOfUser(String username) throws Exception{
        List<String> listId = null;
        String id;
        try {
            conn = MyConnection.getConnection();
            String sql = "Select PetID from tbl_UserPet where Username = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            listId = new ArrayList<String>();
            while(rs.next()){
                id = rs.getString("PetID");
                listId.add(id);
            }
        } finally {
            closeConnection();
        }
        
        return listId;
    }
    
    public List<PetDTO> getListPetOfUser(List<String> listID) throws Exception{
        List<PetDTO> mylist = null;
        PetDTO pet = null;
        try {
            mylist = new ArrayList<PetDTO>();
            for (String petID : listID) {
                pet = searchPetByID(petID);
                mylist.add(pet);
            }
        }finally{
            
        } 
        return mylist;
    }
    
    public String findOwner(String petID) throws Exception{
        String owner = "Falied";
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Username from tbl_UserPet where PetID = ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, petID);
            rs = preStm.executeQuery();
            if(rs.next()){
                owner = rs.getString("Username");
            }
        } finally {
            closeConnection();
        }
        return owner;
    }
}
