package com.example.docrepositorytry;

public class Realty {
    int id;
    String name;
    int address;
    String type;
    int status;
    String photo;

    public void setId() {
        this.id = id;
    }
    public void setName() {
        this.name = name;
    }

    public void setAddress() {
        this.address = address;
    }

    public void setType() {
        this.type = type;
    }

    public void setStatus() {
        this.status = status;
    }

    public void setPhoto() {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAddress() {
        return address;
    }

    public int getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getPhoto() {
        return photo;
    }

    public Realty (int id, int name, int type) {
        this.id = id;
        this.name = String.valueOf(name);
        this.address = address;
        this.type = String.valueOf(type);
        this.status = status;
        this.photo = photo;
    }
}
