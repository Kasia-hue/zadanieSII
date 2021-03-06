package com.example.demo.model;


import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name="`Lecture`")
public class Lecture {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "`lectureStart`")
    private String lectureStart;
    @Column(name = "`lectureEnd`")
    private String lectureEnd;
    @Column(name = "`lectureDate`")
    private String lectureDate = "01.06.2022";
    @Column(name = "`path`")
    private Long path;


    public Lecture(){}

    public Lecture(String lectureStart, String lectureEnd, Long path) {
        this.lectureStart = lectureStart;
        this.lectureEnd = lectureEnd;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public String getLectureStart() {
        return lectureStart;
    }

    public String getLectureEnd() {
        return lectureEnd;
    }

    public String getLectureDate() {
        return lectureDate;
    }

    public Long getPath() {
        return path;
    }


 }
