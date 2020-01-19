package android.dawgpack.seventythree.dawgpackapp.model;

public class User {

    // gender enunm
    public enum Gender {
        MALE,
        FEMALE,
        NONBINARY;
    }

    // username of this user
    private String username;

    // the net id of this user
    private String netId;

    // the gender of this user
    private Gender gender;

    // key (for authentication purposes)
    // private String key;

    // constructor
    public User(String username, String netId, Gender gender) {
        this.username = username;
        this.netId = netId;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public String getNetId() {
        return netId;
    }

    public Gender getGender() {
        return gender;
    }

}
