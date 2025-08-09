package test.java.br.com.arangel;

import main.java.br.com.arangel.dao.*;
import main.java.br.com.arangel.domain.Cliente;
import main.java.br.com.arangel.domain.Produto;
import main.java.br.com.arangel.domain.Venda;
import main.java.br.com.arangel.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

public class VendaDaoTest {
    private IVendaDao vendaDao;
    private IProdutoDao produtoDao;
    private Produto produto;
    private Cliente cliente;
    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        this.produtoDao = new ProdutoDao();
        this.vendaDao = new VendaDao();
        this.cliente = new Cliente(12345678901L,"Alessandro", "São Gonçalo",
                    "RJ", "end", 31, 2143215678L);
        this.produto = cadastrarProduto("ewq121", BigDecimal.valueOf(100));

    }

    @Test
    public void pesquisar() throws TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("A1");
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Venda vendaConsultada = vendaDao.consultar(venda.getCodigo());
        Assert.assertNotNull(vendaConsultada);
        Assert.assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("A2");
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(200)));
        Assert.assertEquals(Venda.Status.INICIADA, venda.getStatus());
    }


    @Test
    public void cancelarVenda() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A3";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        venda.setStatus(Venda.Status.CANCELADA);
        vendaDao.alterar(venda);

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        Assert.assertEquals(codigoVenda, vendaConsultada.getCodigo());
        Assert.assertEquals(Venda.Status.CANCELADA, vendaConsultada.getStatus());
    }

    @Test
    public void adicionarMaisProdutosDoMesmo() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A4";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(produto, 1);

        Assert.assertEquals(3, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(300));
        Assert.assertEquals(Venda.Status.INICIADA, venda.getStatus());
    }

    @Test
    public void adicionarMaisProdutosDiferentes() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A5";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(500));
        Assert.assertNotNull(prod);
        Assert.assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);

        Assert.assertEquals(3, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(700));
        Assert.assertEquals(Venda.Status.INICIADA, venda.getStatus());
    }

    @Test
    public void salvarProdutoExistente() throws TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("A6");
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);

        Boolean retorno1 = vendaDao.cadastrar(venda);
        Assert.assertFalse(retorno1);
        Assert.assertEquals(Venda.Status.INICIADA, venda.getStatus());
    }

    @Test
    public void removerProduto() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A7";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(500));
        Assert.assertNotNull(prod);
        Assert.assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);
        Assert.assertEquals(3, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(700));

        vendaConsultada.removerProduto(prod, 1);
        Assert.assertEquals(2, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(200));
        Assert.assertEquals(Venda.Status.INICIADA, venda.getStatus());
    }

    @Test
    public void removerApenasUmProduto() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A8";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(500));
        Assert.assertNotNull(prod);
        Assert.assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);
        Assert.assertEquals(3, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(700));

        vendaConsultada.removerProduto(prod, 1);
        Assert.assertEquals(2, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(200));
        Assert.assertEquals(Venda.Status.INICIADA, venda.getStatus());
    }

    @Test
    public void removerTodosProdutos() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A9";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(500));
        Assert.assertNotNull(prod);
        Assert.assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);
        Assert.assertEquals(3, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(700));

        vendaConsultada.removerTodosProdutos();
        Assert.assertEquals(0, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(0));
        Assert.assertEquals(Venda.Status.INICIADA, venda.getStatus());
    }

    @Test
    public void finalizarVenda() throws TipoChaveNaoEncontradaException, TipoChaveNaoEncontradaException {
        String codigoVenda = "A10";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        vendaDao.finalizarVenda(venda);

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        Assert.assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        Assert.assertEquals(venda.getStatus(), vendaConsultada.getStatus());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void tentarAdicionarProdutosVendaFinalizada() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A11";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        vendaDao.finalizarVenda(venda);
        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        Assert.assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        Assert.assertEquals(venda.getStatus(), vendaConsultada.getStatus());

        vendaConsultada.adicionarProduto(this.produto, 1);

    }

    private Produto cadastrarProduto(String codigo, BigDecimal valor) throws TipoChaveNaoEncontradaException {
        Produto produto = new Produto(codigo,
                "sei la", valor,
                "um produto que eu não sei o que é");
        produtoDao.cadastrar(produto);
        return produto;
    }


    private Venda criarVenda(String codigo) {
        Venda venda = new Venda(codigo, this.cliente,Instant.now() ,Venda.Status.INICIADA );
        venda.adicionarProduto(this.produto, 2);
        return venda;
    }

}
