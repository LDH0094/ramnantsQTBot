package lee.ramnants.ramnants_qt_bot.model;

import java.util.List;

public class QTEntity {
    private String title;
    private List<Verse> verses;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVerses(List<Verse> verses) {
        this.verses = verses;
    }

    public String getTitle() {
        return title;
    }

    public List<Verse> getVerses() {
        return verses;
    }
// Constructors, getters, and setters
}

