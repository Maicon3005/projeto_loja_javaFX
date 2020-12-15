/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author maico
 */
@Entity(name="TBL_CONTACT")
@Table(name="TBL_CONTACT")
public class ContactModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PK_ID_CONTACT")
    private Long id;
    @Column(name="EMAIL")
    private String email;
    @Column(name="CELLPHONE")
    private String cellPhone;
    @Column(name="TELEPHONE")
    private String telephone;

    public ContactModel() {
    }

    public ContactModel(String email, String cellPhone, String telephone) {
        this.email = email;
        this.cellPhone = cellPhone;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "ContactModel{" + "id=" + id + ", email=" + email + ", cellPhone=" + cellPhone + ", telephone=" + telephone + '}';
    }

    
}
