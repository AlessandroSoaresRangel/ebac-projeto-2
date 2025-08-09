package main.java.br.com.arangel.services;

import main.java.br.com.arangel.domain.Cliente;
import main.java.br.com.arangel.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.arangel.services.generics.IGenericService;

public interface IClienteService extends IGenericService<Cliente, Long> {
    boolean salvar(Cliente cliente) throws TipoChaveNaoEncontradaException;

    Cliente buscarPorCpf(long cpf);
    void excluir(Long cpf);

    void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException;
}
