package model;

import java.util.Date;

public class AdoptionRequest {

   private String id,petId,name,email,address, reason,status;
   private Date date;

    public AdoptionRequest(){


    }

    public AdoptionRequest(String id, String petId, String name, String email, String address, String motive, String status, Date date) {

        this.id = id;
        this.petId = petId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.reason = motive;
        this.status = status;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
