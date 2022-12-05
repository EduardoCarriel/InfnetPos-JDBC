package br.edu.infnet.pedido.model.entidade;

public class CategoriaProduto {
	public long codigo;
	public String descricao;
	
	public CategoriaProduto() {}
	
	public CategoriaProduto(long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "Categoria [codigo=" + codigo + ", descricao=" + descricao + "]";
	}
}
