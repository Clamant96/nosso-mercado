package org.helpconnect.NossoMercado.controller;

import java.util.List;

import org.helpconnect.NossoMercado.model.Loja;
import org.helpconnect.NossoMercado.repository.LojaRepository;
import org.helpconnect.NossoMercado.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lojas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LojaController {
	
	@Autowired
	private LojaRepository repository;
	
	@Autowired
	private LojaService service;
	
	@GetMapping
	public ResponseEntity<List<Loja>> findAllLoja(){
		
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Loja> findByIdLoja(@PathVariable long id){
		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nomeLoja/{nomeLoja}")
	public ResponseEntity<List<Loja>> findByNomeLoja(@PathVariable String nomeLoja){
		
		return ResponseEntity.ok(repository.findAllByNomeLojaContainingIgnoreCase(nomeLoja));
	}
	
	@PostMapping
	public ResponseEntity<Loja> postLoja(@RequestBody Loja loja){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(loja));
	}
	
	/*
	 * EXPLICACAO URI:
	 * 
	 * 	/plataformaJogo -> nome da tabela associativa
	 * 	/usuarios -> nome da lista de jogos dentro da classe Loja
	 * 	/inscricao -> nome da lista de plataformas dentro da classe Usuario
	 * 
	 * */
	
	@PutMapping("/inscricao/usuarios/{idLoja}/inscricoes/{idUsuario}")
	public ResponseEntity<Loja> postLojaUsuario(@PathVariable long idLoja, @PathVariable long idUsuario){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastroUsuarioLoja(idLoja, idUsuario));
	}
	
	@PutMapping
	public ResponseEntity<Loja> putLoja(@RequestBody Loja loja){
		
		return ResponseEntity.ok(repository.save(loja));
	}
	
	@DeleteMapping("/{id}")
	public void deleteLoja(@PathVariable long id){
		
		repository.deleteById(id);
	}
}
