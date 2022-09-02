package pe.rodcar.shelterpet.adoption.request;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpForm {

	@NotBlank
	@Email
	@Size(max = 254)
	private String email;

	@NotBlank
	@Size(min = 8, max = 150)
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "phone")
	private String phone;

	/*@JsonProperty("businessName")
	@Column(name = "business_name")
	private String businessName;

	@JsonProperty("businessType")
	@Column(name = "business_type")
	private String businessType;*/

	public SignUpForm() {

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}