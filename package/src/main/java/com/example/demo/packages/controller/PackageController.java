package com.example.demo.packages.controller;

import com.example.demo.packages.dto.PackageDto;
import com.example.demo.packages.entity.Package;
import com.example.demo.packages.mapper.PackageMapper;
import com.example.demo.packages.service.PackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packages")
public class PackageController {
    private final PackageService packageService;
    private final PackageMapper packageMapper;
    public PackageController(PackageService packageService,PackageMapper packageMapper){
        this.packageService = packageService;
        this.packageMapper = packageMapper;
    }

    /** 패키지 등록 **/
    @PostMapping
    public ResponseEntity createPackages(@RequestBody PackageDto packageDto){
        Package packages = packageMapper.PackageDtoToPackage(packageDto);
        packageService.createPackage(packages);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /** 패키지 조회 **/
    @GetMapping("/{packages-id}")
    public ResponseEntity getPackages(@PathVariable("packages-id") long packageId){
        Package packages = packageService.findPackage(packageId);

        return new ResponseEntity<>(packageMapper.packageToPackageDto(packages),HttpStatus.OK);
    }

    /** 패키지 수정 **/
    @PatchMapping("/{packages-id}/edit")
    public ResponseEntity updatePackages(@PathVariable("packages-id") long packageId,
                                         @RequestBody PackageDto packageDto){
        Package packages = packageMapper.PackageDtoToPackage(packageDto);
        Package updatePackages = packageService.updatePackage(packageId,packageDto);

        return new ResponseEntity<>(packageMapper.packageToPackageDto(updatePackages),HttpStatus.OK);
    }

    /** 패키지 삭제 **/
    @DeleteMapping("/{packages-id}")
    public ResponseEntity deletePackages(@PathVariable("packages-id") long packageId){
        packageService.deletePackage(packageId);

        return ResponseEntity.noContent().build();
    }
}
