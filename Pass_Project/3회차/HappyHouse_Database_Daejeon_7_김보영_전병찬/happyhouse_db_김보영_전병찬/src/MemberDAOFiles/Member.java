package MemberDAOFiles;

public class Member {
	private String id;
	private String password;
	private String email;
	private String name;
	private int age;

	public Member() {
	}

	public Member(String id, String password, String email, String name, int age) {
		setId(id);
		setPassword(password);
		setEmail(email);
		setName(name);
		setAge(age);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		String msg = id + "\t" + password + "\t" + email + "\t" + name + "\t" + age;
		return msg;
	}
}