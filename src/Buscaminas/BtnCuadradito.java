package Buscaminas;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class BtnCuadradito extends JButton implements MouseListener {

	private int nombre;
	public static int cantBanderas = 0;
	private boolean estaDescubierto;
	private int cantBombas;
	private boolean tieneBandera;
	private boolean tieneBomba;
	private ArrayList <Integer> vacios = new ArrayList<Integer>();
	private ArrayList <Integer> aDespejar = new ArrayList<Integer>();
	public static String modo = "bomba";
	
	public static ImageIcon[] numeros = {new ImageIcon("src/Buscaminas/img/vacio.png"),
			new ImageIcon("src/Buscaminas/img/btn1.png"), 
			new ImageIcon("src/Buscaminas/img/btn2.png"),
			new ImageIcon("src/Buscaminas/img/btn3.png"),
			new ImageIcon("src/Buscaminas/img/btn4.png"),
			new ImageIcon("src/Buscaminas/img/btn5.png"),
			new ImageIcon("src/Buscaminas/img/btn6.png"),
			new ImageIcon("src/Buscaminas/img/btn7.png"),
			new ImageIcon("src/Buscaminas/img/btn8.png")};

	private ImageIcon bomba = new ImageIcon("src/Buscaminas/img/bomba.png");
	private ImageIcon vacio = new ImageIcon("src/Buscaminas/img/vacio2.png");
	private ImageIcon bandera = new ImageIcon("src/Buscaminas/img/bandera.png");

	public BtnCuadradito(){

		addMouseListener(this); 
		this.setIcon(vacio);


	}

	
	private void llenarVacios(int inicial){
		
		//Obtener los vecinos del inicial
		int[] vecinos = obtenerVecinos(inicial);
		
		//Saber cuales vecinos no tienen bombas
		vacios.clear();
		vacios.add(inicial);
		for(int b = 0; b<Constantes.FILAS*Constantes.COLUMNAS; b++){
			vecinos = obtenerVecinos(vacios.get(b));
			for(int i =0; i<vecinos.length;i++){
				if(vecinos[i] != -1 && LayerBotones.botones[vecinos[i]].getCantBombas() == 0){
					vacios.add(vecinos[i]);
					LayerBotones.botones[vecinos[i]].setDescubierto(true);
				}else if (vecinos[i] != -1){
					aDespejar.add(vecinos[i]);
					LayerBotones.botones[vecinos[i]].setDescubierto(true);
				}
			}
		}

		for(int i =0; i<vacios.size();i++){
			LayerBotones.botones[vacios.get(i)].setIcon(numeros[0]);
			//LayerBotones.botones[8].ponerIcono();
			
		}
		for(int i =0; i<aDespejar.size();i++){
			LayerBotones.botones[aDespejar.get(i)].ponerIcono();
			//LayerBotones.botones[8].ponerIcono();
			
		}
		
	}

	public void mostrarVecinos(int num){
		int[] vecinos;
		vecinos = obtenerVecinos(num);
		LayerBotones.botones[num].ponerIcono();
		for (int i =0;i<vecinos.length;i++){
			if (vecinos[i] != -1){
				LayerBotones.botones[vecinos[i]].ponerIcono();
			}
		}
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
	
	public void ponerIcono(){
		
		if (LayerBotones.botones[this.nombre].cantBombas == -1){
			
			LayerBotones.botones[this.nombre].setIcon(bomba);
			
		}else if(LayerBotones.botones[this.nombre].cantBombas == 0){
			
			this.setIcon(numeros[0]);
			//mostrarVecinos(this.nombre);
			llenarVacios(LayerBotones.botones[this.nombre].getNombre());
			
			for(int i =0; i< vacios.size();i++){
				llenarVacios(vacios.get(i));
//				mostrarVecinos(vacios.get(i));
			}
			
			
		}else if (LayerBotones.botones[this.nombre].cantBombas == 1){
			
			LayerBotones.botones[this.nombre].setIcon(numeros[1]);
			
		}else if (LayerBotones.botones[this.nombre].cantBombas == 2){
			
			LayerBotones.botones[this.nombre].setIcon(numeros[2]);
			
		}else if (LayerBotones.botones[this.nombre].cantBombas == 3){
			
			LayerBotones.botones[this.nombre].setIcon(numeros[3]);
			
		}else if (LayerBotones.botones[this.nombre].cantBombas == 4){
			
			LayerBotones.botones[this.nombre].setIcon(numeros[4]);
			
		}else if (LayerBotones.botones[this.nombre].cantBombas == 5){
			
			LayerBotones.botones[this.nombre].setIcon(numeros[5]);
			
		}else if (LayerBotones.botones[this.nombre].cantBombas == 6){
			
			LayerBotones.botones[this.nombre].setIcon(numeros[6]);
			
		}else if (LayerBotones.botones[this.nombre].cantBombas == 7){
			
			LayerBotones.botones[this.nombre].setIcon(numeros[7]);
			
		}else if (LayerBotones.botones[this.nombre].cantBombas == 8){
			
			LayerBotones.botones[this.nombre].setIcon(numeros[8]);
			
		}
		
	}
	
	public boolean todosDescubiertos(){
		
		for(int i =0; i<Constantes.FILAS*Constantes.COLUMNAS;i++){
			
			if(LayerBotones.botones[i].isDescubierto() == false){
				return false;
			}
			
		}
		
		return true;
	}

	public int ponerIcono2(int num){
		
		if(LayerBotones.botones[num].getCantBombas() == -1){
			return -1;
		}else if (LayerBotones.botones[num].getCantBombas() == -1){
			return 0;
		}else if (LayerBotones.botones[num].getCantBombas() == -1){
			return 1;
		}else if (LayerBotones.botones[num].getCantBombas() == -1){
			return 2;
		}else if (LayerBotones.botones[num].getCantBombas() == -1){
			return 3;
		}else if (LayerBotones.botones[num].getCantBombas() == -1){
			return 4;
		}else if (LayerBotones.botones[num].getCantBombas() == -1){
			return 5;
		}else if (LayerBotones.botones[num].getCantBombas() == -1){
			return 6;
		}else if (LayerBotones.botones[num].getCantBombas() == -1){
			return 7;
		}else if (LayerBotones.botones[num].getCantBombas() == -1){
			return 8;
		}
		
		
		return 0; 
	}
	
	
	

	
	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	
	public boolean isDescubierto() {
		return estaDescubierto; 
	}

	public void setDescubierto(boolean bool) {
		this.estaDescubierto = bool;
	}

	public boolean isTieneBomba() {
		return tieneBomba;
	}

	public void setTieneBomba(boolean tieneBomba) {
		this.tieneBomba = tieneBomba;
	}

	public int getCantBombas() {
		return cantBombas;
	}

	public void setCantBombas(int cantBombas) {
		this.cantBombas = cantBombas;
	}

	

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (modo == "bomba"){
			
			if (SwingUtilities.isLeftMouseButton(e)){
				this.ponerIcono();
				
				this.setDescubierto(true);
				
				if(this.cantBombas == -1){
					System.out.println("HAS PERDIDO");
					
					Main.ventana1.setVisible(false);
					Main.ventana1.setVisible(true);
				}else if(todosDescubiertos()){
					System.out.println("HAS GANADO");
				}
				
			}else if (SwingUtilities.isRightMouseButton(e)){
				if(this.tieneBandera){
					this.setIcon(vacio);
				}else{
					this.setIcon(bandera);
				}
				
				cantBanderas++;
				if(LayerBotones.botones[this.nombre].getCantBombas() == -1){
					LayerBotones.botones[this.nombre].setDescubierto(true);
				}
				
				if(todosDescubiertos()){
					System.out.println("HAS GANADO");
				}
				
			}
			
		}else{
			
			if (SwingUtilities.isLeftMouseButton(e)){
				if(this.tieneBandera){
					this.setIcon(vacio);
				}else{
					this.setIcon(bandera);
				}
				
				cantBanderas++;
				if(LayerBotones.botones[this.nombre].getCantBombas() == -1){
					LayerBotones.botones[this.nombre].setDescubierto(true);
				}
				
				if(todosDescubiertos()){
					System.out.println("HAS GANADO");
				}
				
			}else if (SwingUtilities.isRightMouseButton(e)){
				this.ponerIcono();
				
				this.setDescubierto(true);
				
				if(this.cantBombas == -1){
					System.out.println("HAS PERDIDO");
					Main.ventana1.setVisible(false);
					Main.ventana1.setVisible(true);
				}else if(todosDescubiertos()){
					System.out.println("HAS GANADO");
				}
				
			}
			
		}
		
		
		
		
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	

}
