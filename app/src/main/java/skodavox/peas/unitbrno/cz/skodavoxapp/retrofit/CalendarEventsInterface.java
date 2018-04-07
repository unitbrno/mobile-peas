package skodavox.peas.unitbrno.cz.skodavoxapp.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import skodavox.peas.unitbrno.cz.skodavoxapp.model.CalendarEvent;

public interface CalendarEventsInterface {

    @GET("{id}/events")
    Call<List<CalendarEvent>> getEvents(@Path("id") String calendarId,
                                        @Query("timeMin") String timeMin, @Query("timeMax") String timeMax);
}
