package ProjetoLoja;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjetoLoja{
    public static ArrayList<HashMap> lista = new ArrayList<>();


    public static boolean vazio(){
        if(lista.isEmpty()){
            JOptionPane.showMessageDialog(null, "A lista está vazia.");
            return true;
        }
        return false;
    }


    public static void adicionar(){
        String nome = "";
        double preco = 0;

        try {
            nome = JOptionPane.showInputDialog(null, "Digite o nome do produto a ser adicionado: ");
            if (nome.isBlank()){
                JOptionPane.showMessageDialog(null, "O produto precisa ter um nome.");
                return;
            }
        }

        catch (NullPointerException e){
            return;
        }

        try{
            String precoStr = JOptionPane.showInputDialog(null, "Digite o preço do produto: ");
            preco = Double.parseDouble(precoStr);
            HashMap<Object, Object> produto = new HashMap<>();
            produto.put("Nome", nome);
            produto.put("Preço", preco);
            produto.put("Quantidade no estoque", 0);
            produto.put("ID", (lista.size() + 1));
            lista.add(produto);
            JOptionPane.showMessageDialog(null, "Produto adicionado!");
        }

        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor Inválido.");
        }
    }


    public static void listar(){
        if(!vazio()){
            for (int i = 0; i < lista.size(); i++) {
                HashMap<Object, Object> item = lista.get(i);
                JOptionPane.showMessageDialog(null,
                        "ID: " + item.get("ID") + "\n" +
                                "Nome: " + item.get("Nome") + "\n" +
                                "Preço: R$" + item.get("Preço") + "\n" +
                                "Quantidade no estoque: " + item.get("Quantidade no estoque"));
            }
        }
    }


    public static void buscar(){
        if(!vazio()) {
                boolean achou = false;
                String busca = JOptionPane.showInputDialog(null, "Nome do produto: ");
                for (HashMap<Object, Object> item : lista) {
                    if (item.get("Nome").equals(busca)) {
                        JOptionPane.showMessageDialog(null,
                                "ID: " + item.get("ID") + "\n" +
                                        "Nome: " + item.get("Nome") + "\n" +
                                        "Preço: " + item.get("Preço") + "\n" +
                                        "Quantidade no estoque: " + item.get("Quantidade no estoque"));
                        achou = true;
                    }
                }
                if (!achou) {
                    JOptionPane.showMessageDialog(null, "Não há nenhum produto com esse nome.");
                }
        }
    }


    public static void sair(){
        JOptionPane.showMessageDialog(null, "Programa finalizado.");
    }


    public static void main(String[] args){
        int opcao = 0;
        do{
            Runnable[] funcoes = {
                    ProjetoLoja::adicionar,
                    ProjetoLoja::listar,
                    ProjetoLoja::buscar,
                    ProjetoLoja::sair
            };
            String[] opcoes = {"Adicionar", "Listar", "Buscar", "Sair"};
            try {
                String opcaoSTR = (String) JOptionPane.showInputDialog(null, "Escolha algo", "Cursos disponíveis", 1, null, opcoes, opcoes[0]);
                for (int i = 0; i < opcoes.length; i++) {
                    if (opcaoSTR.equals(opcoes[i])) {
                        opcao = i;
                        funcoes[opcao].run();
                        break;
                    }
                }
                if (opcaoSTR.equals("Sair")){
                    opcao = 10;
                }
            }
            catch (NullPointerException e){
                opcao = 10;
            }
        }while(opcao != 10);
    }
}