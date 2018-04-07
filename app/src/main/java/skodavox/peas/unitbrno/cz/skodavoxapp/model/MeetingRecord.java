package skodavox.peas.unitbrno.cz.skodavoxapp.model;

import java.util.Date;
import java.util.List;

public class MeetingRecord {

    private String name;

    private String desctiption;

    private Date from;

    private Date due;

    private List<Attender> attenderList;


    private List<RecordSession> recordSessionList;


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

    public List<Attender> getAttenderList() {
        return attenderList;
    }

    public void setAttenderList(List<Attender> attenderList) {
        this.attenderList = attenderList;
    }

    public List<RecordSession> getRecordSessionList() {
        return recordSessionList;
    }

    public void setRecordSessionList(List<RecordSession> recordSessionList) {
        this.recordSessionList = recordSessionList;
    }
}
