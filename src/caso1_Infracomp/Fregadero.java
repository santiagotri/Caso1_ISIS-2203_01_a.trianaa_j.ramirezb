package caso1_Infracomp;
import java.util.*;

public class Fregadero {
	
	private int tamFregadero;
	
	
	private Queue<Cubierto> cubiertos;
	
	
	public Fregadero (int tamFregadero) {
		this.tamFregadero = tamFregadero;
		cubiertos = new LinkedList<>();
	}
	
	public int darTamFregadero () {
		return tamFregadero;
	}
	
	public void agregarCubierto (Cubierto cubiertoNuevo) {
		cubiertos.add(cubiertoNuevo);
		mensaje("llego cubierto de id: " + cubiertoNuevo.darId() + "(nueva cantidad "+ cubiertos.size() + ")");
	}
	
	public void mensaje (String mensaje) {
		System.out.println("Fregadero: " + mensaje);
	}
	
	public Cubierto darCubierto () {
		Cubierto rta = cubiertos.poll();
		mensaje("Se paso a lavar el cubierto con id: " + rta.darId() );
		return rta;
	}

	public boolean hayEspacio() {
		if(darTamFregadero()-cubiertos.size()>=2) return true;
		return false;
	}
	
	public boolean hayCubiertosParaLavar() {
		if(cubiertos.size()>0) return true;
		return false;
	}

}
