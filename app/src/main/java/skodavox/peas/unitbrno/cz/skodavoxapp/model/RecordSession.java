package skodavox.peas.unitbrno.cz.skodavoxapp.model;

import java.util.Date;

public class RecordSession {


    private MeetingRecord meetingRecord;

    private Date from;

    private Date due;

    private RecordType recordType;

    private Attender attender;

    private String recognizedText;

    public MeetingRecord getMeetingRecord() {
        return meetingRecord;
    }

    public void setMeetingRecord(MeetingRecord meetingRecord) {
        this.meetingRecord = meetingRecord;
    }

    public String getRecognizedText() {
        return recognizedText;
    }

    public void setRecognizedText(String recognizedText) {
        this.recognizedText = recognizedText;
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

    public RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    public Attender getAttender() {
        return attender;
    }

    public void setAttender(Attender attender) {
        this.attender = attender;
    }
}
