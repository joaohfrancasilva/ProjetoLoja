package ProjetoLoja;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjetoLoja{
    public static ArrayList<HashMap> lista = new ArrayList<>();
 


    public static void adicionar(){
        String nome = "";
        String precoStr = "";
        try {
            nome = JOptionPane.showInputDialog(null, "Digite o nome do produto a ser adicionado: ");
            if (nome.isBlank()){
                JOptionPane.showMessageDialog(null, "O produto precisa ter um nome.");
                return;
            }
            else {
                JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
            }
        }
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "O produto precisa ter um nome.");
            return;
        }
        try{
            precoStr = JOptionPane.showInputDialog(null, "Digite o preço do produto: ");
            double preco = Double.parseDouble(precoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor Inválido.");
        }
        HashMap<String, String> produto = new HashMap<>();
        produto.put("Nome do produto", nome);
        produto.put("Preço do produto", precoStr);
        produto.put("Estoque dos produtos", "0");
        lista.add(produto);
    }



    public static void main(String[] args){
        int opcao = 0;
        do{
            Runnable[] funcoes = {
                    ProjetoLoja::adicionar,
            };
            String[] opcoes = {"Adicionar"};
            do{
                try {
                    String opcaoSTR = (String) JOptionPane.showInputDialog(null, "Escolha algo", "Cursos disponíveis", 1, null, opcoes, opcoes[0]);
                    for (int i = 0; i < opcoes.length; i++) {
                        if (opcaoSTR.equals(opcoes[i])){
                            opcao = i + 1;
                        }
                    }
                }
                catch (HeadlessException e) {
                    JOptionPane.showMessageDialog(null, "teste");
                }
                catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, opcao);
                    JOptionPane.showMessageDialog(null, "Você precisa digitar um número.");
                    opcao = 0;
                }
            } while (opcao < 1 || opcao > 7);
            if(opcao != 7) {
                funcoes[opcao - 1].run();
            }
        }while(opcao != 7);
    }
}