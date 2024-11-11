package br.com.swing.controle;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private static Conexao instancia;
	private static String DRIVER = "org.sqlite.JDBC";
	private static String BD = "jdbc:sqlite:resources/bdclientes.bd";
	private static Connection conexao;
	
	private Conexao() {
		
		
	}
	
	public static Conexao getInstancia() {
		if(instancia == null) {
			instancia = new Conexao();
		}
		return instancia;
	}
	
	public Connection abrirConexao() {
		try {
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(BD);
			conexao.setAutoCommit(false);
		}catch (Exception e) {
			
			System.out.println("Erro ao conectar ao BD" +  e.getMessage());;
		}
		return conexao;
	}
	
	public void fecharConexao() {
		try {
			if(conexao != null && !conexao.isClosed()) {
				conexao.close();
			}
		} catch (Exception e) {
			System.out.println("Erro ao fechar ao BD" +  e.getMessage());;
		}finally {
			conexao = null;
		}
		
	}
	
}
