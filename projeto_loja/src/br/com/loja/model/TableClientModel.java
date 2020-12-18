/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.model;

import java.util.List;

/**
 *
 * @author maico
 */
public class TableClientModel {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String cellPhone;
    private String Telephone;

    public TableClientModel(Long id, String name, String cpf, String email, String cellPhone, String Telephone) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.cellPhone = cellPhone;
        this.Telephone = Telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

}
