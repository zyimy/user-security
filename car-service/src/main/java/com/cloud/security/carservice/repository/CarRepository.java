package com.cloud.security.carservice.repository;

import com.cloud.security.carservice.entity.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

  List<Car>findByUserId(Integer userId);
}
