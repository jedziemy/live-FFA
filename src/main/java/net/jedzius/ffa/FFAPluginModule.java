package net.jedzius.ffa;

import com.google.inject.AbstractModule;
import net.jedzius.ffa.user.MasterUserDatabaseCommunication;
import net.jedzius.ffa.user.MasterUserService;

import java.sql.Connection;

public class FFAPluginModule extends AbstractModule {

    public Connection connection;

    public FFAPluginModule(Connection connection) {
        this.connection = connection;
    }

    protected void configure() {
        this.bind(MasterUserService.class)
                .toInstance(new MasterUserService());

        this.bind(Connection.class)
                .toInstance(this.connection);

        this.bind(MasterUserDatabaseCommunication.class)
                .toInstance(new MasterUserDatabaseCommunication());
    }
}
