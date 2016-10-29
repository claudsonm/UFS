package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JTable;

public class main extends JFrame {

    private JPanel contentPane;
    private JTextField txtHost;
    private JTextField txtBatabase;
    private JTextField txtUsuario;
    private JTextField txtSenha;
    private JTable tabela;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    main frame = new main();
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
    public main() {
        setTitle("Grupo 10 - Delivery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 325);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Roboto Mono", Font.PLAIN, 12));
        contentPane.add(tabbedPane, BorderLayout.CENTER);
        
        JPanel panelConexao = new JPanel();
        tabbedPane.addTab("Conex\u00E3o", null, panelConexao, null);
        panelConexao.setLayout(null);
        
        JLabel lblHost = new JLabel("Host");
        lblHost.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        lblHost.setBounds(10, 11, 46, 14);
        panelConexao.add(lblHost);
        
        txtHost = new JTextField();
        txtHost.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        txtHost.setText("127.0.0.1");
        txtHost.setBounds(10, 30, 210, 20);
        panelConexao.add(txtHost);
        txtHost.setColumns(10);
        
        JLabel lblDatabase = new JLabel("Database");
        lblDatabase.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        lblDatabase.setBounds(10, 61, 81, 14);
        panelConexao.add(lblDatabase);
        
        txtBatabase = new JTextField();
        txtBatabase.setText("bd_trabalho");
        txtBatabase.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        txtBatabase.setColumns(10);
        txtBatabase.setBounds(10, 80, 210, 20);
        panelConexao.add(txtBatabase);
        
        JLabel lblUsuario = new JLabel("Usu\u00E1rio");
        lblUsuario.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        lblUsuario.setBounds(10, 111, 81, 14);
        panelConexao.add(lblUsuario);
        
        txtUsuario = new JTextField();
        txtUsuario.setText("aluno");
        txtUsuario.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        txtUsuario.setColumns(10);
        txtUsuario.setBounds(10, 130, 210, 20);
        panelConexao.add(txtUsuario);
        
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        lblSenha.setBounds(10, 161, 81, 14);
        panelConexao.add(lblSenha);
        
        txtSenha = new JTextField();
        txtSenha.setText("aluno");
        txtSenha.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        txtSenha.setColumns(10);
        txtSenha.setBounds(10, 180, 210, 20);
        panelConexao.add(txtSenha);
        
        JButton btnTestarConexo = new JButton("Testar Conex\u00E3o");
        btnTestarConexo.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        btnTestarConexo.setBounds(40, 211, 150, 23);
        panelConexao.add(btnTestarConexo);
        
        JPanel panelConsultas = new JPanel();
        tabbedPane.addTab("Consultas", null, panelConsultas, null);
        panelConsultas.setLayout(null);
        
        JLabel lblSelecione = new JLabel("Consulta");
        lblSelecione.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        lblSelecione.setBounds(10, 11, 157, 14);
        panelConsultas.add(lblSelecione);
        
        JComboBox comboConsulta = new JComboBox();
        comboConsulta.setToolTipText("");
        comboConsulta.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        comboConsulta.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma consulta", "Bairro do CEP 08658-075", "Todos os bairros de Sergipe", "Lista de propriet\u00E1rios", "Produtos que cont\u00EAm 'a' e participam de alguma promo\u00E7\u00E3o", "Quantidade de produtos por promo\u00E7\u00E3o", "Soma do valor do pedido pelo id do pedido", "Pedidos com mais de 10 produtos", "Todos os funcionarios da empresa", "Produtos com mais de 10 pedidos"}));
        comboConsulta.setBounds(10, 30, 400, 20);
        panelConsultas.add(comboConsulta);
        
        JLabel lblConsultaSql = new JLabel("Consulta SQL");
        lblConsultaSql.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        lblConsultaSql.setBounds(10, 61, 101, 14);
        panelConsultas.add(lblConsultaSql);
        
        JTextPane txtpnCo = new JTextPane();
        txtpnCo.setEditable(false);
        txtpnCo.setBounds(10, 80, 400, 90);
        panelConsultas.add(txtpnCo);
        
        JLabel lblResultados = new JLabel("Resultados");
        lblResultados.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        lblResultados.setBounds(10, 180, 86, 14);
        panelConsultas.add(lblResultados);
        
        tabela = new JTable();
        tabela.setFont(new Font("Roboto Mono", Font.PLAIN, 12));
        tabela.setBounds(10, 199, 400, 45);
        panelConsultas.add(tabela);
    }
}
