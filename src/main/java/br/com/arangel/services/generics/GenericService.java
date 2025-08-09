package main.java.br.com.arangel.services.generics;

import main.java.br.com.arangel.dao.generecs.IGenericDAO;
import main.java.br.com.arangel.domain.Persistente;
import main.java.br.com.arangel.exceptions.TipoChaveNaoEncontradaException;

import java.util.Collection;

public abstract class GenericService<T extends Persistente, E> implements IGenericService<T, E>{

    protected IGenericDAO<T,E> dao;

    public GenericService(IGenericDAO<T, E> dao) {
        this.dao = dao;
    }

    @Override
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException {
        return this.dao.cadastrar(entity);
    }

    @Override
    public T consultar(E valor) {
        return this.dao.consultar(valor);
    }

    @Override
    public void excluir(E valor) {
        this.dao.excluir(valor);
    }

    @Override
    public void alterar(T entity) throws TipoChaveNaoEncontradaException {
        this.dao.alterar(entity);
    }

    @Override
    public Collection<T> buscarTodos() {
        return this.dao.buscarTodos();
    }
}
