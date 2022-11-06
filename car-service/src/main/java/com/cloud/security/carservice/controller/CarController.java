package com.cloud.security.carservice.controller;

import com.cloud.security.carservice.dto.CarDto;
import com.cloud.security.carservice.entity.Car;
import com.cloud.security.carservice.mapper.CarMapper;
import com.cloud.security.carservice.service.CarService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/car")
public class CarController {

  private final CarService carService;

  private final CarMapper carMapper;

  public CarController(final CarService carService, final CarMapper carMapper) {
    this.carService = carService;
    this.carMapper = carMapper;
  }

  @GetMapping("/getList")
  public ResponseEntity<List<CarDto>> getListCar() {
    log.info("Llamando al metodo getListCar para buscar una lista de CAR");
    return ResponseEntity.ok(carMapper.toListDto(carService.getListAll()));
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<CarDto> getCarById(@PathVariable("id") Integer id) {
    log.info("Llamando al metodo getCarById para buscar un CAR por su ID");
    return ResponseEntity.ok(carMapper.toDto(carService.getCarById(id)));
  }

  @PostMapping
  public ResponseEntity<CarDto> save(@RequestBody Car car) {
    log.info("Llamando al metodo save para guardar un CAR {} ", car);
    return ResponseEntity.ok(carMapper.toDto(carService.save(car)));
  }

  @GetMapping("/byUserId/{idUser}")
  public ResponseEntity<List<CarDto>>getListCartByUserId(@PathVariable("idUser") Integer idUser){
    log.info("Llamando al metodo getListCartByUserId para buscar una lista de car para el usuario {} ", idUser);
    return ResponseEntity.ok(carMapper.toListDto(carService.getListCarById(idUser)));
  }


}
