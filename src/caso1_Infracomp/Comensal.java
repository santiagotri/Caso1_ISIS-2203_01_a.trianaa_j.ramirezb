package caso1_Infracomp;

public class Comensal extends Thread{
	
	private int id;
	
	private static Fregadero fregadero;
	
	private CubiertoT1 cubiertoT1;
	
	private CubiertoT2 cubiertoT2;
	
	private static Mesa mesa;
	
	public Comensal (Integer id, Fregadero fregadero, Mesa mesa) {
		this.fregadero = fregadero;
		this.id = id;
		this.mesa = mesa;
	}
	
	public void run() {
		tomarCubiertos();
		if(cubiertoT1!=null && cubiertoT2!=null) comer();
		dejarCubiertos();
	}
	
	private void tomarCubiertos() {
		
	}
	
	private void comer() {
		
	}
	
	private void dejarCubiertos() {
		fregadero.agregarCubierto(cubiertoT1);
		fregadero.agregarCubierto(cubiertoT2);
	}

}
