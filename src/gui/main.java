package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import jdbc.DB;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class main extends JFrame {

    private JPanel contentPane;
    private JTextField txtHost;
    private JTextField txtBatabase;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JComboBox comboConsulta;
    private JTextPane txtConsulta;
    public DB db;
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
        setIconImage(Toolkit.getDefaultToolkit().getImage(main.class.getResource("/resources/icon_truck.png")));
        setTitle("Grupo 10 - Delivery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 535, 350);
        setLocationRelativeTo(null);
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
        txtHost.setBounds(10, 30, 190, 20);
        panelConexao.add(txtHost);
        txtHost.setColumns(10);
        
        JLabel lblDatabase = new JLabel("Database");
        lblDatabase.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        lblDatabase.setBounds(10, 61, 81, 14);
        panelConexao.add(lblDatabase);
        
        txtBatabase = new JTextField();
        txtBatabase.setText("db_trabalho");
        txtBatabase.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        txtBatabase.setColumns(10);
        txtBatabase.setBounds(10, 80, 190, 20);
        panelConexao.add(txtBatabase);
        
        JLabel lblUsuario = new JLabel("Usu\u00E1rio");
        lblUsuario.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        lblUsuario.setBounds(10, 111, 81, 14);
        panelConexao.add(lblUsuario);
        
        txtUsuario = new JTextField();
        txtUsuario.setText("postgres");
        txtUsuario.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        txtUsuario.setColumns(10);
        txtUsuario.setBounds(10, 130, 190, 20);
        panelConexao.add(txtUsuario);
        
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        lblSenha.setBounds(10, 161, 81, 14);
        panelConexao.add(lblSenha);
        
        JButton btnTestarConexao = new JButton("Conectar");
        btnTestarConexao.setIcon(new ImageIcon(main.class.getResource("/resources/icon_plug.png")));
        btnTestarConexao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                DB dbTentativa = new DB();
                boolean b = dbTentativa.setConnection(txtHost.getText(), txtBatabase.getText(), txtUsuario.getText(),
                                                      txtSenha.getPassword());
                if (b) {
                    db = dbTentativa;
                    JOptionPane.showMessageDialog(null, "Tudo pronto para realizar as consultas!", "Conexão estabelecida",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, dbTentativa.erro, "Oops...", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        btnTestarConexao.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        btnTestarConexao.setBounds(55, 210, 110, 23);
        panelConexao.add(btnTestarConexao);
        
        txtSenha = new JPasswordField();
        txtSenha.setBounds(10, 180, 190, 20);
        panelConexao.add(txtSenha);
        
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setForeground(Color.LIGHT_GRAY);
        separator.setBackground(Color.WHITE);
        separator.setBounds(215, 20, 1, 225);
        panelConexao.add(separator);
        
        JTextPane txtpnAlo = new JTextPane();
        txtpnAlo.setFont(new Font("Roboto Mono", Font.PLAIN, 12));
        txtpnAlo.setBackground(SystemColor.menu);
        txtpnAlo.setEditable(false);
        txtpnAlo.setContentType("text/html");
        txtpnAlo.setText("<center><b>Grupo 10 - Delivery &copy; 2016</b></center>\r\n<p>Simples aplica\u00E7\u00E3o para exibir as consultas em SQL do Grupo 10.</p>\r\n<p>Claudson Martins <a href=\"mailto:claudsonbms@dcomp.ufs.br\">claudsonbms@dcomp.ufs.br</a><br>\r\nEdgar Lima <a href=\"mailto:edgarvln@dcomp.ufs.br\">edgarvln@dcomp.ufs.br</a><br>\r\nGuilherme Boroni <a href=\"mailto:guilhermebp@dcomp.ufs.br\">guilhermebp@dcomp.ufs.br</a></p>");
        txtpnAlo.setBounds(226, 30, 278, 203);
        panelConexao.add(txtpnAlo);
        
        JPanel panelConsultas = new JPanel();
        tabbedPane.addTab("Consultas", null, panelConsultas, null);
        panelConsultas.setLayout(null);
        
        JLabel lblSelecione = new JLabel("Consulta");
        lblSelecione.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        lblSelecione.setBounds(10, 11, 157, 14);
        panelConsultas.add(lblSelecione);
        
        comboConsulta = new JComboBox();
        comboConsulta.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (db == null) {
                        JOptionPane.showMessageDialog(null, "Nenhuma conexão foi estabelecida.\nVá para a aba "
                                + "\"Conexão\", preencha as informações e clique em \"Conectar\".", "Vixe...",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        String sql = db.consultaSQL(comboConsulta.getSelectedIndex());
                        txtConsulta.setText(sql);
                        if (!sql.equals("")) {
                            DefaultTableModel modelo = db.executaConsulta(sql);
                            if (modelo == null) {
                                JOptionPane.showMessageDialog(null, db.erro, "Eita...", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                tabela.setModel(modelo);
                            }
                        }
                    }
                }
            }
        });
        comboConsulta.setToolTipText("");
        comboConsulta.setFont(new Font("Roboto Mono", Font.PLAIN, 14));
        comboConsulta.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma consulta", "Bairro do CEP 08658-075", "Todos os bairros de Sergipe", "Lista de propriet\u00E1rios", "Produtos que cont\u00EAm 'a' e participam de alguma promo\u00E7\u00E3o", "Quantidade de produtos por promo\u00E7\u00E3o", "Soma do valor do pedido pelo id do pedido", "Pedidos com mais de 10 produtos", "Todos os funcionarios da empresa", "Produtos com mais de 10 pedidos"}));
        comboConsulta.setBounds(10, 30, 494, 20);
        panelConsultas.add(comboConsulta);
        
        JLabel lblConsultaSql = new JLabel("Consulta SQL");
        lblConsultaSql.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        lblConsultaSql.setBounds(10, 61, 101, 14);
        panelConsultas.add(lblConsultaSql);
        
        txtConsulta = new JTextPane();
        txtConsulta.setFont(new Font("Roboto Mono", Font.PLAIN, 12));
        txtConsulta.setEditable(false);
        txtConsulta.setBounds(10, 80, 494, 70);
        panelConsultas.add(txtConsulta);
        
        JLabel lblResultados = new JLabel("Resultados");
        lblResultados.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 12));
        lblResultados.setBounds(10, 160, 86, 14);
        panelConsultas.add(lblResultados);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 179, 494, 90);
        panelConsultas.add(scrollPane);
        
        tabela = new JTable();
        tabela.setFont(new Font("Roboto Mono", Font.PLAIN, 12));
        scrollPane.setViewportView(tabela);
    }
}
