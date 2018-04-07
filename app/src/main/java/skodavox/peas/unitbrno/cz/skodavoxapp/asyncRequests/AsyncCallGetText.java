package skodavox.peas.unitbrno.cz.skodavoxapp.asyncRequests;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncCallGetText extends AsyncTask<RequestParams, Integer, String> {

    private String basicUrl = "http://77.240.177.148:8602";
    private String credential = Credentials.basic("team4", "hackathon");


    @Override
    protected String doInBackground(RequestParams... call) {

        RequestParams requestParams = call[0];

        Log.i(this.getClass().getName(), "AsyncCallGetText for " + requestParams.toString());

        String url = basicUrl + requestParams.url;


        if (requestParams.params.keySet().size() > 0) {
            url += "?";

            for (String s : requestParams.params.keySet()) {
                url += s + "=" + requestParams.params.get(s) + "&";
            }
        }

        Log.i(this.getClass().getName(), url);


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .header("Authorization", credential)
                .url(url)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("a", e.getLocalizedMessage());
        }

        return null;
    }


    @Override
    protected void onPostExecute(String result) {

        JsonElement el = new JsonParser().parse(result);

        try {

            // if task on server not completed
            if (el.getAsJsonObject().get("result").getAsJsonObject().get("name").getAsString().equals("PendingInfoResult")) {

                // TODO: LET CALLER ENTITY KNOW - RESULT NOT PREPARED
            } else {


                String parsedText; // Recognized text or user name (if speaker identification)
                if (el.getAsJsonObject().get("result").getAsJsonObject().get("name").getAsString().equals("SpeakerIdentificationMultiResult"))
                    parsedText = ResponseJsonParser.parseSpeakerRecog(result);
                else
                    parsedText = ResponseJsonParser.parseRecogJson(result);


                Log.i(this.getClass().getName(), parsedText);

                // parsedTest je věta rozpoznanych slov nebo je to jméno rozpoznaného uživatele
                // TODO NEKAM TO NACPAT
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("a", e.getLocalizedMessage());
        }

    }


}
