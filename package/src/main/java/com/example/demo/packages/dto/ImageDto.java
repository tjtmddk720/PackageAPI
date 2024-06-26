package com.example.demo.packages.dto;

import com.example.demo.packages.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageDto {
    private String filename;
    private Image.Type type;
}
