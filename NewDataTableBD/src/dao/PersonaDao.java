package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import vo.Persona;

public class PersonaDao {
	public String agregarPersona(Persona miPersona){
		String resultado="";
		
		Connection connection=null;
		Conexion conexion=new Conexion();
		PreparedStatement preStatement=null;
		
		connection=conexion.getConnection();
		String consulta="INSERT INTO persona (documento,nombre,apellido,salario,sexo)"+"VALUES(?,?,?,?,?)";
		
		try{
			preStatement=connection.prepareStatement(consulta);
			preStatement.setString(1, miPersona.getDocumento());
			preStatement.setString(2, miPersona.getNombre());
			preStatement.setString(3, miPersona.getApellido());
			preStatement.setString(4, miPersona.getSalario()+"");
			preStatement.setString(5, miPersona.getSexo());
			preStatement.execute();
			
			resultado="Registro exitoso";
		}catch(SQLException e){
			System.out.println("No se pudo registrar la persona: " + e.getMessage());
			resultado="No se pudo registrar";
		}finally{
			conexion.desconectar();
		}
		return resultado;
	}
	
	public ArrayList<Persona> obtenerListaPersonas(){
		Connection connection=null;
		Conexion conexion=new Conexion();
		PreparedStatement preStatement=null;
		ResultSet result=null;
		Persona miPersona=new Persona();
		ArrayList<Persona> listaPersonas=null;
		
		connection=conexion.getConnection();
		String consulta="SELECT * FROM persona";
		
		try{
			if(connection!=null){
				listaPersonas=new ArrayList<>();
				preStatement = connection.prepareStatement(consulta);
				result=preStatement.executeQuery();
				
				while(result.next()== true){
					miPersona=new Persona();
					miPersona.setDocumento(result.getString("documento"));
					miPersona.setNombre(result.getString("nombre"));
					miPersona.setApellido(result.getString("apellido"));
					miPersona.setSalario(Double.parseDouble(result.getString("salario")));
					miPersona.setSexo(result.getString("sexo"));
					listaPersonas.add(miPersona);
				}
				
			}
		}catch (SQLException e) {
			System.out.println("Error en la consulta de usuario: " + e.getMessage());
		}finally {
			conexion.desconectar();
		}
		return listaPersonas;
	}
	
	public String editarPersona(Persona persona){
String resultado="";
		
		Connection connection=null;
		Conexion conexion=new Conexion();
		PreparedStatement preStatement=null;
		
		connection=conexion.getConnection();
		
		
		try{
			String consulta="UPDATE persona SET nombre = ?, apellido=? , salario=? , sexo=? WHERE documento = ?";
			preStatement=connection.prepareStatement(consulta);
			preStatement.setString(1, persona.getNombre());
			preStatement.setString(2, persona.getApellido());
			preStatement.setString(3, persona.getSalario()+"");
			preStatement.setString(4, persona.getSexo());
			preStatement.setString(5, persona.getDocumento());
			preStatement.executeUpdate();
			
			resultado="Se ha actualizado la persona satisfactoriamente";
			conexion.desconectar();
		}catch(SQLException e){
			System.out.println(e);
			resultado="No se pudo actualizar la persona";
		}
		return resultado;
	}
	
	public String eliminarPersona(Persona persona){
		Connection connection=null;
		Conexion conexion=new Conexion();
		connection = conexion.getConnection();
		
		String resp="";
		
		try {
			String sentecia="DELETE FROM persona WHERE documento = ?";
			PreparedStatement preStatement=connection.prepareStatement(sentecia);
			preStatement.setString(1, persona.getDocumento());
			preStatement.executeUpdate();
			
			resp="Se ha eliminado exitosamente";
			preStatement.close();
			conexion.desconectar();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			resp="no se pudo eliminar";
		}
		return resp;
	}
	
	public ArrayList<Persona> consultarPersonaIndividual(String documento){

			Connection connection=null;
			Conexion conexion=new Conexion();
			PreparedStatement preStatement=null;
			ResultSet result=null;
			Persona miPersona=new Persona();
			ArrayList<Persona> listaPersonas=null;
			connection=conexion.getConnection();
			
				String consulta="SELECT * FROM persona WHERE documento like '%"+documento+"%'";
				try {
					if(connection!=null){
						listaPersonas= new ArrayList<>();
						preStatement = connection.prepareStatement(consulta);
						result=preStatement.executeQuery();
						
						if(result.next() == true){
							miPersona= new Persona();
							miPersona.setDocumento(result.getString("documento"));
							miPersona.setNombre(result.getString("nombre"));
							miPersona.setApellido(result.getString("apellido"));
							miPersona.setSalario(Double.parseDouble(result.getString("salario")));
							miPersona.setSexo(result.getString("sexo"));
							listaPersonas.add(miPersona);
							
						}
				}
				}catch (Exception e) {
					System.out.println("Error en la consulta de usuario: " +e.getMessage());
				}finally {
					conexion.desconectar();
				}
				return listaPersonas;	
			
		}
	}
