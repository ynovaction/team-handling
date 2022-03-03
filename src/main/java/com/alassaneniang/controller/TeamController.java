package com.alassaneniang.controller;

import com.alassaneniang.service.TeamDto;
import com.alassaneniang.service.TeamFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping( "/teams" )
@RequiredArgsConstructor
@RestController
public class TeamController {

    private final TeamFacadeService teamFacadeService;

    @GetMapping
    public List<TeamDto> getTeams () {
        return teamFacadeService.getTeams();
    }

    @GetMapping( "/{id}" )
    public TeamDto getTeamById ( @PathVariable Long id ) {
        return teamFacadeService.getTeamById( id );
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public TeamDto createTeam ( @RequestBody TeamDto teamDto ) {
        return teamFacadeService.createTeam( teamDto );
    }

    @PutMapping
    public TeamDto updateTeam ( @RequestBody TeamDto teamDto ) {
        return teamFacadeService.updateTeam( teamDto );
    }

    @DeleteMapping( "/{id}" )
    public void deleteTeam ( @PathVariable Long id ) {
        teamFacadeService.deleteTeam( id );
    }

}
