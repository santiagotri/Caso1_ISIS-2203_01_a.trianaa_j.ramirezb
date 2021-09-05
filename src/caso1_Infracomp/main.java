package caso1_Infracomp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
		
	}

}
