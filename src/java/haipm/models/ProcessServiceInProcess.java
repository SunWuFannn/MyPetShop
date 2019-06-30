/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipm.models;

import haipm.daos.ServiceProcessDAO;
import haipm.dtos.ServiceProcessDTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 99hai
 */
public class ProcessServiceInProcess implements Serializable {

    private ServiceProcessDTO serviceProcess;
    private int idProcess;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProcessServiceInProcess() {
    }

    public int getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(int idProcess) {
        this.idProcess = idProcess;
    }

    public ServiceProcessDTO getServiceProcess() {
        return serviceProcess;
    }

    public void setServiceProcess(ServiceProcessDTO serviceProcess) {
        this.serviceProcess = serviceProcess;
    }

    //add
    public boolean addServiceProcess() throws Exception {
        ServiceProcessDAO dao = new ServiceProcessDAO();
        return dao.addServiceProcess(serviceProcess);
    }

    //get List
    public List<ServiceProcessDTO> getAllProcess() throws Exception {
        ServiceProcessDAO dao = new ServiceProcessDAO();
        return dao.getAllProcessService();
    }

    //finish
    public boolean finishedProcess() throws Exception {
        ServiceProcessDAO dao = new ServiceProcessDAO();
        return dao.finishedProcess(idProcess);
    }

    //get Finished Process;
    public List<ServiceProcessDTO> getFinishedProcess() throws Exception {
        ServiceProcessDAO dao = new ServiceProcessDAO();
        return dao.getFinishedProcess();
    }
    
    //get service of user
    
    public List<ServiceProcessDTO> getServiceOfUSer() throws Exception{
        ServiceProcessDAO dao = new ServiceProcessDAO();
        return dao.getServiceOfUSer(username);
    }

}
