package javadev.bootcamp.MeetingRoomManager.model;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "Room")
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "date", nullable = false)
    private String date;
    @Column(name = "startingTime")
    private String startingTime;
    @Column(name = "endingTime")
    private String endingTime;

    public Room() {
    }

    public Room(Long id, String name, String date, String startingTime, String endingTime) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    @Override
    public String toString(){
        return "Room {id = " + id + ", name = " + name + ", startingTime = " + startingTime + ", endingTime = " + endingTime + "}";
    }

}



