package br.com.swing.dao;

import java.sql.Connection;

import br.com.swing.controle.Conexao;

public class BD {
	private static Connection connection = null;
	
	public static void main(String[] args) {
		try {
			connection = Conexao.getInstancia().abrirConexao();
			System.out.println("Base criada com sucesso");
			Conexao.getInstancia().fecharConexao();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(05);
		}
		
	}
}
