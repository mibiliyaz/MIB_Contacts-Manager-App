package contactmanager;

import java.util.List;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ContactsTable extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int NAME = 0, NUM = 1, MAIL = 2;
	
	private String[] columnNames = {"Name", "Number ", "Mail"};
	private List<ContactsData> contacts;

	public ContactsTable(List<ContactsData> cdata) {
		contacts = cdata;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return contacts.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		ContactsData cdata = contacts.get(row);
		String[] data = cdata.getData();
		switch(col) {
			case NAME:
				return data[0];
			case NUM:
				return data[1];
			case MAIL:
				return data[2];
			case OBJECT_COL:
				return cdata;
			default:
				return data[0];
		}
	}
	
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 @Override
	 public Class getColumnClass(int c) {
	 	return getValueAt(0,c).getClass();
	 }

}
