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
public class Client extends Pessoa{

    public Client() {
    }

    public Client(String name, String cpf, String rg, Date dataOfBirth, String cellPhone, String telephone, char gender, Address address) {
        super(name, cpf, rg, dataOfBirth, cellPhone, telephone, gender, address);
    }
    
}
