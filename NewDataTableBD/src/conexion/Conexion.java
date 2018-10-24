package conexion;
import java.sql.*;
public class Conexion {
	private String nombreBd="data_table_bd";
	private String usuario="root";
	private String password="";
	private String url="jdbc:mysql://localhost:3306/"+nombreBd;
	
	Connection conn=null;
	
	public Conexion(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,usuario,password);
			if (conn != null) {
				System.out.println("Conexion exitosa a la BD: "+nombreBd);
			}else{
				System.out.println("*******************NO SE PUDO CONECTAR "+nombreBd);
			}
		}catch (ClassNotFoundException e){
			System.out.println("ocurre una ClassNotFoundException : "+e.getMessage());
		}catch (SQLException e){
			System.out.println("ocurre una SQLException: "+e.getMessage());
			System.out.println("Verifique que Mysql este encendido...");
		}
	}
	
	public Connection getConnection(){
		return conn;
	}
	public void desconectar(){
		conn=null;
	}
	
}
