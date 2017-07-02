package Buscaminas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LayerArriba extends JPanel{
	private boolean pressedBombas = true;
	
	private ImageIcon bombas = new ImageIcon("src/Buscaminas/img/btn-bomba.png");
	private ImageIcon bombas2 = new ImageIcon("src/Buscaminas/img/btn-bomba2.png");
	private ImageIcon banderas = new ImageIcon("src/Buscaminas/img/btn-bandera.png");
	private ImageIcon banderas2 = new ImageIcon("src/Buscaminas/img/btn-bandera2.png");
	
	private BtnBombas btnBombas = new BtnBombas();
	private BtnBanderas btnBanderas = new BtnBanderas();
	
	public LayerArriba (){
		setLayout(new GridLayout(1,3));
		add(btnBombas);
		add(new LayerMedio());
		add(btnBanderas);
	}

	private class BtnBombas extends JButton implements ActionListener{

		public BtnBombas(){
			setIcon(bombas2);
			addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (pressedBombas == false){
				BtnCuadradito.modo = "bomba";
				btnBombas.setIcon(bombas2);
				btnBanderas.setIcon(banderas);
				pressedBombas = true;
			}
		}
		
	}
	
	private class BtnBanderas extends JButton implements ActionListener{

		public BtnBanderas(){
			setIcon(banderas);
			addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (pressedBombas){
				BtnCuadradito.modo = "bandera";
				btnBombas.setIcon(bombas);
				btnBanderas.setIcon(banderas2);
				pressedBombas = false;
			}
		}
		
	}
}
