package com.example.android.provectustask;

import java.util.Date;

/**
 * Created by nexus on 28.06.2017.
 */
public class UserProfile {

    // User's name components
    private String mTitle;

    private String mFirstName;

    private String mLastName;

    // User's gender
    private String mGender;

    //User's location components
    private String mStreet;

    private String mCity;

    private String mState;

    private int mPostalCode;

    //User's date of birth
    private Date mDateOfBirth;

    //User's phone numbers
    private String mCellPhone;

    private String mPhone;

    //User's email
    private String mEmail;

    //User's picture
    private String mPictureUrl;

    //Create a new profile object
    public UserProfile(String mTitle, String mFirstName, String mLastName, String mGender, String mStreet, String mCity, String mState, int mPostalCode, Date mDateOfBirth, String mCellPhone, String mPhone, String mEmail, String mPictureUrl) {
        this.mTitle = mTitle;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mGender = mGender;
        this.mStreet = mStreet;
        this.mCity = mCity;
        this.mState = mState;
        this.mPostalCode = mPostalCode;
        this.mDateOfBirth = mDateOfBirth;
        this.mCellPhone = mCellPhone;
        this.mPhone = mPhone;
        this.mEmail = mEmail;
        this.mPictureUrl = mPictureUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getGender() {
        return mGender;
    }

    public String getStreet() {
        return mStreet;
    }

    public String getCity() {
        return mCity;
    }

    public String getState() {
        return mState;
    }

    public int getPostalCode() {
        return mPostalCode;
    }

    public Date getDateOfBirth() {
        return mDateOfBirth;
    }

    public String getCellPhone() {
        return mCellPhone;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPictureUrl() {
        return mPictureUrl;
    }
}
