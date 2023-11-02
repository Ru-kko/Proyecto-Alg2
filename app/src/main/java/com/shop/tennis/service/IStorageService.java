package com.shop.tennis.service;

import java.util.List;

import com.shop.tennis.entity.Tennis;

public interface IStorageService {
  public List<Tennis> getProducts();
  public List<Tennis> fill(Tennis tennis);
}
