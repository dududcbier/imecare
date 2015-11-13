package modelo;

public class Person {

	String name = " ";
	String address = " ";
	String email = " ";
	String birthDate = " ";
	String parentesco = " ";
	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	int phone[] = new int[15];
	String rg = " ";
	private String cpf = " ";
	private String bloodType = " ";	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getBloodType() {
		return bloodType;
	}
	
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int[] getPhone() {
		return phone;
	}

	public void setPhone(int[] phone) {
		this.phone = phone;
	}
		
}
