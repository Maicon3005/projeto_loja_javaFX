package br.com.loja.model;

/**
 *
 * @author maico
 */
public class AddressModel {

    private int id;
    private String zipCode;
    private String street;
    private String districty;
    private String city;
    private int number;
    private String state;
    private String complement;

    public AddressModel() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
