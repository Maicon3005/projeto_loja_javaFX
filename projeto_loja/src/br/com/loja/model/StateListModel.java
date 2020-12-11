package br.com.loja.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maico
 */
public final class StateListModel {

    private static StateListModel Instance;
    private List<String> listStates = null;

    public static StateListModel getInstance() {
        if (Instance == null) {
            Instance = new StateListModel();
        }

        return Instance;
    }

    private StateListModel() {
        listStates = new ArrayList<String>();
        this.listStates.add("AC");
        this.listStates.add("AL");
        this.listStates.add("AP");
        this.listStates.add("AM");
        this.listStates.add("BA");
        this.listStates.add("CE");
        this.listStates.add("DF");
        this.listStates.add("ES");
        this.listStates.add("GO");
        this.listStates.add("MA");
        this.listStates.add("MT");
        this.listStates.add("MS");
        this.listStates.add("MG");
        this.listStates.add("PA");
        this.listStates.add("PB");
        this.listStates.add("PR");
        this.listStates.add("PE");
        this.listStates.add("PI");
        this.listStates.add("RJ");
        this.listStates.add("RN");
        this.listStates.add("RS");
        this.listStates.add("RO");
        this.listStates.add("RR");
        this.listStates.add("SC");
        this.listStates.add("SP");
        this.listStates.add("SE");
        this.listStates.add("TO");
    }

    public List<String> getAllStates() {
        return this.listStates;
    }
}
