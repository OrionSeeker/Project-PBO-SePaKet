import javax.swing.JPanel;

public abstract class tiket {
    private int iduser;
    private String title;
    private String jadwal;
    private String image;

    public tiket(int iduser,String title, String jadwal, String image) {
        this.title = title;
        this.jadwal = jadwal;
        this.image = image;
    }
    public int getIduser() {
        return iduser;
    }
    public String getTitle() {
        return title;
    }
    public String getJadwal() {
        return jadwal;
    }
    public String getImage() {
        return image;
    }

    public abstract JPanel tiketPanel(int idAkun, String kategori, int id);

}
