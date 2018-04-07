package skodavox.peas.unitbrno.cz.skodavoxapp.model;

import java.util.Date;
import java.util.List;

public class InvitationMeeting {

    private String name;

    private String desctiption;

    private Date from;

    private Date due;

    private List<User> attenderList;

    public InvitationMeeting(String name, String desctiption) {
        this.name = name;
        this.desctiption = desctiption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public List<User> getAttenderList() {
        return attenderList;
    }

    public void setAttenderList(List<User> attenderList) {
        this.attenderList = attenderList;
    }

}
