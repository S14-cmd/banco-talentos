package com.sonotalento.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@OneToOne
	@JoinColumn(name = "idPerfil")
	private Perfil perfil;
	private String email;
	private String senha;
	private Boolean bolAtivo;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean isBolAtivo() {
		return bolAtivo;
	}
	public void setBolAtivo(boolean bolAtivo) {
		this.bolAtivo = bolAtivo;
	}
	
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", perfil=" + perfil + ", email=" + email + ", senha=" + senha
				+ ", bolAtivo=" + bolAtivo + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bolAtivo, email, id, nome, perfil, senha);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return bolAtivo == other.bolAtivo && Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(perfil, other.perfil)
				&& Objects.equals(senha, other.senha);
	}
}
