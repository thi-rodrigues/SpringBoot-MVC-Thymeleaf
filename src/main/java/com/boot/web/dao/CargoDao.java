package com.boot.web.dao;

import java.util.List;

import com.boot.web.domain.Cargo;

public interface CargoDao {
	
	public abstract void save(Cargo cargo);

	void update(Cargo cargo);

	void delete(Long id);

	Cargo findById(Long id);

	List<Cargo> findAll();

}
