package com.example.profilescreen;


public class Job {

    String category;
    String job_title;
    String radius;
    String user_id;
    String job_id;
    String price;
    String job_description;
    String city_state;
    Boolean isAccepted;

    public String getJob_id() {
        return job_id;
    }

    public Boolean getisAccepted() {
        return isAccepted;
    }

    public String getPrice() {
        return price;
    }

    public String getJob_description() {
        return job_description;
    }

    public String getCityState() {
        return city_state;
    }

    public String getCategory() {
        return category;
    }

    public String getJob_title() {
        return job_title;
    }

    public String getRadius() {
        return radius;
    }

    public String getUser_id() {
        return user_id;
    }

}