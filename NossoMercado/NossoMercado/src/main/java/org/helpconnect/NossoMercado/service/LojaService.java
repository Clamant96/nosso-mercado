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
	
	public Produto gerenciarEstoque(Produto produto) {
		Optional<Produto> produtoExistente = produtoRepository.findById(produto.getId());
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(produto.getUsuario().getIdUsuario());
		
		int qtdProdutos = produtoRepository.save(produtoExistente.get()).getQtdProduto();
		
		if(usuarioExistente.get().getIdUsuario() == produtoExistente.get().getUsuario().getIdUsuario()) {
			if(qtdProdutos <= 0) {
				qtdProdutos = produtoRepository.save(produto).getQtdProduto();
			}
			
			produtoExistente.get().setQtdProduto(produtoExistente.get().getQtdProduto() - 1);
			
			produtoRepository.save(produtoExistente.get());
			
			return produtoRepository.save(produtoExistente.get());
		
		}else {
			if(qtdProdutos <= 0) {
				qtdProdutos = produtoRepository.save(produto).getQtdProduto();
			}
			
			produto.setQtdProduto(qtdProdutos - 1);
			
			produtoRepository.save(produto).getUsuario().getIdUsuario();
			produtoRepository.save(produtoExistente.get()).getQtdProduto();
			
			return produtoRepository.save(produto);
			
		}
	}

}
