package com.alassaneniang.controller;

import com.alassaneniang.service.TeamDto;
import com.alassaneniang.service.TeamFacadeService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.mockito.ArgumentMatchers.anyLong;

@WebMvcTest( TeamController.class )
class TeamControllerTest {

    private final String baseUrl = "/teams";

    @MockBean
    TeamFacadeService teamFacadeService;

    @BeforeEach
    void setUp () {
        RestAssuredMockMvc.standaloneSetup( new TeamController( teamFacadeService ) );
    }

    @Test
    void shouldReturnAllTeams () {
        Mockito.when( teamFacadeService.getTeams() ).thenReturn( teams() );

        when().get( baseUrl ).then().statusCode( 200 );

    }

    @Test
    void shouldReturnATeamWithGivenID () {
        Mockito.when( teamFacadeService.getTeamById( anyLong() ) ).thenReturn( teams().get( 0 ) );

        when().get( String.format( "%s/%d", baseUrl, anyLong() ) ).then().statusCode( 200 );
    }

    @Test
    void shouldCreateATeam () throws JSONException {
        TeamDto teamDto = new TeamDto( "PSG", "ICI C PARIS" );
        JSONObject teamDtoJson = new JSONObject();
        teamDtoJson.put( "name", teamDto.getName() )
                .put( "slogan", teamDto.getSlogan() );
        TeamDto expected = new TeamDto( anyLong(), "PSG", "ICI C PARIS" );

        Mockito.when( teamFacadeService.createTeam( teamDto ) ).thenReturn( expected );

        given()
                .contentType( "application/json" ).
                body( teamDtoJson.toString() ).
                when().post( baseUrl ).then().statusCode( 201 );

    }

    @Test
    void shouldUpdateTeamByID () throws JSONException {
        TeamDto teamDto = new TeamDto( anyLong(), "PSG", "ICI C PARIS" );
        JSONObject teamDtoJson = new JSONObject();
        teamDtoJson.put( "id", teamDto.getId() )
                .put( "name", teamDto.getName() )
                .put( "slogan", teamDto.getSlogan() );
        Mockito.when( teamFacadeService.updateTeam( teamDto ) ).thenReturn( teamDto );

        given()
                .contentType( "application/json" )
                .body( teamDtoJson.toString() )
                .when().put( baseUrl ).then().statusCode( 200 );

    }

    @Test
    void shouldDeleteTeamByID () {

        when().delete( String.format( "%s/%d", baseUrl, anyLong() ) ).then().statusCode( 200 );

        Mockito.verify( teamFacadeService ).deleteTeam( anyLong() );

    }


    private List<TeamDto> teams () {
        return List.of(
                new TeamDto( 1L, "PSG", "Paris Saint-Germain" ),
                new TeamDto( 2L, "OM", "Marseille" ),
                new TeamDto( 3L, "Juventus", "Juventus" )
        );
    }
}