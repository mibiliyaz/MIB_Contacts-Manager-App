package contactmanager;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ContactsTable extends AbstractTableModel{
	public static final int ID = -1;
	private static final int NAME = 0, NUM = 1, MAIL = 2, ADDR = 3, DOB = 4, NOTES = 5;
	
	private String[] columnNames = {"Name", "Number ", "Mail", "Address", "DOB", "Notes"};
	private List<ContactsData> contacts;
	
	public ContactsTable(List<ContactsData> AllContacts) {
		contacts = AllContacts;
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
		ContactsData temp=contacts.get(row);
		String[] data = temp.getData();
		switch(col)
		{
		case NAME:
			return data[1];
		case NUM:
			return data[2];
		case MAIL:
			return data[3];
		case ADDR:
			return data[4];
		case DOB:
			return data[5];
		case NOTES:
			return data[6];
		case ID:
			return temp;
		default:
			return data[1];
		}
	}
	
	 @Override
	 public Class getColumnClass(int c) {
	 	return getValueAt(0,c).getClass();
	 }

}
