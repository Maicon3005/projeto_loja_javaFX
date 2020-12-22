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
public class TableProviderModel {

    private Long id;
    private String fantasyName;
    private String cnpj;
    private String email;
    private String cellPhone;
    private String Telephone;

    public TableProviderModel(Long id, String fantasyName, String cnpj, String email, String cellPhone, String Telephone) {
        this.id = id;
        this.fantasyName = fantasyName;
        this.cnpj = cnpj;
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

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    @Override
    public String toString() {
        return "TableProviderModel{" + "id=" + id + ", fantasyName=" + fantasyName + ", cnpj=" + cnpj + ", email=" + email + ", cellPhone=" + cellPhone + ", Telephone=" + Telephone + '}';
    }

}
