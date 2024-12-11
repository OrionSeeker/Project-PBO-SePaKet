public class jadwalTayang {
    private int id;
    private int idMovie;
    private String jamTanggal;
    private String hari;
    private String studio;
    public jadwalTayang(int id, int idMovie, String jamTanggal, String hari, String studio) {
        this.id = id;
        this.idMovie = idMovie;
        this.jamTanggal = jamTanggal;
        this.hari = hari;
        this.studio = studio;
    }
    public int getId() {
        return id;
    }
    public int getIdMovie() {
        return idMovie;
    }
    public String getJamTanggal() {
        return jamTanggal;
    }
    public String getHari() {
        return hari;
    }
    public String getStudio() {
        return studio;
    }
}
