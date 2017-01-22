package com.elock.tylerphelps.elock;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "LOCK".
 */
public class Lock {

    private Long id;
    /** Not-null value. */
    private String identifier;
    /** Not-null value. */
    private String channel;
    /** Not-null value. */
    private String publishKey;
    /** Not-null value. */
    private String subscribeKey;
    /** Not-null value. */
    private String nickname;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Lock() {
    }

    public Lock(Long id) {
        this.id = id;
    }

    public Lock(Long id, String identifier, String channel, String publishKey, String subscribeKey, String nickname) {
        this.id = id;
        this.identifier = identifier;
        this.channel = channel;
        this.publishKey = publishKey;
        this.subscribeKey = subscribeKey;
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getIdentifier() {
        return identifier;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /** Not-null value. */
    public String getChannel() {
        return channel;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /** Not-null value. */
    public String getPublishKey() {
        return publishKey;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setPublishKey(String publishKey) {
        this.publishKey = publishKey;
    }

    /** Not-null value. */
    public String getSubscribeKey() {
        return subscribeKey;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setSubscribeKey(String subscribeKey) {
        this.subscribeKey = subscribeKey;
    }

    /** Not-null value. */
    public String getNickname() {
        return nickname;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
