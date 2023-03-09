import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexion {

    public String connectionUrl;
    public Connection connection;
    public PreparedStatement sentencia;
    public ResultSet resultado;

    public void Conexion (){
        try {
            connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=BDNomina;trustServerCertificate=true";//con trust ServerCertificate=true le reafirmo al cliente que puede confiar en el certificado del servidor
            connection = DriverManager.getConnection(connectionUrl, "sa", "HCKatv79");

            System.out.println("Conexión exitosa!");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error de conexión con la base de datos \n(" + e.getMessage()+")");
        }
    }

}
