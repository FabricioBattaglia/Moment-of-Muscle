package com.example.profilescreen;

public class Job {

    String category;
    String job_title;
    String radius;
    String user_id;
    String job_id;

    public Job() {}

    public Job(String job_title, String category, String radius, String job_id) {
        this.job_title = job_title;
        this.category = category;
        this.radius = radius;
        this.job_id = job_id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
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

    public void setCategory() {
        this.category = category;
    }

    public void setJob_title() {
        this.job_title = job_title;
    }

    public void setRadius() {
        this.radius = radius;
    }

    public void setUser_id() {
        this.user_id = user_id;
    }

}