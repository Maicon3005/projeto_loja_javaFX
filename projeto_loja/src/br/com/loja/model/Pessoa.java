package br.com.loja.model;

import java.util.Date;

/**
 *
 * @author maico
 */
public abstract class Pessoa {

    private String name;
    private String cpf;
    private String rg;
    private Date dataOfBirth;
    private String email;
    private String cellPhone;
    private String telephone;
    private char gender;
    private Address address;

    public Pessoa() {
    }

    public Pessoa(String name, String cpf, String rg, Date dataOfBirth, String cellPhone, String telephone, char gender,Address address) {
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.dataOfBirth = dataOfBirth;
        this.cellPhone = cellPhone;
        this.telephone = telephone;
        this.gender = gender;
        this.address = address;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Address getEndereco() {
        return address;
    }

    public void setEndereco(Address address) {
        this.address = address;
    }

    
}
