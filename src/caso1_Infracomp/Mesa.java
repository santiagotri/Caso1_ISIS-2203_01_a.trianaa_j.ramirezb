package caso1_Infracomp;

import java.util.*;

public class Mesa {
	
	private int numPlatos;
	private Queue<CubiertoT1> cubiertosT1;
	private Queue<CubiertoT2> cubiertosT2;
	
	public Mesa (Integer PnumPlatos) {
		this.numPlatos = PnumPlatos;
		cubiertosT1 = new LinkedList<>();
		cubiertosT2 = new LinkedList<>();
	}
	
	public synchronized void anadirCubierto(Cubierto cubierto) {
		if(cubierto.darDescripcion() == "CubiertoT1")
		{
			cubiertosT1.add((CubiertoT1) cubierto);
			if(cubiertosT1.size()==1 && cubiertosT2.size()>0) notify();
			System.out.println("Llego cubiertos T1 con id " + cubierto.darId());
		}
		else if (cubierto.darDescripcion() == "CubiertoT2")
		{
			cubiertosT2.add((CubiertoT2) cubierto);
			if(cubiertosT2.size()==1 && cubiertosT1.size()>0) notify();
			System.out.println("Llego cubiertos T2 con id " + cubierto.darId());
		}
		else
		{
			System.out.println("No se reconoce el cubierto que se quiere agregar (Clase Mesa)");
		}
	}
	
	public CubiertoT2 darCubiertoT2 () {return cubiertosT2.poll();}
	public CubiertoT1 darCubiertoT1 () {return cubiertosT1.poll();}
	
	public int darNumPlatos() { return numPlatos;}
	
}
