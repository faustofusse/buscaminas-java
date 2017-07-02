package Buscaminas;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LayerMedio extends JPanel{
	
	private Dimension nada = new Dimension(0,0);
	
	public BtnCarita btnCarita = new BtnCarita();
	
	public LayerMedio(){
		setLayout(new GridLayout(1,3,0,0));
		
		add(new BtnVacio());
		add(btnCarita);
		add(new BtnVacio());
		
	}
	
	private class BtnVacio extends JButton{
		public BtnVacio(){
			setIcon(new ImageIcon("src/Buscaminas/img/vacio2.png"));
			setEnabled(false);
		}

	}
	
	class BtnCarita extends JButton implements ActionListener{
		public boolean pressed = false;
		public ImageIcon caraBien = new ImageIcon("src/Buscaminas/img/cara-sonriente.png");
		public ImageIcon caraMal = new ImageIcon("src/Buscaminas/img/cara-llorando.png");
		public BtnCarita(){
			setIcon(caraBien);
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (this.pressed){
				this.setIcon(caraBien);
				this.pressed = false;
			}else{
				this.setIcon(caraMal);
				this.pressed = true;
				Ventana2.layerBotones = new LayerBotones();
				Ventana2.layerBotones.removeAll();
			}
		}
		
		public void setPressed(boolean bool){
			this.pressed = bool;
		}
		
		public void setCoso(ImageIcon icon){
			this.setIcon(icon);
		}
	}
	
}
