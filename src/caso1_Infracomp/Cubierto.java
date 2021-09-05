package caso1_Infracomp;

public class Cubierto extends Object{

	private int id;
	
	private String descripcion;
	
	public Integer darId() {return id;}
	
	public String darDescripcion() {return descripcion;}
	
	public Cubierto(int id, String pDescripcion) {
		this.id = id;
		this.descripcion = pDescripcion;
	}
	
	
	
}
