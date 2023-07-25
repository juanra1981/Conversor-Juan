import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ConversorJuan {

	private JFrame frame;
	private JButton btn;
	private JComboBox cmb;
	private JTextField txt;
	private JLabel lbl;
	
	public enum Moneda{
		pesos_dolar,
		pesos_euros,
		pesos_libra,
		pesos_yuanes,
		pesos_wons,
		dolar_pesos,
		euros_pesos,
		libra_pesos,
		yuanes_pesos,
		wons_pesos,
		
	}
	
	public double dolar = 3739.00;
	public double euro = 4050.48;
	public double libra = 4890.52;
	public double yuanes = 29.68;
	public double wons = 3.04;
	
	public double valorInput = 0.00;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConversorJuan window = new ConversorJuan();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConversorJuan() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 515, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(10, 10, 126, 19);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setBounds(10, 60, 126, 21);
		frame.getContentPane().add(cmb);
		
		// evento boton
		btn = new JButton("Convertir");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(202, 60, 110, 21);
		frame.getContentPane().add(btn);
		
		lbl = new JLabel("00.00");
		lbl.setBounds(202, 8, 167, 22);
		frame.getContentPane().add(lbl);
	}
	
	public void Convertir() {
		if(Validar(txt.getText())) {
			Moneda moneda = (Moneda)cmb.getSelectedItem();
			
			switch (moneda) {
			
			case pesos_dolar:
				PesosAMoneda(dolar);
				break;
			case pesos_euros:
				PesosAMoneda(euro);
				break;
			case pesos_libra:
				PesosAMoneda(libra);
				break;
			case pesos_yuanes:
				PesosAMoneda(yuanes);
				break;
			case pesos_wons:
				PesosAMoneda(wons);
				break;
			case dolar_pesos:
				MonedaAPesos(dolar);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
			
		}
		
		}
			
	}
	
	public void PesosAMoneda(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res));
	}
	
    public void MonedaAPesos(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Redondear(res));
	}
    
    public String Redondear(double valor) {
    	DecimalFormat df = new DecimalFormat("0.00");
    	df.setRoundingMode(RoundingMode.HALF_UP);
    	return df.format(valor);
    }
    
    public boolean Validar(String texto) {
    	try {
    		double X = Double.parseDouble(texto);
    		if(X > 0);
    		valorInput = X;
    		return true;
    	}catch(NumberFormatException e) {
    		lbl.setText("Solamente numeros !!");
    		return false;
    	}
    }
}
