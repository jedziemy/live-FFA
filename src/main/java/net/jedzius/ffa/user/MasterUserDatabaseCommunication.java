package net.jedzius.ffa.user;

import com.google.inject.Inject;
import net.jedzius.ffa.user.data.MasterUserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MasterUserDatabaseCommunication {

    @Inject private Connection connection;

    public void updateOrInsert(MasterUserDAO masterUserDAO) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(new StringBuilder()
                        .append("UPDATE users SET ")
                        .append("name=?, ")
                        .append("points=?, ")
                        .append("prestige=?, ")
                        .append("kills=?, ")
                        .append("deaths=?, ")
                        .append("ks=?, ")
                        .append("join_time=? WHERE uuid=?")
                .toString());

        preparedStatement.setString(1, masterUserDAO.getName());
        preparedStatement.setInt(2, masterUserDAO.getPoints());
        preparedStatement.setInt(3, masterUserDAO.getPrestige());
        preparedStatement.setInt(4, masterUserDAO.getKills());
        preparedStatement.setInt(5, masterUserDAO.getDeaths());
        preparedStatement.setInt(6, masterUserDAO.getKs());
        preparedStatement.setString(7, String.valueOf(masterUserDAO.getJoinTime()));
        preparedStatement.setString(8, masterUserDAO.getUniqueId().toString());

        int status = preparedStatement.executeUpdate();

        if(!(status > 0)) {
            PreparedStatement preparedStatement2 = this.connection.prepareStatement(new StringBuilder()
                            .append("INSERT INTO users VALUES (?,?,?,?,,?,?,?,?)")
                    .toString());

            preparedStatement2.setString(1, masterUserDAO.getUniqueId().toString());
            preparedStatement2.setString(2, masterUserDAO.getName());
            preparedStatement2.setInt(3, masterUserDAO.getPoints());
            preparedStatement2.setInt(4, masterUserDAO.getPrestige());
            preparedStatement2.setInt(5, masterUserDAO.getKills());
            preparedStatement2.setInt(6, masterUserDAO.getDeaths());
            preparedStatement2.setInt(7, masterUserDAO.getKs());
            preparedStatement2.setString(8, String.valueOf(masterUserDAO.getJoinTime()));

            preparedStatement2.executeUpdate();
        }
    }
}
