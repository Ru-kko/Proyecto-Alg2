package com.shop.tennis.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tennis {
  private String name;
  private Brands brand;
  private BigDecimal price;
}
