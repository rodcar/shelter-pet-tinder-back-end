package pe.rodcar.shelterpet.adoption.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "pets")
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden = true)
	private Long id;

	@Column(name = "name", nullable = false, length = 500)
	private String name;

	@Column(name = "owners_name", nullable = false, length = 500)
	private String ownersName;

	@Column(name = "location", nullable = false, length = 50)
	private String location;

	@Column(name = "phone_number", nullable = false, length = 15)
	private String phoneNumber;

	@Column(name = "age", nullable = false, length = 10)
	private String age;

	@Column(name = "breed", nullable = false, length = 20)
	private String breed;

	@Column(name = "photo", nullable = false, length = 500)
	private String photo;

	@Column(name = "bio", nullable = false, length = 500)
	private String bio;
	
	@Column(name = "status", nullable = false, length = 50)
	private String status;

	@Column(name = "type", nullable = false, length = 10)
	private String type;

	@ApiModelProperty(hidden = true)
	@JsonProperty("dateAdded")
	@Column(name = "date_added", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;

	public Pet() {
		this.dateAdded = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnersName() {
		return ownersName;
	}

	public void setOwnersName(String ownersName) {
		this.ownersName = ownersName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
