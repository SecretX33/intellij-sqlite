public class db{
    public static boolean status=false;

    public static java.sql.Connection getMySQLConnector(){
       Connection connection = null;

        try {
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            
            String serverName = "localhost";    
            String mydatabase ="projeto_jogo";        
            
            String url = String.format("jdbc:mysql://%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",serverName,mydatabase);
            String username = "root";        
            String password = "root";      
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) status = true;
            return connection;

        } catch (ClassNotFoundException e) {  
            System.out.println("O driver especificado nao foi encontrado.\nErro: " + e);
            return null;
        } catch (SQLException e) { 
            System.out.println("Nao foi possivel conectar ao Banco de Dados.\nErro: " + e);
            return null;
        }
    }

    public static boolean closeConnection(){
        try {
            ConexaoMySQL.getConexaoMySQL().close();
            status = false;
            return true;

        } catch (SQLException e){
            System.out.println("Erro ao desconectar; erro: " + e);
            return false;
        }
    }
}
