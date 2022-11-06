package com.cloud.security.bikeservice.controller;

import com.cloud.security.bikeservice.dto.BikeDto;
import com.cloud.security.bikeservice.entity.Bike;
import com.cloud.security.bikeservice.mapper.BikeMapper;
import com.cloud.security.bikeservice.service.BikeService;
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
@RequestMapping("/v1/bike")
public class BikeController {

  private final BikeService bikeService;

  private final BikeMapper bikeMapper;

  public BikeController(final BikeService bikeService,  final BikeMapper bikeMapper) {
    this.bikeService = bikeService;
    this.bikeMapper = bikeMapper;
  }

  @GetMapping("/getList")
  public ResponseEntity<List<BikeDto>> getListBike() {
    log.info("Llamando al metodo getListBike para buscar una lista de user");
    return ResponseEntity.ok(bikeMapper.toListDto(bikeService.getListAll()));
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<BikeDto> getBikeById(@PathVariable("id") Integer id) {
    log.info("Llamando al metodo getUserById para buscar un BIKE por su ID");
    return ResponseEntity.ok(bikeMapper.toDto(bikeService.getBikeById(id)));
  }

  @PostMapping
  public ResponseEntity<BikeDto> save(@RequestBody Bike bike) {
    log.info("Llamando al metodo save para guardar un BIKE {} ", bike);
    return ResponseEntity.ok(bikeMapper.toDto(bikeService.save(bike)));
  }

  @GetMapping("/byUserId/{idUser}")
  public ResponseEntity<List<BikeDto>>getListCartByUserId(@PathVariable("idUser") Integer idUser){
    log.info("Llamando al metodo getListCartByUserId para buscar una lista de BIKE para el usuario {} ", idUser);
    return ResponseEntity.ok(bikeMapper.toListDto(bikeService.getListBikeById(idUser)));
  }


}
