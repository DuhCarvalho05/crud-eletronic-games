package model.entities.Game;

public enum ImageType {
	
	COVER ("COVER"),
    BACKGROUND ("BACKGROUND"),
    DEFAULT ("DEFAULT");

    private final String type;

    ImageType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

}
