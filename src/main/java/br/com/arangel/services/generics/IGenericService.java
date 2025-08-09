package main.java.br.com.arangel.services.generics;


import main.java.br.com.arangel.domain.Persistente;
import main.java.br.com.arangel.exceptions.TipoChaveNaoEncontradaException;

import java.util.Collection;

public interface IGenericService<T extends Persistente, E> {
    Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException;

    T consultar(E valor);

    void excluir(E valor);

    void alterar(T entity) throws TipoChaveNaoEncontradaException;

    Collection<T> buscarTodos();

}
