package pl.test.jpaplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.test.jpaplugin.entity.Player;

import static pl.test.jpaplugin.JPAPlugin.playerService;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(playerService.findByFirstName(event.getPlayer().getName()) != null) return;
        Player player = new Player();
        player.setFirstName(event.getPlayer().getName());
        playerService.addPlayer(player);
    }
}
