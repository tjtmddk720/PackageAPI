package com.example.demo.packages.mapper;

import com.example.demo.packages.dto.ImageDto;
import com.example.demo.packages.dto.PackageDto;
import com.example.demo.packages.entity.Image;
import com.example.demo.packages.entity.Package;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PackageMapper {
    default Package PackageDtoToPackage(PackageDto packageDto) {
        Package packages = new Package();

        if (packageDto != null) {
            packages.setId(packageDto.getId());
            packages.setTrackingNo(packageDto.getTrackingNo());

            if (packageDto.getImages() != null) {
                List<Image> images = packageDto.getImages().stream()
                        .map(this::mapImageDtoToImage)
                        .collect(Collectors.toList());
                packages.setImages(images);
            }
        }
        return packages;
    }

    default Image mapImageDtoToImage(ImageDto imageDto) {
        Image image = new Image();

        if (imageDto != null) {
            image.setFilename(imageDto.getFilename());
            image.setType(imageDto.getType());
        }
        return image;
    }

    default PackageDto packageToPackageDto(Package packages) {
        PackageDto packageDto = new PackageDto();

        if (packages != null) {
            packageDto.setId(packages.getId());
            packageDto.setTrackingNo(packages.getTrackingNo());

            if (packages.getImages() != null) {
                List<ImageDto> imageDtos = packages.getImages().stream()
                        .map(this::mapImageToImageDto)
                        .collect(Collectors.toList());
                packageDto.setImages(imageDtos);
            }
        }
        return packageDto;
    }

    default ImageDto mapImageToImageDto(Image image) {
        ImageDto imageDto = new ImageDto();

        if (image != null) {
            imageDto.setFilename(image.getFilename());
            imageDto.setType(image.getType());
        }
        return imageDto;
    }
}
