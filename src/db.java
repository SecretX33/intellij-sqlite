import java.sql.*;
import java.io.File;

public class db{
    public static boolean status=false;
    private static Connection connection = null;

    public static boolean insert(Cliente c){
        try {
            if(connection == null) connection = connect();
            String query = "INSERT INTO clientes VALUES(?,?,?)";
            PreparedStatement prepstate = connection.prepareStatement(query);

            prepstate.setString(1,c.nome);
            prepstate.setInt(2,c.idade);
            prepstate.setString(3,c.telefone);

            prepstate.executeUpdate();
            System.out.println("[DB] Data has been inserted successfully.");
            return true;
        }
        catch (SQLException e){
            System.out.println("[DB] Error while trying to insert data into DB.\nError: "+ e.getMessage());
            return false;
        }
    }

    public static void show(){
        try {
            if(connection == null) connection = connect();
            String query = "SELECT * FROM clientes";
            PreparedStatement prepstate = connection.prepareStatement(query);
            ResultSet result = prepstate.executeQuery();

            if(result==null){
                System.out.println("[DB] There's no data on table clientes.");
            }
            else{
                System.out.println("\nDADOS TABELA CLIENTES.\n");
                while(result.next()){
                    System.out.println("Nome: " + result.getString("nome"));
                    System.out.println("Idade: " + result.getString("idade"));
                    System.out.println("Telefone: " + result.getString("telefone"));
                    System.out.println();
                }
            }
        }
        catch (SQLException e){
            System.out.println("[DB] Error while trying to request data into DB.\nError: "+ e.getMessage());
        }
    }

    public static boolean update(Cliente c){
        try {
            if(connection == null) connection = connect();
            String query = "UPDATE clientes SET idade=?,telefone=? WHERE nome=?";
            PreparedStatement prepstate = connection.prepareStatement(query);

            prepstate.setInt(1,c.idade);
            prepstate.setString(2,c.telefone);
            prepstate.setString(3,c.nome);

            prepstate.executeUpdate();
            System.out.println("[DB] Data has been updated successfully.");
            return true;
        }
        catch (SQLException e){
            System.out.println("[DB] Error while trying to update some data into DB.\nError: "+ e.getMessage());
            return false;
        }
    }

    public static boolean delete(String nome){
        try {
            if(connection == null) connection = connect();
            String query = "DELETE FROM clientes WHERE nome=?";
            PreparedStatement prepstate = connection.prepareStatement(query);

            prepstate.setString(1,nome);
            prepstate.executeUpdate();
            System.out.println("[DB] Data has been deleted successfully.");
            return true;
        }
        catch (SQLException e){
            System.out.println("[DB] Error while trying to delete some data into DB.\nError: "+ e.getMessage());
            return false;
        }
    }

    public static void createNewDatabase(String url){

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("[DB] The driver name is " + meta.getDriverName());
                System.out.println("[DB] A new database has been created.");
                setTables(url);
            }

        } catch (SQLException e) {
            System.out.println("[DB] Error while trying to create a new database.\nError: " + e.getMessage());
        }
    }

    public static void setTables(String url){
        String query = "CREATE TABLE IF NOT EXISTS clientes(nome VARCHAR(50) PRIMARY KEY, idade INT, telefone VARCHAR(20))";

        try (Connection conn = DriverManager.getConnection(url)){
            if(conn != null){
                PreparedStatement prepstate = conn.prepareStatement(query);
                prepstate.executeUpdate();
                System.out.println("[DB] Tables has been successfully created.");
            }
        } catch (SQLException e) {
            System.out.println("[DB] Error while trying to set tables.\nError: " + e.getMessage());
        }
    }

    public static java.sql.Connection connect(){
        try {
            String path = "sqlite\\db.db";
            String url = String.format("jdbc:sqlite:" + path);
            if(!new File(path).exists()) createNewDatabase(url);

            connection = DriverManager.getConnection(url);

            if (connection != null){
                status = true;
                System.out.println("[DB] Connection to SQLite has been established.");
            }
            else{
                status = false;
                System.out.println("[DB] Connection to SQLite has failed.");
            }
            return connection;

        } catch (SQLException e) {
            System.out.println("[DB] Connection to SQLite has failed.\nError: " + e);
            return null;
        }
    }

    public static boolean disconnect(){
        try {
            if(connection != null) connection.close();
            status = false;
            return true;

        } catch (SQLException e){
            System.out.println("[DB] Error while trying to disconnect.\nError: " + e);
            return false;
        }
    }
}
