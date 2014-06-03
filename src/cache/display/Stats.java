package cache.display;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.SwingConstants;

import cache.server.Cache_Server;
import java.awt.Component;
import javax.swing.Box;

public class Stats extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel Stats = new JPanel();
	private final JPanel Content = new JPanel();
	private final JLabel lblStats = new JLabel("Stats");
	private final JLabel lblContents = new JLabel("Contents");
	private final JLabel lblPetitions = new JLabel("Petitions");
	private final JLabel lblNumeric = new JLabel("Numeric");
	private final JLabel lblSizebytes = new JLabel("Size (bytes)");
	private final JLabel lblHits = new JLabel("Hits");
	private final JLabel lblHitsRatio = new JLabel("Hits Ratio (%)");
	private final JLabel lblPetitions_number = new JLabel("Petitions");
	private final JLabel lblHitsnumber = new JLabel("Hits_number");
	private final JLabel lblHitsrationumber = new JLabel("Hits_ratio_number");
	private final JLabel lblPetitionsSize = new JLabel("Petitions size");
	private final JLabel lblHitsSize = new JLabel("Hits size");
	private final JLabel lblHitsSizeRatio = new JLabel("Hits size ratio");
	
	public Stats() {
		initGUI();
	}
	private void initGUI() {
		setSize(800, 500);
		setVisible(true);
		InetAddress host = null;
		try {
			host = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		setTitle("Cache on "+ host.getHostAddress() +" Stats");
		getContentPane().setLayout(null);
		Stats.setBounds(0, 11, 382, 233);
		
		getContentPane().add(Stats);
		Stats.setLayout(null);
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblStats.setBounds(158, 11, 74, 34);
		
		Stats.add(lblStats);
		lblPetitions.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPetitions.setBounds(10, 93, 74, 34);
		
		Stats.add(lblPetitions);
		lblNumeric.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeric.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNumeric.setBounds(129, 61, 74, 34);
		
		Stats.add(lblNumeric);
		lblSizebytes.setHorizontalAlignment(SwingConstants.CENTER);
		lblSizebytes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSizebytes.setBounds(222, 61, 136, 34);
		
		Stats.add(lblSizebytes);
		lblHits.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHits.setBounds(10, 138, 74, 34);
		
		Stats.add(lblHits);
		lblHitsRatio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsRatio.setBounds(10, 183, 109, 34);
		
		Stats.add(lblHitsRatio);
		lblPetitions_number.setHorizontalAlignment(SwingConstants.CENTER);
		lblPetitions_number.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPetitions_number.setBounds(139, 102, 64, 21);
		
		Stats.add(lblPetitions_number);
		lblHitsnumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsnumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHitsnumber.setBounds(129, 147, 74, 21);

		
		Stats.add(lblHitsnumber);
		lblHitsrationumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsrationumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHitsrationumber.setBounds(129, 192, 74, 21);
		lblHitsnumber.setText(Integer.toString(Cache_Server.hit));
		
		Stats.add(lblHitsrationumber);
		lblPetitionsSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblPetitionsSize.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPetitionsSize.setBounds(249, 102, 85, 21);
		
		Stats.add(lblPetitionsSize);
		lblHitsSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsSize.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHitsSize.setBounds(266, 147, 55, 21);
		
		Stats.add(lblHitsSize);
		lblHitsSizeRatio.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsSizeRatio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHitsSizeRatio.setBounds(252, 192, 92, 21);
		
		Stats.add(lblHitsSizeRatio);
		Content.setBounds(392, 11, 390, 446);
		
		getContentPane().add(Content);
		Content.setLayout(null);
		lblContents.setHorizontalAlignment(SwingConstants.CENTER);
		lblContents.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblContents.setBounds(138, 11, 124, 43);
		
		Content.add(lblContents);
		
		refresh_stats();
	}
	
	public void refresh_stats() {
		lblPetitions_number.setText(Integer.toString(Cache_Server.peticiones));
		lblHitsnumber.setText(Integer.toString(Cache_Server.hit));
		lblHitsrationumber.setText(Double.toString(Cache_Server.porc));
		lblPetitionsSize.setText(Long.toString(Cache_Server.peticiones_bytes));
		lblHitsSize.setText(Long.toString(Cache_Server.hits_bytes));
		lblHitsSizeRatio.setText(Double.toString(Cache_Server.porc_bytes));
	}
	/**
	 * Starts the already initialized frame, making it 
	 * visible and ready to interact with the user. 
	 */ 
	public void start() {
	   setVisible(true);
	}
}
