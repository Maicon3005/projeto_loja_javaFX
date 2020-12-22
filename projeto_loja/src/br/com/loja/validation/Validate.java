/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author maico
 */
public class Validate {

    private static Validate instance;
    private Pattern pattern;
    private Matcher matcher;
    private final String borderColorRed = "-fx-border-color: #FF0000";
    private final String borderColorGray = "-fx-border-color: #C2C0C0;";

    public static Validate getInstance() {
        if (instance == null) {
            instance = new Validate();
        }
        return instance;
    }

    private Validate() {

    }

    //name validator
    public boolean validateName(TextField textField) {
        pattern = Pattern.compile("^([A-Z]|[a-z]| ){3,}$");
        matcher = pattern.matcher(textField.getText());
        boolean check = matcher.find();
        if (check) {
            textField.setStyle(borderColorGray);
            return check;
        }
        textField.setStyle(borderColorRed);
        return check;
    }

    public boolean validateName(String name) {
        pattern = Pattern.compile("^([A-Z]|[a-z]| ){3,}$");
        matcher = pattern.matcher(name);
        return matcher.find();
    }

    //name validator
    public boolean validateEmptyField(TextField textField) {
        pattern = Pattern.compile("^[\\S]+$");
        matcher = pattern.matcher(textField.getText());
        boolean check = matcher.find();
        if (check) {
            textField.setStyle(borderColorGray);
            return check;
        }
        textField.setStyle(borderColorRed);
        return check;
    }

    public boolean validateEmptyField(String value) {
        pattern = Pattern.compile("^[\\S]+$");
        matcher = pattern.matcher(value);
        return !matcher.find();
    }

    //date validator
    public boolean validateDate(DatePicker datePicker) {
        pattern = Pattern.compile("^((20[0-9][0-9])|(19[0-9][0-9])){1}-((0[1-9]|1[0-2])){1}-((0[1-9]|[1-2][0-9])|3[0-1])$");
        matcher = pattern.matcher(String.valueOf(datePicker.getValue()));
        boolean check = matcher.find();
        if (check) {
            datePicker.setStyle(borderColorGray);
            return check;
        }
        datePicker.setStyle(borderColorRed);
        return check;
    }

    public boolean validateDate(String date) {
        pattern = Pattern.compile("^((20[0-9][0-9])|(19[0-9][0-9])){1}-((0[1-9]|1[0-2])){1}-((0[1-9]|[1-2][0-9])|3[0-1])$");
        matcher = pattern.matcher(date);
        return matcher.find();
    }

    //number validator
    public boolean validateNumber(TextField textField) {
        pattern = Pattern.compile("^[\\d]+$");
        matcher = pattern.matcher(textField.getText());
        boolean check = matcher.find();
        if (check) {
            textField.setStyle(borderColorGray);
            return check;
        }
        textField.setStyle(borderColorRed);
        return check;
    }

    public boolean validateNumber(String number) {
        pattern = Pattern.compile("^[\\d]+$");
        matcher = pattern.matcher(number);
        return matcher.find();
    }

    //cpf validator
    public boolean validateCpf(TextField textField) {
        pattern = Pattern.compile("(\\d{3}).(\\d{3}).(\\d{3})-(\\d{2})");
        matcher = pattern.matcher(textField.getText());
        boolean check = matcher.find();
        if (check) {
            textField.setStyle(borderColorGray);
            return check;
        }
        textField.setStyle(borderColorRed);
        return check;
    }

    public boolean validateCpf(String cpf) {
        pattern = Pattern.compile("(\\d{3}).(\\d{3}).(\\d{3})-(\\d{2})");
        matcher = pattern.matcher(cpf);
        return matcher.find();
    }

    //cnpj validator
    public boolean validateCnpj(TextField textField) {
        pattern = Pattern.compile("^\\d{2}.\\d{3}.\\d{3}\\/\\d{4}-\\d{2}$");
        matcher = pattern.matcher(textField.getText());
        boolean check = matcher.find();
        if (check) {
            textField.setStyle(borderColorGray);
            return check;
        }
        textField.setStyle(borderColorRed);
        return check;
    }

