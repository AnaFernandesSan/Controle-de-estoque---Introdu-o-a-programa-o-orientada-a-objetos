public abstract class Cliente {
    private String nome;
    private int id; 
    private int totalCompras = 0;

    public Cliente(int id, String nome){
        this.nome= nome; 
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void incrementarCompras() {
        totalCompras++;
    }
    
    public int getTotalCompras() {
        return totalCompras;
    }
    
    public abstract boolean validarDocumento();
    public abstract String getDocumento();
}
