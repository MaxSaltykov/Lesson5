package Test;

import ApiBase.BaseApi;
import Settings.ApiAllurePreset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * -Найти информацию по персонажу Морти Смит.
 * -Выбрать из ответа последний эпизод, где появлялся Морти.
 * -Получить из списка последнего эпизода последнего персонажа.
 * -Получить данные по местонахождению и расе этого персонажа.
 * -Проверить, этот персонаж той же расы и находится там же где и Морти?
 */

@ExtendWith({ApiAllurePreset.class})
public class ApiTest extends BaseApi {

    @Tag("1api")
    @Test
    @DisplayName("Последний эпизод с Морти")
    public void tests1 () {
        lastMortyEpisode();
    }

    @Tag("2api")
    @Test
    @DisplayName("Последний персонаж")
    public void tests2 () {
        lastMortyEpisode();
        lastPerson();
    }


    @Tag("3api")
    @Test
    @DisplayName("Местонахожение и раса последнего персонажа")
    public void tests3 () {
        lastMortyEpisode();
        lastPerson();
        lastPersonProperties();
    }

    @Tag("4api")
    @Test
    @DisplayName("Сравнение местонахожения и расы последнего персонажа и Морти")
    public void tests4 () {
        lastMortyEpisode();
        lastPerson();
        lastPersonProperties();
        check();
    }
}