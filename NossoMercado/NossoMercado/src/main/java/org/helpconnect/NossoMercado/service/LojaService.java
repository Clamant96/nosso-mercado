package org.helpconnect.NossoMercado.service;

import java.util.Optional;

import org.helpconnect.NossoMercado.model.Loja;
import org.helpconnect.NossoMercado.model.Usuario;
import org.helpconnect.NossoMercado.repository.LojaRepository;
import org.helpconnect.NossoMercado.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LojaService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LojaRepository lojaRepository;
	
	public Loja cadastroUsuarioLoja(long idLoja, long idUsuario) {
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);
		Optional<Loja> lojaExistente = lojaRepository.findById(idLoja);
		
		if(usuarioExistente.isPresent() && lojaExistente.isPresent()) {
			lojaExistente.get().getUsuarios().add(usuarioExistente.get());
			
			lojaRepository.save(lojaExistente.get());
			
			return lojaRepository.save(lojaExistente.get());
			
		}
		
		return null;
	}

}
