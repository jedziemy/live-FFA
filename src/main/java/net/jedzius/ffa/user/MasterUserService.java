package net.jedzius.ffa.user;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MasterUserService {
    private final Map<UUID, MasterUser> users = new ConcurrentHashMap<UUID, MasterUser>();

    public Optional<MasterUser> addMasterUser(MasterUser masterUser) {
        this.users.put(masterUser.getUniqueId(), masterUser);

        return Optional.of(masterUser);
    }

    public Optional<MasterUser> findUser(UUID uuid) {
        return Optional.ofNullable(this.users.get(uuid));
    }

    public Map<UUID, MasterUser> getUsers() {
        return users;
    }
}
