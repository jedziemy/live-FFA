package net.jedzius.ffa;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.jedzius.ffa.listener.PlayerJoinListener;
import net.jedzius.ffa.postgresql.Database;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class FFAPlugin extends JavaPlugin {

    private Database database;
    private Injector injector;
    @Override
    public void onEnable() {
        try {
            this.database = new Database();
            this.database.connect();

            this.injector = Guice.createInjector(new FFAPluginModule(this.database.getExistedConnection()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.registerListener();
    }

    @Override
    public void onDisable() {
        try {
            this.database.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void registerListener() {
        this.getServer().getPluginManager().registerEvents(this.injector.getInstance(PlayerJoinListener.class), this);
    }
}
