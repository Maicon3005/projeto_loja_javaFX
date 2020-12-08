package br.com.loja.jdbc;

import javax.swing.JOptionPane;

/**
 *
 * @author maico
 */
public class TestaConexao {
    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com Sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro: "+erro);
        }
    }
}
