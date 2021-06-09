package spring.mysqljwtauthdemo.demo.model;

public class AuthorizationResponse {

    private String jwt;

    public AuthorizationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
