package skodavox.peas.unitbrno.cz.skodavoxapp.asyncRequests;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ResponseJsonParser {


    public static String parseRecogJson(String jsonurl){

        JsonElement jelement = new JsonParser().parse(jsonurl);
        JsonArray array  = jelement.getAsJsonObject().get("result")
                .getAsJsonObject().get("one_best_result")
                .getAsJsonObject().get("segmentation").getAsJsonArray();
        StringBuilder sb = new StringBuilder();
        for (JsonElement jsonElement : array) {
            String word = jsonElement.getAsJsonObject().get("word").getAsString();
            word = word.trim();
            if(word.equals("<sil/>") || word.equals("<s>") || word.equals("</s>"))
                continue;
            sb.append(word + " ");
        }
        return sb.toString();
    }

    public static String parseSpeakerRecog(String jsonString){
        JsonElement jelement = new JsonParser().parse(jsonString);

        return jelement.getAsJsonObject().get("result")
                .getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject().get("speaker_model").getAsString();
    }


}
