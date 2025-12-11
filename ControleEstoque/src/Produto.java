public class Produto {
    private int id;
    private String nome;
    private int qtdInicial;
    private double precoUni;

    public Produto(int id, String nome, int qtdInicial, double precoUni){ 
        this.id = id;
        this.nome = nome;
        this.qtdInicial = qtdInicial;
        this.precoUni = precoUni;
    }
    
    public int getId(){
        return id;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setQtdInicial(int qtdInicial){
        this.qtdInicial = qtdInicial;
    }
    
    public int getQtdInicial(){
        return qtdInicial;
    }
    
    public void setPrecoUni(double precoUni){
        this.precoUni = precoUni;
    }
    
    public double getPrecoUni(){
        return precoUni;
    }
}

