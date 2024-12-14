public class Konser extends Movie {
    private String date;
    public Konser(int id, String title, String description, String image, String status, String date){
        super(id, title, description, image, status );
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }
}
