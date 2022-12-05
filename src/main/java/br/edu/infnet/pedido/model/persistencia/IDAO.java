package br.edu.infnet.pedido.model.persistencia;

import java.util.List;

public interface IDAO<T> {

	Boolean salvar(T obj);

	Boolean atualizar(T obj);

	Boolean deletar(T obj);

	T obter(Long codigo);

	List<T> listarTodos();

}