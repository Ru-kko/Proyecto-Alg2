package com.shop.tennis.service;

import java.util.List;

import com.shop.tennis.entity.CarIndex;
import com.shop.tennis.entity.Tennis;
import com.shop.tennis.util.EventListener;

public interface ICarService {
  public List<CarIndex> getCarContent();
  public void addToCar(Integer quantity, Tennis tennis);
  public void removeFromCar(Tennis tennis);
  public void subcribe(EventListener<CarIndex> observer);
}
