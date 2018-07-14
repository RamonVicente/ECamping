/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.beans;

import com.ecamping.entidade.Camping;
import com.ecamping.entidade.User;
import com.ecamping.service.CampingService;
import com.ecamping.service.UserService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ramon
 */
@ManagedBean(name = "listaCampistas")
@SessionScoped
public class ListaCampistaBean {
    
    private List<User> campistas;
    private User campista;
    private User campistaAtual;
    @EJB
    UserService userService;
    
    @PostConstruct
    public void iniciar() {
        campistaAtual  = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("campistaAtual");
    }

    public List<User> getCampistas() {
        campistas = userService.getAllUsers();
        return campistas;
    }
    
    public void removeCampista(User user){
        this.userService.delete(user);
        addMessage("Campista removido com sucesso!");
    }
    
    public String redirectToProfile(User campista){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("campistaAtual", campista);
        return "perfilCampista?faces-redirect=true";
    }
    
    public void editProfile(User campista){
        this.userService.update(campista);
        addMessage("Campista editado com sucesso!");
    }
    
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public User getCampista() {
        return campista;
    }

    public void setCampista(User campista) {
        this.campista = campista;
    }

    public User getCampistaAtual() {
        return campistaAtual;
    }

    public void setCampistaAtual(User campistaAtual) {
        this.campistaAtual = campistaAtual;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    
    
}
