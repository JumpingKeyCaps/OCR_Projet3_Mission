package com.openclassrooms.tajmahal.domain.model;

import java.util.Objects;

/**
 * Represent the current user of the app profile.
 * This class encapsulates all data of the user.
 */
public class UserProfile {
    /** The name of the user */
    private String userName;
    /** The Url of the user avatar */
    private String userAvatarURL;

    //---- Constructor

    /**
     * Construct a new user profile instance.
     *
     * @param userName the name of the user.
     * @param userAvatarURL  the url of his avatar.
     */
    public UserProfile(String userName, String userAvatarURL) {
        this.userName = userName;
        this.userAvatarURL = userAvatarURL;
    }

    //---- Getters/Setters

    /**
     * Return the user name.
     *
     * @return a String representing the name of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set or update the name  of the user.
     *
     * @param userName the new name of the user
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Return the url of the user avatar.
     *
     * @return a String of the url.
     */
    public String getUserAvatarURL() {
        return userAvatarURL;
    }

    /**
     * Set or update the avatar url of the user.
     *
     * @param userAvatarURL the new new avatar url
     */
    public void setUserAvatarURL(String userAvatarURL) {
        this.userAvatarURL = userAvatarURL;
    }


    //---- hashcode and equals

    /**
     * Compares this user profile with another object for equality.
     * Two reviews summary are considered equal if all their fields are identical.
     *
     * @param o the object to be compared with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userName, that.userName) && Objects.equals(userAvatarURL, that.userAvatarURL);
    }

    /**
     * Generates a hash code for this user profile based on its fields.
     *
     * @return the generated hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(userName, userAvatarURL);
    }
}
