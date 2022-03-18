package contactmanager;

public class ContactsData {
	
	private String id, name, num, mail, addr, dob, notes, uid;
	
	public ContactsData() {}
	public ContactsData(String data[]) {
		super();
		int i = 0;
		this.id = (data.length > 7) ? data[i++] : id;
		this.name = data[i++];
		this.num = data[i++];
		this.mail = data[i++];
		this.addr = data[i++];
		this.dob = data[i++];
		this.notes = data[i++];
		this.uid = data[i];
	}
	
	public void setData(String data[]) {
		int i = (data.length > 7) ? 1 : 0;
		name = data[i++];
		num = data[i++];
		mail = data[i++];
		addr = data[i++];
		dob = data[i++];
		notes = data[i++];
		uid = data[i++];
	}
	
	public String[] getData() {
		String data[]= {name, num, mail, addr, dob, notes};
		return data;
	}
	public String getId() {
		return id;
	}
	public String getUID() {
		return uid;
	}
	
}
