/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.model;

import java.util.Date;

/**
 *
 * @author maico
 */
public class ClientModel extends PersonModel{

    public ClientModel() {
    }

    public ClientModel(String name, String cpf, String rg, Date dataOfBirth, char gender, AddressModel address, ContactModel contact) {
        super(name, cpf, rg, dataOfBirth, gender, address, contact);
    }

    
    
}
