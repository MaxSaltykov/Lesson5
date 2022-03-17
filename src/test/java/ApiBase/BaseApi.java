package ApiBase;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BaseApi extends BaseElements {
    @Step("Последний эпизод с Морти")
    public static void lastMortyEpisode() {
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

        String episode;
        List<Object> episodeList = new JSONObject(response1.body().asString()).getJSONArray("episode").toList();
        episode = episodeList.get(episodeList.size() - 1).toString();
        BaseElements.mortyLocation = new JSONObject(response1.body().asString()).getJSONObject("location").getString("name").toString();
        BaseElements.mortySpecies = new JSONObject(response1.body().asString()).getString("species").toString();
        System.out.println("Ссылка на последний эпизод: " + episode);
        BaseElements.lastEpisode = (episode.substring(episode.lastIndexOf("/")).substring(1));
        System.out.println("Последний эпизод " + BaseElements.lastEpisode);
    }

    @Step("Последний персонаж")
    public static void lastPerson() {
        Response response2 = given()
                .baseUri("https://rickandmortyapi.com")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/episode/" + BaseElements.lastEpisode)
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Object> characterList = new JSONObject(response2.body().asString()).getJSONArray("characters").toList();
        BaseElements.lastChar = characterList.get(characterList.size() - 1).toString();
        BaseElements.lastCharEpisode = BaseElements.lastChar.substring(BaseElements.lastChar.lastIndexOf("/")).substring(1);
        System.out.println("Ссылка на последнего персонажа в последнем эпизоде " + BaseElements.lastChar);
    }

    @Step("Местонахожение и раса последнего персонажа")
    public static void lastPersonProperties() {
        Response response3 = given()
                .baseUri("https://rickandmortyapi.com")
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/character/" + lastCharEpisode)
                .then()
                .statusCode(200)
                .extract()
                .response();

        lastCharLocation = new JSONObject(response3.body().asString()).getJSONObject("location").getString("name").toString();
        lastCharSpecies = new JSONObject(response3.body().asString()).getString("species").toString();
        System.out.println("Местонахожение последнего персонажа " + lastCharLocation + ", Раса последнего персонажа " + lastCharSpecies);
    }

    @Step("Сравнение местонахожения и расы последнего персонажа и Морти")
    public static void check() {
        Assertions.assertEquals(lastCharLocation, mortyLocation);
        Assertions.assertEquals(lastCharSpecies, mortySpecies);
    }

}
