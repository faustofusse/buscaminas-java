package Buscaminas;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class VentanaPerdiste extends JFrame{
	
	public VentanaPerdiste(){
		setLayout(new GridLayout(2,1));
		setBounds(400+(Constantes.COLUMNAS * 30 - (Constantes.COLUMNAS * 30)/4)/8, 100+((Constantes.FILAS * 30 + 20)/4), Constantes.COLUMNAS * 30 - (Constantes.COLUMNAS * 30)/4, (Constantes.FILAS * 30 + 20)/2); 
		setResizable(true);
	}

}





