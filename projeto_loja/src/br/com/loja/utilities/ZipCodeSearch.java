/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.utilities;

import br.com.loja.model.AddressModel;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author maico
 */
public class ZipCodeSearch {

    private String street;
    private String complement;
    private String district;
    private String city;
    private String state;

    private AddressModel addressModel;

    public AddressModel buscarCep(String cep) {
        String json;

        try {
            URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));
            json = jsonSb.toString();

            // JOptionPane.showMessageDialog(null, json);
            json = json.replaceAll("[{},:]", "");
            json = json.replaceAll("\"", "\n");
            String array[] = new String[30];
            array = json.split("\n");

            // JOptionPane.showMessageDialog(null, array);
            street = array[7];
            complement = array[11];
            district = array[15];
            city = array[19];
            state = array[23];

            return addressModel = new AddressModel(street, complement, district, city, state);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
