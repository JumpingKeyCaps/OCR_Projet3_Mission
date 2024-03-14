package com.openclassrooms.tajmahal.domain.modelUnitTest;

import static junit.framework.TestCase.assertEquals;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.openclassrooms.tajmahal.domain.model.UserProfile;

import org.junit.Assert;
import org.junit.Test;

/**
 * UserProfile class unit tests.
 */
public class UserProfileUnitTest {

    /**
     * Test Constructor of the UserProfile object.
     */
    @Test
    public void testConstructor() {
        //Act: create a new UserProfile object
        String userName = "John Doe";
        String userAvatarURL = "https://example.com/avatar.jpg";

        UserProfile profile = new UserProfile(userName, userAvatarURL);

        //Assert: test if object is well created
        assertNotNull(profile);
        assertEquals(userName, profile.getUserName());
        assertEquals(userAvatarURL, profile.getUserAvatarURL());
    }

    /**
     * Test Getter and Setter of the UserProfile object.
     */
    @Test
    public void testGettersAndSetters() {
        //Act: create a new UserProfile object and set new values
        UserProfile profile = new UserProfile("", "");

        String newUserName = "Alice Smith";
        String newUserAvatarURL = "https://test.test/new_avatar.jpg";

        profile.setUserName(newUserName);
        profile.setUserAvatarURL(newUserAvatarURL);
        //Assert: test with getter if values are well added with setter.
        assertEquals(newUserName, profile.getUserName());
        assertEquals(newUserAvatarURL, profile.getUserAvatarURL());
    }

    /**
     * Test Equality of the UserProfile object.
     */
    @Test
    public void testEquals() {
        //Act : create 2 same user profile object, and 1 different.
        UserProfile profile1 = new UserProfile("user1", "pic1");
        UserProfile profile2 = new UserProfile("user1", "pic1");
        UserProfile profile3 = new UserProfile("user2", "pic2");

        //Assert: test equality result
        Assert.assertEquals(profile1, profile2);
        assertNotEquals(profile1, profile3);
        assertNotEquals(null, profile1);
    }

    /**
     * Test HashCode of the UserProfile object.
     */
    @Test
    public void testHashCode() {
        //Act: create 2 identical UserProfile object with the same params.
        UserProfile profile1 = new UserProfile("user1", "pic1");
        UserProfile profile2 = new UserProfile("user1", "pic1");
        //Assert:  test  hashCode equality of the 2 objects.
        assertEquals(profile1.hashCode(), profile2.hashCode());
    }
}
