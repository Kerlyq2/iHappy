package me.tesis.ihappy.data.remote;

import me.tesis.ihappy.data.remote.Request.UserRequest;
import retrofit2.Retrofit;


public class ApiConstants {

    public static final String LOGIN = "start/login-api/";
    public static final String U_DESCRIPTION = "person-api/{person_id}/";
    public static final String LOGOUT = "logout-api/";
    public static final String REGISTER = "register-api/";
    public static final String SEND_REPORT = "animal-report-api/";
    public static final String SEND_PHOTO = "report-image-api/";
    public static final String SEND_PHOTO_ADOPTION ="adoption-image-api/";
    public static final String GET_REPORTS = "animal-report-api/";
    public static final String GET_REPORTS_ADOPTION = "adoption-proposal-api/";
    public static final String SEND_ADOPTION = "adoption-proposal-api/";
    public static final String UPLOAD_PHOTO = "person-image-api/";
    public static final String CHANGE_PHOTO= "person-image-api/{person_id}/";
    public static final String EDIT_U="person-api/{person_id}/";
    public static final String FIND_EMAIL="find-user-api/";

    public static final String UPDATE_PASS="update-password-api/";
    public static final String SEND_PROPOSAL="adoption-request-api/";
    public static final String GET_PROPOSAL="adoption-proposal-api/";
    public static final String GET_REQUESTS= "adoption-request-api";
    public static final String DELETE_PROPOSAL= "adoption-proposal-api/{proposal_id}/";

    public static final String GET_REQUEST_PERSON ="adoption-request-api/";
    public static final String REGISTER_DEVICE="person-device-api/";

    public static final String DELETE_REQUEST = "adoption-request-api/{proposal_id}/";
    public static final String CHANGED_STATE = "adoption-request-api/{request_id}/";

    //API KEYS CONSTANS
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String CONTENT_TYPE_JSON = "application/json";

}
