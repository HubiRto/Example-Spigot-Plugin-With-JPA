package pl.test.jpaplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
    }

    @Override
    public void onDisable() {
        applicationContext.close();
    }
}
