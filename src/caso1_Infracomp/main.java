package caso1_Infracomp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class main {
	
	static int numComensales;
	static int numCubiertosT1;
	static int numCubiertosT2;
	static int numPlatos;
	static int tamFregadero;
	
	static String[] variables;

	public static void cargar()
	{
		File file = new File("./data/data1.txt");

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			
			
			while ((st = br.readLine()) != null) {
				variables = st.split("=");
				if(variables[0].trim().equals("concurrencia.numComensales")) {
					numComensales = Integer.parseInt(variables[1].trim());
				} else if(variables[0].trim().equals("concurrencia.numCubiertosT1")) {
					numCubiertosT1 = Integer.parseInt(variables[1].trim());
				} else if(variables[0].trim().equals("concurrencia.numCubiertosT2")) {
					numCubiertosT2 = Integer.parseInt(variables[1].trim());
				} else if(variables[0].trim().equals("concurrencia.numPlatos")) {
					numPlatos = Integer.parseInt(variables[1].trim());
				} else if(variables[0].trim().equals("concurrencia.tamFregadero")) {
					tamFregadero = Integer.parseInt(variables[1].trim());
				} 
			}
			
			System.out.println(numComensales + " " + numCubiertosT1 + " " +numCubiertosT2 + " "+ numPlatos +" " + tamFregadero);
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		cargar();
		iniciarCena();
	}
	
	private static void iniciarCena () {
		Mesa mesa = new Mesa(numPlatos);
		Fregadero fregadero = new Fregadero(tamFregadero);
		Lavaplatos lavaplatos = new Lavaplatos(fregadero, mesa);
		
		for(int i=0; i<numCubiertosT1; i++) {
			CubiertoT1 act = new CubiertoT1(i);
			mesa.anadirCubierto(act);
		}
		
		for(int i=numCubiertosT1; i<numCubiertosT2+numCubiertosT1; i++) {
			CubiertoT2 act = new CubiertoT2(i);
			mesa.anadirCubierto(act);
		}
		CyclicBarrier barrera = new CyclicBarrier(numComensales);
		ArrayList<Comensal> comensales = new ArrayList<>();
		for(int i = 1 ; i<= numComensales; i++) {
			comensales.add(new Comensal(i, fregadero, mesa, barrera));
		}
		
		lavaplatos.start();
		for(int i = 0 ; i< numComensales; i++) {
			comensales.get(i).start();
		}
		for(int i = 0 ; i< numComensales; i++) {
			try {
				comensales.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("La cena ha acabado");
		lavaplatos.detener();
		
		
		
	}

}
