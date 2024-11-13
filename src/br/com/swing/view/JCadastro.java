package br.com.swing.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.swing.dao.DAO;
import br.com.swing.model.Cliente;
<<<<<<< HEAD
import br.com.swing.model.ModeloTabela;
=======
>>>>>>> ed223e403a3183c22194445e71c2eec1cfafcf14

public class JCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JTextField textFieldTelefone;
	private JTextField textFieldEmail;
	private JTextArea textAreaEndereco;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastro frame = new JCadastro(null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

<<<<<<< HEAD
	public JCadastro(Cliente clienteSelecionado, JPrincipal jprincipal) {
		DAO dao = new DAO();
		
=======
	/**
	 * Create the frame.
	 */
	public JCadastro(Cliente clienteSelecionado, JPrincipal jprincipal) {
		DAO dao = new DAO();
>>>>>>> ed223e403a3183c22194445e71c2eec1cfafcf14
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 377);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelCadastro = new JLabel("Cadastrozudo");
		labelCadastro.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelCadastro.setBounds(185, 11, 111, 18);
		contentPane.add(labelCadastro);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(10, 41, 46, 14);
		contentPane.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 54, 456, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldCpf = new JTextField();
		textFieldCpf.setColumns(10);
		textFieldCpf.setBounds(10, 112, 201, 20);
		contentPane.add(textFieldCpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCpf.setBounds(10, 98, 46, 14);
		contentPane.add(lblCpf);
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(254, 112, 212, 20);
		contentPane.add(textFieldTelefone);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefone.setBounds(254, 98, 71, 14);
		contentPane.add(lblTelefone);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(10, 166, 456, 20);
		contentPane.add(textFieldEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(10, 154, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEndereo.setBounds(10, 208, 76, 14);
		contentPane.add(lblEndereo);
		
		textAreaEndereco = new JTextArea();
		textAreaEndereco.setBounds(10, 223, 456, 46);
		contentPane.add(textAreaEndereco);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(10, 304, 89, 23);
		contentPane.add(btnExcluir);
		btnExcluir.setForeground(new Color(255, 0, 0));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.excuirCliente(clienteSelecionado.getId());
				abrirTelaPrincipal(jprincipal);
			}
		});
		btnExcluir.setVisible(false);
		
		
		JButton btnIncluir = new JButton(clienteSelecionado == null ? "Incluir" :"Alterar");
		btnIncluir.setBounds(377, 304, 89, 23);
		contentPane.add(btnIncluir);
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente cliente = new Cliente(null,textFieldNome.getText(),textFieldCpf.getText(),
						textFieldEmail.getText(),textAreaEndereco.getText(),textFieldTelefone.getText());
				
				if(clienteSelecionado == null) {
					dao.cadastraCliente(cliente);
					abrirTelaPrincipal(jprincipal);
					
				}else {
					dao.alterarCliente(clienteSelecionado.getId(), cliente);
					abrirTelaPrincipal(jprincipal);
				}
			}
		});
		
		if(clienteSelecionado != null) {
			preencherCampos(clienteSelecionado);
			btnExcluir.setVisible(true);
		}
	}
	
	private void preencherCampos(Cliente clienteSelecCliente) {
		textFieldNome.setText(clienteSelecCliente.getNome());
		textFieldCpf.setText(clienteSelecCliente.getCpf());
		textFieldEmail.setText(clienteSelecCliente.getEmail());
		textFieldTelefone.setText(clienteSelecCliente.getTelefone());
		textAreaEndereco.setText(clienteSelecCliente.getEndereco());
	}
	
<<<<<<< HEAD
	public void abrirTelaPrincipal(JPrincipal jprincipal) {
=======
	private void abrirTelaPrincipal(JPrincipal jprincipal) {
>>>>>>> ed223e403a3183c22194445e71c2eec1cfafcf14
		jprincipal.dispose();
		dispose();
		jprincipal = new JPrincipal();
		jprincipal.setLocationRelativeTo(null);
		jprincipal.setVisible(true);
		
	}
	
}
