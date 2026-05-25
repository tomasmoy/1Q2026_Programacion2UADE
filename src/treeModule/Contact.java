package treeModule;

public class Contact implements Comparable<Contact>{

	String firstName;
	String lastName;
	int cellphone;
		
	public Contact(String nombre, String apellido,int telefono) {
		this.firstName = nombre;
		this.lastName = apellido;
		this.cellphone = telefono;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public int getCellphone() {
		return cellphone;
	}



	public void setCellphone(int cellphone) {
		this.cellphone = cellphone;
	}



	@Override
	public int compareTo(Contact other) {
		String comparableContactString = this.firstName + this.lastName;
		return comparableContactString.compareTo(other.firstName + other.lastName);
	}
	
}
