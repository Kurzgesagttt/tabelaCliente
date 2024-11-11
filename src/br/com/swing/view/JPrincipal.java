package br.com.swing.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.swing.model.Cliente;
import br.com.swing.model.ModeloTabela;

@SuppressWarnings("serial")
public class JPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private ArrayList<Cliente> clientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPrincipal frame = new JPrincipal();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JPrincipal() {
		clientes = new ArrayList<>();
		clientes.add(new Cliente("1","Lucas","472992","Lcuas@ghmail","1499143982","nao informado"));
		clientes.add(new Cliente("2","Maria","999999","maria@ghmail","1499143982","Ramos Pereria"));
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 494);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastraCliente = new JButton("Cadastrar Cliente");
		btnCadastraCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastraCliente.setBounds(30, 55, 117, 23);
		contentPane.add(btnCadastraCliente);
		
		textField = new JTextField();
		textField.setBounds(157, 55, 193, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 117, 606, 296);
		contentPane.add(scrollPane);
		
		ModeloTabela modeloTabela = new ModeloTabela(clientes);
		
		table = new JTable();
		table.setModel(modeloTabela);
		scrollPane.setViewportView(table);
		
	}
}
