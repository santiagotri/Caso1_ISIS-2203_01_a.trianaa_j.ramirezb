package caso1_Infracomp;

import java.util.Queue;

public class Lavaplatos extends Thread{
	
	private Cubierto cubiertoActual;
	
	private Fregadero fregadero;
	
	private boolean centinela;
	
	private Mesa mesa;
	
	public Lavaplatos (Fregadero pFregadero, Mesa pMesa) {
		fregadero = pFregadero;
		mesa = pMesa;
	}
	
	public void run(){
		centinela = true;
		while (centinela || fregadero.hayCubiertosParaLavar()) {
			Thread.yield();
			lavar();
			if(cubiertoActual != null)devolverCubierto();
		}
		mensaje("El lavaplatos se ha detenido :)");
		Queue<CubiertoT1> cubiertost1 = mesa.darCubiertosT1();
		Queue<CubiertoT2> cubiertost2 = mesa.darCubiertosT2();
		System.out.println("\n CubiertosT2:");
		for (CubiertoT2 cubiertoT2 : cubiertost2) {
			System.out.print(cubiertoT2.darId());
		}
		System.out.println("\n CubiertosT1:");
		for (CubiertoT1 cubiertoT1 : cubiertost1) {
			System.out.print(cubiertoT1.darId());
		}
		
	}
	
	public void detener () {
		centinela =false;
	}
	
	private void lavar() {
		if(fregadero.hayCubiertosParaLavar()) {
			cubiertoActual = fregadero.darCubierto();
			mensaje("Se ha empezado a lavar el " + cubiertoActual.darDescripcion() + " con id " + cubiertoActual.darId());
			int tiempo = (int) Math.floor(Math.random()*(2-1+1)+1);
			try {
				Thread.sleep(tiempo*1000);
			}catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	
	private void mensaje(String mensaje) {
		System.out.println("Lavaplatos: " + mensaje);
	}
	
	private void devolverCubierto() {
		mensaje("Se ha enviado el " + cubiertoActual.darDescripcion() + " con id " + cubiertoActual.darId());
		mesa.anadirCubierto(cubiertoActual);
		cubiertoActual = null;
	}
	
	
}
