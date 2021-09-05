package caso1_Infracomp;

import java.util.*;

public class Mesa{
	
	private int numPlatos;
	private static Queue<CubiertoT1> cubiertosT1;
	private static Queue<CubiertoT2> cubiertosT2;
	
	public Queue<CubiertoT1> darCubiertosT1() {return cubiertosT1;}
	public Queue<CubiertoT2> darCubiertosT2() {return cubiertosT2;}
	
	private boolean imprimirMensajes;
	
	public Mesa (Integer PnumPlatos, boolean imprimirMensajes) {
		this.numPlatos = PnumPlatos;
		cubiertosT1 = new LinkedList<>();
		cubiertosT2 = new LinkedList<>();
		this.imprimirMensajes =imprimirMensajes;
	}
	
	public synchronized void anadirCubierto(Cubierto cubierto) {
		if(cubierto.darDescripcion() == "CubiertoT1")
		{
			cubiertosT1.add((CubiertoT1) cubierto);
			if(cubiertosT1.size()==1 && cubiertosT2.size()>0) notify();
			mensaje("Llego el cubiertoT1 con id " + cubierto.darId() + " cantCubT1= " + cubiertosT1.size()+ " cantCubT2= " + cubiertosT2.size());
		}
		else if (cubierto.darDescripcion() == "CubiertoT2")
		{
			cubiertosT2.add((CubiertoT2) cubierto);
			if(cubiertosT2.size()==1 && cubiertosT1.size()>0) notify();
			mensaje("Llego el cubiertoT2 con id " + cubierto.darId() + " cantCubT1= " + cubiertosT1.size()+ " cantCubT2= " + cubiertosT2.size());
		}
		else
		{
			mensaje("No se reconoce el cubierto que se quiere agregar (Clase Mesa)");
		}
	}
	
	public synchronized CubiertoT2 darCubiertoT2 () {return cubiertosT2.poll();}
	public synchronized CubiertoT1 darCubiertoT1 () {return cubiertosT1.poll();}
	
	public int darNumPlatos() { return numPlatos;}
	
	private void mensaje(String mensaje) {
		if(imprimirMensajes)System.out.println("Mesa: " + mensaje);
	}

	public boolean hayCubiertosDeAmbosTipos() {
		if(cubiertosT1.size()>0 && cubiertosT2.size()>0) return true;
		return false;
	}
	
}
