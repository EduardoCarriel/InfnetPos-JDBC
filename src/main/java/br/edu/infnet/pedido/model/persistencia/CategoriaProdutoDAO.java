package br.edu.infnet.pedido.model.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.pedido.model.entidade.CategoriaProduto;

public class CategoriaProdutoDAO extends JdbcDAO<CategoriaProduto> {

	@Override
	public Boolean salvar(CategoriaProduto categoria) {
		String sql = "insert into categoria_produto(codigo, descricao) values (null,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, categoria.getDescricao());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean atualizar(CategoriaProduto categoria) {
		String sql = "update categoria_produto set descricao = ? where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, categoria.getDescricao()); 
			pstm.setLong(2, categoria.getCodigo()); 
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deletar(CategoriaProduto categoria) {
		String sql = "delete from categoria_produto where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, categoria.getCodigo()); 
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public CategoriaProduto obter(Long codigoCategoria) {
		String sql = "select * from categoria_produto where codigo = ?";
		CategoriaProduto categoria = new CategoriaProduto();
		try {
			pstm = con.prepareStatement(sql); 
			pstm.setLong(1, codigoCategoria); 
			rs = pstm.executeQuery();
			if(rs.next()) {
				Long codigo = rs.getLong("codigo");
				String descricao = rs.getString("descricao");
				categoria = new CategoriaProduto(codigo, descricao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoria;
	}

	@Override
	public List<CategoriaProduto> listarTodos() {
		String sql = "select * from cliente";
		List<CategoriaProduto> categorias = new ArrayList<>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Long codigo = rs.getLong("codigo");
				String descricao = rs.getString("descricao");
				CategoriaProduto categoria = new CategoriaProduto(codigo, descricao);
				categorias.add(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorias;
	}

}
