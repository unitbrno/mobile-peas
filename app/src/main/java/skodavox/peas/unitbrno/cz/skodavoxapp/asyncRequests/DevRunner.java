package skodavox.peas.unitbrno.cz.skodavoxapp.asyncRequests;


import android.os.AsyncTask;

import skodavox.peas.unitbrno.cz.skodavoxapp.Listeners.DictateListener;

public class DevRunner {

    public static void runSometing(){

        // TODO : Working "Speech to text" call - feed it with file and process result (String og words)
//        RequestParams getTextCall = new RequestParams();
//        getTextCall.type = ApiCallType.GET_TEXT;
//        getTextCall.url = "/technologies/stt";
//        getTextCall.params.put("path","/solo-petr.wav");
//        getTextCall.params.put("model","CS_CZ");
//        new AsyncCallGetText().execute(getTextCall);


        // TODO : Working "Speaker recognizer" call - feed it with file and process result (1 user)
//        RequestParams getSpeakerCall = new RequestParams();
//        getSpeakerCall.type = ApiCallType.GET_TEXT;
//        getSpeakerCall.url = "/technologies/speakerid";
//        getSpeakerCall.params.put("path","/solo-petr.wav");
//        getSpeakerCall.params.put("group","test-group");
//        getSpeakerCall.params.put("model","XL3");
//        new AsyncCallGetText().execute(getSpeakerCall);


        // TODO : Put audio file to server
//        RequestParams getTextCall = new RequestParams();
//        getTextCall.type = ApiCallType.GET_TEXT;
//        getTextCall.url = "/audiofile";
//        getTextCall.params.put("path","/solo-petr.wav");
//        new AsyncCallPutFile().execute(getTextCall);


        new AsyncTask<String, Integer, Long>() {
            @Override
            protected Long doInBackground(String... strings) {

                DictateListener.create();
                return null;
            }

            protected void onProgressUpdate(Integer... progress) {
            }

            protected void onPostExecute(Long result) {
            }

        }.execute("makej");

    }

}
