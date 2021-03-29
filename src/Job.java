package com.example.momentofmuscle;

public class Job {

    String category;
    String job_title;
    //String radius;
    String user_id;
    //String job_id;
    String price;
    String job_description;
    //String city_state;
    String accepted_by_id;
    String accepted_by_name;
    String accepted_by_email;
    String accepted_by_phone;
    //Boolean isRejected;
    Boolean isAccepted;
    Boolean bothAccepted;
    Boolean hostFinished;
    Boolean workerFinished;

    public Job(String acategory, String ajob_title, String auser_id, String aprice, String ajob_description,
               String aaccepted_by_id, String aaccepted_by_name, String aaccepted_by_email, String aaccepted_by_phone,
               boolean aisAccepted, boolean bothAccepted, boolean hostFinished, boolean workerFinished) {
    }

    public Job(){

    }

    //public String getJob_id() {
    //    return job_id;
    //}

    public String getAccepted_by_email() {
        return accepted_by_email;
    }

    public String getAccepted_by_phone() {
        return accepted_by_phone;
    }

    public String getAccepted_by_id() {
        return accepted_by_id;
    }

    public String getAccepted_by_name() {
        return accepted_by_name;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    public Boolean getWorkerFinished() {
        return workerFinished;
    }

    public Boolean getHostFinished() {
        return hostFinished;
    }

    //public Boolean getIsRejected() {
    //    return isRejected;
    //}

    public Boolean getBothAccepted() {
        return bothAccepted;
    }

    public String getPrice() {
        return price;
    }

    public String getJob_description() {
        return job_description;
    }

    //public String getCityState() {
    //    return city_state;
    //}

    public String getCategory() {
        return category;
    }

    public String getJob_title() {
        return job_title;
    }

    //public String getRadius() {
    //    return radius;
    //}

    public String getUser_id() {
        return user_id;
    }

}