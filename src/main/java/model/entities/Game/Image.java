package model.entities.Game;

public class Image {

	private String name;
	private ImageType type;

	public Image() {}

	public Image(String name, ImageType type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageType getType() {
		return type;
	}

	public void setType(ImageType type) {
		this.type = type;
	}



}
