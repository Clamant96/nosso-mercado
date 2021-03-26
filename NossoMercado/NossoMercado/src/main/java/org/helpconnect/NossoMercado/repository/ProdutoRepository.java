package org.helpconnect.NossoMercado.repository;

import java.util.List;

import org.helpconnect.NossoMercado.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	public List<Produto> findAllByTituloProdutoContainingIgnoreCase(String tituloProduto);
	
}
