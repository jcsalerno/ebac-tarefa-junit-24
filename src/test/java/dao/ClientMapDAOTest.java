import dao.ClientMapDAO;
import domain.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ClientMapDAOTest {

    private ClientMapDAO clientMapDAO;
    private Client client;

    @BeforeEach
    void setUp() {
        clientMapDAO = new ClientMapDAO();
        client = new Client(
                "Maria Silva",
                12345678901L,
                11999999999L,
                "Rua A",
                123,
                "São Paulo",
                "SP"
        );
    }

    @Test
    void testCadastrarCliente() {

        boolean resultado = clientMapDAO.cadastrar(client);
        assertTrue(resultado, "O cliente deveria ser cadastrado com sucesso.");


        boolean resultadoDuplicado = clientMapDAO.cadastrar(client);
        assertFalse(resultadoDuplicado, "Não deveria permitir cadastro duplicado.");
    }

    @Test
    void testConsultarCliente() {

        clientMapDAO.cadastrar(client);
        Client clienteConsultado = clientMapDAO.consultar(client.getCpf());
        assertNotNull(clienteConsultado, "O cliente deveria ser encontrado.");
        assertEquals(client.getNome(), clienteConsultado.getNome(), "Os nomes deveriam ser iguais.");
    }

    @Test
    void testAlterarCliente() {

        clientMapDAO.cadastrar(client);


        client.setNome("Maria Souza");
        client.setTel(11888888888L);
        clientMapDAO.alterar(client);


        Client clienteAlterado = clientMapDAO.consultar(client.getCpf());
        assertNotNull(clienteAlterado, "O cliente alterado deveria ser encontrado.");
        assertEquals("Maria Souza", clienteAlterado.getNome(), "O nome deveria ter sido alterado.");
        assertEquals(11888888888L, clienteAlterado.getTel(), "O telefone deveria ter sido alterado.");
    }

    @Test
    void testExcluirCliente() {

        clientMapDAO.cadastrar(client);


        clientMapDAO.excluir(client.getCpf());


        Client clienteExcluido = clientMapDAO.consultar(client.getCpf());
        assertNull(clienteExcluido, "O cliente deveria ter sido excluído.");
    }

    @Test
    void testBuscarTodos() {
        // Cadastrar múltiplos clientes
        Client outroCliente = new Client(
                "João Santos", // Nome
                98765432101L,  // CPF
                11988888888L,  // Telefone
                "Rua B",       // Endereço
                456,           // Número
                "Rio de Janeiro", // Cidade
                "RJ"           // Estado
        );

        clientMapDAO.cadastrar(client);
        clientMapDAO.cadastrar(outroCliente);


        Collection<Client> todosClientes = clientMapDAO.buscarTodos();
        assertEquals(2, todosClientes.size(), "Deveriam existir 2 clientes cadastrados.");
    }
}
