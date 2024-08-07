package model.entities.User;

public enum UserType {

	ADMIN("ADMIN"),
	DEFAULT("DEFAULT");

	private String type;

	UserType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}



}
