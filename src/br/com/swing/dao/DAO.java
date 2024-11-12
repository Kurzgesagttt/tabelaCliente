package br.com.swing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.swing.controle.Conexao;
import br.com.swing.model.Cliente;
import br.com.swing.model.Usuario;

public class DAO {
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	private static String CADASTRAR_CLIENTE = "INSERT INTO CLIENTE "
			+ "(NOME,CPF,EMAIL,ENDERECO,TELEFONE) "
			+ "VALUES (?, ? ,? ,? ,? )";
	
	private static String CONSULTA_CLIENTE = "SELECT * FROM CLIENTE "
			+ "WHERE ID = ?";
	
	private static String ALTERAR_CLIENTE = "UPDATE CLIENTE SET"
			+ "NOME = ?, CPF = ?, EMAIL = ?, ENDERECO = ?, TELEFONE = ? "
			+ "WHERE ID = ?";
	private static String DELETAR_CLIENTE = "DELETE FROM CLIENTE "
			+ "WHERE ID = ?";
	
	private static String LISTAR_CLIENTE = "SELECT * FROM CLIENTE "
			+ "WHERE 1=1";
	
	private static String CONSULTAR_USUARIO = "SELECT USUARIO, SENHA "
			+" FROM USUARIO "
			+" WHERE USUARIO = ? "
			+" AND SENHA = ?";
			
	public DAO() {
		
	}
	
	public void cadastraCliente(Cliente cliente) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = CADASTRAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);
			int i = 1;
			preparedStatement.setString(i++, cliente.getNome());
			preparedStatement.setString(i++, cliente.getCpf());
			preparedStatement.setString(i++, cliente.getEmail());
			preparedStatement.setString(i++, cliente.getEndereco());
			preparedStatement.setString(i++, cliente.getTelefone());
			
			preparedStatement.executeUpdate();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Cliente incluido com sucesso");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}	
	}

	public Cliente consultarCliente(String id) throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		Cliente cliente = null;
		String query = CONSULTA_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);
			int i = 1;
			preparedStatement.setString(i++, id);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				//String id, String nome,String cpf, String email, String endereco, String telefone
				cliente = new Cliente(resultSet.getString("ID"),
						resultSet.getString("NOME"),
						resultSet.getString("CPF"),
						resultSet.getString("EMAIL"),
						resultSet.getString("ENDERECO"),
						resultSet.getString("TELEFONE")
						);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}	
		if(cliente == null) {
			JOptionPane
			.showMessageDialog(null, "Nao foi possivel localizar cliente solicitado","",JOptionPane.WARNING_MESSAGE);
			throw new Exception("Nao foi possivel localizar cliente solicitado");
		}
		return cliente;
	}
	
	public void alterarCliente(String id,Cliente cliente) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = ALTERAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			preparedStatement.setString(i++, cliente.getNome());
			preparedStatement.setString(i++, cliente.getCpf());
			preparedStatement.setString(i++, cliente.getEmail());
			preparedStatement.setString(i++, cliente.getEndereco());
			preparedStatement.setString(i++, cliente.getTelefone());
			preparedStatement.setString(i++, id);
			
			preparedStatement.execute();
			
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");

			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}	
	}

	public void excuirCliente(String id) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = DELETAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);			
			preparedStatement.setString(1, id);
			preparedStatement.execute();
			connection.commit();
			JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}	
	}
	
	public ArrayList<Cliente> listarCliente() throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		String query = LISTAR_CLIENTE;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				//String id, String nome,String cpf, String email, String endereco, String telefone
				clientes.add( new Cliente(resultSet.getString("ID"),
						resultSet.getString("NOME"),
						resultSet.getString("CPF"),
						resultSet.getString("EMAIL"),
						resultSet.getString("ENDERECO"),
						resultSet.getString("TELEFONE")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}	
		if(clientes.size() < 0 ) {
			JOptionPane
			.showMessageDialog(null, "Nao ha clientes cadastrados","",JOptionPane.WARNING_MESSAGE);
			throw new Exception("Nao ha clientes cadastrados");
		}
		return clientes;
	}
	
	public Usuario consultarUsuario(String nomeUsuario,String senhaCriptografada) throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		Usuario usuario = null;
		String query = CONSULTAR_USUARIO;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			int i = 1;
			
			preparedStatement.setString(i++, nomeUsuario);
			preparedStatement.setString(i++, senhaCriptografada);

			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				usuario = new Usuario( resultSet.getInt("ID"),
						resultSet.getString("NOME"),
						resultSet.getString("SENHA"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}	
		if(usuario == null) {
			JOptionPane
			.showMessageDialog(null, "Nao foi possivel localizar usuario","",JOptionPane.WARNING_MESSAGE);
			throw new Exception("Nao foi possivel localizar usuario");
		}
		return usuario;
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
