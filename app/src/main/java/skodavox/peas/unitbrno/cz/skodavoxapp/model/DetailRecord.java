package skodavox.peas.unitbrno.cz.skodavoxapp.model;

public class DetailRecord {
    private String photo;
    private String text;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DetailRecord(String text) {
        this.text = text;
    }
}