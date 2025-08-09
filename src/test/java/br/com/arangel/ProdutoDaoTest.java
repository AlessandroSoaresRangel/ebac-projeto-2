package test.java.br.com.arangel;

import main.java.br.com.arangel.dao.IProdutoDao;
import main.java.br.com.arangel.dao.ProdutoDao;
import main.java.br.com.arangel.domain.Produto;
import main.java.br.com.arangel.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

public class ProdutoDaoTest {

    private Produto produto;
    private IProdutoDao dao;

    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        this.dao = new ProdutoDao();
        this.produto = new Produto(
                "l23as",
                "notebook",
                BigDecimal.valueOf(4000),
                "notebook para uso geral"
        );

        this.dao.cadastrar(this.produto);

    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException {
        this.produto.setCodigo("se12ls");
        Boolean isSalvo = this.dao.cadastrar(this.produto);

        Assert.assertTrue(isSalvo);
    }

    @Test
    public void consultar() {
        Produto produto = this.dao.consultar(this.produto.getCodigo());
        Assert.assertNotNull(produto);

    }

    @Test
    public void excluir(){
        this.dao.excluir(this.produto.getCodigo());
    }

    @Test
    public void alterar() throws TipoChaveNaoEncontradaException {

        this.produto.setNome("Cadeira");
        this.dao.alterar(this.produto);
        assert "Cadeira".equals(produto.getNome());
    }

    @Test
    public void buscarTodos() {
        Collection<Produto> list = this.dao.buscarTodos();

        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());

    }

}
