package com.example.demo.packages.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PackageDto {
    private long id;
    private String trackingNo;
    private List <ImageDto> images;
}
