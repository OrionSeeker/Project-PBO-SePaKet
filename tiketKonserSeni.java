public class tiketKonserSeni {
    String namaLengkap = "";
        String nik = "";
        String email = "";
        String jenisTiket = "";
        String jumlahTiket = "";
        String jadwal = "";
        String image = "";
        String title = "";
        public tiketKonserSeni(String namaLengkap, String nik, String email, String jenisTiket, String jumlahTiket, String jadwal, String image, String title){
            this.namaLengkap = namaLengkap;
            this.nik = nik;
            this.email = email;
            this.jenisTiket = jenisTiket;
            this.jumlahTiket = jumlahTiket;
            this.jadwal = jadwal;
            this.image = image;
            this.title = title;
        }
        public String getNamaLengkap(){
            return this.namaLengkap;
        }
        public String getNik(){
            return this.nik;
        }
        public String getEmail(){
            return this.email;
        }
        public String getJenisTiket(){
            return this.jenisTiket;
        }
        public String getJumlahTiket(){
            return this.jumlahTiket;
        }
        public String getJadwal(){
            return this.jadwal;
        }
        public String getImage(){
            return this.image;
        }
        public String getTitle(){
            return this.title;
        }
        
}
