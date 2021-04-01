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
	
	/* GERENCIA O ESTOQUE SEMPRE QUE UM NOVO PRODUTO E SELECIONADO */
	public Produto gerenciarEstoque(Produto produto) {
		Optional<Produto> produtoExistente = produtoRepository.findById(produto.getId());
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(produto.getUsuario().getIdUsuario());
		
		int qtdProdutos = produtoRepository.save(produtoExistente.get()).getQtdProduto();
		
		if(usuarioExistente.get().getIdUsuario() == produtoExistente.get().getUsuario().getIdUsuario() && produto.isAtivo()) {
			if(qtdProdutos <= 0) {
				qtdProdutos = produtoRepository.save(produto).getQtdProduto();
			}
			
			produtoExistente.get().setQtdProduto(produtoExistente.get().getQtdProduto() - 1);
			produtoExistente.get().setAtivo(produto.isAtivo());
			
			produtoRepository.save(produtoExistente.get());
			
			return produtoRepository.save(produtoExistente.get());
			
		}else {
			produto.setQtdProduto(produtoExistente.get().getQtdProduto());
			
			return produtoRepository.save(produto);
		}
	}

}
