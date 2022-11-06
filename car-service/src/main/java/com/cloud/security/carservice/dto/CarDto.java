package com.cloud.security.carservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

  private Integer id;

  private String brand;

  private String model;

  private Integer userId;
}
