package homeworkone;

import javax.persistence.*;

@Entity
@Table(name = "GROUP_MEMBER")
public class GroupMember {

    /**
     * The group member's email address (primary key)
     */
    @Id
    @Column(name = "email")
    protected String email;

    /**
     * The group member's first name
     */
    @Basic
    @Column(name = "first")
    protected String firstName;

    /**
     * The group member's last name
     */
    @Column(name = "last")
    protected String lastName;

    /**
     * A picture representing the group member
     */
    @Column(name = "avatar")
    protected String avatar;

    /**
     * The group member's favorite language
     */
    @Column(name = "lang")
    protected String language;

    public GroupMember() {

    }

    public GroupMember(String email, String firstName, String lastName, String avatarURL, String favoriteLanguage) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        avatar = avatarURL;
        this.language = favoriteLanguage;
    }

    //<editor-fold desc="Getters and Setters">
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    //</editor-fold>
}

