package modelo;

public class Login {

	String user;
	String email;
	String password;
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void checksLogin(String eMail, String passwd) {
		//if login AND pass word associated is in system
		//returns valid;
		//else
		//returns invalid; try again until 3 times
	}
	
	public boolean isValid(String eMail, String passwd) {
		//if login AND password associated is in system
		//returns valid;
				//else
				//returns invalid; try again until 3 times
		return true;
	}
	
	public void showAll() {
		
	}
	
	public void showOne(String patient) {
		
	}
	
	
	
	public void checksAcess(String name, String type) {
		
		
		switch (type) {
		
			case "doctor":
				showAll(); //autoriza ver todos os prontuários
				break;
			case "nurse":
				//autoriza ver todos os prontuários
				break;
			case "patient":
				//showOne(String patient);//autoriza só o próprio prontuário
				break;
			default:
				System.out.println("Este não é um tipo válido!");
		}
			
		
		
		
	}
	
	
	
}
