package net.jedzius.ffa.listener;

import com.google.inject.Inject;
import net.jedzius.ffa.user.MasterUser;
import net.jedzius.ffa.user.MasterUserDatabaseCommunication;
import net.jedzius.ffa.user.MasterUserService;
import net.jedzius.ffa.user.data.MasterUserDAO;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class PlayerJoinListener implements Listener {

    @Inject private MasterUserService masterUserService;
    @Inject private MasterUserDatabaseCommunication masterUserDatabaseCommunication;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Optional<MasterUser> masterUser = this.masterUserService.findUser(player.getUniqueId());

        if(!masterUser.isPresent()) {
            masterUser = this.masterUserService.addMasterUser(new MasterUser(player.getUniqueId(), player.getName()));
            System.out.println("New user has been created!");
        }

        MasterUser user = masterUser.get();

        MasterUserDAO userDAO = new MasterUserDAO(player.getUniqueId(), player.getName());
        userDAO.setJoinTime(System.currentTimeMillis());

        user.getMasterUserData().ustawTutaj(userDAO);

        CompletableFuture<String> update = CompletableFuture.supplyAsync(() -> {
            try {
                this.masterUserDatabaseCommunication.updateOrInsert(userDAO);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "";
        });
    }
}
