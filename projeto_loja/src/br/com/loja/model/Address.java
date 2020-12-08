package br.com.loja.model;

/**
 *
 * @author maico
 */
public class Address {

    private String zipCode;
    private String street;
    private String districty;
    private String city;
    private int number;
    private String state;
    private String complement;

    public Address() {
    }

    public Address(String zipCode, String street, String districty, String city, int number, String state, String complement) {
        this.zipCode = zipCode;
        this.street = street;
        this.districty = districty;
        this.city = city;
        this.number = number;
        this.state = state;
        this.complement = complement;
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

}
