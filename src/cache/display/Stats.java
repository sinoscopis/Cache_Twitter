/*
 * Ventana de estadisticas
 * refresh_stats() metodo que refresca la ventana de estadisticas
 */

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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class Stats extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final JPanel Stats = new JPanel();
	private final JPanel Cache_content = new JPanel();
	private final JLabel lblStats = new JLabel("Stats");
	private final JLabel lblContents = new JLabel("Pull");
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
	private final JLabel lblEcousinCacheStack = new JLabel("Pull eCOUSIN");
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
	private final JLabel cache_typelbl = new JLabel("Stats");
	private final JPanel panel_1 = new JPanel();
	private final JLabel label_17 = new JLabel("Stats");
	private final JLabel label_18 = new JLabel("Hits");
	private final JLabel label_19 = new JLabel("Hits Ratio");
	private final JLabel lblHitsnumber_push = new JLabel("0");
	private final JLabel lblHitsrationumber_push = new JLabel("0");
	private final JPanel panel_2 = new JPanel();
	private final JLabel label_22 = new JLabel("LRU Stats");
	private final JLabel label_23 = new JLabel("Petitions");
	private final JLabel label_24 = new JLabel("Numeric");
	private final JLabel label_25 = new JLabel("Size (bytes)");
	private final JLabel label_26 = new JLabel("Hits");
	private final JLabel label_27 = new JLabel("Hits Ratio (%)");
	private final JLabel label_28 = new JLabel("0");
	private final JLabel label_29 = new JLabel("0");
	private final JLabel label_30 = new JLabel("0.0");
	private final JLabel label_31 = new JLabel("0");
	private final JLabel label_32 = new JLabel("0");
	private final JLabel label_33 = new JLabel("0.0");
	private final JLabel label_34 = new JLabel("%");
	private final JLabel cache_typelbl2 = new JLabel("Stats");
	private final JPanel panel_3 = new JPanel();
	private final JLabel lblPushEcousinStats = new JLabel("Push eCousin Stats");
	private final JLabel label_37 = new JLabel("Hits");
	private final JLabel label_38 = new JLabel("Hits Ratio");
	private final JLabel ecohitnum_push = new JLabel("0");
	private final JLabel ecoratnum_push = new JLabel("0");
	private final JLabel label_41 = new JLabel("%");
	private final JLabel label_42 = new JLabel("Enhance:");
	private final JLabel enhance_lbl_push = new JLabel("0");
	private final JLabel label_44 = new JLabel("%");
	private final JPanel panel_4 = new JPanel();
	private final JLabel lblPushStack = new JLabel("Push");
	private final JScrollPane scrollPane3 = new JScrollPane();
	private final JLabel pushStackCount = new JLabel("0");
	private final JPanel panel_5 = new JPanel();
	private final JLabel lblPushEcousin = new JLabel("Push eCOUSIN");
	private final JScrollPane scrollPane4 = new JScrollPane();
	private final JLabel pushecoStackCount = new JLabel("0");
	private final JLabel lblPush = new JLabel("Push");
	private final JTable table3 = new JTable();
	private final JTable table4 = new JTable();
	
	
	public Stats() throws UnknownHostException {
		initGUI();
	}
	private void initGUI() throws UnknownHostException {
		setBackground(SystemColor.menu);
		getContentPane().setBackground(SystemColor.menu);
		setSize(1230, 750);
		setVisible(true);
		setTitle("Cache "+ Cache_Server.cache_type +" number " +Cache_Server.cache_num + " on "+ InetAddress.getLocalHost().getHostAddress() +" Stats");
		if(Cache_Server.cache_type.startsWith("fifo")){
			cache_typelbl.setText("FIFO");
			cache_typelbl2.setText("FIFO");
		}
		else{
			cache_typelbl.setText("LRU");
			cache_typelbl2.setText("LRU");
		}
		
		Stats.setBorder(new LineBorder(new Color(0, 0, 0)));
		Stats.setBounds(7, 95, 301, 170);
		Stats.setBackground(SystemColor.menu);
		Stats.setLayout(null);
		lblStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblStats.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblStats.setBounds(157, 11, 74, 34);
		
		Stats.add(lblStats);
		lblHits.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHits.setBounds(45, 75, 74, 21);
		
		Stats.add(lblHits);
		lblHitsRatio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsRatio.setBounds(45, 120, 74, 21);
		
		Stats.add(lblHitsRatio);
		lblHitsnumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsnumber.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsnumber.setBounds(157, 75, 74, 21);

		
		Stats.add(lblHitsnumber);
		lblHitsrationumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsrationumber.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsrationumber.setBounds(157, 120, 74, 21);
		lblHitsnumber.setText(Integer.toString(Cache_Server.hit));
		
		Stats.add(lblHitsrationumber);
		Cache_content.setBounds(-1, 276, 309, 446);
		Cache_content.setBackground(SystemColor.menu);
		Cache_content.setLayout(null);
		lblContents.setHorizontalAlignment(SwingConstants.CENTER);
		lblContents.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblContents.setBounds(33, 14, 186, 43);
		
		Cache_content.add(lblContents);
		scrollPane.setBackground(SystemColor.menu);
		scrollPane.setBounds(33, 68, 250, 367);
		
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
		label_3.setBounds(223, 120, 33, 21);
		
		Stats.add(label_3);
		cache_typelbl.setHorizontalAlignment(SwingConstants.CENTER);
		cache_typelbl.setFont(new Font("Tahoma", Font.PLAIN, 27));
		cache_typelbl.setBounds(85, 11, 74, 34);
		
		Stats.add(cache_typelbl);
		getContentPane().add(Cache_content);
		lblStackCount.setBackground(SystemColor.textHighlight);
		lblStackCount.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblStackCount.setBounds(215, 18, 62, 35);
		
		Cache_content.add(lblStackCount);
		eCousin_Stats.setBorder(new LineBorder(new Color(0, 0, 0)));
		eCousin_Stats.setLayout(null);
		eCousin_Stats.setBackground(SystemColor.menu);
		eCousin_Stats.setBounds(318, 95, 286, 170);
		
		getContentPane().add(eCousin_Stats);
		lblEcousinStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblEcousinStats.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblEcousinStats.setBounds(0, 11, 286, 34);
		
		eCousin_Stats.add(lblEcousinStats);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_4.setBounds(33, 74, 74, 21);
		
		eCousin_Stats.add(label_4);
		lblHitsRatio_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsRatio_1.setBounds(33, 119, 74, 21);
		
		eCousin_Stats.add(lblHitsRatio_1);
		ecohitnum.setHorizontalAlignment(SwingConstants.CENTER);
		ecohitnum.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ecohitnum.setBounds(162, 74, 74, 21);
		
		eCousin_Stats.add(ecohitnum);
		ecoratnum.setHorizontalAlignment(SwingConstants.CENTER);
		ecoratnum.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ecoratnum.setBounds(162, 119, 74, 21);
		
		eCousin_Stats.add(ecoratnum);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_2.setBounds(229, 119, 33, 21);
		
		eCousin_Stats.add(label_2);
		eCousin_Cache_content.setLayout(null);
		eCousin_Cache_content.setBackground(SystemColor.menu);
		eCousin_Cache_content.setBounds(318, 276, 286, 446);
		
		getContentPane().add(eCousin_Cache_content);
		lblEcousinCacheStack.setHorizontalAlignment(SwingConstants.CENTER);
		lblEcousinCacheStack.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblEcousinCacheStack.setBounds(10, 11, 209, 43);
		
		eCousin_Cache_content.add(lblEcousinCacheStack);
		scrollPane2.setBackground(SystemColor.activeCaptionBorder);
		scrollPane2.setBounds(10, 68, 250, 367);
		
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
		ecoStackCount.setBounds(240, 15, 62, 35);
		
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
		lblEnhance.setBounds(366, 63, 101, 21);
		
		getContentPane().add(lblEnhance);
		enhance_lbl.setForeground(SystemColor.desktop);
		enhance_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		enhance_lbl.setFont(new Font("Tahoma", Font.PLAIN, 22));
		enhance_lbl.setBounds(479, 63, 64, 21);
		
		getContentPane().add(enhance_lbl);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(154, 63, 36, 21);
		
		getContentPane().add(label_1);
		label_5.setForeground(SystemColor.desktop);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_5.setBounds(550, 63, 36, 21);
		
		getContentPane().add(label_5);
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.activeCaptionBorder);
		panel_1.setBounds(614, 95, 301, 170);
		
		getContentPane().add(panel_1);
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_17.setBounds(182, 11, 74, 34);
		
		panel_1.add(label_17);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_18.setBounds(45, 75, 74, 21);
		
		panel_1.add(label_18);
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_19.setBounds(45, 120, 74, 21);
		
		panel_1.add(label_19);
		lblHitsnumber_push.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsnumber_push.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsnumber_push.setBounds(157, 75, 74, 21);
		
		panel_1.add(lblHitsnumber_push);
		lblHitsrationumber_push.setHorizontalAlignment(SwingConstants.CENTER);
		lblHitsrationumber_push.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsrationumber_push.setBounds(157, 120, 74, 21);
		
		panel_1.add(lblHitsrationumber_push);
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.activeCaptionBorder);
		panel_2.setBounds(0, -74, 382, 68);
		
		panel_1.add(panel_2);
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_22.setBounds(113, 11, 153, 34);
		
		panel_2.add(label_22);
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_23.setBounds(10, 117, 74, 34);
		
		panel_2.add(label_23);
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		label_24.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_24.setBounds(143, 80, 74, 34);
		
		panel_2.add(label_24);
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		label_25.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_25.setBounds(236, 80, 136, 34);
		
		panel_2.add(label_25);
		label_26.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_26.setBounds(10, 162, 74, 34);
		
		panel_2.add(label_26);
		label_27.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_27.setBounds(10, 207, 133, 34);
		
		panel_2.add(label_27);
		label_28.setHorizontalAlignment(SwingConstants.CENTER);
		label_28.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_28.setBounds(153, 121, 64, 21);
		
		panel_2.add(label_28);
		label_29.setHorizontalAlignment(SwingConstants.CENTER);
		label_29.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_29.setBounds(153, 166, 64, 21);
		
		panel_2.add(label_29);
		label_30.setHorizontalAlignment(SwingConstants.CENTER);
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_30.setBounds(153, 211, 74, 21);
		
		panel_2.add(label_30);
		label_31.setHorizontalAlignment(SwingConstants.CENTER);
		label_31.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_31.setBounds(280, 121, 64, 21);
		
		panel_2.add(label_31);
		label_32.setHorizontalAlignment(SwingConstants.CENTER);
		label_32.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_32.setBounds(280, 166, 64, 21);
		
		panel_2.add(label_32);
		label_33.setHorizontalAlignment(SwingConstants.CENTER);
		label_33.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_33.setBounds(280, 211, 74, 21);
		
		panel_2.add(label_33);
		label_34.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_34.setBounds(223, 120, 33, 21);
		
		panel_1.add(label_34);
		cache_typelbl2.setHorizontalAlignment(SwingConstants.CENTER);
		cache_typelbl2.setFont(new Font("Tahoma", Font.PLAIN, 27));
		cache_typelbl2.setBounds(110, 11, 74, 34);
		
		panel_1.add(cache_typelbl2);
		lblPush.setHorizontalAlignment(SwingConstants.CENTER);
		lblPush.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblPush.setBounds(35, 11, 74, 34);
		
		panel_1.add(lblPush);
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBackground(SystemColor.activeCaptionBorder);
		panel_3.setBounds(925, 95, 286, 170);
		
		getContentPane().add(panel_3);
		lblPushEcousinStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblPushEcousinStats.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblPushEcousinStats.setBounds(0, 11, 286, 34);
		
		panel_3.add(lblPushEcousinStats);
		label_37.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_37.setBounds(33, 74, 74, 21);
		
		panel_3.add(label_37);
		label_38.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_38.setBounds(33, 119, 74, 21);
		
		panel_3.add(label_38);
		ecohitnum_push.setHorizontalAlignment(SwingConstants.CENTER);
		ecohitnum_push.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ecohitnum_push.setBounds(162, 74, 74, 21);
		
		panel_3.add(ecohitnum_push);
		ecoratnum_push.setHorizontalAlignment(SwingConstants.CENTER);
		ecoratnum_push.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ecoratnum_push.setBounds(162, 119, 74, 21);
		
		panel_3.add(ecoratnum_push);
		label_41.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_41.setBounds(229, 119, 33, 21);
		
		panel_3.add(label_41);
		label_42.setForeground(SystemColor.desktop);
		label_42.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_42.setBounds(957, 63, 101, 21);
		
		getContentPane().add(label_42);
		enhance_lbl_push.setHorizontalAlignment(SwingConstants.CENTER);
		enhance_lbl_push.setForeground(SystemColor.desktop);
		enhance_lbl_push.setFont(new Font("Tahoma", Font.PLAIN, 22));
		enhance_lbl_push.setBounds(1070, 63, 64, 21);
		
		getContentPane().add(enhance_lbl_push);
		label_44.setForeground(SystemColor.desktop);
		label_44.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_44.setBounds(1141, 63, 36, 21);
		
		getContentPane().add(label_44);
		panel_4.setLayout(null);
		panel_4.setBackground(SystemColor.activeCaptionBorder);
		panel_4.setBounds(614, 276, 301, 446);
		
		getContentPane().add(panel_4);
		lblPushStack.setHorizontalAlignment(SwingConstants.CENTER);
		lblPushStack.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblPushStack.setBounds(33, 14, 186, 43);
		
		panel_4.add(lblPushStack);
		scrollPane3.setBackground(SystemColor.activeCaptionBorder);
		scrollPane3.setBounds(33, 68, 250, 367);
		
		panel_4.add(scrollPane3);
		table3.setRowHeight(22);
		table3.setGridColor(Color.WHITE);
		table3.setForeground(Color.BLACK);
		table3.setShowHorizontalLines(false);
		table3.setShowVerticalLines(false);
		table3.setBackground(SystemColor.activeCaptionBorder);
		table3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		scrollPane3.setViewportView(table3);
		pushStackCount.setFont(new Font("Tahoma", Font.PLAIN, 27));
		pushStackCount.setBackground(SystemColor.activeCaption);
		pushStackCount.setBounds(215, 18, 62, 35);
		
		panel_4.add(pushStackCount);
		panel_5.setLayout(null);
		panel_5.setBackground(SystemColor.activeCaptionBorder);
		panel_5.setBounds(925, 276, 286, 446);
		
		getContentPane().add(panel_5);
		lblPushEcousin.setHorizontalAlignment(SwingConstants.CENTER);
		lblPushEcousin.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblPushEcousin.setBounds(10, 11, 209, 43);
		
		panel_5.add(lblPushEcousin);
		scrollPane4.setBackground(SystemColor.activeCaptionBorder);
		scrollPane4.setBounds(10, 68, 250, 367);
		
		panel_5.add(scrollPane4);
		table4.setRowHeight(22);
		table4.setGridColor(Color.WHITE);
		table4.setForeground(Color.BLACK);
		table4.setShowHorizontalLines(false);
		table4.setShowVerticalLines(false);
		table4.setBackground(SystemColor.activeCaptionBorder);
		table4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		scrollPane4.setViewportView(table4);
		pushecoStackCount.setFont(new Font("Tahoma", Font.PLAIN, 27));
		pushecoStackCount.setBackground(SystemColor.activeCaption);
		pushecoStackCount.setBounds(240, 15, 62, 35);
		
		panel_5.add(pushecoStackCount);
	}
	
	public void refresh_stats() {

		table.setTableHeader(null);
		table2.setTableHeader(null);
		table3.setTableHeader(null);
		table4.setTableHeader(null);
		
		table.setModel(new MyTableModel(Cache_Server.c));
		table2.setModel(new MyTableModel(Cache_Server.c2));
		table3.setModel(new MyTableModel(Cache_Server.c3));
		table4.setModel(new MyTableModel(Cache_Server.c4));
		
		Cache_size.setText(Integer.toString(Cache_Server.cache_lines));
		Conected_users.setText(Integer.toString(Cache_Server.users_by_cache));
		threshold_lbl.setText(Integer.toString(Cache_Server.umbral));
		ecopetnum.setText(Integer.toString(Cache_Server.peticiones));
		
		////////////PULL//////////////
		
		String resultLabel3 = df.format(Cache_Server.enhance);
		enhance_lbl.setText(resultLabel3);
		
		lblStackCount.setText(Integer.toString(Cache_Server.c.usedEntries()));
		lblHitsnumber.setText(Integer.toString(Cache_Server.hit));
		String resultLabel = df.format(Cache_Server.porc);
		lblHitsrationumber.setText(resultLabel);
		
		ecoStackCount.setText(Integer.toString(Cache_Server.c2.usedEntries()));
		ecohitnum.setText(Integer.toString(Cache_Server.hit2));
		String resultLabel2 = df.format(Cache_Server.porc2);
		ecoratnum.setText(resultLabel2);
		
		
		
		////////////PUSH//////////////
		
		String resultLabel4 = df.format(Cache_Server.enhance2);
		enhance_lbl_push.setText(resultLabel4);
		
		pushStackCount.setText(Integer.toString(Cache_Server.c3.usedEntries()));
		lblHitsnumber_push.setText(Integer.toString(Cache_Server.hit3));
		String resultLabel5 = df.format(Cache_Server.porc3);
		lblHitsrationumber_push.setText(resultLabel5);
		
		pushecoStackCount.setText(Integer.toString(Cache_Server.c4.usedEntries()));
		ecohitnum_push.setText(Integer.toString(Cache_Server.hit4));
		String resultLabel6 = df.format(Cache_Server.porc4);
		ecoratnum_push.setText(resultLabel6);
	}
	
	public void refresh_stats_push() {

		table3.setTableHeader(null);
		table4.setTableHeader(null);
		
		table3.setModel(new MyTableModel(Cache_Server.c3));
		table4.setModel(new MyTableModel(Cache_Server.c4));
		
		pushStackCount.setText(Integer.toString(Cache_Server.c3.usedEntries()));
		
		pushecoStackCount.setText(Integer.toString(Cache_Server.c4.usedEntries()));
		
	}
	
	/**
	 * Starts the already initialized frame, making it 
	 * visible and ready to interact with the user. 
	 */ 
	public void start() {
	   setVisible(true);
	}
}
