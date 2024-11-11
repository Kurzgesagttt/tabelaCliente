package br.com.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import br.com.swing.controle.Conexao;
import br.com.swing.model.Cliente;

public class DAO {
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	private static String DRIVER = "org.sqlite.JDBC";
	private static String BD = "jdbc:sqlite:resources/bdclientes.bd";
	
	private static String CADASTRAR_CLIENTE = "INSERT INTO CLIENTE "
			+ "(ID,NOME,CPF,EMAIL,ENDERECO,TELEFONE "
			+ "VALUES (?, ? ,? ,? ,? ,? )";
	
	private static String CONSULTA_CLIENTE = "SELECT * FROM CLIENTE "
			+ "WHERE ID = ?";
	
	private static String ALTERAR_CLIENTE = "UPDATE CLIENTE "
			+ "NOME = ?, CPF = ?, EMAIL = ?, ENDERECO = ?, TELEFONE = ? "
			+ "WHERE ID = ?";
	private static String DELETAR_CLIENTE = "DELETE CLIENTE "
			+ "WHERE ID = ?";
	
	private static String LISTAR_CLIENTE = "SELECT * FROM CLIENTE "
			+ "WHERE 1=1";
	
	private static String CONSULTAR_USUARIO = "SELECT USUARIO, SENHA "
			+" FROM USUARIO "
			+" WHERE USUARIO = ? "
			+" SND SENHA = ?";
			
	public DAO() {
		
	}
	
	public void cadastraCliente(Cliente cliente) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = CADASTRAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);
			int i = 0;
			preparedStatement.setString(i++, cliente.getNome());
			preparedStatement.setString(i++, cliente.getCpf());
			preparedStatement.setString(i++, cliente.getEmail());
			preparedStatement.setString(i++, cliente.getEndereco());
			preparedStatement.setString(i++, cliente.getTelefone());
			
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Cliente incluido com sucesso");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}
		
		
	}

	private void fecharConexao() {
		try {
			if(resultSet != null) {
				resultSet.close();
			}
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			Conexao.getInstancia().fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
