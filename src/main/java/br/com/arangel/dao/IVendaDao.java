package main.java.br.com.arangel.dao;

import main.java.br.com.arangel.dao.generecs.IGenericDAO;
import main.java.br.com.arangel.domain.Venda;
import main.java.br.com.arangel.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaDao extends IGenericDAO<Venda, String> {
    void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException;
}
