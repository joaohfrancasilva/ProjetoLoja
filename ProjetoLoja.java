import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjetoLoja{
    public static void main(String[] args){
        ArrayList<HashMap<String, Object>> lista = new ArrayList<>();
        int opcao = 0;
        int id = 0;
        boolean produtoEncontrado;
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
                String opcaoSTR = ((String) JOptionPane.showInputDialog(null, "Escolha uma opção", 
                "Sistema da loja", JOptionPane.QUESTION_MESSAGE, null, menu, menu[0])).substring(0,2);

                opcao = Integer.parseInt(opcaoSTR);

                if (lista.isEmpty() && opcao != 1 && opcao != 10){
                    opcao = 0;
                }

                switch (opcao){
                    case 0:
                        JOptionPane.showMessageDialog(null, "A lista está vazia"); 
                        break;
                    case 1:
                        String nome;
                        try {
                            nome = (JOptionPane.showInputDialog(null, "Nome do produto: ")).trim();
                            if (nome.isBlank()){
                                JOptionPane.showMessageDialog(null, "O produto precisa ter um nome.");
                                break;
                            }

                            try{
                                double preco = Double.parseDouble(
                                    JOptionPane.showInputDialog(null, "Digite o preço do produto: "));

                                if(preco <= 0){
                                    JOptionPane.showMessageDialog(null, "Valor Inválido.");
                                }
                                
                                else{
                                    HashMap<String, Object> produto = new HashMap<>();
                                    produto.put("Nome", nome);
                                    produto.put("Preço", preco);
                                    produto.put("Quantidade no estoque", 0);
                                    produto.put("ID", ++id);
                                    lista.add(produto);
                                    JOptionPane.showMessageDialog(null, "Produto adicionado!");
                                }
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
                        for (HashMap<String, Object> item : lista) {
                            JOptionPane.showMessageDialog(null,
                                    "ID: " + item.get("ID") + "\n" +
                                            "Nome: " + item.get("Nome") + "\n" +
                                            "Preço: R$" + String.format("%.2f\n", (double) item.get("Preço")) +
                                            "Quantidade no estoque: " + item.get("Quantidade no estoque"));
                        }
                        break;



                    case 3:
                        try{
                            produtoEncontrado = false;
                            String busca = (JOptionPane.showInputDialog(null, "Nome do produto: ")).toLowerCase();

                            if (busca.isBlank()) {
                                JOptionPane.showMessageDialog(null, "Produto precisa de um nome.");
                                break;
                            }

                            for (HashMap<String, Object> produto : lista) {

                                String nomeProduto = ((String) produto.get("Nome")).toLowerCase();

                                if (nomeProduto.contains(busca)) {

                                    if (produtoEncontrado == false) {
                                        produtoEncontrado = true;
                                    }

                                        JOptionPane.showMessageDialog(null,
                                                "ID: " + produto.get("ID") + "\n" +
                                                "Nome: " + produto.get("Nome") + "\n" +
                                                "Preço: R$" + String.format("%.2f\n", (double) produto.get("Preço")) +
                                                "Quantidade no estoque: " + produto.get("Quantidade no estoque"));
                                    }
                                }

                                if (produtoEncontrado == false) {
                                    JOptionPane.showMessageDialog(null, "Não há nenhum produto com esse nome.");
                                }
                        }
                        catch (NullPointerException e){
                            JOptionPane.showMessageDialog(null, "Voltando ao menu principal.");  
                        }
                        break;



                    case 4:
                        try {
                            produtoEncontrado = false; 

                            int idBusca = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do produto:"));

                            for (HashMap<String, Object> item : lista) {
                                
                                if ((int) item.get("ID") == idBusca) {

                                    produtoEncontrado = true;

                                    int quantidade = Integer.parseInt(JOptionPane.showInputDialog(null,
                                         "Quantidade adquirida:"));

                                    if (quantidade <= 0){
                                        JOptionPane.showMessageDialog(null, "Valor inválido.");
                                    }

                                    else{
                                        int estoqueAtual = (int) item.get("Quantidade no estoque");
                                        item.put("Quantidade no estoque", estoqueAtual + quantidade);
                                        JOptionPane.showMessageDialog(null,
                                                "Entrada registrada com sucesso.\n" +
                                                "Novo estoque: " + item.get("Quantidade no estoque"));
                                    }
                                    break;
                                }
                            }
                            if (produtoEncontrado == false){
                                JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                            }
                        }

                        catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Valor inválido.");
                        }

                        break;



                    case 5:
                        try {
                            produtoEncontrado = false; 

                            int idBusca = Integer.parseInt(
                                JOptionPane.showInputDialog(null, "Digite o ID do produto:"));

                            for (HashMap<String, Object> item : lista) {

                                if ((int) item.get("ID") == idBusca) {

                                    produtoEncontrado = true;

                                    int quantidadeVenda = Integer.parseInt(
                                        JOptionPane.showInputDialog(null, "Quantidade a ser vendida:"));

                                    if (quantidadeVenda <= 0){
                                        JOptionPane.showMessageDialog(null, "Valor inválido.");
                                    }

                                    else{

                                        int estoqueAtual = (int) item.get("Quantidade no estoque");

                                        if (quantidadeVenda > estoqueAtual) {
                                            JOptionPane.showMessageDialog(null,
                                                    "FALHA NA VENDA: Estoque insuficiente. Apenas "+ estoqueAtual + " unidades disponíveis.");
                                        }

                                        else {
                                            item.put("Quantidade no estoque", estoqueAtual - quantidadeVenda);
                                            JOptionPane.showMessageDialog(null,
                                                    "Venda realizada com sucesso.\n" +
                                                    "Estoque restante: " + item.get("Quantidade no estoque"));
                                        }
                                    }
                                    break;
                                }
                            }

                            if (produtoEncontrado == false){
                                JOptionPane.showMessageDialog(null, "Produto não encontrado");
                            }

                        }
                        catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Valor inválido.");
                        }
                        break;



                    case 6:
                        produtoEncontrado = false;

                        try {
                            int idBusca = Integer.parseInt(
                                    JOptionPane.showInputDialog(null, "Digite o ID do produto:"));
                          
                            for (int i = 0; i < lista.size(); i++) {

                                HashMap<String, Object> produto = lista.get(i);

                                if ((int) produto.get("ID") == idBusca) {

                                    produtoEncontrado = true; 

                                    int estoqueAtual = (int) produto.get("Quantidade no estoque");

                                    if (estoqueAtual > 0) {
                                        JOptionPane.showMessageDialog(null,
                                                "AÇÃO PROIBIDA: Não é possível excluir produtos com estoque.\n" +
                                                "Quantidade atual: " + estoqueAtual + ".");
                                    }

                                    else {
                                        int confirmar = JOptionPane.showConfirmDialog(
                                                null,
                                                "Tem certeza que deseja excluir o produto " + produto.get("Nome") + "?",
                                                "Confirmação", JOptionPane.YES_NO_OPTION);

                                        if (confirmar == JOptionPane.YES_OPTION) {
                                            lista.remove(i);
                                            JOptionPane.showMessageDialog(null, "Produto removido com sucesso.");
                                        }
                                        else {
                                            JOptionPane.showMessageDialog(null, "Voltando ao menu principal.");
                                        }
                                    }
                                    break;
                                }
                            }
                            if (produtoEncontrado == false) {
                                JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                            } 
                        }
                        catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Valor inválido.");
                        }
                        break;



                    case 7:
                        produtoEncontrado = false;

                        for (HashMap<String, Object>item : lista) {

                            if ((int) item.get("Quantidade no estoque") == 0) {

                                if(produtoEncontrado == false){
                                    produtoEncontrado = true;
                                }

                                JOptionPane.showMessageDialog(null,
                                        "ID: " + item.get("ID") + "\n" +
                                        "Nome: " + item.get("Nome") + "\n" +
                                        "Preço: R$" + String.format("%.2f\n", (double) item.get("Preço")));
                            }
                        }
                        if (produtoEncontrado == false) {
                            JOptionPane.showMessageDialog(null, "Não existem produtos com estoque zerado.");
                        }
                        break;



                    case 8:
                        double total = 0;

                        for (HashMap<String, Object> item : lista) {

                            double preco = (double) item.get("Preço");

                            int quantidade = (int) item.get("Quantidade no estoque");

                            total += preco * quantidade;
                        }

                        JOptionPane.showMessageDialog(null, "O valor total do estoque é: R$ " 
                        + String.format("%.2f", total));

                        break;



                    case 9:
                        try {

                            produtoEncontrado = false;

                            int idBusca = Integer.parseInt(
                                JOptionPane.showInputDialog(null, "Digite o ID do produto:"));

                            for (HashMap<String, Object> item : lista) {

                                if ((int) item.get("ID") == idBusca) {

                                    produtoEncontrado = true;

                                    double novoPreco = Double.parseDouble(
                                        JOptionPane.showInputDialog(null, "Novo preço:"));

                                    if (novoPreco <= 0){
                                        JOptionPane.showMessageDialog(null, "Valor inválido.");
                                    }

                                    else{
                                        item.put("Preço", novoPreco);
                                        JOptionPane.showMessageDialog(null, "SUCESSO: O preço do produto " + item.get("Nome") +
                                                " foi atualizado para R$ " + String.format("%.2f", novoPreco) + ".");
                                    }
                                    break;
                                }
                            }
                            if (produtoEncontrado == false) {
                                JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                            } 
                        }
                        catch (NullPointerException e ){
                            JOptionPane.showMessageDialog(null, "Retornando ao menu principal.");
                        }
                        catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Valor inválido.");
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