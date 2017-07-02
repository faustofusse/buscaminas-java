package Buscaminas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Ventana2 extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public static LayerBotones layerBotones = new LayerBotones();
	public static LayerArriba layerArriba = new LayerArriba();
	
	public Ventana2(){
		
		setLayout(new BorderLayout(0,0));
		setBounds(400, 100, Constantes.COLUMNAS * 30, Constantes.FILAS * 30 + 20); 
		setResizable(true);
		setTitle("Buscaminas");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		layerBotones.setVisible(true);
		layerArriba.setPreferredSize(new Dimension(Constantes.COLUMNAS*30, 30));
		layerBotones.setPreferredSize(new Dimension(Constantes.COLUMNAS*30, Constantes.FILAS*30+10));
		add(layerArriba, BorderLayout.NORTH);
		add(layerBotones, BorderLayout.CENTER);
		
	}
	
	public void add(Component comp, Object constraints) {
        addImpl(comp, constraints, -1);
    }
}
