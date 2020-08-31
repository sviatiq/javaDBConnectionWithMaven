package models;

public class Film {
    private int filmId;
    private String title;
    private String description;
    private int year;
    private int language_id;

    public Film() {
    }

    public Film(int filmId, String title, String description, int year, int language_id) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.year = year;
        this.language_id = language_id;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", language_id=" + language_id +
                '}';
    }
}
