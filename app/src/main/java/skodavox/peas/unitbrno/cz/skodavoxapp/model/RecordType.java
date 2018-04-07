package skodavox.peas.unitbrno.cz.skodavoxapp.model;

public enum RecordType {

    GENERIC("Zápis"),
    DECISION("Rozhodnutí"),
    TASK("Úkol");

    String title;

    RecordType(String title) {
        title = title;
    }
}
