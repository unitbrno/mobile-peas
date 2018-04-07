package skodavox.peas.unitbrno.cz.skodavoxapp.Listeners;

import java.util.ArrayList;

import skodavox.peas.unitbrno.cz.skodavoxapp.generic.IListener;

public class WavWriter implements IListener<ArrayList<Byte>> {

    ArrayList<Byte> header;

    public WavWriter(ArrayList<Byte> header) {
        this.header = header;
    }

    private ArrayList<Byte> buffer;

    void start() {
        buffer = new ArrayList<>();
    }

    ArrayList<Byte> stop() {
        if(buffer == null) return new ArrayList<>();
        ArrayList<Byte> result = new ArrayList<>();
        result.addAll(header);
        result.addAll(buffer);
        buffer = null;
        return result;
    }
    @Override
    public void notify(ArrayList<Byte> data) {
        if(buffer != null) {
            buffer.addAll(data);
        }
    }
}
