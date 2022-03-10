import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * -Найти информацию по персонажу Морти Смит.
 * -Выбрать из ответа последний эпизод, где появлялся Морти.
 * -Получить из списка последнего эпизода последнего персонажа.
 * -Получить данные по местонахождению и расе этого персонажа.
 * -Проверить, этот персонаж той же расы и находится там же где и Морти?
 */

public class ApiTest {
    public static String epsd;
    public static String lastepsd;

    @Tag("1api")
    @Test
    @DisplayName("Последний эпизод с Морти")
    public void morti() {
        Response response1 = given()
                .baseUri("https://rickandmortyapi.com")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/character/2")
                .then()
                .statusCode(200)
                .extract()
                .response();
        List<Object> episodeList = new JSONObject(response1.body().asString()).getJSONArray("episode").toList();
        epsd = episodeList.get(episodeList.size() - 1).toString();
        System.out.println("Ссылка на последний эпизод: " + epsd);
        lastepsd = (epsd.substring(epsd.lastIndexOf("/")).substring(1)).toString();
        System.out.println("Последний эпизод " + lastepsd);
    }

    @Tag("2api")
    @Test
    @DisplayName("Последний персонаж")
    public void lastPerson() {
        System.out.println(lastepsd);
        Response response2 = given()
                .baseUri("https://rickandmortyapi.com")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/episode/" + lastepsd)
                .then()
                .statusCode(200)
                .extract()
                .response();
        String lastChar;
        List<Object> characterList = new JSONObject(response2.body().asString()).getJSONArray("characters").toList();
        lastChar = characterList.get(characterList.size() - 1).toString();
        System.out.println("Ссылка на последнего персонажа в последнем эпизоде " + lastChar);
    }

/**
 Response response1 = given()
 .baseUri("https://rickandmortyapi.com/api")
 .when().get("/character/2")
 .then()
 .extract().response();
 String name = new JSONObject(response1.body().asString()).get("name").toString();
 String location1 = new JSONObject(response1.body().asString()).getJSONObject("location").get("name").toString();
 int lst = (new JSONObject(response1.body().asString()).getJSONArray("episode").length() - 1);
 int episode = Integer.parseInt(new JSONObject(response1.body().asString()).getJSONArray("episode").get(lst).toString().replaceAll("[^0-9]", ""));
 System.out.println(name);
 System.out.println(location1);
 System.out.println(episode);
 //Stash.put("q", episode);

 Response response2 = given()
 .baseUri("https://rickandmortyapi.com/api")
 .when()
 .get("/episode/" + episode)
 .then()
 .extract().response();
 int lst1 = (new JSONObject(response2.body().asString()).getJSONArray("characters").length() - 1);
 int character = Integer.parseInt(new JSONObject(response2.body().asString()).getJSONArray("characters").get(lst1).toString().replaceAll("[^0-9]", ""));
 System.out.println(character);
 System.out.println(character);
 */


    /**
     * @Tag("2api")
     *     @Test
     *     @DisplayName("test")
     *     public void test2() {
     *         String body = "{\"name\": \"morpheus\",\"job\": \"leader\"}";
     *
     *         JSONObject requestBody = new JSONObject();
     *         requestBody.put("name", "morpheus");
     *         requestBody.put("job", "leader");
     *
     *         Response response3 = given()
     *                 .baseUri("https://reqres.in/")
     *                 .contentType("application/json;charset=UTF-8")
     *                 .log().all()
     *                 .when()
     *                 .body(requestBody.toString())
     *                 .post("/api/users")
     *                 .then()
     *                 .statusCode(201)
     *                 .log().all()
     *                 .extract().response();
     *
     *         JSONObject json = new JSONObject(response3);
     *         Assertions.assertEquals(json.getString("name"), "morpheus");
     *         Assertions.assertEquals(json.getString("job"), "leader");
     *     }
     */

}

//    String resp2 = response1.getBody().asString();
//    JSONObject json = new JSONObject(resp2);
//    int count = json.getJSONObject("info").getInt("count");
//    int jsonsize = json.getJSONArray("results").length();
//    String name = json.getJSONArray("results").getJSONObject(jsonsize-1).getJSONObject("origin").getString("name");




