import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) {

        // cria os gerenciadores
        GerenciarProdutos gp = new GerenciarProdutos();
        GerenciarClientes gc = new GerenciarClientes();
        GerenciarVendas gv = new GerenciarVendas(gc, gp);

        String[] menuPrincipal = {
            "Gerenciar Produtos",
            "Gerenciar Clientes",
            "Gerenciar Vendas",
            "Sair"
        };

        int escolha = -1;

        while (escolha != 3) { 

            escolha = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma funcionalidade:",
                    "MENU PRINCIPAL",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    menuPrincipal,
                    menuPrincipal[0]); // pré-seleção do botao

            switch (escolha) {

                case 0:
                    menuProdutos(gp);
                    break;

                case 1:
                    menuClientes(gc);
                    break;

                case 2:
                    menuVendas(gv);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Encerrando...");
                    break;

                default:
                    escolha = 2;
            }
        }
    }

    // ------------- MENU DE PRODUTOS ------------------

    private static void menuProdutos(GerenciarProdutos gp) {

        String[] opcoesProd = {
            "Cadastrar",
            "Listar",
            "Excluir",
            "Editar",
            "Estoque total",
            "Estoque baixo",
            "Voltar"
        };

        int escolha = -1;

        while (escolha != 6) {

            escolha = JOptionPane.showOptionDialog(
                    null,
                    "Menu de Produtos",
                    "PRODUTOS",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcoesProd,
                    opcoesProd[0]);

            switch (escolha) {
                case 0:
                    gp.cadastrarProduto();
                    break;
                case 1:
                    gp.listarProdutos();
                    break;
                case 2:
                    gp.excluirProduto();
                    break;
                case 3:
                    gp.atualizarEstoque();
                    break;
                case 4:
                    gp.estoqueTotal();
                    break;
                case 5:
                    gp.estoqueBaixo();
                    break;
                case 6:
                    break; // voltar
                default:
                    escolha = 6;
            }
        }
    }
    // ----------- MENU DE CLIENTES ---------------

    private static void menuClientes(GerenciarClientes gc) {

        String[] opcoesCli = {
            "Cadastrar",
            "Listar",
            "Voltar"
        };

        int escolha = -1;

        while (escolha != 4) {

            escolha = JOptionPane.showOptionDialog(
                    null,
                    "Menu de Clientes",
                    "CLIENTES",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcoesCli,
                    opcoesCli[0]);

            switch (escolha) {
                case 0:
                    gc.cadastrarCliente();
                    break;
                case 1:
                    gc.listarCliente();
                    break;
                case 4:
                    break; // voltar
                default:
                    escolha = 4;
            }
        }
    }

    private static void menuVendas(GerenciarVendas gv) {

        String[] opcoesVenda = {
                "Cadastrar Venda",
                "Listar Vendas",
                "Voltar"
        };

        int escolha = -1;

        while (escolha != 2) {

            escolha = JOptionPane.showOptionDialog(
                    null,
                    "Menu de Vendas",
                    "VENDAS",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcoesVenda,
                    opcoesVenda[0]); 

            switch (escolha) {
                case 0:
                    gv.cadastrarVenda();
                    break;
                case 1:
                    gv.listarVendas();
                    break;
                case 2:
                    break;
                default:
                    escolha = 2;
            }
        }
    }

}
