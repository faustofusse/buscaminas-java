package Buscaminas;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LayerBotones extends JPanel{
	
	public static BtnCuadradito[] botones = new BtnCuadradito[Constantes.FILAS * Constantes.COLUMNAS]; 
	
	private static ImageIcon imagen = new ImageIcon("src/Buscaminas/img/vacio2.png");
	private static ImageIcon bomba = new ImageIcon("src/Buscaminas/img/bomba.png");
	
	public static int posBomba[] = llenarBombas(Constantes.BOMBAS);
	
	
	public LayerBotones(){
		
		setLayout(new GridLayout(Constantes.FILAS, Constantes.COLUMNAS));
		
		this.configurar(false); 
	}
	
	public void configurar(boolean mostrar){
		
		if (mostrar){
			
			for(int i = 0; i< Constantes.FILAS * Constantes.COLUMNAS ; i++){
				BtnCuadradito cuadradito = new BtnCuadradito();
				cuadradito.setNombre(i);
	 			cuadradito.setIcon(imagen);
	 			cuadradito.setDescubierto(false);
				botones[i] = cuadradito;			
		 		add(cuadradito);
			}
			
			
			for(int i = 0; i< posBomba.length ; i++){
				botones[posBomba[i]].setTieneBomba(true);
				botones[posBomba[i]].setCantBombas(-1);
	//			botones[posBomba[i]].setIcon(bomba);
			}
			
			
			for(int i = 0; i< Constantes.FILAS * Constantes.COLUMNAS ; i++){
				botones[i].setCantBombas(cantBombasVecinas(botones[i].getNombre()));  
				botones[i].ponerIcono();
			}	
			
		}else{
			
			for(int i = 0; i< Constantes.FILAS * Constantes.COLUMNAS ; i++){
				BtnCuadradito cuadradito = new BtnCuadradito();
				cuadradito.setNombre(i);
	 			cuadradito.setIcon(imagen);
	 			cuadradito.setDescubierto(false);
				botones[i] = cuadradito;			
		 		add(cuadradito);
			}
			
			
			for(int i = 0; i< posBomba.length ; i++){
				botones[posBomba[i]].setTieneBomba(true);
				botones[posBomba[i]].setCantBombas(-1);
	//			botones[posBomba[i]].setIcon(bomba);
			}
			
			
			for(int i = 0; i< Constantes.FILAS * Constantes.COLUMNAS ; i++){
				botones[i].setCantBombas(cantBombasVecinas(botones[i].getNombre()));  
			}	
		}
	}
	
	
	public int cantBombasVecinas(int origen){

		int[] vecinos = obtenerVecinos(origen);
		int bombas = 0;
		for(int i = 0; i < vecinos.length; i++){		
			if (vecinos[i] != -1 && LayerBotones.botones[vecinos[i]].isTieneBomba()){					
				bombas++;
			}
		}	
		
		if(LayerBotones.botones[origen].isTieneBomba()){
			return -1; 
		}
	
		return bombas;		
	}

	public int[] obtenerVecinos(int origen){
		int[] vecinos = new int[8];

		if (origen == 0){
			//ESQ IZQ SUP
			vecinos = llenarArray(-1,-1,-1,-1,1,-1,Constantes.FILAS,Constantes.FILAS+1);			
		} else if (origen == Constantes.FILAS -1){
			//ESQ DER SUP
			vecinos = llenarArray(-1,-1,-1, Constantes.FILAS -2, -1 , Constantes.FILAS * 2 - 2, Constantes.FILAS * 2 - 1 , -1);			
		} else if (origen == Constantes.FILAS * Constantes.COLUMNAS - Constantes.FILAS){
			//ESQ IZQ INF
			vecinos = llenarArray(-1,Constantes.FILAS * Constantes.COLUMNAS - (2 * Constantes.FILAS) , Constantes.FILAS * Constantes.COLUMNAS - 2 * Constantes.FILAS + 1,-1, Constantes.FILAS * Constantes.COLUMNAS - Constantes.FILAS+1, -1,-1,-1);
		} else if (origen == Constantes.FILAS * Constantes.COLUMNAS-1){
			//ESQ DER INF
			vecinos = llenarArray(Constantes.FILAS * (Constantes.COLUMNAS -1)-2, Constantes.FILAS * (Constantes.COLUMNAS -1)-1, -1, Constantes.FILAS * Constantes.COLUMNAS -2, -1, -1, -1, -1);
		}else if (origen % Constantes.FILAS == 0){
			//Borde Izq
			vecinos = llenarArray(-1, origen - Constantes.FILAS,origen - Constantes.FILAS + 1 , -1, origen + 1, -1, origen + Constantes.FILAS, origen + Constantes.FILAS + 1);
		}else if ((origen + 1) % Constantes.FILAS == 0){
			//Borde der
			vecinos = llenarArray(origen - Constantes.FILAS -1, origen - Constantes.FILAS, -1, origen -1, -1, origen + Constantes.FILAS -1, origen + Constantes.FILAS, -1);
		}else if (origen < Constantes.FILAS ){
			//Arriba
			vecinos = llenarArray(-1, -1, -1, origen -1, origen + 1, origen + Constantes.FILAS -1, origen + Constantes.FILAS , origen + Constantes.FILAS + 1);
		}else if (origen > Constantes.FILAS * Constantes.COLUMNAS - Constantes.FILAS){
			//Abajo
			vecinos = llenarArray(origen - Constantes.FILAS -1, origen - Constantes.FILAS , origen - Constantes.FILAS +1, origen -1, origen +1, -1, -1, -1);
		}else{
			vecinos = llenarArray(origen - Constantes.FILAS -1, origen - Constantes.FILAS, origen - Constantes.FILAS +1, origen -1, origen + 1, origen + Constantes.FILAS -1, origen + Constantes.FILAS, origen + Constantes.FILAS + 1);
		}
		
		return vecinos;
	}

	public int[] llenarArray(int a, int b, int c, int d, int e, int f, int g, int h){
		int[] arreglo = new int[8];

		arreglo[0] = a;
		arreglo[1] = b;
		arreglo[2] = c;
		arreglo[3] = d;
		arreglo[4] = e;
		arreglo[5] = f;
		arreglo[6] = g;
		arreglo[7] = h;


		return arreglo;
	}
	
	private static int[] llenarBombas(int i) {
		int num1 = 0;	
		int num2 = Constantes.FILAS * Constantes.COLUMNAS;
		int posBomba[] = new int[i];
		for(int x =0; x < i; x++){
			posBomba[x] = -1;
		}
		for(int x =0; x < i; x++){
			boolean esta = false;
			int pos = (int)Math.floor(Math.random()*(num1-(num2 +1))+(num2));
			for(int j = 0; j < posBomba.length; j++){
				if(posBomba[j] == pos){
					esta = true;
				}
			}
			if(!esta){
				posBomba[x] = pos;
			}else{
				x--;
			}
		}
		return posBomba;
	}
	
}

