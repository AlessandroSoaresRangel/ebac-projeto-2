package main.java.br.com.arangel.dao;

import main.java.br.com.arangel.dao.generecs.GenericDAO;
import main.java.br.com.arangel.domain.Produto;

public class ProdutoDao extends GenericDAO<Produto, String> implements IProdutoDao {
    @Override
    public Class<Produto> getTipoClasse() {
        return Produto.class;
    }

    @Override
    public void atualiarDados(Produto entity, Produto entityCadastrado) {
        entityCadastrado.setCodigo(entity.getCodigo());
        entityCadastrado.setNome(entity.getNome());
        entityCadastrado.setValor(entity.getValor());
        entityCadastrado.setDescricao(entity.getDescricao());

    }
}
