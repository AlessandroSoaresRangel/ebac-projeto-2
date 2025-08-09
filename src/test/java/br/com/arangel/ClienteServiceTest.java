package test.java.br.com.arangel;



import main.java.br.com.arangel.dao.ClienteDao;
import main.java.br.com.arangel.domain.Cliente;
import main.java.br.com.arangel.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.arangel.services.ClienteService;
import main.java.br.com.arangel.services.IClienteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.java.br.com.arangel.mock.ClienteDaoMock;

import java.util.Collection;

public class ClienteServiceTest {

    private IClienteService service;
    private Cliente cliente;

    public ClienteServiceTest() {
        this.service = new ClienteService(new ClienteDao());
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

        service.salvar(cliente);
    }

    @Test
    public void pesquisarCliente() {

        Cliente clienteConsultado = service.buscarPorCpf(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setCpf(21943345415L);
        boolean resultado = this.service.salvar(this.cliente);

        Assert.assertTrue(resultado);
    }

    @Test
    public void excluirCliente() {
        service.excluir(cliente.getCpf());
    }

    @Test
    public void alterar() throws TipoChaveNaoEncontradaException {
        cliente.setNome("outro nome");
        service.alterar(cliente);

        assert "outro nome".equals(cliente.getNome());
    }

    @Test
    public void buscarTodos() {
        Collection<Cliente> lista = service.buscarTodos();
        Assert.assertNotNull(lista);
        Assert.assertEquals(2, lista.size());
    }
}
