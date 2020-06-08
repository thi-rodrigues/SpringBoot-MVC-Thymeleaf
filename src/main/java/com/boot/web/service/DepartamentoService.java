package com.boot.web.service;

import java.util.List;

import com.boot.web.domain.Departamento;
import com.boot.web.util.PaginacaoUtil;

public interface DepartamentoService {

	void salvar(Departamento departamento);

	void editar(Departamento departamento);

	void excluir(Long id);

	Departamento buscarPorId(Long id);

	List<Departamento> buscarTodos();

	boolean departamentoTemCargos(Long id);
	
	PaginacaoUtil<Departamento> buscaPorPagina(int pagina);
}
