package pl.test.jpaplugin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.test.jpaplugin.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
