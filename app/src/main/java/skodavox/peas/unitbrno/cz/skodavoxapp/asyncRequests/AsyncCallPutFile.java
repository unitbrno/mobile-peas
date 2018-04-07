package skodavox.peas.unitbrno.cz.skodavoxapp.asyncRequests;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AsyncCallPutFile extends AsyncTask<RequestParams, Integer, String> {

    String basicUrl = "http://77.240.177.148:8602";
    private String credential = Credentials.basic("team4", "hackathon");

    @Override
    protected void onPreExecute() {

        Log.i(this.getClass().getName(), "onPreExecute");

    }

    @Override
    protected String doInBackground(RequestParams... call) {

        RequestParams requestParams = call[0];

        Log.i(this.getClass().getName(), "AsyncCallGetText for " + requestParams.toString());

        String url = basicUrl + requestParams.url;

        Log.i(this.getClass().getName(), url);

        if (requestParams.params.keySet().size() > 0) {
            url += "?";

            for (String s : requestParams.params.keySet()) {
                url += s + "=" + requestParams.params.get(s) + "&";
            }
        }


        ArrayList<Byte> arraylist = requestParams.file;

        byte[] bytes = convertBytes(arraylist);

        Log.e("Bajty", String.valueOf(bytes.length ) );


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .header("Authorization", credential)
                .url(url)
                .post(RequestBody.create(MediaType.parse("multipart/form-data"), bytes))
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(String result) {

        Log.i(this.getClass().getName(), "onPostExecute result = " + result);

    }

    public static byte[] convertBytes(List<Byte> integers) {
        byte[] ret = new byte[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i);
        }
        return ret;
    }

}
