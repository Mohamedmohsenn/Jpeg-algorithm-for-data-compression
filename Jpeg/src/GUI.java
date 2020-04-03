import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JSeparator;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1262, 746);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 20));
		textField.setBounds(45, 42, 1150, 128);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnCompress = new JButton("Compress");
		btnCompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Jpeg j = new Jpeg();
				try {
					textField_1.setText(j.Compression(textField.getText().toString()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnCompress.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCompress.setBounds(514, 196, 198, 52);
		contentPane.add(btnCompress);

		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_1.setBounds(243, 261, 952, 112);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("Answer of compress :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(12, 298, 219, 38);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(52, 386, 1143, 14);
		contentPane.add(separator);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_2.setBounds(45, 413, 1150, 93);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton = new JButton("Decompress");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jpeg j = new Jpeg();
				try {
					textField_3.setText(j.deCompression(textField_2.getText().toString()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(514, 536, 198, 44);
		contentPane.add(btnNewButton);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_3.setBounds(243, 593, 952, 93);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblAnswerOfDecompress = new JLabel("Answer of decompress :");
		lblAnswerOfDecompress.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAnswerOfDecompress.setBounds(12, 620, 219, 38);
		contentPane.add(lblAnswerOfDecompress);
	}
}
