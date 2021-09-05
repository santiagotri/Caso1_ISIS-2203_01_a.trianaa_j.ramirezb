package caso1_Infracomp;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Comensal extends Thread{
	
	private int id;
	
	private static Fregadero fregadero;
	
	private CubiertoT1 cubiertoT1;
	
	private CubiertoT2 cubiertoT2;
	
	private static Mesa mesa;
	
	private CyclicBarrier barrera;
	
	public Comensal (Integer id, Fregadero pFregadero, Mesa pMesa, CyclicBarrier barrera) {
		fregadero = pFregadero;
		this.id = id;
		mesa = pMesa;
		this.barrera = barrera;
	}
	
	public void run() {
		int numPlatoActual = mesa.darNumPlatos();
		int mitadPlatos = (mesa.darNumPlatos())/2;
		boolean centinela = true;
		while(centinela) {
			if(tomarCubiertos()) 
			{
				comer();
				numPlatoActual--;
				dejarCubiertos();
				if(numPlatoActual == 0)centinela = false;
				if(mitadPlatos == numPlatoActual) {
					try {
						mensaje("Esta esperando a que los demas comensales llegen a la mitad (faltan " + (barrera.getParties()-barrera.getNumberWaiting()-1) + ")");
						barrera.await();
						mensaje("Se ha despertado, porque todos han llegado a la mitad de la cena");
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
				}else {
					espera(1,3);
				}
			}else {
				try {
					mensaje("No hay cubiertos en la mesa, esperando notify");
					synchronized(mesa) {
						while(!mesa.hayCubiertosDeAmbosTipos()) {
							mesa.wait();
						}
					}
					mensaje("Notify recibido, intentando coger cubiertos de nuevo");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		mensaje("Finalizo");
		
	}
	
	private void espera(int min, int max) {
		int tiempo = (int) Math.floor(Math.random()*(max-min+1)+min);
		try {
			Thread.sleep(tiempo*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean tomarCubiertos() {
		cubiertoT1 = mesa.darCubiertoT1();
		cubiertoT2 = mesa.darCubiertoT2();
		
		if(cubiertoT1==null || cubiertoT2==null)
		{
			if(cubiertoT1 != null) {
				mensaje("Ha devuelto el " + cubiertoT1.darDescripcion() + " con id " + cubiertoT1.darId());
				mesa.anadirCubierto(cubiertoT1);
			}
			if(cubiertoT2 != null) {
				mensaje("Ha devuelto el " + cubiertoT2.darDescripcion() + " con id " + cubiertoT2.darId());
				mesa.anadirCubierto(cubiertoT2);
			}
			
			cubiertoT1 = null;
			cubiertoT2 = null;
			
			return false;
		}
		mensaje("Ha cogido los cubiertos de manera exitosa (T1: " + cubiertoT1.darId() +", T2: "+ cubiertoT2.darId()+").");
		return true;
	}
	
	private void comer() {
		espera (3,5);
	}
	
	private void dejarCubiertos() {
		while(!fregadero.hayEspacio()) {
			try {
				Thread.sleep(1000);
				mensaje("No hay espacio en el fregadero, esperando...");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mensaje ("Envio los cubiertos al fregadero.(T1: " + cubiertoT1.darId() +",T2: "+ cubiertoT2.darId()+").");
		fregadero.agregarCubierto(cubiertoT1);
		fregadero.agregarCubierto(cubiertoT2);
		
		cubiertoT1 = null;
		cubiertoT2 = null;
	}
	
	private void mensaje (String mensaje) {
		System.out.println("Comensal " + id + ": " + mensaje);
	}

}
