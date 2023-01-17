package net.jedzius.ffa.user.data;

public class MasterUserData {
    private int points = 1000;
    private int prestige = 0;
    private int kills = 0;
    private int deaths = 0;
    private int ks = 0;
    private long joinTime = 0L;

    public void ustawTutaj(MasterUserDAO userDAO) {
        this.points = userDAO.getPoints();
        this.prestige = userDAO.getPrestige();
        this.kills = userDAO.getKills();
        this.deaths = userDAO.getDeaths();
        this.ks = userDAO.getKs();
        this.joinTime = userDAO.getJoinTime();
    }

    public void ustaweDAO(MasterUserDAO masterUserDAO) {
        masterUserDAO.setPoints(this.points);
        masterUserDAO.setPrestige(this.prestige);
        masterUserDAO.setKills(this.prestige);
        masterUserDAO.setDeaths(this.prestige);
        masterUserDAO.setKs(this.prestige);
        masterUserDAO.setJoinTime(this.joinTime);
    }

    public long getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(long joinTime) {
        this.joinTime = joinTime;
    }

    public int getPoints() {
        return points;
    }

    public int getPrestige() {
        return prestige;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getKs() {
        return ks;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setKs(int ks) {
        this.ks = ks;
    }
}
