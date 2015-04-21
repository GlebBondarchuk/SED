package com.bsu.sed.model;

/**
 * @author gbondarchuk
 */
public enum Tiles {
    LOGIN_PAGE("sed-login"),
    MAIN_PAGE("sed-main"),
    SIGN_UP_PAGE("sed-sign-up"),
    USER_PAGE("sed-user"),
    EXCEPTION_PAGE("error.exception"),
    CONTACT_PAGE("sed-contact"),
    PEOPLES_PAGE("sed-peoples"),
    STUDENTS_PAGE("sed-students"),
    SEARCH_PAGE("sed-search"),
    CONTENT_PAGE("sed-content"),
    LIST_NEWS_PAGE("sed-list-news"),
    ADD_NEWS_PAGE("sed-add-news"),
    NEWS_PAGE("sed-news"),
    STUDENT_PAGE("sed-student"),
    SITE_MAP("sed-site-map"),
    STATISTICS_PAGE("sed-statistics"),
    NOTIFICATIONS_PAGE("sed_notifications"),

    //admin pages
    USERS_PAGE("sed-users"),
    SYSTEM_PAGE("sed-system"),
    ADD_NEW_TAB("sed-add-new-tab"),
    EDIT_TAB("sed-edit-tab"),
    NEWS_URL_PAGE("sed-news-url"),
    BACKGROUND_PROCESSES_PAGE("sed-process"),
    DOCUMENTS_PAGE("sed-documents"),
    GALLERY_PAGE("sed-gallery"),
    CREATE_PAGE("create-page"),
    CONTENTS_PAGE("sed-contents"),
    SITE_MAP_ADD("sed-site-map-add"),
    NES_URL_EDIT("sed-news-url-edit"),
    ADMIN_NEWS_PAGE("sed-admin-news"),
    TEXT_PAGE("sed_text"),
    EDIT_TEXT_PAGE("edit_sed_text");

    private String tileName;

    Tiles(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
