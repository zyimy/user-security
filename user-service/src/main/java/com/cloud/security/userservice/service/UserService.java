package com.cloud.security.userservice.service;

import com.cloud.security.userservice.entity.User;
import com.cloud.security.userservice.exception.ErrorCode;
import com.cloud.security.userservice.exception.UserException;
import com.cloud.security.userservice.feignclients.BikeFeingClient;
import com.cloud.security.userservice.feignclients.CarfeingClient;
import com.cloud.security.userservice.model.Bike;
import com.cloud.security.userservice.model.Car;
import com.cloud.security.userservice.repository.UserRepository;
import feign.FeignException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private CarfeingClient carfeingClient;

  @Autowired
  private BikeFeingClient bikeFeingClient;

  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getListAll() {
    List<User> listUser = userRepository.findAll();
    log.info("Buscando una lista de usuarios {} ", listUser);
    if (listUser.isEmpty()) {
      throw new UserException(ErrorCode.USER_NOT_FOUND, "La lista esta vacia");
    } else {
      return listUser;
    }

  }

  public User getUserById(Integer id) {
    Optional<User> user = userRepository.findById(id);
    log.info("Obteniendo un usuario por su ID {} ", user);
    if (user.isEmpty()) {
      throw new UserException(ErrorCode.USER_NOT_FOUND);
    }
    return user.get();
  }

  public User save(User user) {
    log.info("Persistence usuario {} ", user);
    return userRepository.save(user);
  }

  public List<Car> getCars(Integer userId) {
    log.info("Llamando a car-service para obtener sus CARS con id {} ", userId);
    try {
      return restTemplate.getForObject("http://car-service/v1/car/byUserId/" + userId, List.class);
    } catch (Exception ex) {
      log.error("Error en restTemplate {}", ex.getMessage());
      throw new RuntimeException("Error en restTemplate", ex);
    }
  }

  public List<Bike> getBikes(Integer userId) {
    log.info("Llamando a Bike-service para obtener sus BIKES con id {} ", userId);
    try {
      return restTemplate.getForObject("http://bike-service/v1/bike/byUserId/" + userId, List.class);
    } catch (Exception ex) {
      log.error("Error en restTemplate {}", ex.getMessage());
      throw new RuntimeException("Error en restTemplate", ex);
    }
  }

  public Car saveCar(Integer userId, Car car) {
    car.setUserId(userId);
    log.info("Llamando a car-service para guardar un CAR con userId {} ", userId);
    try {
      return restTemplate.postForObject("http://car-service/v1/car",car,Car.class);
    } catch (FeignException ex) {
      log.error("Fallo llamando a feing {} ", ex);
      throw new RuntimeException("Fallo feing", ex);
    }
  }

  public Bike saveBike(Integer userId, Bike bike) {
    bike.setUserId(userId);
    log.info("Llamando a bike-service para guardar un BIKE con userId {} ", userId);
    try {
      return restTemplate.postForObject("http://bike-service/v1/bike",bike,Bike.class);
    } catch (FeignException ex) {
      log.error("Fallo llamando a feing {} ", ex);
      throw new RuntimeException("Fallo feing", ex);
    }
  }

  public Map<String, Object> getUserAndVehicles(Integer userId) {
    log.info("Llamando a bike-service y car-service para mostrar una lista de  BIKES y CARS con userId {} ", userId);
    Map<String, Object> result = new HashMap<>();
    User user = userRepository.findById(userId).orElse(null);

    if (user == null) {
      result.put("Mensaje", "No existe el usuario con Id" + userId);
      return result;
    }

    result.put("User", user);
    List<Car> cars = restTemplate.getForObject("http://car-service/v1/car/byUserId/" + userId, List.class);
    if (cars.isEmpty()) {
      result.put("Cars", "ese user no tiene coches");
    } else {
      result.put("cars", cars);
    }

    List<Bike> bikes = restTemplate.getForObject("http://bike-service/v1/bike/byUserId/" + userId, List.class);
    if (bikes.isEmpty()) {
      result.put("Bikes", "Ese user no tiene motos");
    } else {
      result.put("Bikes", bikes);
    }
    return result;
  }

}
