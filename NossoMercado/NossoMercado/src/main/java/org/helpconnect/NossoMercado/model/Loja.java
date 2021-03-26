package org.helpconnect.NossoMercado.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

}
