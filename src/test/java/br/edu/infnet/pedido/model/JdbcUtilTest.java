package br.edu.infnet.pedido.model;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;


public class JdbcUtilTest {

	@Test
	public void testConexao() {
    	try (Connection obterConexao = JdbcUtil.obterConexao()) {
			Assert.assertTrue("Conectado", true);
		} catch (SQLException e) {
			fail();
		};
	}

}
