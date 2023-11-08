package com.shop.tennis.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class CarIndex {
  @Getter@Setter
  private Tennis shoes;
  @Getter
  private Integer quantity = 0;
  @Getter
  private BigDecimal totalPrice;
  
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
    this.totalPrice = new BigDecimal(this.quantity).multiply(this.shoes.getPrice());
  }
}
