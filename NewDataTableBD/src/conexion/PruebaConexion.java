package conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class PruebaConexion
 */
@WebServlet("/PruebaConexion")
public class PruebaConexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PruebaConexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection=null;
		Conexion miConexion=new Conexion();
		PreparedStatement statement=null;
		ResultSet result=null;
		
		try{
			connection=miConexion.getConnection();
			
			String consulta= "SELECT * FROM persona;";
			if(connection!=null){
				statement=connection.prepareStatement(consulta);
				result=statement.executeQuery();
				
				while(result.next()){
					String datos=" "+result.getInt("documento")+" - "+result.getString("nombre")+"<br/>";
					response.getWriter().append(datos);
					System.out.println(datos);
				}
				miConexion.desconectar();
			}else{
				response.getWriter().append("Verifique que Mysql este encendido...");
			}
		}catch(Exception exc){
			exc.printStackTrace();
			response.getWriter().append(exc.getMessage());
			
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
