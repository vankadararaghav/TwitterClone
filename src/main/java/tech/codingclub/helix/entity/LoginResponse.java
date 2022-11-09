package tech.codingclub.helix.entity;

public class LoginResponse {
   public Long  user_id;
    public boolean is_logined;
    public  String message;

    public LoginResponse(Long user_id, boolean is_logined, String message) {
        this.user_id = user_id;
        this.is_logined = is_logined;
        this.message = message;
    }
}
