package org.helpconnect.NossoMercado.service;

import java.util.Optional;

import org.helpconnect.NossoMercado.model.Loja;
import org.helpconnect.NossoMercado.model.Produto;
import org.helpconnect.NossoMercado.model.Usuario;
import org.helpconnect.NossoMercado.repository.LojaRepository;
import org.helpconnect.NossoMercado.repository.ProdutoRepository;
import org.helpconnect.NossoMercado.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LojaService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LojaRepository lojaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	/* CADASTRA UM USUARIO EM UMA LOJA */
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
	
	/*
	 * CORRECOES:
	 * 	Criar logica para impedir de comprar um produto, caso a pessoa nao esteja cadastrada na loja; <- Pendente
	 * 
	 * AJUSTADO:
	 * 	Diminuicao de estoque ao trocar de comprador;
	 * 	Ajuste de logica no repository na atualizacao de produtos, AINDA PRECISA DE AJUSTES	
	 * 
	 * NOVAS IMPLEMENTENTACOES
	 * 	Criar novo atributo de carrinho, para somar o valor total a pagar <- Pendente, exemplo na API 'menuNegocio'
	 * 	Criacao de metodo para acrescentar e retirar produtos de usuarios, e ajustar estoque
	 * */
	
	/* GERENCIA O ESTOQUE SEMPRE QUE UM NOVO PRODUTO E SELECIONADO */
	public Produto gerenciarEstoque(Produto produto) {
		Optional<Produto> produtoExistente = produtoRepository.findById(produto.getId());
		
		if(produto.getUsuario() != null) {
			Optional<Usuario> usuarioExistente = usuarioRepository.findById(produto.getUsuario().getIdUsuario());
			
			int qtdProdutos = produtoRepository.save(produtoExistente.get()).getQtdProduto();
			
			if(qtdProdutos <= 0) {
				produto.setAtivo(false);
				
				qtdProdutos = produtoRepository.save(produto).getQtdProduto();
			}
			
			if(usuarioExistente.get().getIdUsuario() == produto.getUsuario().getIdUsuario() && produto.isAtivo()) {
				produtoExistente.get().setQtdProduto(produtoExistente.get().getQtdProduto() - 1);
				produtoExistente.get().setAtivo(produto.isAtivo());
				
				produtoRepository.save(produtoExistente.get()).setUsuario(produto.getUsuario());
				
				return produtoRepository.save(produtoExistente.get());
			
			}else if(produto.isAtivo()) {
				produto.setQtdProduto(produtoExistente.get().getQtdProduto() - 1);
				
				return produtoRepository.save(produto);
			
			}else {
				produto.setQtdProduto(produtoExistente.get().getQtdProduto());
				
				return produtoRepository.save(produto);
				
			}
			
		}
		
		return produtoRepository.save(produto);
		
	}

}
