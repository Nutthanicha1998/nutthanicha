package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;



@Data
@Entity
@NoArgsConstructor
@Table(name="Apply")
public class Apply{

    @Id
    @SequenceGenerator(name="Apply_seq",sequenceName="Apply_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Apply_seq")

    @Column(name = "idApply_Apply", unique = true, nullable = true)
    private @NonNull Long id;

    
    private @NonNull String name;

    
    private @NonNull String lname;

    private @NonNull String institutionS;

    private @NonNull Integer yearStudy;

    
    private @NonNull long address;

    
    private @NonNull Integer number;

    
    private @NonNull long Email;

    
    private @NonNull long gpa;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Student.class)
    @JoinColumn(name = "Student_id", insertable = true)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeS.class)
    @JoinColumn(name = "TypeS_id", insertable = true)
    private TypeS type;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Major.class)
    @JoinColumn(name = "Major_id", insertable = true)
    private  Major major;
}