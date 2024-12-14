public  class tiket {
    private int idTiket;
    private String namaTiket;
    private String jadwal;

    public tiket(int idTiket, String namaTiket, String jadwal) {
        this.idTiket = idTiket;
        this.namaTiket = namaTiket;
        this.jadwal = jadwal;
    }

    public int getIdTiket() {
        return idTiket;
    }
    public String getNamaTiket() {
        return namaTiket;
    }
    public String getJadwal() {
        return jadwal;
    }


}
