package br.com.loja.model;

import java.util.Date;

/**
 *
 * @author maico
 */
public abstract class PersonModel {

    private int id;
    private String name;
    private String cpf;
    private String rg;
    private Date dataOfBirth;
    private char gender;
    private AddressModel address;
    private ContactModel contact;

    public PersonModel() {
    }

    public PersonModel(String name, String cpf, String rg, Date dataOfBirth, char gender, AddressModel address, ContactModel contact) {
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.dataOfBirth = dataOfBirth;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getDataOfBirth() {
        return dataOfBirth;
    }

    public void setDataOfBirth(Date dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
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

}
