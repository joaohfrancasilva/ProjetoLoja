package ProjetoLoja;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjetoLoja{
    public static void main(String[] args){
        ArrayList<HashMap> lista = new ArrayList<>();
        int opcao = 0;
        int id = 1;
        do{
            String[] menu = {
                    "01. Cadastrar novo produto",
                    "02. Listar todos os produtos",
                    "03. Buscar produtos pelo nome",
                    "04. Registrar entrada no estoque (Compra)",
                    "05. Registrar sáida de estoque (Venda)",
                    "06. Excluir produto do sistema",
                    "07. Listar produtos com estoque zerado",
                    "08. Calcular valor total do estoque",
                    "09. Atualizar preço do produto",
                    "10. Sair"
            };
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
                                produto.put("ID", id);
                                lista.add(produto);
                                JOptionPane.showMessageDialog(null, "Produto adicionado!");
                                id++;
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
                            String busca = JOptionPane.showInputDialog(null, "Nome do produto: ");
                            if (busca == null) {
                                JOptionPane.showMessageDialog(null, "Voltando ao menu principal.");
                            }
                            else if (busca.isBlank()){
                                JOptionPane.showMessageDialog(null, "Valor inválido.");
                            }else {
                                for (HashMap item : lista) {
                                    String nomeProduto = (String) item.get("Nome");
                                    if (nomeProduto.toLowerCase().contains(busca.toLowerCase())) {
                                        JOptionPane.showMessageDialog(null,
                                                "ID: " + item.get("ID") + "\n" +
                                                        "Nome: " + item.get("Nome") + "\n" +
                                                        "Preço: R$" + String.format("%.2f\n", (double) item.get("Preço")) +
                                                        "Quantidade no estoque: " + item.get("Quantidade no estoque"));
                                        if (!achou) {
                                            achou = true;
                                        }
                                    }
                                }
                                if (!achou) {
                                    JOptionPane.showMessageDialog(null, "Não há nenhum produto com esse nome.");
                                }
                            }
                        }
                        break;



                    case 4:
                        if (lista.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "A lista está vazia.");
                        }
                        else {
                            try {
                                int idBusca = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do produto:"));
                                boolean achou = false;
                                for (HashMap item : lista) {
                                    if ((int) item.get("ID") == idBusca) {
                                        achou = true;
                                        int quantidade = Integer.parseInt(
                                                JOptionPane.showInputDialog(null, "Quantidade adquirida:")
                                        );
                                        int estoqueAtual = (int) item.get("Quantidade no estoque");
                                        item.put("Quantidade no estoque", estoqueAtual + quantidade);
                                        JOptionPane.showMessageDialog(null,
                                                "Entrada registrada com sucesso.\n" +
                                                        "Novo estoque: " + item.get("Quantidade no estoque"));
                                        break;
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



                    case 5:
                        if (lista.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "A lista está vazia.");
                        }
                        else {
                            try {
                                int idBusca = Integer.parseInt(
                                        JOptionPane.showInputDialog(null, "Digite o ID do produto:")
                                );
                                boolean achou = false;
                                for (HashMap item : lista) {
                                    if ((int) item.get("ID") == idBusca) {
                                        achou = true;
                                        int quantidadeVenda = Integer.parseInt(
                                                JOptionPane.showInputDialog(null, "Quantidade a ser vendida:")
                                        );
                                        int estoqueAtual = (int) item.get("Quantidade no estoque");
                                        if (quantidadeVenda > estoqueAtual) {
                                            JOptionPane.showMessageDialog(null,
                                                    "FALHA NA VENDA: Estoque insuficiente. Apenas "
                                                            + estoqueAtual + " unidades disponíveis.");
                                        }
                                        else {
                                            item.put("Quantidade no estoque", estoqueAtual - quantidadeVenda);
                                            JOptionPane.showMessageDialog(null,
                                                    "Venda realizada com sucesso.\n" +
                                                            "Estoque restante: " + item.get("Quantidade no estoque"));
                                        }
                                        break;
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



                    case 6:
                        if (lista.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "A lista está vazia.");
                        }
                        else {
                            try {
                                int idBusca = Integer.parseInt(
                                        JOptionPane.showInputDialog(null, "Digite o ID do produto:")
                                );
                                boolean achou = false;
                                for (int i = 0; i < lista.size(); i++) {
                                    HashMap item = lista.get(i);
                                    if ((int) item.get("ID") == idBusca) {
                                        achou = true;
                                        int estoqueAtual = (int) item.get("Quantidade no estoque");
                                        if (estoqueAtual > 0) {
                                            JOptionPane.showMessageDialog(null,
                                                    "AÇÃO PROIBIDA: Não é possível excluir produtos com estoque.\n" +
                                                            "Quantidade atual: " + estoqueAtual + ".");
                                        }
                                        else {
                                            int confirmar = JOptionPane.showConfirmDialog(
                                                    null,
                                                    "Tem certeza que deseja excluir o produto " + item.get("Nome") + "?",
                                                    "Confirmação",
                                                    JOptionPane.YES_NO_OPTION
                                            );
                                            if (confirmar == JOptionPane.YES_OPTION) {
                                                lista.remove(i);
                                                JOptionPane.showMessageDialog(null, "Produto removido com sucesso.");
                                            }
                                        }
                                        break;
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
                                int idBusca = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do produto:"));
                                boolean achou = false;
                                for (HashMap item : lista) {
                                    if ((int) item.get("ID") == idBusca) {
                                        if(!achou) {
                                            achou = true;
                                        }
                                        double novoPreco = Double.parseDouble(JOptionPane.showInputDialog(null, "Novo preço:"));
                                        item.put("Preço", novoPreco);
                                        JOptionPane.showMessageDialog(null, "SUCESSO: O preço do produto " + item.get("Nome") +
                                                " foi atualizado para R$ " + String.format("%.2f", novoPreco) + ".");
                                        break;
                                    }
                                }
                                if (!achou) {
                                    JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                                }
                            }
                            catch (NullPointerException e ){
                                JOptionPane.showMessageDialog(null, "Retornando ao menu principal.");
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
