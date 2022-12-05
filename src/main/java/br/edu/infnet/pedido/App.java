package br.edu.infnet.pedido;

import java.sql.SQLException;

import br.edu.infnet.pedido.model.JdbcUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, ClassNotFoundException
    {
    	JdbcUtil.obterConexao();
		System.out.println( "Hello!" );
    }
}
