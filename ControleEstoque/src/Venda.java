public class Venda {
    private int id;
    private String data;
    private Cliente cliente;
    private Produto produto;
    
    private int qtdVendida;

    public Venda(int id, String data, Cliente cliente, Produto produto, int qtdVendida) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.produto = produto; 
        
        this.qtdVendida = qtdVendida;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public int getQtdVendida() {
        return qtdVendida;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setQtdVendida(int qtdVendida) {
        this.qtdVendida = qtdVendida;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public Produto getProduto() {
        return produto;
    }
    
    
}
