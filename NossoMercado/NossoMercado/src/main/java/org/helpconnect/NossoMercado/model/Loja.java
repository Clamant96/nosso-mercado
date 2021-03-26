package org.helpconnect.NossoMercado.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "loja")
public class Loja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idLoja;
	
	@NotNull
	@Size(max = 45)
	private String nomeLoja;
	
	@NotNull
	@Size(max = 100)
	private String descricaoLoja;
	
	@OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("loja")
	private List<Produto> produto;
	
	@ManyToOne
	@JsonIgnoreProperties("loja")
	private Usuario usuario;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
	  name = "inscricao", 
	  joinColumns = @JoinColumn(name = "loja_id"), 
	  inverseJoinColumns = @JoinColumn(name = "usuario_id")
	  )
	@JsonIgnoreProperties({"nome", "usuario", "senha", "produto", "loja", "inscricoes"})
	private List<Usuario> usuarios = new ArrayList<>();

	public long getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(long idLoja) {
		this.idLoja = idLoja;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public String getDescricaoLoja() {
		return descricaoLoja;
	}

	public void setDescricaoLoja(String descricaoLoja) {
		this.descricaoLoja = descricaoLoja;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
