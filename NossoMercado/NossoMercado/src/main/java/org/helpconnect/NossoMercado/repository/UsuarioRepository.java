package org.helpconnect.NossoMercado.repository;

import java.util.Optional;

import org.helpconnect.NossoMercado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	/*public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);*/
	
	public Optional<Usuario> findByUsuario(String nome);
	
}
