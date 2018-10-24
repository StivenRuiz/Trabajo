package bean;
import java.util.ArrayList;

import javax.faces.bean.SessionScoped;

import dao.PersonaDao;

import javax.faces.bean.ManagedBean;
import vo.Persona;


@ManagedBean
@SessionScoped
public class TablaBean {
	
	private static ArrayList<Persona> listaPersona= new ArrayList<>();
	

	private Persona miPersona;
	PersonaDao personaDao;
	private String msjBD;
	private boolean mostrarTabla = true;
	private boolean consulta = false;
	
	

	public TablaBean(){
		miPersona= new Persona();
		personaDao= new PersonaDao();
		cargarPersonas();
	}
	
	public void cargarPersonas(){
		setListaPersona(personaDao.obtenerListaPersonas());
		if(listaPersona==null){
			setMsjBD("No se pudo conectar, verifique que la Base de Datos "+"se encuentre iniciada");
		}
	}
	
	public static void setListaPersona(ArrayList<Persona> listaPersona) {
		TablaBean.listaPersona = listaPersona;
	}

	public void consultaPersona(){
		setListaPersona(personaDao.consultarPersonaIndividual(miPersona.getDocumento()));
		if(miPersona!=null){
			setMsjBD("");
		}else {
			setMsjBD("No se encuentra la persona");
			miPersona=new Persona();
		}
	}
	

	public ArrayList<Persona> getListaPersona(){
		return listaPersona;
	}
	
	public void agregarPersona(){
		listaPersona.add(miPersona);
		setMsjBD(personaDao.agregarPersona(miPersona));
		miPersona= new Persona();
	}
	
	public void eliminarPersona(Persona persona){
		setMsjBD(personaDao.eliminarPersona(persona));
		listaPersona.remove(persona);
	}
	public String editarPersona(Persona persona){
		msjBD="ingresa";
		persona.setEditar(true);
		 return "index";
	}
	public void guardarPersona(Persona persona){
		setMsjBD(personaDao.editarPersona(persona));
		persona.setEditar(false);
		//for(Persona persona : listaPersona){
		//	persona.setEditar(false);
		//}
	}
	public Persona getMiPersona(){
		return miPersona;
	}
	public void setMiPersona(Persona miPersona){
		this.miPersona= miPersona;
	}
	
	public String getMsjBD(){
		return msjBD;
	}
	
	public void setMsjBD(String msjBD){
		this.msjBD=msjBD;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}
	public boolean isConsulta() {
		return consulta;
	}

	public void setConsulta(boolean consulta) {
		this.consulta = consulta;
	}

}
