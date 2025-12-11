import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GerenciarClientes {

    private List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    // ---------------------- CADASTRAR CLIENTE ----------------------
    public void cadastrarCliente() {

        String tipo = JOptionPane.showInputDialog(
                "Tipo de cliente?\n1- Pessoa física\n2- Pessoa jurídica");

        if (tipo == null)
            return;

        Cliente novo = null;

        switch (tipo) {
            case "1":
                novo = cadastrarPF();
                break;

            case "2":
                novo = cadastrarPJ();
                break;

            default:
                JOptionPane.showMessageDialog(null, "Tipo inválido!");
                return;
        }

        // Se deu erro no cadastro
        if (novo == null)
            return;

        clientes.add(novo);
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        proximoId++;
    }

    // ---------------------- LISTAR ----------------------
    public void listarCliente() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado!");
            return;
        }

        String lista = "LISTA DE CLIENTES:\n\n";

        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);

            lista += "ID: " + c.getId()
                    + " | Nome: " + c.getNome()
                    + " | Documento: " + c.getDocumento()
                    + " | Total de compras: " + c.getTotalCompras()
                    + "\n";
        }

        JOptionPane.showMessageDialog(null, lista);
    }

    // ---------------------- CADASTRO PF ----------------------
    private Cliente cadastrarPF() {
        String nome = JOptionPane.showInputDialog("Nome:");
        if (!validarNome(nome))
            return null;

        String cpf = JOptionPane.showInputDialog("CPF:");

        // cria obj PF
        ClientePF cliente = new ClientePF(proximoId, nome.trim(), cpf.trim());

        if (!cliente.validarDocumento()) {
            JOptionPane.showMessageDialog(null, "Documento inválido!");
            return null;
        }

        if (documentoDuplicado(cliente.getDocumento())) {
            JOptionPane.showMessageDialog(null, "Documento já cadastrado!");
            return null;
        }

        return cliente;
    }

    // ---------------------- CADASTRO PJ ----------------------
    private Cliente cadastrarPJ() {
        String razao = JOptionPane.showInputDialog("Razão Social:");
        if (!validarNome(razao))
            return null;

        String cnpj = JOptionPane.showInputDialog("CNPJ:");

        ClientePJ cliente = new ClientePJ(proximoId, razao.trim(), cnpj.trim());

        if (!cliente.validarDocumento()) {
            JOptionPane.showMessageDialog(null, "Documento inválido!");
            return null;
        }

        if (documentoDuplicado(cliente.getDocumento())) {
            JOptionPane.showMessageDialog(null, "Documento já cadastrado!");
            return null;
        }

        return cliente;
    }

    // ---------------------- VALIDAÇÕES ----------------------
    private boolean validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome/razão social não pode ser vazio!");
            return false;
        }

        if (!nome.matches("[A-Za-zÀ-ÿ ]+")) {
        JOptionPane.showMessageDialog(null, "O nome deve conter apenas letras!");
        return false;
        }

        return true;
    }

    private boolean documentoDuplicado(String documento) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);

            if (c.getDocumento().equals(documento)) {
                return true;
            }
        }
        return false;
    }

    // ---------------------- BUSCAS ----------------------
    public Cliente buscarPorDocumento(String doc) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);

            if (c.getDocumento().equals(doc)) {
                return c;
            }
        }
        return null;
    }

    public Cliente buscarPorId(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);

            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}
