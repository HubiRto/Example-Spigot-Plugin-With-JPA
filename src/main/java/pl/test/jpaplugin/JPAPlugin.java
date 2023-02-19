package pl.test.jpaplugin;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.test.jpaplugin.service.PlayerService;

import java.lang.reflect.InvocationTargetException;

public final class JPAPlugin extends JavaPlugin {
    public static JPAPlugin plugin;
    public static PlayerService playerService;
    private static AnnotationConfigApplicationContext applicationContext;
    @Override
    public void onEnable() {
        plugin = this;

        String packageName = getClass().getPackage().getName();
        for (Class<?> clazz : new Reflections(packageName + ".listeners").getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                getServer().getPluginManager().registerEvents(listener, this);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("pl.test.jpaplugin");
        applicationContext.refresh();


        playerService = (PlayerService) getApplicationContext().getBean("playerService");
    }

    @Override
    public void onDisable() {
        applicationContext.close();
    }

    public AnnotationConfigApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
