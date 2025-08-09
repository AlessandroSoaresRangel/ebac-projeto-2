package main.java.br.com.arangel.dao;

import main.java.br.com.arangel.dao.generecs.GenericDAO;
import main.java.br.com.arangel.domain.Venda;
import main.java.br.com.arangel.exceptions.TipoChaveNaoEncontradaException;

public class VendaDao extends GenericDAO<Venda, String> implements IVendaDao {
    @Override
    public Class<Venda> getTipoClasse() {
        return Venda.class;
    }

    @Override
    public void atualiarDados(Venda entity, Venda entityCadastrado) {
        entityCadastrado.setCodigo(entity.getCodigo());
        entityCadastrado.setStatus(entity.getStatus());
    }

    @Override
    public void excluir(String valor) {
        throw new UnsupportedOperationException("Não é permitido excluir vendas");
    }


    @Override
    public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException {
        venda.setStatus(Venda.Status.CONCLUIDA);
        super.alterar(venda);
    }
}
