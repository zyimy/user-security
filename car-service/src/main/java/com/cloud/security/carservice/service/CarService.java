package com.cloud.security.carservice.service;

import com.cloud.security.carservice.entity.Car;
import com.cloud.security.carservice.exception.CarException;
import com.cloud.security.carservice.exception.ErrorCode;
import com.cloud.security.carservice.repository.CarRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CarService {

  private final CarRepository carRepository;

  public CarService(final CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public List<Car> getListAll() {
    List<Car> listUser = carRepository.findAll();
    log.info("Buscando una lista de usuarios {} ", listUser);
    if (listUser.isEmpty()) {
      log.error("No existe cars para este user");
    } else {
      return listUser;
    }
    return null;
  }

  public Car getCarById(Integer id) {
    Optional<Car> user = carRepository.findById(id);
    log.info("Obteniendo un CAR por su ID {} ", user);
    if (user.isEmpty()) {
      throw new CarException(ErrorCode.CAR_NOT_FOUND);
    }
    return user.get();
  }

  public Car save(Car car) {
    log.info("Persistence CAR {} ", car);
    return carRepository.save(car);
  }

  public List<Car>getListCarById(Integer idUser){
    List<Car>listUser = carRepository.findByUserId(idUser);
    log.info("Buscando una lista de CARS por userId ");
    if (listUser.isEmpty()) {
      log.error("No existen cars para este usuario {} ", idUser);
    }
      return listUser;
  }

}
