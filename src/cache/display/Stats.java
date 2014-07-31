package cache.display;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
//import java.net.InetAddress;
//import java.net.UnknownHostException;

import javax.swing.SwingConstants;

import cache.server.Cache_Server;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

public class Stats extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final JPanel Stats = new JPanel();
	private final JPanel Cache_content = new JPanel();
	private final JLabel lblStats = new JLabel("LRU Stats");
	private final JLabel lblContents = new JLabel("Cache Stack");
	private final JLabel lblHits = new JLabel("Hits");
	private final JLabel lblHitsRatio = new JLabel("Hits Ratio (%)");
	private final JLabel lblHitsnumber = new JLabel("Hits_number");
	private final JLabel lblHitsrationumber = new JLabel("Hits_ratio_number");
	private final JLabel lblPetitionsSize = new JLabel("Petitions size");
	private final JLabel lblHitsSize = new JLabel("Hits size");
	private final JLabel lblHitsSizeRatio = new JLabel("Hits size ratio");
	private final JTable table = new JTable();
	private final JTable table2 = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JLabel lblStackCount = new JLabel("");
	private final JPanel eCousin_Stats = new JPanel();
	private final JLabel lblEcousinStats = new JLabel("eCousin Stats");
	private final JLabel label_1 = new JLabel("Petitions");
	private final JLabel label_4 = new JLabel("Hits");
	private final JLabel label_5 = new JLabel("Hits Ratio (%)");
	private final JLabel ecopetnum = new JLabel("0");
	private final JLabel ecohitnum = new JLabel("0");
	private final JLabel ecoratnum = new JLabel("0.0");
	private final JLabel ecohitsiz = new JLabel("0");
	private final JLabel ecoratsiz = new JLabel("0.0");
	private final JPanel eCousin_Cache_content = new JPanel();
	private final JLabel lblEcousinCacheStack = new JLabel("eCOUSIN Cache Stack");
	private final JScrollPane scrollPane2 = new JScrollPane();
	private final JLabel ecoStackCount = new JLabel("0");
	private final JPanel panel = new JPanel();
	private final JLabel label = new JLabel("LRU Stats");
	private final JLabel label_6 = new JLabel("Petitions");
	private final JLabel label_7 = new JLabel("Numeric");
	private final JLabel label_8 = new JLabel("Size (bytes)");
	private final JLabel label_9 = new JLabel("Hits");
	private final JLabel label_10 = new JLabel("Hits Ratio (%)");
	private final JLabel label_11 = new JLabel("0");
	private final JLabel label_12 = new JLabel("0");
	private final JLabel label_13 = new JLabel("0.0");
	private final JLabel label_14 = new JLabel("0");
	private final JLabel label_15 = new JLabel("0");
	private final JLabel label_16 = new JLabel("0.0");
	private final JLabel lblCacheSize = new JLabel("Cache Size:");
	private final JLabel Cache_size = new JLabel("0");
	private final JLabel lblEcocoste = new JLabel("Coste (\u20AC):");
	private final JLabel lblCoste = new JLabel("Coste (\u20AC):");
	private final JLabel Coste = new JLabel("");
	private final JLabel EcoCoste = new JLabel("");
	
	public Stats() {
		initGUI();
	}
	private void initGUI() {
		setBackground(SystemColor.menu);
		getContentPane().setBackground(SystemColor.menu);
		setSize(800, 750);
		setVisible(true);
		/*InetAddress host = null;
		try {
			host = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		setTitle("Cache on "+ host.getHostAddress() +" Stats");*/
		setTitle("Cache Statistics - "+ Cache_Server.cache_num);
		Stats.setBounds(7, 95, 382, 170);
		Stats.setBackground(SystemColor.menu);
		Stats.setLayout(null);
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblStats.setBounds(112, 5, 153, 34);
		
		Stats.add(lblStats);
		lblHits.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHits.setBounds(10, 50, 74, 34);
		
		Stats.add(lblHits);
		lblHitsRatio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsRatio.setBounds(10, 95, 133, 34);
		
		Stats.add(lblHitsRatio);
		lblHitsnumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsnumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHitsnumber.setBounds(153, 54, 64, 21);

		
		Stats.add(lblHitsnumber);
		lblHitsrationumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsrationumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHitsrationumber.setBounds(153, 99, 74, 21);
		lblHitsnumber.setText(Integer.toString(Cache_Server.hit));
		
		Stats.add(lblHitsrationumber);
		lblHitsSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsSize.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHitsSize.setBounds(280, 54, 64, 21);
		
		Stats.add(lblHitsSize);
		lblHitsSizeRatio.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsSizeRatio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHitsSizeRatio.setBounds(280, 99, 74, 21);
		
		Stats.add(lblHitsSizeRatio);
		Cache_content.setBounds(-1, 276, 390, 446);
		Cache_content.setBackground(SystemColor.menu);
		Cache_content.setLayout(null);
		lblContents.setHorizontalAlignment(SwingConstants.CENTER);
		lblContents.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblContents.setBounds(78, 11, 186, 43);
		
		Cache_content.add(lblContents);
		scrollPane.setBackground(SystemColor.menu);
		scrollPane.setBounds(33, 68, 347, 367);
		
		Cache_content.add(scrollPane);
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
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaptionBorder);
		panel.setBounds(0, -74, 382, 68);
		
		Stats.add(panel);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label.setBounds(113, 11, 153, 34);
		
		panel.add(label);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_6.setBounds(10, 117, 74, 34);
		
		panel.add(label_6);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_7.setBounds(143, 80, 74, 34);
		
		panel.add(label_7);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_8.setBounds(236, 80, 136, 34);
		
		panel.add(label_8);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_9.setBounds(10, 162, 74, 34);
		
		panel.add(label_9);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_10.setBounds(10, 207, 133, 34);
		
		panel.add(label_10);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_11.setBounds(153, 121, 64, 21);
		
		panel.add(label_11);
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_12.setBounds(153, 166, 64, 21);
		
		panel.add(label_12);
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_13.setBounds(153, 211, 74, 21);
		
		panel.add(label_13);
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_14.setBounds(280, 121, 64, 21);
		
		panel.add(label_14);
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_15.setBounds(280, 166, 64, 21);
		
		panel.add(label_15);
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_16.setBounds(280, 211, 74, 21);
		
		panel.add(label_16);
		lblCoste.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCoste.setBounds(64, 136, 90, 34);
		
		Stats.add(lblCoste);
		Coste.setHorizontalAlignment(SwingConstants.CENTER);
		Coste.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Coste.setBounds(153, 136, 133, 34);
		
		Stats.add(Coste);
		getContentPane().add(Cache_content);
		lblStackCount.setBackground(SystemColor.textHighlight);
		lblStackCount.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblStackCount.setBounds(288, 15, 62, 35);
		
		Cache_content.add(lblStackCount);
		eCousin_Stats.setLayout(null);
		eCousin_Stats.setBackground(SystemColor.activeCaptionBorder);
		eCousin_Stats.setBounds(399, 95, 382, 170);
		
		getContentPane().add(eCousin_Stats);
		lblEcousinStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblEcousinStats.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblEcousinStats.setBounds(102, 0, 188, 34);
		
		eCousin_Stats.add(lblEcousinStats);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_4.setBounds(10, 45, 74, 34);
		
		eCousin_Stats.add(label_4);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_5.setBounds(10, 90, 133, 34);
		
		eCousin_Stats.add(label_5);
		ecohitnum.setHorizontalAlignment(SwingConstants.CENTER);
		ecohitnum.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ecohitnum.setBounds(153, 49, 64, 21);
		
		eCousin_Stats.add(ecohitnum);
		ecoratnum.setHorizontalAlignment(SwingConstants.CENTER);
		ecoratnum.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ecoratnum.setBounds(153, 94, 74, 21);
		
		eCousin_Stats.add(ecoratnum);
		ecohitsiz.setHorizontalAlignment(SwingConstants.CENTER);
		ecohitsiz.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ecohitsiz.setBounds(280, 49, 64, 21);
		
		eCousin_Stats.add(ecohitsiz);
		ecoratsiz.setHorizontalAlignment(SwingConstants.CENTER);
		ecoratsiz.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ecoratsiz.setBounds(280, 94, 74, 21);
		
		eCousin_Stats.add(ecoratsiz);
		lblEcocoste.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEcocoste.setBounds(65, 136, 91, 34);
		
		eCousin_Stats.add(lblEcocoste);
		EcoCoste.setHorizontalAlignment(SwingConstants.CENTER);
		EcoCoste.setFont(new Font("Tahoma", Font.PLAIN, 13));
		EcoCoste.setBounds(153, 136, 133, 34);
		
		eCousin_Stats.add(EcoCoste);
		eCousin_Cache_content.setLayout(null);
		eCousin_Cache_content.setBackground(SystemColor.activeCaptionBorder);
		eCousin_Cache_content.setBounds(399, 276, 390, 446);
		
		getContentPane().add(eCousin_Cache_content);
		lblEcousinCacheStack.setHorizontalAlignment(SwingConstants.CENTER);
		lblEcousinCacheStack.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblEcousinCacheStack.setBounds(10, 11, 278, 43);
		
		eCousin_Cache_content.add(lblEcousinCacheStack);
		scrollPane2.setBackground(SystemColor.activeCaptionBorder);
		scrollPane2.setBounds(10, 68, 347, 367);
		
		eCousin_Cache_content.add(scrollPane2);
		table2.setShowVerticalLines(false);
		table2.setShowHorizontalLines(false);
		table2.setRowHeight(22);
		table2.setGridColor(Color.WHITE);
		table2.setForeground(Color.BLACK);
		table2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table2.setBackground(SystemColor.activeCaptionBorder);
		
		scrollPane2.setViewportView(table2);
		ecoStackCount.setFont(new Font("Tahoma", Font.PLAIN, 27));
		ecoStackCount.setBackground(SystemColor.activeCaption);
		ecoStackCount.setBounds(298, 15, 62, 35);
		
		eCousin_Cache_content.add(ecoStackCount);
		label_1.setBounds(217, 11, 111, 34);
		getContentPane().add(label_1);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		ecopetnum.setBounds(338, 20, 64, 21);
		getContentPane().add(ecopetnum);
		ecopetnum.setHorizontalAlignment(SwingConstants.CENTER);
		ecopetnum.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPetitionsSize.setBounds(399, 20, 184, 21);
		getContentPane().add(lblPetitionsSize);
		lblPetitionsSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblPetitionsSize.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblCacheSize.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblCacheSize.setBounds(217, 56, 138, 34);
		
		getContentPane().add(lblCacheSize);
		Cache_size.setHorizontalAlignment(SwingConstants.CENTER);
		Cache_size.setFont(new Font("Tahoma", Font.PLAIN, 22));
		Cache_size.setBounds(365, 63, 64, 21);
		
		getContentPane().add(Cache_size);
	}
	
	public void refresh_stats() {
		
		table.setTableHeader(null);
		table2.setTableHeader(null);
		
		table.setModel(new MyTableModel(Cache_Server.c));
		table2.setModel(new MyTableModel(Cache_Server.c2));
		
		Cache_size.setText(Integer.toString(Cache_Server.cache_lines));
		lblPetitionsSize.setText(Long.toString(Cache_Server.peticiones_bytes));
		ecopetnum.setText(Integer.toString(Cache_Server.peticiones));
		
		lblStackCount.setText(Integer.toString(Cache_Server.c.usedEntries()));
		lblHitsnumber.setText(Integer.toString(Cache_Server.hit));
		lblHitsrationumber.setText(Double.toString(Cache_Server.porc));
		lblHitsSize.setText(Long.toString(Cache_Server.hits_bytes));
		lblHitsSizeRatio.setText(Double.toString(Cache_Server.porc_bytes));
		Coste.setText(Double.toString(Cache_Server.costeLRU));
		
		ecoStackCount.setText(Integer.toString(Cache_Server.c2.usedEntries()));
		ecohitnum.setText(Integer.toString(Cache_Server.hit2));
		ecoratnum.setText(Double.toString(Cache_Server.porc2));
		ecohitsiz.setText(Long.toString(Cache_Server.hits_bytes2));
		ecoratsiz.setText(Double.toString(Cache_Server.porc_bytes2));
		EcoCoste.setText(Double.toString(Cache_Server.costeECO));
		
		
	}
	/*
	public void refresh_stack1() {
		
		table.setModel(new MyTableModel(Cache_Server.c));
		
		Cache_size.setText(Integer.toString(Cache_Server.cache_lines));
		
		lblStackCount.setText(Integer.toString(Cache_Server.c.usedEntries()));
		lblHitsnumber.setText(Integer.toString(Cache_Server.hit));
		lblHitsrationumber.setText(Double.toString(Cache_Server.porc));
		lblPetitionsSize.setText(Long.toString(Cache_Server.peticiones_bytes));
		lblHitsSize.setText(Long.toString(Cache_Server.hits_bytes));
		lblHitsSizeRatio.setText(Double.toString(Cache_Server.porc_bytes));
	}
	public void refresh_stack2() {
		
		table2.setModel(new MyTableModel(Cache_Server.c2));
		
		Cache_size.setText(Integer.toString(Cache_Server.cache_lines));
		
		ecoStackCount.setText(Integer.toString(Cache_Server.c2.usedEntries()));
		ecopetnum.setText(Integer.toString(Cache_Server.peticiones));
		ecohitnum.setText(Integer.toString(Cache_Server.hit2));
		ecoratnum.setText(Double.toString(Cache_Server.porc2));
		ecohitsiz.setText(Long.toString(Cache_Server.hits_bytes2));
		ecoratsiz.setText(Double.toString(Cache_Server.porc_bytes2));
	}
	public void refresh_stats1() {

		Cache_size.setText(Integer.toString(Cache_Server.cache_lines));
		
		lblStackCount.setText(Integer.toString(Cache_Server.c.usedEntries()));
		lblHitsnumber.setText(Integer.toString(Cache_Server.hit));
		lblHitsrationumber.setText(Double.toString(Cache_Server.porc));
		lblPetitionsSize.setText(Long.toString(Cache_Server.peticiones_bytes));
		lblHitsSize.setText(Long.toString(Cache_Server.hits_bytes));
		lblHitsSizeRatio.setText(Double.toString(Cache_Server.porc_bytes));
	}
	public void refresh_stats2() {
		
		Cache_size.setText(Integer.toString(Cache_Server.cache_lines));
		
		ecoStackCount.setText(Integer.toString(Cache_Server.c2.usedEntries()));
		ecopetnum.setText(Integer.toString(Cache_Server.peticiones));
		ecohitnum.setText(Integer.toString(Cache_Server.hit2));
		ecoratnum.setText(Double.toString(Cache_Server.porc2));
		ecohitsiz.setText(Long.toString(Cache_Server.hits_bytes2));
		ecoratsiz.setText(Double.toString(Cache_Server.porc_bytes2));
	}
	*/
	
	/**
	 * Starts the already initialized frame, making it 
	 * visible and ready to interact with the user. 
	 */ 
	public void start() {
	   setVisible(true);
	}
}
