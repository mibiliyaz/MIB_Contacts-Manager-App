package contactmanager;

public class ContactsData {
	
	int id;
	private String name, num, mail, addr, dob, notes;
	
	public ContactsData(String data[]) {
		super();
		this.id = Integer.parseInt(data[0]);
		this.name = data[1];
		this.num = data[2];
		this.mail = data[3];
		this.addr = data[4];
		this.dob = data[5];
		this.notes = data[6];
	}
	
	public void setData(String data[]) {
		id = Integer.parseInt(data[0]);
		name = data[1];
		num = data[2];
		mail = data[3];
		addr = data[4];
		dob = data[5];
		notes = data[6];
	}
	
	public String[] getData() {
		String data[]= {Integer.toString(id), name, num, mail, addr, dob, notes};
		return data;
	}
	
	public int getId() {
		return id;
	}
	
}
