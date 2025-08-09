package test.java.br.com.arangel;

import main.java.br.com.arangel.dao.ProdutoDao;
import main.java.br.com.arangel.services.IProdutoService;
import main.java.br.com.arangel.domain.Produto;
import main.java.br.com.arangel.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.arangel.services.ProdutoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

public class ProdutoServiceTest {

    private Produto produto;
    private IProdutoService service;

    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        this.service = new ProdutoService(new ProdutoDao());
        this.produto = new Produto(
                "l23as",
                "notebook",
                BigDecimal.valueOf(4000),
                "notebook para uso geral"
        );

        this.service.cadastrar(this.produto);

    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException {
        this.produto.setCodigo("se12ls");
        Boolean isSalvo = this.service.cadastrar(this.produto);

        Assert.assertTrue(isSalvo);
    }

    @Test
    public void consultar() {
        Produto produto = this.service.consultar(this.produto.getCodigo());
        Assert.assertNotNull(produto);

    }

    @Test
    public void excluir(){
        this.service.excluir(this.produto.getCodigo());
    }

    @Test
    public void alterar() throws TipoChaveNaoEncontradaException {

        this.produto.setNome("Cadeira");
        this.service.alterar(this.produto);
        assert "Cadeira".equals(produto.getNome());
    }

    @Test
    public void buscarTodos() {
        Collection<Produto> list = this.service.buscarTodos();

        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());

    }
}
