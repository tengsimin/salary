package gcloud.email.entity;

public class UserEmail {
	private int id;
	private String name;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	private String foxaddrID;
	public String getName() {
		return name;
	}
	public String getFoxaddrID() {
		return foxaddrID;
	}
	public void setFoxaddrID(String foxaddrID) {
		this.foxaddrID = foxaddrID;
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
	
	@Override
	public String toString() {
		return "email [name=" + name + ", email=" + email + ", foxaddrID="
				+ foxaddrID + "]";
	}
	
}
