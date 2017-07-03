package com.example.android.provectustask;

import java.io.Serializable;

/**
 * Created by nexus on 28.06.2017.
 */
public class UserProfile implements Serializable {

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

    private String mPostalCode;

    //User's date of birth
    private String mDateOfBirth;

    //User's phone numbers
    private String mCellPhone;

    private String mPhone;

    //User's email
    private String mEmail;

    //User's picture's sizes
    private String mPictureUrlThumb;

    private String mPictureUrlMedium;

    private String mFileName;

    public String getmFileName() {
        return mFileName;
    }

    //Create a new profile object
    public UserProfile(String mTitle, String mFirstName, String mLastName,
                       String mGender,
                       String mStreet, String mCity, String mState, String mPostalCode,
                       String mDateOfBirth,
                       String mCellPhone, String mPhone,
                       String mEmail,
                       String mPictureUrl, String mPictureUrlMedium) {
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
        this.mPictureUrlThumb = mPictureUrl;
        this.mPictureUrlMedium = mPictureUrlMedium;

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

    public String getPostalCode() {
        return mPostalCode;
    }

    public String getDateOfBirth() {
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

    public String getPictureThumbUrl() {
        return mPictureUrlThumb;
    }

    public String getmPictureUrlMedium() {
        return mPictureUrlMedium;
    }
}
