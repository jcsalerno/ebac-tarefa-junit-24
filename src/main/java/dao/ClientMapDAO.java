package dao;

import domain.Client;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ClientMapDAO implements IClienteDAO{
    private Map<Long, Client> map;

    public ClientMapDAO() {map = new TreeMap<>();}

    @Override
    public Boolean cadastrar(Client client) {
        if (map.containsKey(client.getCpf())) {
            return false;
        }
        map.put(client.getCpf(), client);
        return true;
    }



    @Override
    public void excluir(Long cpf) {
        Client clientCadastrado = map.get(cpf);
        map.remove(clientCadastrado.getCpf(), clientCadastrado);
    }

    @Override
    public void alterar(Client client) {
        Client clientCadastrado = map.get(client.getCpf());
        clientCadastrado.setNome(client.getNome());
        clientCadastrado.setTel(client.getTel());
        clientCadastrado.setNumero(client.getNumero());
        clientCadastrado.setEnd(client.getEnd());
        clientCadastrado.setCidade(client.getCidade());
        clientCadastrado.setEstado(client.getEstado());

    }

    @Override
    public Client consultar(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Client> buscarTodos() {
        return this.map.values();
    }
}
