package skodavox.peas.unitbrno.cz.skodavoxapp;

import android.media.AudioFormat;
import android.media.AudioRecord;

import com.github.piasy.rxandroidaudio.StreamAudioRecorder;

import java.util.ArrayList;

import skodavox.peas.unitbrno.cz.skodavoxapp.generic.IListener;
import skodavox.peas.unitbrno.cz.skodavoxapp.generic.IObserver;

/**
 * Observer that observes microphone and distributes streamed data between its listeners
 */
public class MicrophoneStream implements StreamAudioRecorder.AudioDataCallback, IObserver<ArrayList<Byte>> {
    private ArrayList<IListener<ArrayList<Byte>>> listeners = new ArrayList<>();
    private static int[] bitRates = {8000, 11025, 16000, 22050, 44100};

    private ArrayList<Byte> header = new ArrayList<>();

    /**
     * @return header of wav file
     */
    public ArrayList<Byte> getHeader() {
        while(header.size() != 46) {}
        return header;
    }

    /**
     * Returns maximum possible bitrate that device supports
     * @return int - bitrate
     */
    public static int maximalBitrate() {
        int maximumBitrate = AudioRecord.ERROR_BAD_VALUE;
        for(int i = 0; i < bitRates.length; i++) {
            int bufferSize = AudioRecord.getMinBufferSize(bitRates[i], AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
            if(bufferSize != AudioRecord.ERROR_BAD_VALUE) {
                maximumBitrate = Math.max(bitRates[i], maximumBitrate);
            }
        }
        return maximumBitrate;
    }

    private int calculateBufferSize(int bitRate) {
        return (int)Math.floor(bitRate * 2 * 0.250); // 2B 250 ms
    }

    public MicrophoneStream(int maxBitRate) {
        assert maxBitRate >= 8000 : "maxBitRate has to be positive number with at least 8000";
        StreamAudioRecorder recorder = StreamAudioRecorder.getInstance();
        int bitRate = maximalBitrate();
        if(bitRate == AudioRecord.ERROR_BAD_VALUE) {
            throw new RuntimeException("Couldn't determine valid bitrate");
        }
        bitRate = Math.min(bitRate, maxBitRate);

        recorder.start(bitRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, calculateBufferSize(bitRate), this);
    }

    @Override
    public void onAudioData(byte[] data, int size) {
        synchronized (this) {
            ArrayList<Byte> transformedData = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {
                if(header.size() != 46) header.add(data[i]);
                transformedData.add(data[i]);
            }
            for (IListener<ArrayList<Byte>> listener : listeners) {
                listener.notify(transformedData);
            }
        }
    }

    @Override
    public void onError() {
        // TODO: We should add error handling for this class
    }

    @Override
    public void registerListener(IListener<ArrayList<Byte>> listener) {
        synchronized (this) {
            assert listener != null : "Listener must not be null!";
            listeners.add(listener);
        }
    }
}
