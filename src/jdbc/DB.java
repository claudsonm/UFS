package jdbc;

import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DB {
    
    private String schema = "grupo10_delivery";
    private String url;
    private Connection cnx;
    
    public String erro;
    
    public boolean setConnection(String host, String db, String user, char[] pass) {
        try {
            this.url = "jdbc:postgresql://" + host + "/" + db + "?currentSchema=" + schema;
            this.cnx = DriverManager.getConnection(url, user, new String(pass));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            this.erro = e.toString();
            return false;
        }
    }
    
    public DefaultTableModel executaConsulta(String sql) {
        try {
            Statement comando = cnx.createStatement();
            ResultSet resultado = comando.executeQuery(sql);
            ResultSetMetaData metaData = resultado.getMetaData();
            
            // Nome das colunas
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }
            
            // Dados da tabela
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (resultado.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(resultado.getObject(columnIndex));
                }
                data.add(vector);
            }

            comando.close();
            return new DefaultTableModel(data, columnNames);
        } catch (SQLException e) {
            e.printStackTrace();
            this.erro = e.toString();
            return null;
        }
    }
    
    public String consultaSQL(int opcao) {
        String r;
        switch (opcao) {
        case 1:
            r = "SELECT nome FROM bairros b " +
                "JOIN cep c ON c.bairros_id = b.id " +
                "WHERE cep = '08658-075'";
            break;
        
        case 2:
            r = "SELECT nome FROM bairros WHERE estado = 'SE'";
            break;
        
        case 3:
            r = "SELECT u.nome FROM proprietarios p " +
                "NATURAL JOIN usuarios u " +
                "JOIN empresas e ON p.id = e.proprietarios_id";
            break;
        
        case 4:
            r = "SELECT pr.nome, p.nome,p.inicio,p.fim from produtos_participantes pp " +
                "JOIN promocoes p on p.id = pp.promocoes_id " +
                "JOIN produtos pr on pr.id = pp.produtos_id " +
                "WHERE pr.nome like '%a%'";
            break;
        
        case 5:
            r = "SELECT p.nome, COUNT(pp.promocoes_id) FROM promocoes p " +
                "INNER JOIN produtos_participantes pp " +
                "ON pp.promocoes_id = p.id " +
                "GROUP BY pp.promocoes_id,p.nome";
            break;
        
        case 6:
            r = "SELECT SUM(p.valor*ip.quantidade) as conta FROM itens_pedido ip " +
                "JOIN produtos p ON p.id = ip.produtos_id " +
                "WHERE ip.pedidos_id = 300";
            break;
        
        case 7:
            r = "select *, (SELECT count(*) FROM itens_pedido ip WHERE p.id = ip.pedidos_id) as quantidade from pedidos p " +
                "where  (SELECT count(*) FROM itens_pedido ip WHERE p.id = ip.pedidos_id) > 10 " +
                "AND situacao = 'FINALIZADO'";
            break;
        
        case 8:
            r = "SELECT u.nome FROM funcionarios f " +
                "JOIN usuarios u ON u.id = f.usuarios_id " +
                "WHERE empresas_id = 2 " +
                "ORDER BY u.nome DESC";
            break;
        
        case 9:
            r = "SELECT p.nome FROM itens_pedido ip " +
                "JOIN produtos p on ip.produtos_id = p.id " +
                "GROUP BY ip.produtos_id,  p.nome " +
                "HAVING count(ip.produtos_id)>10";
            break;
            
        default:
            r = "";
            break;
        }
        return r;
    }
    
}
