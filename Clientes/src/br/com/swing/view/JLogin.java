package br.com.swing.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.swing.controle.Criptografia;

public class JLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLogin frame = new JLogin();
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(92, 11, 243, 215);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblNewLabel.setBounds(47, 63, 76, 14);
		panel.add(lblNewLabel);
		
		userField = new JTextField();
		userField.setBounds(47, 78, 150, 20);
		panel.add(userField);
		userField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Entrar");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(102, 11, 52, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setBounds(47, 111, 89, 14);
		panel.add(lblSenha);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Criptografia criptografia = new Criptografia(passwordField.getText(), Criptografia.MD5);
				System.out.println(criptografia.criptografar());
				
				if(userField.getText() != null && !userField.getText().isEmpty()
						&& passwordField.getText() != null
						&& !passwordField.getText().isEmpty()) {
					if(criptografia.criptografar().equals("202CB962AC59075B964B07152D234B70")) {
						
					JOptionPane.showMessageDialog(btnNewButton, "Informações válidas");
					dispose();//fecha a tela anterior
					
					JPrincipal jprincipal= new JPrincipal();
					jprincipal.setLocationRelativeTo(null);
					jprincipal.setVisible(true);//abre a outra tela Jprincipal
					}
					
				}else {
					JOptionPane.showMessageDialog(btnNewButton, "Verifique as informações","Aviso",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setForeground(Color.PINK);
		btnNewButton.setBounds(86, 181, 89, 23);
		panel.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(47, 127, 150, 20);
		panel.add(passwordField);
	}
}
