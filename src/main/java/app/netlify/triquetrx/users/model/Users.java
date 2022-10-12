package app.netlify.triquetrx.users.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public @Data @NoArgsConstructor class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	private String name;
	private String organizationName;
	private String emailId;
	private String username;
	private long phoneNumber;
	private String address1;
	private String address2;
	private String city;
	private String gstNumber;

	public Users(String name, String organizationName,String emailId, String username,long phoneNumber, String address1, String address2, String city,
			String gstNumber) {
		super();
		this.name = name;
		this.organizationName = organizationName;
		this.emailId = emailId;
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.gstNumber = gstNumber;
	}

}
