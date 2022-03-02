package com.alassaneniang.service;

import com.alassaneniang.entity.TeamEntity;
import com.alassaneniang.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .get();
    }

    public TeamEntity createTeam ( TeamEntity teamEntity ) {
        return teamRepository.save( teamEntity );
    }

    public void deleteTeam ( Long id ) {
        teamRepository.deleteById( id );
    }

}
