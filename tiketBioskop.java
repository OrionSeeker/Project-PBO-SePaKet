public class tiketBioskop {
    String tempatDuduk = "";
    String jadwal = "";
    String hari = "";
    String studio = "";
    String title = "";
    String image = "";
    String username = "";
    public tiketBioskop(String tempatDuduk, String jadwal, String hari, String studio, String title, String image, String username){
        this.tempatDuduk = tempatDuduk;
        this.jadwal = jadwal;
        this.hari = hari;
        this.studio = studio;
        this.title = title;
        this.image = image;
        this.username = username;
    }
    public String getTempatDuduk(){
        return this.tempatDuduk;
    }
    public String getJadwal(){
        return this.jadwal;
    }
    public String getHari(){
        return this.hari;
    }
    public String getStudio(){
        return this.studio;
    }
    public String getTitle(){
        return this.title;
    }
    public String getImage(){
        return this.image;
    }
    public String getUsername(){
        return this.username;
    }
        
}
