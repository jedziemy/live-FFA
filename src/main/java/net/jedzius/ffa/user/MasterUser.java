package net.jedzius.ffa.user;

import net.jedzius.ffa.user.data.MasterUserData;

import java.util.UUID;

public class MasterUser {
    private final UUID uniqueId;
    private String name;

    private final MasterUserData masterUserData;

    public MasterUser( UUID uniqueId, String name) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.masterUserData = new MasterUserData();
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MasterUserData getMasterUserData() {
        return masterUserData;
    }
}