    public boolean validateCnpj(String cpf) {
        pattern = Pattern.compile("^\\d{2}.\\d{3}.\\d{3}\\/\\d{4}-\\d{2}$");
        matcher = pattern.matcher(cpf);
        return matcher.find();
    }

    //rg validator
    public boolean validateRg(TextField textField) {
        pattern = Pattern.compile("(\\d{2}).(\\d{3}).(\\d{3})-(\\d{1})");
        matcher = pattern.matcher(textField.getText());
        boolean check = matcher.find();
        if (check) {
            textField.setStyle(borderColorGray);
            return check;
        }
        textField.setStyle(borderColorRed);
        return check;
    }

    public boolean validateRg(String rg) {
        pattern = Pattern.compile("(\\d{2}).(\\d{3}).(\\d{3})-(\\d{1})");
        matcher = pattern.matcher(rg);
        return matcher.find();
    }

    //email validator
    public boolean validateEmail(TextField textField) {
        pattern = Pattern.compile("^([a-z]|[\\d]|[._]){3,}@[\\w]{3,}.com(.br)?$");
        matcher = pattern.matcher(textField.getText());
        boolean check = matcher.find();
        if (check) {
            textField.setStyle(borderColorGray);
            return check;
        }
        textField.setStyle(borderColorRed);
        return check;
    }

    public boolean validateEmail(String email) {
        pattern = Pattern.compile("^([a-z]|[\\d]|[._]){3,}@[\\w]{3,}.com(.br)?$");
        matcher = pattern.matcher(email);
        return matcher.find();
    }

    //cellphone validator
    public boolean validateCellPhone(TextField textField) {
        pattern = Pattern.compile("^\\(\\d{2}\\)\\d{5}\\-\\d{4}$");
        matcher = pattern.matcher(textField.getText());
        boolean check = matcher.find();
        if (check) {
            textField.setStyle(borderColorGray);
            return check;
        }
        textField.setStyle(borderColorRed);
        return check;
    }

    public boolean validateCellPhone(String cellPhone) {
        pattern = Pattern.compile("^\\(\\d{2}\\)\\d{5}\\-\\d{4}$");
        matcher = pattern.matcher(cellPhone);
        return matcher.find();
    }

    //telephone validator
    public boolean validateTelephone(TextField textField) {
        pattern = Pattern.compile("^\\(\\d{2}\\)\\d{4}-\\d{4}$");
        matcher = pattern.matcher(textField.getText());
        boolean check = matcher.find();
        if (check) {
            textField.setStyle(borderColorGray);
            return check;
        }
        textField.setStyle(borderColorRed);
        return check;
    }

    public boolean validateTelephone(String telephone) {
        pattern = Pattern.compile("^\\(\\d{2}\\)\\d{4}-\\d{4}$");
        matcher = pattern.matcher(telephone);
        return matcher.find();
    }

    //zipcode validator
    public boolean validateZipCode(TextField textField) {
        pattern = Pattern.compile("^\\d{5}-\\d{3}$");
        matcher = pattern.matcher(textField.getText());
        boolean check = matcher.find();
        if (check) {
            textField.setStyle(borderColorGray);
            return check;
        }
        textField.setStyle(borderColorRed);
        return check;
    }

    public boolean validateZipCode(String cep) {
        pattern = Pattern.compile("^\\d{5}-\\d{3}$");
        matcher = pattern.matcher(cep);
        return matcher.find();
    }

    //text validator
    public boolean validateText(TextField textField) {
        pattern = Pattern.compile("^([A-Z]|[a-z]|[0-9]| ){3,}");
        matcher = pattern.matcher(textField.getText());
        boolean check = matcher.find();
        if (check) {
            textField.setStyle(borderColorGray);
            return check;
        }
        textField.setStyle(borderColorRed);
        return check;
    }

    public boolean validateText(String text) {
        pattern = Pattern.compile("^([A-Z]|[a-z]|[0-9]| ){3,}");
        matcher = pattern.matcher(text);
        return matcher.find();
    }
}
