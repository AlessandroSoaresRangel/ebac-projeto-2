package test.java.br.com.arangel;

import main.java.br.com.arangel.dao.ClienteDao;
import main.java.br.com.arangel.dao.IClienteDao;
import main.java.br.com.arangel.domain.Cliente;
import main.java.br.com.arangel.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class ClienteDaoTest {

    private Cliente cliente;
    private IClienteDao dao;

    public ClienteDaoTest() {
        this.dao = new ClienteDao();
    }

    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        this.cliente = new Cliente();
        cliente.setCpf(12345678910L);
        cliente.setNome("Alessandro");
        cliente.setCidade("Rio de Janeiro");
        cliente.setEstado("RJ");
        cliente.setEnd("end");
        cliente.setNumero(10);
        cliente.setTel(21943345445L);

        this.dao.cadastrar(cliente);

    }

    @Test
    public void pesquisarCliente() {

        Cliente clienteConsultado = this.dao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException {
        cliente.setCpf(21943345435L);
        boolean retorno =  this.dao.cadastrar(cliente);

        Assert.assertTrue(retorno);

    }

    @Test
    public void excluir() {
        dao.excluir(cliente.getCpf());
    }

    @Test
    public void alterar() throws TipoChaveNaoEncontradaException {
        cliente.setNome("outro nome");
        dao.alterar(cliente);

        assert "outro nome".equals(cliente.getNome());

    }

    @Test
    public void buscarTodos() {
        Collection<Cliente> list = dao.buscarTodos();
        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());
    }

}
