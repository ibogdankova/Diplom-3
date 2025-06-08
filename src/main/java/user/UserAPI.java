package user;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserAPI {

    public static void register(User user) {
        given()
                .header("Content-Type", "application/json")
                .body(user)
                .post("https://stellarburgers.nomoreparties.site/api/auth/register");
    }

    public static void deleteIfExists(User user) {
        Response login = given()
                .header("Content-Type", "application/json")
                .body(user)
                .post("https://stellarburgers.nomoreparties.site/api/auth/login");

        String token = login.jsonPath().getString("accessToken");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
            given()
                    .header("Authorization", token)
                    .delete("https://stellarburgers.nomoreparties.site/api/auth/user");
        }
    }
}
