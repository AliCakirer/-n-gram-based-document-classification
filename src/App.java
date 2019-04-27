import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class App {

	private JFrame frame;
	private JTextField txtIsim;
	public static String testName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
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
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 788, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnnileVeEitime = new JButton("Önİşle ve Eğitim Kümesi Oluştur");
		btnnileVeEitime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Onisleme.Calistir();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnnileVeEitime.setBounds(0, 307, 772, 121);
		frame.getContentPane().add(btnnileVeEitime);
		
		JButton btnNewButton = new JButton("Eğitim Yaptır");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Onisleme.egitim();
				JOptionPane.showMessageDialog(null, "Eğitimler her 4 txtden 3 ü alınarak yapılmıştır.\n Lütfen test için 4 ün katlarını giriniz.!",
					      "Display Message", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(0, 0, 171, 308);
		frame.getContentPane().add(btnNewButton);
		
		txtIsim = new JTextField();
		txtIsim.setBounds(211, 75, 86, 30);
		frame.getContentPane().add(txtIsim);
		txtIsim.setColumns(10);
		
		JButton btnTestYap = new JButton("Test Yap");
		btnTestYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testName = txtIsim.getText();
				String str;
				str = Onisleme.Test(Integer.parseInt(testName));
				JOptionPane.showMessageDialog(null, "Belge Test Edildi!\n Türü "+str + "dir",
					      "Display Message", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnTestYap.setBounds(211, 143, 89, 23);
		frame.getContentPane().add(btnTestYap);
	}
}
