package com.example.demo.packages.repository;

import com.example.demo.packages.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package,Long> {
}
