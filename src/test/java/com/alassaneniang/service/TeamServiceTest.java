package com.alassaneniang.service;

import com.alassaneniang.entity.TeamEntity;
import com.alassaneniang.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// TODO Voir s'il existe un runner JUNIT
@WebMvcTest( TeamService.class )
class TeamServiceTest {

    @MockBean
    TeamRepository teamRepository;

    TeamService teamService;

    @BeforeEach
    void setUp () {
        teamService = new TeamService( teamRepository );
    }

    @Test
    void shouldReturnTheExpectedTeam_whenGetTeamByExistingId () {

        // Given
        TeamEntity expectedTeam = TeamEntity.builder()
                .id( 1L )
                .name( "PSG" )
                .slogan( "Paris Saint-Germain" )
                .build();
        // When
        Mockito.when( teamRepository.findById( 1L ) ).thenReturn( Optional.of( expectedTeam ) );
        // Then
        //assertThat( teamRepository.findById( 1L ) ).isEqualTo( Optional.of( expectedTeam ) );
        assertThat( teamService.getTeamById( 1L ) ).isEqualTo( expectedTeam );
    }

    @Test
    void shouldReturnAllTeams_whenRequired () {
        // Given
        // When
        Mockito.when( teamRepository.findAll() ).thenReturn( teams() );
        // Then
        assertThat( teamService.getTeams() ).isEqualTo( teams() );
    }

    @Test
    void shouldReturnTheCreatedTeam () {
        // Given
        TeamEntity expectedTeam = TeamEntity.builder()
                .id( 1L )
                .name( "PSG" )
                .slogan( "Paris Saint-Germain" )
                .build();
        // When
        Mockito.when( teamRepository.save( expectedTeam ) ).thenReturn( expectedTeam );
        // Then
        assertThat( teamService.createTeam( expectedTeam ) ).isEqualTo( expectedTeam );
    }

    @Test
    void shouldInvokeDeleteById () {
        // Given
        // When
        teamService.deleteTeam( 1L );
        // Then
        Mockito.verify( teamRepository ).deleteById( 1L );
    }

    @Test
    void shouldUpdateExistingTeam () {
        // Given
        TeamEntity expectedTeam = TeamEntity.builder()
                .id( 1L )
                .name( "PSG" )
                .slogan( "Paris Saint-Germain" )
                .build();
        // When
        Mockito.when( teamRepository.existsById( expectedTeam.getId() ) ).thenReturn( true );
        Mockito.when( teamRepository.save( expectedTeam ) ).thenReturn( expectedTeam );
        // Then
        TeamEntity updatedTeam = teamService.updateTeam( expectedTeam );
        Mockito.verify( teamRepository ).existsById( expectedTeam.getId() );
        Mockito.verify( teamRepository ).save( expectedTeam );
        assertThat( updatedTeam ).isEqualTo( expectedTeam );
    }


    private List<TeamEntity> teams () {
        return List.of(
                new TeamEntity( 1L, "PSG", "Paris Saint-Germain" ),
                new TeamEntity( 2L, "OM", "Marseille" ),
                new TeamEntity( 3L, "Juventus", "Juventus" )
        );
    }
}