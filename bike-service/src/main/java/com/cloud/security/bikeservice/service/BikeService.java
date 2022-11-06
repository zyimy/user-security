package com.cloud.security.bikeservice.service;


import com.cloud.security.bikeservice.entity.Bike;
import com.cloud.security.bikeservice.exception.BikeException;
import com.cloud.security.bikeservice.exception.ErrorCode;
import com.cloud.security.bikeservice.repository.BikeRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BikeService {

  private final BikeRepository bikeRepository;

  public BikeService(final BikeRepository bikeRepository) {
    this.bikeRepository = bikeRepository;
  }

  public List<Bike> getListAll() {
    List<Bike> listUser = bikeRepository.findAll();
    log.info("Buscando una lista de bikes {} ", listUser);
    if (listUser.isEmpty()) {
      log.error("No existe bikes para este user");
    } else {
      return listUser;
    }
    return null;
  }

  public Bike getBikeById(Integer id) {
    Optional<Bike> user = bikeRepository.findById(id);
    log.info("Obteniendo un BIKE por su ID {} ", user);
    if (user.isEmpty()) {
      throw new BikeException(ErrorCode.BIKE_NOT_FOUND);
    }
    return user.get();
  }

  public Bike save(Bike bike) {
    log.info("Persistence BIKES {} ", bike);
    return bikeRepository.save(bike);
  }

  public List<Bike>getListBikeById(Integer idUser){
    List<Bike>listUser = bikeRepository.findByUserId(idUser);
    log.info("Buscando una lista de BIKES para userId ");
    if (listUser.isEmpty()){
      log.error("No existen BIKES para este usuario {} ",idUser);
    }
      return listUser;
  }

}
