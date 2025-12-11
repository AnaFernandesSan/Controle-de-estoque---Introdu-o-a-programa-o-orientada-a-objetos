import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GerenciarProdutos {
    private List<Produto> produtos = new ArrayList<>(); // lista dos produtos
    private int proximoId = 1;

    public void cadastrarProduto() {
        String nome;
        int qtdInicial;
        double precoUni;

        nome = JOptionPane.showInputDialog("Nome do produto:");

        if (!validarNomeP(nome))
            return; // se não digitar nada no nome, ele para

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);

            if (produto.getNome().equalsIgnoreCase(nome)) {
                JOptionPane.showMessageDialog(null, "Esse produto já está cadastrado.");
                return;
            }
        }

        // Pede a quantidade inicial e converte para int
        String qtdDigitada = JOptionPane.showInputDialog("Quantidade inicial:");
        if (qtdDigitada == null)
            return;

        // Verifica se digitou apenas números
        if (!qtdDigitada.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Digite apenas números!");
            return; 
        }
        qtdInicial = Integer.parseInt(qtdDigitada);

        if (qtdInicial < 0) {
            JOptionPane.showMessageDialog(null, "Quantidade inválida! Não pode ser menor que zero.");
            return;
        }

        String precoUniDigitado = JOptionPane.showInputDialog("Preço unitário:");

         if (!precoUniDigitado.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Digite apenas números!");
            return; 
        }
        
        precoUni = Integer.parseInt(precoUniDigitado);

        if (precoUni < 0) {
            JOptionPane.showMessageDialog(null, "Preço inválido.");
            return;
        }

        Produto novo = new Produto(proximoId, nome, qtdInicial, precoUni);
        proximoId++;

        // adiciona o produto na lista
        produtos.add(novo);

        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
    }

    public boolean excluirProduto() {

        // pega o texto e converte pra inteiro
        String idDigitado = JOptionPane.showInputDialog("Digite o ID do produto a excluir:");

        if (!idDigitado.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Digite apenas números!");
            return false; 
        }
        int id = Integer.parseInt(idDigitado);
        Produto p = buscarProdutoPorId(id);

        if (p == null) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return false;
        }

        produtos.remove(p);
        JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
        return true;

    }

    public Produto buscarProdutoPorId(int id) {

        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);

            if (p.getId() == id) {
                return p; // encontrou
            }
        }

        return null; // não encontrou
    }

    public void listarProdutos() {
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado.");
            return;
        }

        String lista = "LISTA DE PRODUTOS:\n\n";

        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);

            lista += "ID: " + p.getId()
                    + " | Nome: " + p.getNome()
                    + " | Quantidade: " + p.getQtdInicial()
                    + " | Preço: R$ " + p.getPrecoUni() + "\n";
        }

        JOptionPane.showMessageDialog(null, lista);
    }

    public void atualizarEstoque() {

        // Pede o ID do produto
         String idDigitado = JOptionPane.showInputDialog("Digite o ID do produto  para atualizar:");

        if (!idDigitado.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Digite apenas números!");
            return; 
        }
        int id = Integer.parseInt(idDigitado);

        Produto p = buscarProdutoPorId(id);

        if (p == null) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return;
        }

        // opções para editar
        String[] opcoes = { "Nome", "Quantidade", "Preço", "Cancelar" };

        int escolha = JOptionPane.showOptionDialog(
                null,
                "O que deseja editar?",
                "Editar Produto",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        switch (escolha) {

            case 0: // Nome
                String novoNome = JOptionPane.showInputDialog("Digite o novo nome:", p.getNome());
                if (novoNome != null) {
                    p.setNome(novoNome);
                    JOptionPane.showMessageDialog(null, "Nome atualizado!");
                }
                break;

            case 1: // Quantidade
                int novaQtd = Integer.parseInt(
                        JOptionPane.showInputDialog("Nova quantidade:", p.getQtdInicial()));
                p.setQtdInicial(novaQtd);
                JOptionPane.showMessageDialog(null, "Quantidade atualizada!");
                break;

            case 2: // Preço
                double novoPreco = Double.parseDouble(
                        JOptionPane.showInputDialog("Novo preço:", p.getPrecoUni()));
                p.setPrecoUni(novoPreco);
                JOptionPane.showMessageDialog(null, "Preço atualizado!");
                break;

            default:
                JOptionPane.showMessageDialog(null, "Edição cancelada.");
        }
    }

    public void estoqueTotal() {

        double total = 0;

        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);

            total += p.getQtdInicial() * p.getPrecoUni();
        }

        JOptionPane.showMessageDialog(null,
                String.format("Valor total do estoque: R$ %.2f", total));
    }

    public void estoqueBaixo() {

        // pede o valor limite
        int limite = Integer.parseInt(
                JOptionPane.showInputDialog("Listar produtos com quantidade menor ou igual a:"));

        String texto = "Produtos com estoque baixo (≤ " + limite + "):\n\n";
        boolean encontrou = false;

        // percorre todos os produtos
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);

            if (p.getQtdInicial() <= limite) {
                encontrou = true;

                texto += "ID: " + p.getId()
                        + " | Nome: " + p.getNome()
                        + " | Quantidade: " + p.getQtdInicial()
                        + " | Preço: R$ " + p.getPrecoUni() + "\n";
            }
        }

        if (!encontrou) {
            JOptionPane.showMessageDialog(null,
                    "Nenhum produto com estoque igual ou inferior a " + limite + ".");
        } else {
            JOptionPane.showMessageDialog(null, texto);
        }
    }

    // ------------------- VALIDAÇÃO -------------------
    private boolean validarNomeP(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome do produto não pode ser vazio!");
            return false;
        }
        if (!nome.matches("[A-Za-zÀ-ÿ ]+")) {
            JOptionPane.showMessageDialog(null, "O nome deve conter apenas letras!");
            return false;
        }

        return true;
    }

}
