package br.edu.infnet.pedido.model.entidade;

public class Produto {
	private Long codigo;
	private String descricao;
	private Double preco;
	private CategoriaProduto categoria;
	
	public Produto() {}
	
	public Produto(Long codigo, String descricao, Double preco, CategoriaProduto categoria) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public CategoriaProduto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + ", " + categoria.toString() + "]";
	}
}
