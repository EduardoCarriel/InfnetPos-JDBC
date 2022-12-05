package br.edu.infnet.pedido.model.persistencia;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.edu.infnet.pedido.model.entidade.Pedido;

public class PedidoDAOTest {

	
	@Test
	public void testListaPedidos() {
		IDAO<Pedido> pediIdao = new PedidoDAO();
		List<Pedido> lista = pediIdao.listarTodos();
		System.out.println(lista); 
		Assert.assertTrue(lista.size() > 0);
	}
	

}
