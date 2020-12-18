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
@Entity(name="TBL_ADDRESS")
@Table(name="TBL_ADDRESS")
public class AddressModel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PK_ID_ADDRESS")
    private Long id;
    @Column(name="ZIPCODE")
    private String zipCode;
    @Column(name="STREET")
    private String street;
    @Column(name="DISTRICTY")
    private String districty;
    @Column(name="CITY")
    private String city;
    @Column(name="NUMBER")
    private int number;
    @Column(name="STATE_")
    private String state;
    @Column(name="COMPLEMENT")
    private String complement;

    public AddressModel() {
    }

    public AddressModel(Long id, String zipCode, String street, String districty, String city, int number, String state, String complement) {
        this.id = id;
        this.zipCode = zipCode;
        this.street = street;
        this.districty = districty;
        this.city = city;
        this.number = number;
        this.state = state;
        this.complement = complement;
    }

    public AddressModel(String zipCode, String street, String districty, String city, int number, String state, String complement) {
        this.zipCode = zipCode;
        this.street = street;
        this.districty = districty;
        this.city = city;
        this.number = number;
        this.state = state;
        this.complement = complement;
    }

    public AddressModel(String street, String complement, String districty, String city, String state) {
        this.street = street;
        this.complement = complement;
        this.districty = districty;
        this.city = city;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistricty() {
        return districty;
    }

    public void setDistricty(String districty) {
        this.districty = districty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @Override
    public String toString() {
        return "AddressModel{" + "id=" + id + ", zipCode=" + zipCode + ", street=" + street + ", districty=" + districty + ", city=" + city + ", number=" + number + ", state=" + state + ", complement=" + complement + '}';
    }
}
