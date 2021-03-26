package org.helpconnect.NossoMercado.repository;

import java.util.List;

import org.helpconnect.NossoMercado.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	public List<Produto> findAllByTituloProdutoContainingIgnoreCase(String tituloProduto);
	
	public List<Produto> findAllByValorLessThanEqual(double valor);
	
	public List<Produto> findAllByValorGreaterThanEqual(double valor);
	
	@Query(value = "SELECT * FROM produto WHERE ativo = :ativo", nativeQuery = true)
	public List<Produto> findAllByAtivo(@Param("ativo") boolean ativo);
	
}
