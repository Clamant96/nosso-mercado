package org.helpconnect.NossoMercado.repository;

import java.util.List;

import org.helpconnect.NossoMercado.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long>{
	public List<Loja> findAllByNomeLojaContainingIgnoreCase(String nomeLoja);
	
}
