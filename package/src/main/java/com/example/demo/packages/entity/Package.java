package com.example.demo.packages.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Package {
    @Id
    private long id;
    private String trackingNo;
    @OneToMany(mappedBy = "aPackage", cascade = CascadeType.REMOVE)
    List<Image> images = new ArrayList<>();
}
