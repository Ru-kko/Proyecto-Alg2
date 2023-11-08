package com.shop.tennis.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.shop.tennis.entity.Brands;
import com.shop.tennis.entity.Tennis;
import com.shop.tennis.service.IStorageService;

public class TennisShopService implements IStorageService {
  private static final List<Tennis> shoes = List.of(new Tennis[]{
          new Tennis("UltraBoost 21", Brands.ADIDAS, new BigDecimal("379000.00")),
          new Tennis("Air Max 270", Brands.NIKE, new BigDecimal("219000.00")),
          new Tennis("Chuck Taylor All Star", Brands.CONVERSE, new BigDecimal("97900.00")),
          new Tennis("Cali Sport", Brands.Puma, new BigDecimal("135000.00")),
          new Tennis("Old Skool", Brands.Vans, new BigDecimal("125000.00")),
          new Tennis("Superstar", Brands.ADIDAS, new BigDecimal("299000.00")),
          new Tennis("Air Force 1", Brands.NIKE, new BigDecimal("229000.00")),
          new Tennis("One Star", Brands.CONVERSE, new BigDecimal("119000.00")),
          new Tennis("RS-X3", Brands.Puma, new BigDecimal("169000.00")),
          new Tennis("Sk8-Hi", Brands.Vans, new BigDecimal("129000.00")),
          new Tennis("Gazelle", Brands.ADIDAS, new BigDecimal("259000.00")),
          new Tennis("React Infinity Run", Brands.NIKE, new BigDecimal("189000.00")),
          new Tennis("Jack Purcell", Brands.CONVERSE, new BigDecimal("89000.00")),
          new Tennis("Future Rider", Brands.Puma, new BigDecimal("125000.00")),
          new Tennis("Slip-On", Brands.Vans, new BigDecimal("110000.00")),
          new Tennis("NMD R1", Brands.ADIDAS, new BigDecimal("339000.00")),
          new Tennis("Air Zoom Pegasus", Brands.NIKE, new BigDecimal("269000.00")),
          new Tennis("Pro Leather", Brands.CONVERSE, new BigDecimal("149000.00")),
          new Tennis("Clyde Court", Brands.Puma, new BigDecimal("179000.00")),
          new Tennis("Authentic", Brands.Vans, new BigDecimal("98000.00")),
          new Tennis("Superstar Foundation", Brands.ADIDAS, new BigDecimal("239000.00")),
          new Tennis("Blazer Low", Brands.NIKE, new BigDecimal("179000.00")),
          new Tennis("All Star Hi", Brands.CONVERSE, new BigDecimal("125000.00")),
          new Tennis("California Sneaker", Brands.Puma, new BigDecimal("134000.00")),
          new Tennis("Era", Brands.Vans, new BigDecimal("119000.00")),
          new Tennis("Samba", Brands.ADIDAS, new BigDecimal("169000.00")),
          new Tennis("Air Huarache", Brands.NIKE, new BigDecimal("189000.00")),
          new Tennis("Chuck 70", Brands.CONVERSE, new BigDecimal("139000.00")),
          new Tennis("Future Rider Play", Brands.Puma, new BigDecimal("145000.00"))
  });

  @Override
  public List<Tennis> getProducts() {
    return TennisShopService.shoes;
  }

  @Override
  public List<Tennis> fill(Tennis tennis) {
    if (tennis.getName() == null && tennis.getBrand() == null) {
      return shoes;
    }

    return shoes.stream().filter((t) -> {
      boolean res;

      res = tennis.getName() == null || t.getName().toLowerCase().startsWith(tennis.getName().trim().toLowerCase());
      res = res && (tennis.getBrand() == null || tennis.getBrand() == t.getBrand());

      return res;
    }).toList();
  }

}
