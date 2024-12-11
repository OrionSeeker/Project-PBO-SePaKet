public class user {
    public static int id;
    private String username;
    private String password;
    public user(int idAkun, String username, String password) {
        id = idAkun;
        this.username = username;
        this.password = password;
    }
    public static int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
