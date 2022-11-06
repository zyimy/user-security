package com.cloud.security.bikeservice.repository;


import com.cloud.security.bikeservice.entity.Bike;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {

  List<Bike> findByUserId(Integer userId);
}
