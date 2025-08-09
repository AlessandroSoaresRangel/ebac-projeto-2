package main.java.br.com.arangel.services;

import main.java.br.com.arangel.dao.generecs.IGenericDAO;
import main.java.br.com.arangel.domain.Produto;
import main.java.br.com.arangel.services.generics.GenericService;


public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

    public ProdutoService(IGenericDAO<Produto, String> dao) {
        super(dao);
    }
}
