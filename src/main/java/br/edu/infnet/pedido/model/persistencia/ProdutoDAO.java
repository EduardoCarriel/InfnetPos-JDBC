package br.edu.infnet.pedido.model.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.pedido.model.entidade.CategoriaProduto;
import br.edu.infnet.pedido.model.entidade.Produto;

public class ProdutoDAO extends JdbcDAO<Produto> {

	@Override
	public Boolean salvar(Produto produto) {
		String sql = "insert into produto(codigo, descricao, preco, codigo_categoria_produto) values (null,?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, produto.getDescricao());
			pstm.setDouble(2, produto.getPreco());
			pstm.setLong(3, produto.getCategoria().getCodigo());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean atualizar(Produto produto) {
		String sql = "update produto set descricao = ?, preco = ?, codigo_categoria_produto = ? where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, produto.getDescricao());
			pstm.setDouble(2, produto.getPreco());
			pstm.setLong(3, produto.getCategoria().getCodigo());
			pstm.setLong(4, produto.getCodigo());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deletar(Produto produto) {
		String sql = "delete from produto where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, produto.getCodigo()); 
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Produto obter(Long codigoProduto) {
		String sql = "select * from produto where codigo = ?";
		Produto produto = new Produto();
		try {
			pstm = con.prepareStatement(sql); 
			pstm.setLong(1, codigoProduto); 
			rs = pstm.executeQuery();
			if(rs.next()) {
				Long codigo = rs.getLong("codigo");
				String descricao = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				Long codigoCategoria = rs.getLong("codigo_categoria_produto");
				
				CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();
				CategoriaProduto categoria = new CategoriaProduto();
				
				categoria = categoriaProdutoDAO.obter(codigoCategoria);
				 
				produto = new Produto(codigo, descricao, preco, categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produto;
	}

	@Override
	public List<Produto> listarTodos() {
		String sql = "select * from produto";
		List<Produto> produtos = new ArrayList<>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Long codigo = rs.getLong("codigo");
				String descricao = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				Long codigoCategoria = rs.getLong("codigo_categoria_produto");
				
				CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();
				CategoriaProduto categoria = new CategoriaProduto();
				
				categoria = categoriaProdutoDAO.obter(codigoCategoria);
				 
				Produto produto = new Produto(codigo, descricao, preco, categoria);
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}

}
