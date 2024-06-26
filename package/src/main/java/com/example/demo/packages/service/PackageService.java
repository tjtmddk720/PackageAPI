package com.example.demo.packages.service;

import com.example.demo.packages.dto.PackageDto;
import com.example.demo.packages.entity.Image;
import com.example.demo.packages.entity.Package;
import com.example.demo.packages.mapper.PackageMapper;
import com.example.demo.packages.repository.ImageRepository;
import com.example.demo.packages.repository.PackageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PackageService {
    private final PackageRepository packageRepository;
    private final ImageRepository imageRepository;
    private final PackageMapper packageMapper;

    public PackageService(PackageRepository packageRepository, ImageRepository imageRepository,
                          PackageMapper packageMapper) {
        this.packageRepository = packageRepository;
        this.imageRepository = imageRepository;
        this.packageMapper = packageMapper;
    }

    /** 패키지 등록 **/
    @Transactional
    public void createPackage(Package packages) {
        Package savedPackage = packageRepository.save(packages);
        List<Image> images = packages.getImages();

        if (images != null) {
            for(Image image : images){
                image.setAPackage(savedPackage);
                imageRepository.save(image);
            }
        }
    }

    /** 패키지 조회 **/
    public Package findPackage(long packageId){
        Optional<Package> optionalPackage = packageRepository.findById(packageId);

        return optionalPackage.orElseThrow(() ->
                new NoSuchElementException("Package not found with id: " + packageId));
    }

    /** 패키지 수정 **/
    public Package updatePackage(long packageId, PackageDto packageDto){
        Package verifiedPackages = findPackage(packageId);

        if(verifiedPackages.getId()==packageDto.getId())
        {
            // 트래킹 번호 업데이트
            Optional.ofNullable(packageDto.getTrackingNo()).ifPresent(trackingNo ->
                    verifiedPackages.setTrackingNo(trackingNo));

            // 이미지 업데이트
            Optional.ofNullable(packageDto.getImages()).ifPresent(imagesDto -> {

                // 기존 이미지 제거
                List<Image> existingImages = verifiedPackages.getImages();

                if (existingImages != null) {
                    existingImages.forEach(imageRepository::delete);
                    verifiedPackages.getImages().clear();
                }

                // 업데이트된 이미지 저장
                List<Image> images = imagesDto.stream()
                        .map(packageMapper::mapImageDtoToImage)
                        .collect(Collectors.toList());

                images.forEach(image -> image.setAPackage(verifiedPackages));
                verifiedPackages.setImages(images);

                images.forEach(imageRepository::save);
            });
        }
        return packageRepository.save(verifiedPackages);
    }

    /** 패키지 삭제 **/
    public void deletePackage(long packageId){
        Package verifiedPackages = findPackage(packageId);

        packageRepository.delete(verifiedPackages);
    }
}
