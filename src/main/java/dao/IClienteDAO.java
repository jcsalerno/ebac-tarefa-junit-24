package dao;

import domain.Client;

import java.util.Collection;

public interface IClienteDAO {

    public Boolean cadastrar(Client client);
    public void excluir(Long cpf);
    public void alterar(Client client);
    public Client consultar(Long cpf);
    public Collection<Client> buscarTodos();
}
