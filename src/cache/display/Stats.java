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
import java.text.DecimalFormat;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class Stats extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final JPanel Stats = new JPanel();
	private final JPanel Cache_content = new JPanel();
	private final JLabel lblStats = new JLabel("LRU Stats");
	private final JLabel lblContents = new JLabel("Cache Stack");
	private final JLabel lblHits = new JLabel("Hits");
	private final JLabel lblHitsRatio = new JLabel("Hits Ratio");
	private final JLabel lblHitsnumber = new JLabel("Hits_number");
	private final JLabel lblHitsrationumber = new JLabel("Hits_ratio_number");
	private final JTable table = new JTable();
	private final JTable table2 = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JLabel lblStackCount = new JLabel("");
	private final JPanel eCousin_Stats = new JPanel();
	private final JLabel lblEcousinStats = new JLabel("eCousin Stats");
	private final JLabel lblRequests = new JLabel("# Requests:");
	private final JLabel label_4 = new JLabel("Hits");
	private final JLabel lblHitsRatio_1 = new JLabel("Hits Ratio");
	private final JLabel ecopetnum = new JLabel("0");
	private final JLabel ecohitnum = new JLabel("0");
	private final JLabel ecoratnum = new JLabel("0.0");
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
	private final JLabel lblUsuarios = new JLabel("Users:");
	private final JLabel Conected_users = new JLabel("0");
	private final JLabel lblThreshold = new JLabel("Threshold:");
	private final JLabel threshold_lbl = new JLabel("0");
	private final JLabel lblEnhance = new JLabel("Enhance:");
	private final JLabel enhance_lbl = new JLabel("0");
	private final JLabel label_1 = new JLabel("%");
	private final JLabel label_2 = new JLabel("%");
	private final JLabel label_3 = new JLabel("%");
	private final JLabel label_5 = new JLabel("%");
	String format = "####.##";
	DecimalFormat df = new DecimalFormat(format);
	
	
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
		Stats.setBorder(new LineBorder(new Color(0, 0, 0)));
		Stats.setBounds(7, 95, 382, 170);
		Stats.setBackground(SystemColor.menu);
		Stats.setLayout(null);
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblStats.setBounds(112, 5, 153, 34);
		
		Stats.add(lblStats);
		lblHits.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHits.setBounds(79, 69, 74, 21);
		
		Stats.add(lblHits);
		lblHitsRatio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsRatio.setBounds(79, 114, 74, 21);
		
		Stats.add(lblHitsRatio);
		lblHitsnumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsnumber.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsnumber.setBounds(191, 69, 74, 21);

		
		Stats.add(lblHitsnumber);
		lblHitsrationumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsrationumber.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsrationumber.setBounds(191, 114, 74, 21);
		lblHitsnumber.setText(Integer.toString(Cache_Server.hit));
		
		Stats.add(lblHitsrationumber);
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
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_3.setBounds(257, 114, 74, 21);
		
		Stats.add(label_3);
		getContentPane().add(Cache_content);
		lblStackCount.setBackground(SystemColor.textHighlight);
		lblStackCount.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblStackCount.setBounds(288, 15, 62, 35);
		
		Cache_content.add(lblStackCount);
		eCousin_Stats.setBorder(new LineBorder(new Color(0, 0, 0)));
		eCousin_Stats.setLayout(null);
		eCousin_Stats.setBackground(SystemColor.menu);
		eCousin_Stats.setBounds(399, 95, 382, 170);
		
		getContentPane().add(eCousin_Stats);
		lblEcousinStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblEcousinStats.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblEcousinStats.setBounds(102, 0, 188, 34);
		
		eCousin_Stats.add(lblEcousinStats);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_4.setBounds(87, 63, 74, 21);
		
		eCousin_Stats.add(label_4);
		lblHitsRatio_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsRatio_1.setBounds(87, 108, 74, 21);
		
		eCousin_Stats.add(lblHitsRatio_1);
		ecohitnum.setHorizontalAlignment(SwingConstants.CENTER);
		ecohitnum.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ecohitnum.setBounds(216, 63, 74, 21);
		
		eCousin_Stats.add(ecohitnum);
		ecoratnum.setHorizontalAlignment(SwingConstants.CENTER);
		ecoratnum.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ecoratnum.setBounds(216, 108, 74, 21);
		
		eCousin_Stats.add(ecoratnum);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_2.setBounds(283, 108, 74, 21);
		
		eCousin_Stats.add(label_2);
		eCousin_Cache_content.setLayout(null);
		eCousin_Cache_content.setBackground(SystemColor.menu);
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
		lblRequests.setBounds(202, 11, 139, 34);
		getContentPane().add(lblRequests);
		lblRequests.setFont(new Font("Tahoma", Font.PLAIN, 22));
		ecopetnum.setBounds(351, 20, 64, 21);
		getContentPane().add(ecopetnum);
		ecopetnum.setHorizontalAlignment(SwingConstants.CENTER);
		ecopetnum.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblCacheSize.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCacheSize.setBounds(7, 11, 138, 34);
		
		getContentPane().add(lblCacheSize);
		Cache_size.setHorizontalAlignment(SwingConstants.CENTER);
		Cache_size.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Cache_size.setBounds(97, 20, 64, 21);
		
		getContentPane().add(Cache_size);
		lblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuarios.setBounds(7, 40, 101, 21);
		
		getContentPane().add(lblUsuarios);
		Conected_users.setHorizontalAlignment(SwingConstants.CENTER);
		Conected_users.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Conected_users.setBounds(84, 40, 93, 21);
		
		getContentPane().add(Conected_users);
		lblThreshold.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThreshold.setBounds(7, 63, 101, 21);
		
		getContentPane().add(lblThreshold);
		threshold_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		threshold_lbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		threshold_lbl.setBounds(84, 63, 93, 21);
		
		getContentPane().add(threshold_lbl);
		lblEnhance.setForeground(SystemColor.desktop);
		lblEnhance.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEnhance.setBounds(486, 38, 101, 21);
		
		getContentPane().add(lblEnhance);
		enhance_lbl.setForeground(SystemColor.desktop);
		enhance_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		enhance_lbl.setFont(new Font("Tahoma", Font.PLAIN, 22));
		enhance_lbl.setBounds(599, 38, 64, 21);
		
		getContentPane().add(enhance_lbl);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(154, 63, 36, 21);
		
		getContentPane().add(label_1);
		label_5.setForeground(SystemColor.desktop);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_5.setBounds(670, 38, 36, 21);
		
		getContentPane().add(label_5);
	}
	
	public void refresh_stats() {

		table.setTableHeader(null);
		table2.setTableHeader(null);
		
		table.setModel(new MyTableModel(Cache_Server.c));
		table2.setModel(new MyTableModel(Cache_Server.c2));
		
		Cache_size.setText(Integer.toString(Cache_Server.cache_lines));
		Conected_users.setText(Integer.toString(Cache_Server.users_by_cache));
		threshold_lbl.setText(Integer.toString(Cache_Server.umbral));
		ecopetnum.setText(Integer.toString(Cache_Server.peticiones));
		//enhance_lbl.setText(Double.toString(Cache_Server.enhance));
		
		String resultLabel3 = df.format(Cache_Server.enhance);
		enhance_lbl.setText(resultLabel3);
		
		lblStackCount.setText(Integer.toString(Cache_Server.c.usedEntries()));
		lblHitsnumber.setText(Integer.toString(Cache_Server.hit));
		//lblHitsrationumber.setText(Double.toString(Cache_Server.porc));
		
		String resultLabel = df.format(Cache_Server.porc);
		lblHitsrationumber.setText(resultLabel);
		
		ecoStackCount.setText(Integer.toString(Cache_Server.c2.usedEntries()));
		ecohitnum.setText(Integer.toString(Cache_Server.hit2));
		//ecoratnum.setText(Double.toString(Cache_Server.porc2));
		
		String resultLabel2 = df.format(Cache_Server.porc2);
		ecoratnum.setText(resultLabel2);
	}
	
	/**
	 * Starts the already initialized frame, making it 
	 * visible and ready to interact with the user. 
	 */ 
	public void start() {
	   setVisible(true);
	}
}
