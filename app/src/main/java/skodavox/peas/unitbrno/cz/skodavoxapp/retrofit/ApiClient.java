package skodavox.peas.unitbrno.cz.skodavoxapp.retrofit;

import android.app.Activity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skodavox.peas.unitbrno.cz.skodavoxapp.model.CalendarEvent;
import skodavox.peas.unitbrno.cz.skodavoxapp.utils.DateTimeUtil;
import skodavox.peas.unitbrno.cz.skodavoxapp.utils.SharedPreferencesUtil;

public class ApiClient {

    private static final String GOOGLE_CALENDAR_BASE_URL = "https://www.googleapis.com/calendar/v3/calendars/";

    public static void getUpcomingEvents(Activity activity) {

        CalendarEventsInterface service = RetrofitClientGenerator.createService(CalendarEventsInterface.class, GOOGLE_CALENDAR_BASE_URL);
        Call<List<CalendarEvent>> call = service.getEvents(SharedPreferencesUtil.retrieveEmailOfCurrentUser(activity),
                DateTimeUtil.getCurrentDateTime(), DateTimeUtil.getCurrentDateTimePlusHour());

        call.enqueue(new Callback<List<CalendarEvent>>() {
            @Override
            public void onResponse(Call<List<CalendarEvent>> call, Response<List<CalendarEvent>> response) {
                response.body();
            }

            @Override
            public void onFailure(Call<List<CalendarEvent>> call, Throwable t) {


            }

        });
    }

}
