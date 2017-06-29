package com.example.android.provectustask.Utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.provectustask.UserProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class UtilsJson {
    /** Tag for the log messages */
    public static final String LOG_TAG = UtilsJson.class.getSimpleName();

    /**
     * Query the https://randomuser.me/ dataset and return an {@link com.example.android.provectustask.UserProfile} object.
     */

    public static ArrayList<UserProfile> fetchUserProfileData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Return the {@link Event}
        return extractFeatureFromJson(jsonResponse);
    }

    /**
     * Returns new URL object from the given string URL.
     */

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL! ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            Log.e(LOG_TAG, "Url is null!");
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an {@link UserProfile} object by parsing out information
     */

    private static ArrayList<UserProfile> extractFeatureFromJson(String UserProfileJSON) {

        ArrayList<UserProfile> UserProfileList = new ArrayList<>();

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(UserProfileJSON)) {
            Log.v("json1","json string is empty!!!");
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(UserProfileJSON);

            JSONArray usersProfileArray = jsonObject.getJSONArray("results");
            Log.v("staty", String.valueOf(usersProfileArray.length()));


            // If there are results in the profiles array
            if (usersProfileArray.length() > 0) {
                for (int i = 0; i < usersProfileArray.length();i++){

                    // Extract out the profile's JSON
                    JSONObject usersProfile = usersProfileArray.getJSONObject(i);

                    //Full name construction:
                    JSONObject fullName = usersProfile.getJSONObject("name");

                    String title = fullName.getString("title");
                    String firstName = fullName.getString("first");
                    String lastName = fullName.getString("last");

//                    String name = String.format("%s. %s %s", title,
//                            firstName, lastName);

                    //Gender
                    String sex = usersProfile.getString("gender");

                    //Full address construction:
                    JSONObject fullAddress = usersProfile.getJSONObject("location");

                    String street = fullAddress.getString("street");
                    String city = fullAddress.getString("city");
                    String state = fullAddress.getString("state");
                    String postcode = fullAddress.getString("postcode");

                    //Email
                    String email = usersProfile.getString("email");

                    //Date of birth:
                    String dob = usersProfile.getString("dob");

                    //Phones:

                    String phone = usersProfile.getString("phone");
                    String cellPhone = usersProfile.getString("cell");

                    String name = String.format("%s. %s %s \n %s \n %s %s %s %s \n %s \n %s \n %s %s \n \n \n", title, firstName, lastName, sex,
                            street, city, state, postcode,
                            email, dob,
                            phone, cellPhone);

                    Log.e("staty", name);

//                    String numberOfPeople = usersProfile.getString("services");
//                    int UserProfileCode = usersProfile.getInt("UserProfile");
//                    Log.e("star", String.valueOf(UserProfileCode));

                    // Create a new {@link Event} object

                   // UserProfileList.add(new UserProfile(sex, numberOfPeople, UserProfileCode));
                }
            }
            return UserProfileList;

        } catch (JSONException e) {

            Log.e(LOG_TAG, "Problem parsing the UserProfile JSON results", e);
            return UserProfileList;
        }
        //return null;
    }}

