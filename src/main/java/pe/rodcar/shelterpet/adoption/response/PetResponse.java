package pe.rodcar.shelterpet.adoption.response;

import java.util.List;

public class PetResponse {
	
	private Long id;
	private String type;
	private String name;
	private String age;
	private String breed;
	private String location;
	private String status;
	private String whatsapp;
	private List<String> photos;

	public PetResponse() {

	}

	public PetResponse(Long id, String type, String name, String age, String breed, String location, String status,
			String whatsapp, List<String> photos) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.age = age;
		this.breed = breed;
		this.location = location;
		this.status = status;
		this.whatsapp = whatsapp;
		this.photos = photos;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
