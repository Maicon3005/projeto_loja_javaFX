/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author maico
 */
@Entity(name = "TBL_CLIENT")
@Table(name = "TBL_CLIENT")
public class ClientModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ID_CLIENT")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "RG")
    private String rg;
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;
    @Column(name = "GENDER")
    private char gender;
    @OneToOne(cascade = CascadeType.ALL)
    private AddressModel address;
    @OneToOne(cascade = CascadeType.ALL)
    private ContactModel contact;

    public ClientModel() {
    }

    public ClientModel(Long id, String name, String cpf, String rg, LocalDate dateOfBirth, char gender, AddressModel address, ContactModel contact) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
    }

    public ClientModel(String name, String cpf, String rg, LocalDate dateOfBirth, char gender, AddressModel address, ContactModel contact) {
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public ContactModel getContact() {
        return contact;
    }

    public void setContact(ContactModel contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "PersonModel{" + "id=" + id + ", name=" + name + ", cpf=" + cpf + ", rg=" + rg + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", address=" + address + ", contact=" + contact + '}';
    }

}
