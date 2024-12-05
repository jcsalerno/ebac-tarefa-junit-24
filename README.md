# Sistema de Cadastro de Clientes - Projeto EBAC - TAREFA 24 - Testado com Junit

Este projeto implementa um sistema de cadastro de clientes utilizando o padrão **DAO (Data Access Object)**, onde a lógica de acesso a dados é separada da lógica de negócios. A aplicação permite o cadastro, consulta, exclusão e atualização de informações de clientes em memória, utilizando diferentes implementações de armazenamento (Map e Set).

---

## 📋 Estrutura do Projeto

O sistema permite o gerenciamento de clientes, com a criação de novos clientes, consulta, atualização e exclusão de clientes existentes. O acesso aos dados é feito por meio da interface `IClienteDAO`, que tem duas implementações: `ClientMapDAO` e `ClientSetDAO`.

### Principais Componentes

1. **Entidade Cliente**:
    - A classe `Client` contém as informações de um cliente (nome, CPF, telefone, endereço, etc.), além de métodos para manipulação desses dados (getters, setters, equals, hashCode, etc.).

2. **Interfaces e Implementações DAO**:
    - `IClienteDAO`: Interface que define os métodos para manipulação dos dados dos clientes.
    - `ClientMapDAO`: Implementação de `IClienteDAO` usando `Map` (TreeMap) para armazenar os clientes.
    - `ClientSetDAO`: Implementação de `IClienteDAO` usando `Set` (HashSet) para armazenar os clientes.
3. **Classe Principal**:
- `Main`: Classe cliente que utiliza a interface `IClienteDAO` para realizar as operações de cadastro, consulta, atualização e exclusão de clientes, com interação via interface gráfica (JOptionPane).

---
