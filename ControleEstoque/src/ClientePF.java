import java.util.InputMismatchException;

import javax.swing.JOptionPane;

public class ClientePF extends Cliente {
    private String cpf;

    public ClientePF(int id, String nome, String cpf){
        super(id, nome);
        this.cpf = cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getCpf(){
        return cpf;
    }

    @Override
    public boolean validarDocumento() {

         if (cpf == null || cpf.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Documento não pode ser vazio!");
            return false;
        }
        // remove tudo que não é número
        String cpf = this.cpf.replaceAll("\\D", "");

        if (cpf.equals("00000000000") ||
            cpf.equals("11111111111") ||
            cpf.equals("22222222222") ||
            cpf.equals("33333333333") ||
            cpf.equals("44444444444") ||
            cpf.equals("55555555555") ||
            cpf.equals("66666666666") ||
            cpf.equals("77777777777") ||
            cpf.equals("88888888888") ||
            cpf.equals("99999999999") ||
            (cpf.length() != 11))
            return false;

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            // 1º dígito
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = cpf.charAt(i) - 48;
                sm += (num * peso);
                peso--;
            }

            r = 11 - (sm % 11);
            if (r == 10 || r == 11)
                dig10 = '0';
            else
                dig10 = (char)(r + 48);

            // 2º dígito
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = cpf.charAt(i) - 48;
                sm += (num * peso);
                peso--;
            }

            r = 11 - (sm % 11);
            if (r == 10 || r == 11)
                dig11 = '0';
            else
                dig11 = (char)(r + 48);

            return (dig10 == cpf.charAt(9) && dig11 == cpf.charAt(10));

        } catch (InputMismatchException erro) {
            return false;
        }
    }


    @Override
    public String getDocumento(){
        return cpf;
    }
}
