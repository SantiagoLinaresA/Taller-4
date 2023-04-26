package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;
import uniandes.dpoo.taller4.modelo.RegistroTop10;
public class VentanaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Tablero tablero;
	private int tamano;
	private int jugadas;

	private JPanel panelTablero;
	private JButton[][] botones;

	private JLabel labelJugadas;
	private JLabel labelTamano;
	private JLabel labelTop10;

	private JButton botonReiniciar;

	public VentanaPrincipal(int tamano) {
		this.tablero = new Tablero(tamano);
		this.tamano = tamano;
		this.jugadas = 0;

		// Configurar la ventana principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Lights Out");
		setResizable(false);

		// Configurar el panel del tablero
		panelTablero = new JPanel(new GridLayout(tamano, tamano));
		botones = new JButton[tamano][tamano];
		for (int i = 0; i < tamano; i++) {
			for (int j = 0; j < tamano; j++) {
				botones[i][j] = new JButton("");
				botones[i][j].setBackground(Color.WHITE);
				botones[i][j].addActionListener(this);
				panelTablero.add(botones[i][j]);
			}
		}

		// Configurar el panel de información
		JPanel panelInfo = new JPanel(new GridLayout(3, 1));
		labelJugadas = new JLabel("Jugadas: 0");
		labelTamano = new JLabel("Tamaño: " + tamano + "x" + tamano);
		labelTop10 = new JLabel("Top-10:");
		panelInfo.add(labelJugadas);
		panelInfo.add(labelTamano);
		panelInfo.add(labelTop10);

		// Configurar el botón de reiniciar
		botonReiniciar = new JButton("Reiniciar");
		botonReiniciar.addActionListener(this);

		// Agregar componentes a la ventana principal
		add(panelTablero, BorderLayout.CENTER);
		add(panelInfo, BorderLayout.EAST);
		add(botonReiniciar, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);

		// Inicializar el tablero
		tablero.reiniciar();
	}

	private void actualizarTablero() {
		for (int i = 0; i < tamano; i++) {
			for (int j = 0; j < tamano; j++) {
				if (tablero.estaEncendida(i, j)) {
					botones[i][j].setBackground(Color.YELLOW);
				} else {
					botones[i][j].setBackground(Color.WHITE);
				}
			}
		}
	}

	private void actualizarJugadas() {
		labelJugadas.setText("Jugadas: " + jugadas);
	}

	private void actualizarTop10(List<Integer> puntuaciones) {
		StringBuilder sb = new StringBuilder("<html><ul>");
		for (Integer p : puntuaciones) {
		sb.append("<li>");
		sb.append(p);
		sb.append("</li>");
		}
		sb.append("</ul></html>");
		Top10.agregarRegistro(sb.toString(),puntuaciones);
		}
}