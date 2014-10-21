/**
 * Created by drew on 10/19/14.
 */
public class UserDescription {
    String mFirstname;
    String mLastname;
    String mDescription;


    public UserDescription(String firstname, String lastname, String description) {
        mFirstname = firstname;
        mLastname = lastname;
        mDescription = description;
    }

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

    public String getLastname() {
        return mLastname;
    }

    public void setLastname(String lastname) {
        mLastname = lastname;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
