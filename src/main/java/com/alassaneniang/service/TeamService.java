package com.alassaneniang.service;

import com.alassaneniang.entity.TeamEntity;
import com.alassaneniang.exception.TeamInvalidException;
import com.alassaneniang.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public List<TeamEntity> getTeams () {
        return teamRepository.findAll();
    }

    public TeamEntity getTeamById ( Long id ) {
        return teamRepository.findById( id )
                .orElseThrow( () -> new RuntimeException( "Team with id " + id + " was not found" ) );
    }

    public TeamEntity createTeam ( TeamEntity teamEntity ) {
        if ( teamEntity == null || !StringUtils.hasText( teamEntity.getSlogan() ) ) {
            throw new TeamInvalidException( "No teamentity or slogan empty" );
        }
        return teamRepository.save( teamEntity );
    }

    public TeamEntity updateTeam ( TeamEntity teamEntity ) {
        if ( teamRepository.existsById( teamEntity.getId() ) ) {
            return teamRepository.save( teamEntity );
        }
        throw new RuntimeException( "Team with id " + teamEntity.getId() + " was not found" );
    }

    public void deleteTeam ( Long id ) {
        teamRepository.deleteById( id );
    }

}
