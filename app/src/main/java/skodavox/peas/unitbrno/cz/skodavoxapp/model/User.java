package skodavox.peas.unitbrno.cz.skodavoxapp.model;

public class User {

    private String id;

    private String name;

    private String speakerModelNickName;

    private String email;

    private String position;

    public User(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSpeakerModelNickName() {
        return speakerModelNickName;
    }

    public void setSpeakerModelNickName(String speakerModelNickName) {
        this.speakerModelNickName = speakerModelNickName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
