package vo;

public class Persona {
	private String documento;
	private String nombre;
	private String Apellido;
	private double salario;
	private String sexo;
	private boolean editar;
	public Persona(){
		
	}
	
	public Persona(String documento, String nombre, String apellido, double salario,	String sexo, boolean editar){
		super();
		this.documento=documento;
		this.nombre=nombre;
		Apellido=apellido;
		this.salario=salario;
		this.sexo=sexo;
		this.editar=editar;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}


	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
}
