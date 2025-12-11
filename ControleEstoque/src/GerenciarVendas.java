
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GerenciarVendas {
    private List<Venda> vendas = new ArrayList<>();
    private int proximoId = 1;

    private GerenciarClientes gerClientes;
    private GerenciarProdutos gerProdutos;

    public GerenciarVendas(GerenciarClientes gc, GerenciarProdutos gp) {
        this.gerClientes = gc;
        this.gerProdutos = gp;
        //recebe os objetos de gerenciar cliente e produto no constutor
    }

    public void cadastrarVenda() {
        String data = JOptionPane.showInputDialog("Data da venda:");
        // valida a data
        if (data == null || !validaData(data)) {
            JOptionPane.showMessageDialog(null, "Data inválida!");
            return;
        }
    
        // validar CPF/CNPJ
        String documento = JOptionPane.showInputDialog("CPF/CNPJ do cliente:");
    
        Cliente cliente = gerClientes.buscarPorDocumento(documento); // pra n ter q ficar fazendo varias buscas
    
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return;
        }
    
        int idProduto = Integer.parseInt(
                JOptionPane.showInputDialog("ID do produto:"));
    
        Produto produto = gerProdutos.buscarProdutoPorId(idProduto);
    
        if (produto == null) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return;
        }
    
        int qtd = Integer.parseInt(
                JOptionPane.showInputDialog("Quantidade vendida:"));
    
        if (qtd > produto.getQtdInicial()) {
            JOptionPane.showMessageDialog(null, "Estoque insuficiente!");
            return;
        }
    
        Venda v = new Venda(proximoId, data, cliente, produto, qtd);// cria uma venda
        proximoId++;
        cliente.incrementarCompras(); // adiciona o +1 nas compras do cliente
        vendas.add(v); // adiciona na lista
    
        produto.setQtdInicial(produto.getQtdInicial() - qtd); // diminui o estoque
    
        JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!");
    }

    public void listarVendas() {
        if (vendas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma venda cadastrada.");
            return;
        }
    
        String lista = "LISTA DE VENDAS:\n\n";
    
        for (Venda v : vendas) {
            Cliente c = v.getCliente(); // cria uma variavel c que representa o cliente nessa venda
            Produto p = v.getProduto(); // p que representa o produto nessa venda
    

            lista += "ID Venda: " + v.getId()
                  + " | Data: " + v.getData()
                  + "\nCliente: " + c.getNome() + " (" + c.getDocumento() + ")"
                  + "\nProduto: " + p.getId() + " - " + p.getNome()
                  + " | Quantidade: " + v.getQtdVendida()
                  + "\n-----------------------------------------\n";
        }
    
        JOptionPane.showMessageDialog(null, lista);
    }
    

    private boolean validaData(String data) {
        if (data == null || data.trim().isEmpty()) return false;
    
        
        if (data.length() != 10) return false;
    
        // Split
        String[] partes = data.split("/");
        if (partes.length != 3) return false;
    
        int dia, mes, ano;
    
        try {
            dia = Integer.parseInt(partes[0]);
            mes = Integer.parseInt(partes[1]);
            ano = Integer.parseInt(partes[2]);
        } catch (NumberFormatException e) {
            return false;
        }
    
        if (ano < 1900 || ano > 2100) return false;
        if (mes < 1 || mes > 12) return false;
    
        int[] diasMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
        
        boolean bissexto = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
        if (bissexto) diasMes[1] = 29;
    
        if (dia < 1 || dia > diasMes[mes - 1]) return false;
    
        return true;
    }
    
}
