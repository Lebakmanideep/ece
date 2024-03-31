package org.example.ecommerceapi.DAO;

import org.example.ecommerceapi.Entity.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageFile,Integer> {
}
