package caso1_Infracomp;
import java.util.*;

public class Fregadero {
	
	private int tamFregadero;
	
	private Queue cubiertos;
	
	
	public Fregadero (int tamFregadero) {
		this.tamFregadero = tamFregadero;
	}
	
	public int darTamFregadero () {
		return tamFregadero;
	}
	
	public void agregarCubierto (Cubierto cubiertoNuevo) {
		cubiertos.add(cubiertoNuevo);
	}
	
	public Cubierto darCubierto () {
		return (Cubierto) cubiertos.poll();
	}

}
