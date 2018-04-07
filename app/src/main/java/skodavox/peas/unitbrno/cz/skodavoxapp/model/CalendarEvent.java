package skodavox.peas.unitbrno.cz.skodavoxapp.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

public class CalendarEvent {

    @SerializedName("summary")
    private String summary;

    @SerializedName("location")
    private String location;

    @SerializedName("start")
    private LocalDateTime startTime;

    @SerializedName("end")
    private LocalDateTime endTime;

    @SerializedName("attenders")
    private List<Attender> attenders;
}
