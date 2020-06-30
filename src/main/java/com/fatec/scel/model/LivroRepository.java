package com.fatec.scel.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Long> {
	@Query("SELECT l FROM Livro l WHERE l.isbn = :isbn")
	public Livro findByIsbn(@Param("isbn") String isbn);
}