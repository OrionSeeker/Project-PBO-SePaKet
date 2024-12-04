public class Movie {
    private String title;
    private String director;
    private int year;
    private String genre;
    private String description;
    private String image;
    private String pathMovie;

    public Movie(String title, String director, int year, String genre, String description, String image, String pathMovie) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.image = image;
        this.pathMovie = pathMovie;
    }
    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getPathMovie() {
        return pathMovie;
    }
}
