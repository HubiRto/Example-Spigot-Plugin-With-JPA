package pl.test.jpaplugin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.test.jpaplugin.entity.Player;
import pl.test.jpaplugin.repository.PlayerRepository;

import java.util.List;

@Service("playerService")
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    public void addPlayer(Player player){
        playerRepository.save(player);
    }

    public Player findByFirstName(String firstName){
        return playerRepository.findByFirstName(firstName);
    }

}
