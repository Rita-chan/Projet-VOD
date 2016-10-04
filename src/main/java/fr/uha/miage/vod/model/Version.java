package fr.uha.miage.vod.model;

public enum Version {
	VF("Version Française"),
	VO("Version Originale"),
	VOSTFR("Version Originale Sous-Titrée Française");
	
	private String description;

	private Version(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}	

}
