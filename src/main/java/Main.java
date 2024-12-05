import dao.ClientMapDAO;
import dao.IClienteDAO;
import domain.Client;

import javax.swing.*;

public class Main {
    private static IClienteDAO iClienteDAO;

    public static void main(String[] args) {
        iClienteDAO = new ClientMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                "Green dinner", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção inválida! Digite 1 para cadastro, 2 para consultar, " +
                            "3 para exclusão, 4 para alteração ou 5 para sair ",
                    "Green dinner", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vírgula, conforme o exemplo:" +
                                "Nome, CPF, Telefone, Endereço, Número, Cidade, Estado",
                        "Green dinner", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if (isConsulta(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente",
                        "Consulta Cliente", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            } else if (isExclusao(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente",
                        "Exclusão Cliente", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            } else if ("4".equals(opcao)) { // Atualização
                String cpf = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente que deseja atualizar:",
                        "Atualizar Cliente", JOptionPane.INFORMATION_MESSAGE);
                atualizar(cpf);
            }

            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                    "Green dinner", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void cadastrar(String dados) {
        try {
            String[] dadosSeparados = dados.split(",");
            if (dadosSeparados.length != 7) {
                JOptionPane.showMessageDialog(null, "Formato inválido! Certifique-se de inserir todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nome = dadosSeparados[0].trim();
            Long cpf = Long.parseLong(dadosSeparados[1].trim());
            Long tel = Long.parseLong(dadosSeparados[2].trim());
            String end = dadosSeparados[3].trim();
            Integer numero = Integer.parseInt(dadosSeparados[4].trim());
            String cidade = dadosSeparados[5].trim();
            String estado = dadosSeparados[6].trim();

            Client client = new Client(nome, cpf, tel, end, numero, cidade, estado);
            if (iClienteDAO.cadastrar(client)) {
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente. Verifique os dados inseridos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void consultar(String dados) {
        try {
            Long cpf = Long.parseLong(dados.trim());
            Client client = iClienteDAO.consultar(cpf);
            if (client != null) {
                JOptionPane.showMessageDialog(null, "Cliente encontrado:\n" + client.toString(), "Consulta Cliente", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "CPF inválido. Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void excluir(String dados) {
        try {
            Long cpf = Long.parseLong(dados.trim());
            iClienteDAO.excluir(cpf);
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente. Verifique o CPF informado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void atualizar(String cpfStr) {
        try {
            Long cpf = Long.parseLong(cpfStr.trim());
            Client client = iClienteDAO.consultar(cpf);

            if (client == null) {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String novosDados = JOptionPane.showInputDialog(null,
                    "Digite os novos dados do cliente separados por vírgula, conforme o exemplo:" +
                            "Nome, Telefone, Endereço, Número, Cidade, Estado",
                    "Atualizar Cliente", JOptionPane.INFORMATION_MESSAGE);

            String[] dadosSeparados = novosDados.split(",");
            if (dadosSeparados.length != 6) {
                JOptionPane.showMessageDialog(null, "Dados inválidos! Certifique-se de fornecer todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            client.setNome(dadosSeparados[0].trim());
            client.setTel(Long.parseLong(dadosSeparados[1].trim()));
            client.setEnd(dadosSeparados[2].trim());
            client.setNumero(Integer.parseInt(dadosSeparados[3].trim()));
            client.setCidade(dadosSeparados[4].trim());
            client.setEstado(dadosSeparados[5].trim());

            iClienteDAO.alterar(client);
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro na conversão de dados. Verifique os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente. Verifique os dados inseridos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static boolean isOpcaoValida(String opcao) {
        if (opcao == null || opcao.isEmpty()) {
            return false;
        }
        try {
            int op = Integer.parseInt(opcao.trim());
            return op >= 1 && op <= 5;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOpcaoSair(String opcao) {
        return "5".equals(opcao);
    }

    private static boolean isCadastro(String opcao) {
        return "1".equals(opcao);
    }

    private static boolean isConsulta(String opcao) {
        return "2".equals(opcao);
    }

    private static boolean isExclusao(String opcao) {
        return "3".equals(opcao);
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Programa encerrado.", "Green dinner", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
