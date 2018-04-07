package skodavox.peas.unitbrno.cz.skodavoxapp.utils;

import java.util.regex.Pattern;

/**
 * Provides generic parsing function, that detects whether given string contains keyword.
 * If it does it returns which one.
 */
public class KeywordRecognizer {
    /**
     * Enum that represent result of parse function
     */
    public enum Result {
        MEETING_BEGINS("začátek porady"),
        MEETING_ENDS("konec porady"),
        LOG_BEGIN("začátek zápisu"),
        LOG_END("konec zápisu"),
        NONE_FOUND("none found");
        private String keyword;

        Result(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return keyword;
        }
    }

    /**
     * @param sentence sentence which should be parser - must not be null!
     * @return Result
     */
    public static Result parse(String sentence) {
        assert sentence != null : "Sentence must not be null";
        sentence = sentence.toLowerCase();
        if(sentence.contains(Result.LOG_BEGIN.getKeyword())) {
            return Result.LOG_BEGIN;
        }
        if(sentence.contains(Result.LOG_END.getKeyword())) {
            return Result.LOG_END;
        }
        if(sentence.contains(Result.MEETING_BEGINS.getKeyword())) {
            return Result.MEETING_BEGINS;
        }
        if(sentence.contains(Result.MEETING_ENDS.getKeyword())) {
            return Result.MEETING_ENDS;
        }
        return Result.NONE_FOUND;
    }
}
