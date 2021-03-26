package org.helpconnect.NossoMercado.controller;

import java.util.List;

import org.helpconnect.NossoMercado.model.Produto;
import org.helpconnect.NossoMercado.repository.ProdutoRepository;
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
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAllProdutos(){
		
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findByIdProduto(@PathVariable long id){
		
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tituloProduto/{tituloProduto}")
	public ResponseEntity<List<Produto>> findByTituloProdutoProduto(@PathVariable String tituloProduto){
		
		return ResponseEntity.ok(repository.findAllByTituloProdutoContainingIgnoreCase(tituloProduto));
	}
	
	/* TRAS TODOS OS VALORES <= (menores ou igual) CADASTRADOS DENTRO DE SUA TABELA */
	@GetMapping("/valorMenor/{valor}")
	public ResponseEntity<List<Produto>> findByValorMenorProduto(@PathVariable int valor){
		
		return ResponseEntity.ok(repository.findAllByValorLessThanEqual(valor));
	}
	
	/* TRAS TODOS OS VALORES >= (maiores ou igual) CADASTRADOS DENTRO DE SUA TABELA */
	@GetMapping("/valorMaior/{valor}")
	public ResponseEntity<List<Produto>> findByValorMaiorProduto(@PathVariable int valor){
		
		return ResponseEntity.ok(repository.findAllByValorGreaterThanEqual(valor));
	}
	
	@GetMapping("/ativo/{ativo}")
	public ResponseEntity<List<Produto>> findAllByAtivo(@PathVariable boolean ativo){
		
		return ResponseEntity.ok(repository.findAllByAtivo(ativo));
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto(@RequestBody Produto produto){
		
		return ResponseEntity.ok(repository.save(produto));
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable long id) {
		
		repository.deleteById(id);
	}
}
