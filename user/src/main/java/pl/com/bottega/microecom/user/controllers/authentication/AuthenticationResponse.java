package pl.com.bottega.microecom.user.controllers.authentication;

public class AuthenticationResponse {

    private boolean successful;

    private String authToken;

    public AuthenticationResponse(String token) {
        this.authToken = token;
        this.successful = true;
    }

    public AuthenticationResponse() {
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public static AuthenticationResponse success(String token) {
        return new AuthenticationResponse(token);
    }

    public static AuthenticationResponse failed() {
        return new AuthenticationResponse();
    }
}
