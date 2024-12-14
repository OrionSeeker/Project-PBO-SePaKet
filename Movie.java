public class Movie {
    private int id;
    private String title;
    private String director;
    private int year;
    private String genre;
    private String description;
    private String image;
    private String urlMovie;
    private String status;

    public Movie(int id,String title, String director, int year, String genre, String description, String image, String urlMovie, String status) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.image = image;
        this.urlMovie = urlMovie;
        this.status = status;
    }
    public int getId() {
        return id;
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

    public String getUrlMovie() {
        return urlMovie;
    }
    public String getStatus() {
        return status;
    }
    
}
