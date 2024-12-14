public class Kesenian extends Konser{
    private String lokasi;
    public Kesenian(int id, String title, String description, String image, String status, String date, String lokasi){
        super(id, title, description, image, status, date);
        this.lokasi = lokasi;
    }

    public String getLokasi(){
        return this.lokasi;
    }
}
