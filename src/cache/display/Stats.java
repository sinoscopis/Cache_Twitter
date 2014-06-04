package cache.display;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.SwingConstants;

import cache.server.Cache_Server;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

public class Stats extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final JPanel Stats = new JPanel();
	private final JPanel Content = new JPanel();
	private final JLabel lblStats = new JLabel("Stats");
	private final JLabel lblContents = new JLabel("Cache Stack");
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
	private final JTable table = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JLabel lblStackCount = new JLabel("");
	
	public Stats() {
		initGUI();
	}
	private void initGUI() {
		setBackground(SystemColor.menu);
		getContentPane().setBackground(SystemColor.menu);
		setSize(800, 500);
		setVisible(true);
		/*InetAddress host = null;
		try {
			host = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		setTitle("Cache on "+ host.getHostAddress() +" Stats");*/
		setTitle("Cache Statistics");
		Stats.setBounds(7, 7, 382, 258);
		Stats.setBackground(SystemColor.menu);
		Stats.setLayout(null);
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblStats.setBounds(132, 11, 104, 34);
		
		Stats.add(lblStats);
		lblPetitions.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPetitions.setBounds(10, 117, 74, 34);
		
		Stats.add(lblPetitions);
		lblNumeric.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeric.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNumeric.setBounds(143, 80, 74, 34);
		
		Stats.add(lblNumeric);
		lblSizebytes.setHorizontalAlignment(SwingConstants.CENTER);
		lblSizebytes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSizebytes.setBounds(236, 80, 136, 34);
		
		Stats.add(lblSizebytes);
		lblHits.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHits.setBounds(10, 162, 74, 34);
		
		Stats.add(lblHits);
		lblHitsRatio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsRatio.setBounds(10, 207, 133, 34);
		
		Stats.add(lblHitsRatio);
		lblPetitions_number.setHorizontalAlignment(SwingConstants.CENTER);
		lblPetitions_number.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPetitions_number.setBounds(153, 121, 64, 21);
		
		Stats.add(lblPetitions_number);
		lblHitsnumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsnumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHitsnumber.setBounds(153, 166, 64, 21);

		
		Stats.add(lblHitsnumber);
		lblHitsrationumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsrationumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHitsrationumber.setBounds(153, 211, 74, 21);
		lblHitsnumber.setText(Integer.toString(Cache_Server.hit));
		
		Stats.add(lblHitsrationumber);
		lblPetitionsSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblPetitionsSize.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPetitionsSize.setBounds(280, 121, 64, 21);
		
		Stats.add(lblPetitionsSize);
		lblHitsSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsSize.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHitsSize.setBounds(280, 166, 64, 21);
		
		Stats.add(lblHitsSize);
		lblHitsSizeRatio.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsSizeRatio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHitsSizeRatio.setBounds(280, 211, 74, 21);
		
		Stats.add(lblHitsSizeRatio);
		Content.setBounds(393, 7, 390, 446);
		Content.setBackground(SystemColor.menu);
		Content.setLayout(null);
		lblContents.setHorizontalAlignment(SwingConstants.CENTER);
		lblContents.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblContents.setBounds(78, 11, 186, 43);
		
		Content.add(lblContents);
		scrollPane.setBackground(SystemColor.menu);
		scrollPane.setBounds(43, 68, 347, 367);
		
		Content.add(scrollPane);
		table.setRowHeight(22);
		table.setGridColor(SystemColor.text);
		scrollPane.setViewportView(table);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setBackground(SystemColor.menu);
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setForeground(Color.BLACK);
		
		refresh_stats();
		getContentPane().setLayout(null);
		getContentPane().add(Stats);
		getContentPane().add(Content);
		lblStackCount.setBackground(SystemColor.textHighlight);
		lblStackCount.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblStackCount.setBounds(288, 15, 62, 35);
		
		Content.add(lblStackCount);
	}
	
	public void refresh_stats() {
		
		table.setTableHeader(null);
		table.setModel(new MyTableModel(Cache_Server.c));		
		
		lblStackCount.setText(Integer.toString(Cache_Server.c.usedEntries()));
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
