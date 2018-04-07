package skodavox.peas.unitbrno.cz.skodavoxapp.asyncRequests;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestParams {


    public ApiCallType type;

    public String url;

    public HashMap<String,String> params = new HashMap<>();

    public ArrayList<Byte> file;


}
