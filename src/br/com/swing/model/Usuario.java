package br.com.swing.model;

public class Usuario {
	private int id;
	private String nome;
	private String Senha;
	public Usuario(int id, String nome, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		Senha = senha;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	
	
}
