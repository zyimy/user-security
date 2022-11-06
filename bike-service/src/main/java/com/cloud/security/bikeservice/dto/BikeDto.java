package com.cloud.security.bikeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BikeDto {

  private Integer id;

  private String brand;

  private String model;

  private Integer userId;
}
