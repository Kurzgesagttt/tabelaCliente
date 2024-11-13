package br.com.swing.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ModeloTabela extends AbstractTableModel{

	public ModeloTabela(ArrayList<Cliente> clientes) {
		super();
		this.clientes = clientes;
	}

<<<<<<< HEAD
	
=======
>>>>>>> ed223e403a3183c22194445e71c2eec1cfafcf14
	private static final String[] colunas = {"ID", "Nome", "CPF", "Email", "Telefone", "Endereco"};
	
	private ArrayList<Cliente> clientes; 

	@Override
	public int getRowCount() {
		return clientes.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Cliente cliente = clientes.get(rowIndex);
		if(columnIndex == 0) {
			return cliente.getId();
		}else if(columnIndex == 1) {
			return cliente.getNome();
		}else if(columnIndex == 2) {
			return cliente.getCpf();
		}else if(columnIndex == 3) {
			return cliente.getEmail();
		}else if(columnIndex == 4) {
			return cliente.getTelefone();
		}else if(columnIndex == 5) {
			return cliente.getEndereco();
		}else {
			return null;
		}
<<<<<<< HEAD
	}
	
	public void setCliente(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	
=======

	}
>>>>>>> ed223e403a3183c22194445e71c2eec1cfafcf14
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
}
