package main.java.br.com.arangel.services;

import main.java.br.com.arangel.dao.IClienteDao;
import main.java.br.com.arangel.domain.Cliente;
import main.java.br.com.arangel.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.arangel.services.generics.GenericService;

import java.util.Collection;
import java.util.List;


public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {


    public ClienteService(IClienteDao clienteDao) {

        super(clienteDao);
    }

    @Override
    public boolean salvar(Cliente cliente) throws TipoChaveNaoEncontradaException {
        return super.cadastrar(cliente);
    }

    @Override
    public Cliente buscarPorCpf(long cpf) {
        return super.consultar(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return super.buscarTodos();
    }
}
