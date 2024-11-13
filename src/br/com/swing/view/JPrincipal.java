package br.com.swing.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import br.com.swing.dao.DAO;
import br.com.swing.model.Cliente;
import br.com.swing.model.ModeloTabela;

@SuppressWarnings("serial")
public class JPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textProcura;
	private JTable table;
	private ArrayList<Cliente> clientes;
	private JPrincipal jprincipal;

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
		DAO dao = new DAO();
		this.jprincipal = this;
		try {
			clientes = dao.listarCliente();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 702, 562);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastraCliente = new JButton("Cadastrar Cliente");
		btnCadastraCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCadastro jcadastro = new JCadastro(null,jprincipal);
				jcadastro.setLocationRelativeTo(null);
				jcadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				jcadastro.setVisible(true);
			}
		});
		btnCadastraCliente.setBounds(30, 11, 117, 23);
		contentPane.add(btnCadastraCliente);
		
		textProcura = new JTextField();
		textProcura.setBorder(new LineBorder(new Color(0, 0, 0)));
		textProcura.setBounds(160, 67, 193, 23);
		contentPane.add(textProcura);
		textProcura.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 117, 606, 296);
		contentPane.add(scrollPane);
		
		ModeloTabela modeloTabela = new ModeloTabela(clientes);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textProcura.getText();
				try {
					Cliente clienteEncontrado = dao.consultarCliente(id);
					ArrayList<Cliente> listaClientes;
					
					if(clienteEncontrado != null) {
						listaClientes = new ArrayList<>();;
						listaClientes.add(clienteEncontrado);
						
					}else{
						listaClientes = dao.listarCliente();
					}
					modeloTabela.setCliente(listaClientes);
					modeloTabela.fireTableDataChanged();
					
					
				} catch (Exception e1) {
					System.out.println("Nao encontrado");
					e1.printStackTrace();
				}
			}
		});
		btnPesquisar.setBounds(30, 67, 117, 23);
		contentPane.add(btnPesquisar);
		
		//acoes na tabela
		table = new JTable();
		table.setModel(modeloTabela); 
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {  
				if(e.getButton() == 1) {// 1 é o botao esquerdo
					try {
						Cliente clienteSelecionado =
								dao.consultarCliente(modeloTabela.
										getValueAt(table.getSelectedRow(), 0).toString());
						
						JCadastro jcadastro = new JCadastro(clienteSelecionado,jprincipal);
						
						jcadastro.setLocationRelativeTo(null);
						jcadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						jcadastro.setVisible(true);
						
					} catch (Exception e1) {			
						e1.printStackTrace();
					}
				}
			}
		});;
		
		scrollPane.setViewportView(table);
	}
	
	
	
}
