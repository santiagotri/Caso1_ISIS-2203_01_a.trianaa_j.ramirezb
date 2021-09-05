package caso1_Infracomp;
import java.util.*;

public class Fregadero extends Object{
	
	private int tamFregadero;
	
	
	private Queue<Cubierto> cubiertos;
	
	private boolean imprimirMensajes;
	
	
	public Fregadero (int tamFregadero, boolean imprimirMensajes) {
		this.tamFregadero = tamFregadero;
		cubiertos = new LinkedList<>();
		this.imprimirMensajes=imprimirMensajes;
	}
	
	public int darTamFregadero () {
		return tamFregadero;
	}
	
	public void agregarCubierto (Cubierto cubiertoNuevo) {
		cubiertos.add(cubiertoNuevo);
		mensaje("llego cubierto de id: " + cubiertoNuevo.darId() + "(nueva cantidad "+ cubiertos.size() + ")");
	}
	
	public void mensaje (String mensaje) {
		if(imprimirMensajes)System.out.println("Fregadero: " + mensaje);
	}
	
	public synchronized Cubierto darCubierto () {
		Cubierto rta = cubiertos.poll();
		mensaje("Se paso a lavar el cubierto con id: " + rta.darId() );
		if(darTamFregadero()-cubiertos.size()==2) notify();
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
