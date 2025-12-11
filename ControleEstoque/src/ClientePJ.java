import java.util.InputMismatchException;

import javax.swing.JOptionPane;

public class ClientePJ extends Cliente {

    private String cnpj;

    public ClientePJ(int id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public boolean validarDocumento() {

         if (cnpj == null || cnpj.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Documento não pode ser vazio!");
            return false;
        }
        // Mantém somente números
        String cnpj = this.cnpj.replaceAll("\\D", "");

        // CNPJ possui 14 dígitos
        if (cnpj.equals("00000000000000") ||
                cnpj.equals("11111111111111") ||
                cnpj.equals("22222222222222") ||
                cnpj.equals("33333333333333") ||
                cnpj.equals("44444444444444") ||
                cnpj.equals("55555555555555") ||
                cnpj.equals("66666666666666") ||
                cnpj.equals("77777777777777") ||
                cnpj.equals("88888888888888") ||
                cnpj.equals("99999999999999") ||
                cnpj.length() != 14)
            return false;

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
            // Cálculo do 1º dígito
            sm = 0;
            peso = 5;
            for (i = 0; i < 12; i++) {
                num = cnpj.charAt(i) - 48;
                sm += num * peso;
                peso--;
                if (peso < 2)
                    peso = 9;
            }

            r = sm % 11;
            if (r < 2)
                dig13 = '0';
            else
                dig13 = (char) ((11 - r) + 48);

            // Cálculo do 2º dígito
            sm = 0;
            peso = 6;
            for (i = 0; i < 13; i++) {
                num = cnpj.charAt(i) - 48;
                sm += num * peso;
                peso--;
                if (peso < 2)
                    peso = 9;
            }

            r = sm % 11;
            if (r < 2)
                dig14 = '0';
            else
                dig14 = (char) ((11 - r) + 48);

            // Verifica se bate com os dígitos informados
            return (dig13 == cnpj.charAt(12) && dig14 == cnpj.charAt(13));

        } catch (InputMismatchException erro) {
            return false;
        }
    }
    @Override
    public String getDocumento() {
        return cnpj;
    }
}
