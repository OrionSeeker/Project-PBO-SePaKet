public class Seat {
    private int idJadwalTayang;
    private int noKursi;
    private int status;
    private String tempatDuduk;
    public Seat(int idJadwalTayang, int noKursi, int status, String tempatDuduk) {
        this.idJadwalTayang = idJadwalTayang;
        this.noKursi = noKursi;
        this.status = status;
        this.tempatDuduk = tempatDuduk;
    }
    public int getIdJadwalTayang() {
        return idJadwalTayang;
    }
    public int getNoKursi() {
        return noKursi;
    }
    public int getStatus() {
        return status;
    }
    public String getTempatDuduk() {
        return tempatDuduk;
    }
}
