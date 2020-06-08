package com.boot.web.dao;

import java.util.List;

import com.boot.web.domain.Departamento;
import com.boot.web.util.PaginacaoUtil;

public interface DepartamentoDao {

	void save(Departamento departamento);

	void update(Departamento departamento);

	void delete(Long id);

	Departamento findById(Long id);

	List<Departamento> findAll();
	
	PaginacaoUtil<Departamento> buscaPaginada(int pagina);

}
