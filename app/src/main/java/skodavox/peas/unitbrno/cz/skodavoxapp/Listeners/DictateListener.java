package skodavox.peas.unitbrno.cz.skodavoxapp.Listeners;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import skodavox.peas.unitbrno.cz.skodavoxapp.asyncRequests.RequestParams;
import skodavox.peas.unitbrno.cz.skodavoxapp.asyncRequests.AsyncCallPutFile;
import skodavox.peas.unitbrno.cz.skodavoxapp.generic.IListener;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import skodavox.peas.unitbrno.cz.skodavoxapp.MicrophoneStream;
import skodavox.peas.unitbrno.cz.skodavoxapp.asyncRequests.ResponseJsonParser;
import skodavox.peas.unitbrno.cz.skodavoxapp.utils.KeywordRecognizer;
import skodavox.peas.unitbrno.cz.skodavoxapp.utils.SharedState;

public class DictateListener extends TimerTask implements IListener<ArrayList<Byte>>  {
    private OkHttpClient client;
    private String sessionID;
    private String credential;
    private ArrayList<String> sentences;
    private MicrophoneStream stream;

    private String taskID;

    private DictateListener() {
        stream = new MicrophoneStream(8000);

        credential = Credentials.basic("team4", "hackathon");
        getSessionID();
        taskID = getTaskID();
        sentences = new ArrayList<>();
        SharedState.create().set("sentences", sentences);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 0, 1000);
        stream.registerListener(this);
        WavWriter writer = new WavWriter(stream.getHeader());
        stream.registerListener(writer);
        SharedState.create().set("MeetingWriter", writer);
        writer = new WavWriter(stream.getHeader());
        stream.registerListener(writer);
        SharedState.create().set("LogWriter", writer);
    }

    @Override
    public void run() {
        Request request = new Request.Builder()
                .url("http://77.240.177.148:8602/technologies/dictate?task=" + taskID)
                .header("Authorization", credential)
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response.code() != 200) {
                throw new RuntimeException("Couldn't create stream because post response failed " + response.toString());
            }
            String sentence = ResponseJsonParser.parseRecogJson(response.body().string());
            Log.e("DictateListener", sentence);
            sentences.add(sentence);
            KeywordRecognizer.Result result = KeywordRecognizer.parse(sentence);
            if(!result.equals(KeywordRecognizer.Result.NONE_FOUND)) {
                WavWriter writer = null;
                if(result.equals(KeywordRecognizer.Result.MEETING_ENDS) || result.equals(KeywordRecognizer.Result.MEETING_BEGINS)) {
                    writer = (WavWriter) SharedState.create().get("MeetingWriter");
                }
                if(result.equals(KeywordRecognizer.Result.LOG_END) || result.equals(KeywordRecognizer.Result.LOG_BEGIN)) {
                    writer = (WavWriter) SharedState.create().get("LogWriter");
                }
                if(result.equals(KeywordRecognizer.Result.MEETING_BEGINS)) {
                    Log.e("DictateListener", KeywordRecognizer.Result.MEETING_BEGINS.getKeyword());
                    writer.start();
                }
                if(result.equals(KeywordRecognizer.Result.MEETING_ENDS) || result.equals(KeywordRecognizer.Result.LOG_END)) {
                    Log.e("DictateListener", KeywordRecognizer.Result.MEETING_ENDS.getKeyword());
                    RequestParams call = new RequestParams();
                    call.url = "/audiofile";
                    ArrayList<Byte> wav = writer.stop();
                    call.file = wav;
                    call.params.put("path", "/"+wav.size() + ".wav");
                    Log.e("audioFile", wav.size() + ".wav");
                    new AsyncCallPutFile().execute(call);
                }
            }
        } catch (IOException exception) {
            throw new RuntimeException("Couldn't create stream");
        }
    }

    public String getTaskID() {
        assert sessionID != null : "SessionID must not be null!";
        RequestBody body = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url("http://77.240.177.148:8602/technologies/dictate?model=CS_CZ&result_mode=incremental&stream=" + sessionID)
                .header("Authorization", credential)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response.code() != 200) {
                throw new RuntimeException("Couldn't create stream because post response failed " + response.toString());
            }
            String json = response.body().string();
            Log.e("DictateListener", json);
            JsonElement result = new JsonParser().parse(json);
            JsonObject object = result.getAsJsonObject();
            String taskID = object.get("result").getAsJsonObject().get("stream_task_info").getAsJsonObject().get("id").getAsString();
            return taskID;
        } catch (IOException exception) {
            throw new RuntimeException("Couldn't create stream");
        }
    }

    public void getSessionID() {
        client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("frequency", MicrophoneStream.maximalBitrate() + "")
                .build();

        Request request = new Request.Builder()
                .url("http://77.240.177.148:8602/stream/http?path=/stream.wav")
                .header("Authorization", credential)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response.code() != 200) {
                throw new RuntimeException("Couldn't create stream because post response failed " + response.toString());
            }
            JsonElement result = new JsonParser().parse(response.body().string());
            JsonObject object = result.getAsJsonObject();
            sessionID = object.get("result").getAsJsonObject().get("stream").getAsString();
        } catch (IOException exception) {
            throw new RuntimeException("Couldn't create stream");
        }
    }

    private static DictateListener instance;
    public static DictateListener create() {
        if(instance == null) {
            instance = new DictateListener();
        }
        return instance;
    }

    @Override
    public void notify(ArrayList<Byte> data) {
        RequestBody body = new RequestBody() {
            @Override public long contentLength() { return data.size(); }
            @Override public MediaType contentType() { return MediaType.parse("audio"); }
            @Override public void writeTo(BufferedSink sink) throws IOException {
                byte[] buffer = new byte[data.size()];
                for(int i = 0; i < buffer.length; i++) {
                    buffer[i] = data.get(i);
                }
                sink.write(buffer);
            }
        };

        Request request = new Request.Builder()
                .url("http://77.240.177.148:8602/stream/http?stream=" + sessionID)
                .header("Authorization", credential)
                .put(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response.code() != 200) {
                throw new RuntimeException("Couldn't create stream because post response failed " + response.toString());
            }
        } catch (IOException exception) {
            Log.e("DictateListener", exception.getLocalizedMessage());
        }
    }
}
