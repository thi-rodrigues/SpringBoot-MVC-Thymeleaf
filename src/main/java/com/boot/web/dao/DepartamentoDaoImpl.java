package com.boot.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.boot.web.domain.Departamento;
import com.boot.web.util.PaginacaoUtil;

@Repository
public class DepartamentoDaoImpl extends AbstractDao<Departamento, Long> implements DepartamentoDao {
	
	public PaginacaoUtil<Departamento> buscaPaginada(int pagina){
		int tamanho = 9;
		int inicio = (pagina - 1) * tamanho; // 0*5=0 / 1*5=5 / 2*5=10
		
		List<Departamento> departamentos = getEntityManager()
				.createQuery("select d from Departamento d order by d.nome", Departamento.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, departamentos);
	}
	
	public long count() {
		return getEntityManager()
				.createQuery("select count(*) from Departamento", Long.class)
				.getSingleResult();
	}
	  //16 + 4 = 20 / 5tam / 4 paginas
	 // 16 + 2 = 18 / 3tam / 6 paginas
}
