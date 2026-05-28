package ProjetoLoja;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjetoLoja{
    public static void main(String[] args){
        ArrayList<HashMap> lista = new ArrayList<>();
        int opcao = 0;
        do{
            String[] menu = {
                    "01. Cadastrar novo produto",
                    "02. Listar todos os produtos",
                    "03. Buscar produtos pelo nome",
                    "07. Listar produtos com estoque zerado",
                    "08. Calcular valor total do estoque",
                    "09 Atualizar preço do produto",
                    "10. Sair"};
            try {
                String opcaoSTR = ((String) JOptionPane.showInputDialog(null, "Escolha uma opção", "Sistema da loja",
                        JOptionPane.QUESTION_MESSAGE, null, menu, menu[0])).substring(0,2);
                opcao = Integer.parseInt(opcaoSTR);


                switch (opcao){
                    case 1:
                        String nome;
                        try {
                            nome = (JOptionPane.showInputDialog(null, "Nome do produto: ")).trim();
                            if (nome.isBlank()){
                                JOptionPane.showMessageDialog(null, "O produto precisa ter um nome.");
                                break;
                            }


                            try{
                                String precoStr = JOptionPane.showInputDialog(null, "Digite o preço do produto: ");
                                double preco = Double.parseDouble(precoStr);
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
                        catch (NullPointerException e){
                            JOptionPane.showMessageDialog(null, "Voltando ao menu principal.");
                        }
                        break;



                    case 2:
                        if (lista.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "A lista está vazia.");
                        }
                        else {
                            for (HashMap item : lista) {
                                JOptionPane.showMessageDialog(null,
                                        "ID: " + item.get("ID") + "\n" +
                                                "Nome: " + item.get("Nome") + "\n" +
                                                "Preço: R$" + String.format("%.2f\n", (double) item.get("Preço")) +
                                                "Quantidade no estoque: " + item.get("Quantidade no estoque"));
                            }
                        }
                        break;



                    case 3:
                        if (lista.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "A lista está vazia.");
                        }
                        else {
                            boolean achou = false;
                            String busca = (JOptionPane.showInputDialog(null, "Nome do produto: ")).trim();
                            for (HashMap item : lista) {
                                if (item.containsValue(busca)) {
                                    JOptionPane.showMessageDialog(null,
                                            "ID: " + item.get("ID") + "\n" +
                                                    "Nome: " + item.get("Nome") + "\n" +
                                                    "Preço: R$" + String.format("%.2f\n", (double) item.get("Preço")) +
                                                    "Quantidade no estoque: " + item.get("Quantidade no estoque"));
                                    if(!achou){
                                        achou = true;
                                    }
                                }
                            }
                            if (!achou) {
                                JOptionPane.showMessageDialog(null, "Não há nenhum produto com esse nome.");
                            }
                        }
                        break;



                    case 4:
                    case 5:
                    case 6:



                    case 7:
                        if (lista.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "A lista está vazia.");
                        }
                       else {
                            boolean achou = false;
                            for (HashMap item : lista) {
                                if ((int) item.get("Quantidade no estoque") == 0) {
                                    JOptionPane.showMessageDialog(null,
                                            "ID: " + item.get("ID") + "\n" +
                                                    "Nome: " + item.get("Nome") + "\n" +
                                                    "Preço: R$" + String.format("%.2f\n", (double) item.get("Preço")));
                                    if(!achou){
                                        achou = true;
                                    }
                                }
                            }

                            if (!achou) {
                                JOptionPane.showMessageDialog(null, "Não existem produtos com estoque zerado.");
                            }
                        }
                        break;



                    case 8:
                        if (lista.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "A lista está vazia.");
                        }
                        else {
                            double total = 0;
                            for (HashMap item : lista) {
                                double preco = (double) item.get("Preço");
                                int quantidade = (int) item.get("Quantidade no estoque");
                                total += preco * quantidade;
                            }
                            JOptionPane.showMessageDialog(null, "O valor total do estoque é: R$ " + String.format("%.2f", total));
                        }
                        break;



                    case 9:
                        if (lista.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "A lista está vazia.");
                        }
                       else {
                            try {
                                int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do produto:"));
                                boolean achou = false;
                                for (HashMap item : lista) {
                                    if ((int) item.get("ID") == id) {
                                        double novoPreco = Double.parseDouble(JOptionPane.showInputDialog(null, "Novo preço:"));
                                        item.put("Preço", novoPreco);
                                        JOptionPane.showMessageDialog(null, "SUCESSO: O preço do produto " + item.get("Nome") +
                                                " foi atualizado para R$ " + String.format("%.2f", novoPreco) + ".");
                                        if(!achou) {
                                            achou = true;
                                        }
                                    }
                                }

                                if (!achou) {
                                    JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                                }

                            }
                            catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Valor inválido.");
                            }
                        }
                       break;


                    case 10:
                        JOptionPane.showMessageDialog(null, "Programa finalizado.");
                        break;

                }
            }
            catch (NullPointerException e){
                JOptionPane.showMessageDialog(null, "Você deve escolher a opção 10 para encerrar.");
            }
        }while(opcao != 10);
    }
}
