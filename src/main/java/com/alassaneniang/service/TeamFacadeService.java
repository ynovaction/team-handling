package com.alassaneniang.service;

import com.alassaneniang.entity.TeamEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamFacadeService {

    private final TeamService teamService;
    private final ModelMapper modelMapper;

    public List<TeamDto> getTeams () {
        return teamService.getTeams().stream()
                .map( this::convertToTeamDTO )
                .collect( Collectors.toList() );
    }

    public TeamDto getTeamById ( Long id ) {
        return convertToTeamDTO( teamService.getTeamById( id ) );
    }

    public TeamDto createTeam ( TeamDto teamDto ) {
        return convertToTeamDTO( teamService.createTeam( convertToTeamEntity( teamDto ) ) );
    }

    public TeamDto updateTeam ( TeamDto teamDto ) {
        return convertToTeamDTO( teamService.updateTeam( convertToTeamEntity( teamDto ) ) );
    }

    public void deleteTeam ( Long id ) {
        teamService.deleteTeam( id );
    }

    private TeamDto convertToTeamDTO ( TeamEntity teamEntity ) {
        return modelMapper.map( teamEntity, TeamDto.class );
    }

    private TeamEntity convertToTeamEntity ( TeamDto teamDto ) {
        return modelMapper.map( teamDto, TeamEntity.class );
    }
}
