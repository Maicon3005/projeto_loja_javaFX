/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author maico
 */
@Entity(name = "TBL_PROVIDER")
@Table(name = "TBL_PROVIDER")
public class ProviderModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ID_PROVIDER")
    private Long id;
    @Column(name = "FANTASY_NAME")
    private String fantasyName;
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Column(name = "CNPJ")
    private String cnpj;
    @OneToOne(cascade = CascadeType.ALL)
    private AddressModel address;
    @OneToOne(cascade = CascadeType.ALL)
    private ContactModel contact;

    public ProviderModel() {
    }

    public ProviderModel(Long id, String fantasyName, String companyName, String cnpj, AddressModel address, ContactModel contact) {
        this.id = id;
        this.fantasyName = fantasyName;
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.address = address;
        this.contact = contact;
    }

    public ProviderModel(String fantasyName, String companyName, String cnpj, AddressModel address, ContactModel contact) {
        this.fantasyName = fantasyName;
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.address = address;
        this.contact = contact;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
        return "ProviderModel{" + "id=" + id + ", fantasyName=" + fantasyName + ", companyName=" + companyName + ", cnpj=" + cnpj + ", address=" + address + ", contact=" + contact + '}';
    }

}
