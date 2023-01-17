package net.jedzius.ffa.listener;

import com.google.inject.Inject;
import net.jedzius.ffa.user.MasterUser;
import net.jedzius.ffa.user.MasterUserDatabaseCommunication;
import net.jedzius.ffa.user.MasterUserService;
import net.jedzius.ffa.user.data.MasterUserDAO;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class PlayerQuitListener implements Listener {

    @Inject private MasterUserDatabaseCommunication masterUserDatabaseCommunication;
    @Inject private MasterUserService masterUserService;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        this.masterUserService.findUser(player.getUniqueId()).ifPresent(user -> {
            MasterUser masterUser = this.masterUserService.findUser(player.getUniqueId()).get();

            MasterUserDAO userDAO = new MasterUserDAO(masterUser.getUniqueId(), masterUser.getName());
            masterUser.getMasterUserData().ustaweDAO(userDAO);


            try {
                this.masterUserDatabaseCommunication.updateOrInsert(userDAO);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
