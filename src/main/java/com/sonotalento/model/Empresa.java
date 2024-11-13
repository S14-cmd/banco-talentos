package com.sonotalento.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {
	
	private static final long serialVersionUID = -4982315001890423938L;
	
	@Id
	private Long cnpj;
	private String nome;
	private String descricao;
	
	
	public Long getCnpj() {
		return cnpj;
	}
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	@Override
	public String toString() {
		return "Empresa [cnpj=" + cnpj + ", nome=" + nome + ", descricao=" + descricao + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cnpj, descricao, nome);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(nome, other.nome);
	}
}
