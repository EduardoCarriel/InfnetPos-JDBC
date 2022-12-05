package br.edu.infnet.pedido.model.persistencia;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.edu.infnet.pedido.model.entidade.CategoriaProduto;
import br.edu.infnet.pedido.model.entidade.Cliente;
import br.edu.infnet.pedido.model.entidade.Pedido;
import br.edu.infnet.pedido.model.entidade.Produto;

public class PedidoDAO extends JdbcDAO<Pedido>{

	@Override
	public Boolean salvar(Pedido pedido) {
		String sql = "insert into pedido(codigo, data, cliente_cod) values (null,?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, pedido.getNumero());
			pstm.setDate(2, (java.sql.Date) Date.from(pedido.getData().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			pstm.setLong(3, pedido.getCliente().getCodigo());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean atualizar(Pedido pedido) {
		String sql = "update pedido set data = ?, cliente_cod = ? where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setDate(1, (java.sql.Date) Date.from(pedido.getData().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			pstm.setLong(2, pedido.getCliente().getCodigo());
			pstm.setLong(3, pedido.getNumero());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deletar(Pedido pedido) {
		String sql = "delete from pedido where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, pedido.getNumero()); 
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Pedido obter(Long codigoPedido) {
		String sql = "select p.codigo, p.data, c.nome, pr.descricao, pr.preco from pedido p "
				+ "	join cliente c"
				+ "	join itens_pedido i"
				+ "	join produto pr"
				+ "	on p.cliente_cod = c.codigo"
				+ "	and p.codigo = i.pedido_cod"
				+ "	and pr.codigo = i.produto_cod"
				+ "	where p.codigo = ?";
		Pedido pedido = new Pedido();
		try {
			stm = con.createStatement();
			pstm.setLong(1, codigoPedido);
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Long codigo = rs.getLong("codigo");
				LocalDate data = rs.getDate("data").toLocalDate();
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				Long codigoCategoria = rs.getLong("codigo_categoria_produto");
				
				CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();
				CategoriaProduto categoria = new CategoriaProduto();
				
				categoria = categoriaProdutoDAO.obter(codigoCategoria);
				
				pedido = new Pedido(codigo, data, new Cliente(nome));
				pedido.setProdutos(new ArrayList<>());
					
				Produto produto = new Produto(null, descricao, preco, categoria);
				pedido.getProdutos().add(produto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedido;
	}

	@Override
	public List<Pedido> listarTodos() {
		String sql = "select p.codigo, p.data, c.nome, pr.descricao, pr.preco from pedido p "
				+ "	join cliente c"
				+ "	join itens_pedido i"
				+ "	join produto pr"
				+ "	on p.cliente_cod = c.codigo"
				+ "	and p.codigo = i.pedido_cod"
				+ "	and pr.codigo = i.produto_cod";
		Map<Long, Pedido> pedidos = new TreeMap<>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Long codigo = rs.getLong("codigo");
				LocalDate data = rs.getDate("data").toLocalDate();
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				Long codigoCategoria = rs.getLong("codigo_categoria_produto");
				Pedido pedido = null;
				
				CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();
				CategoriaProduto categoria = new CategoriaProduto();
				
				categoria = categoriaProdutoDAO.obter(codigoCategoria);
				
				if(pedidos.get(codigo) == null) {
					pedido = new Pedido(codigo, data, new Cliente(nome));
					pedido.setProdutos(new ArrayList<>());
					pedidos.put(codigo, pedido);
				}
				Produto produto = new Produto(null, descricao, preco, categoria);
				pedido = pedidos.get(codigo);
				pedido.getProdutos().add(produto);
			}
			return new ArrayList<Pedido>(pedidos.values());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
