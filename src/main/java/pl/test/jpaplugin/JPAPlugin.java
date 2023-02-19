package pl.test.jpaplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.test.jpaplugin.entity.Player;
import pl.test.jpaplugin.service.PlayerService;

import java.util.List;

@SuppressWarnings("unused")
public final class JPAPlugin extends JavaPlugin {
    public static JPAPlugin plugin;
    private static AnnotationConfigApplicationContext applicationContext;
    @Override
    public void onEnable() {
        plugin = this;
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("pl.test.jpaplugin");
        applicationContext.refresh();


        PlayerService playerService = (PlayerService) getApplicationContext().getBean("playerService");
        List<Player> playerList = playerService.findAll();
    }

    @Override
    public void onDisable() {
        applicationContext.close();
    }

    public AnnotationConfigApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
