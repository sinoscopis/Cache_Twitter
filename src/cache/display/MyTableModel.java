package cache.display;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

import cache.LRUCache;
import cache.server.Cache_Server;

class MyTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	//private String columnName = "File";
	private Object[][] data;

	MyTableModel(LRUCache<String, String> cache) {
		data = new Object[cache.usedEntries()][1];
		int i = 0;
		
		for (Map.Entry<String, String> e : Cache_Server.c.getAll()) {
			data[i][0] = e.getValue();
			i++;
		}
	}

	public int getColumnCount() {
		return 1;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return "File";
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
}