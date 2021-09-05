package caso1_Infracomp;

public class Lavaplatos extends Thread{
	
	private Cubierto cubiertoActual;
	
	private Fregadero fregadero;
	
	private boolean centinela;
	
	private Mesa mesa;
	
	public Lavaplatos (Fregadero pFregadero, Mesa pMesa) {
		fregadero = pFregadero;
		centinela = true;
		mesa = pMesa;
	}
	
	public void run(){
		while (centinela) {
			lavar();
			devolverCubierto();
		}
	}
	
	public void detener () {
		centinela =false;
		System.out.println("El lavaplatos se ha detenido :)");
		
	}
	
	private void lavar() {
		cubiertoActual = fregadero.darCubierto();
		int tiempo = (int) Math.floor(Math.random()*(2-1+1)+1);
		try {
			Thread.sleep(tiempo*1000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void devolverCubierto() {
		mesa.anadirCubierto(cubiertoActual);
		cubiertoActual = null;
	}
	
	
}
