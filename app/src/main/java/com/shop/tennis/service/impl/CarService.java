package com.shop.tennis.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.shop.tennis.entity.CarIndex;
import com.shop.tennis.entity.Tennis;
import com.shop.tennis.service.ICarService;
import com.shop.tennis.util.EventListener;

public class CarService implements ICarService {
  private Map<String, CarIndex> content;
  private List<EventListener<CarIndex>> subcribers;

  public CarService() {
    this.subcribers = new LinkedList<EventListener<CarIndex>>();
    this.content = new HashMap<>();
  }

  @Override
  public List<CarIndex> getCarContent() {
    return content.values().stream().toList();
  }

  @Override
  public void addToCar(Integer quantity, Tennis tennis) {
    var idName = tennis.getBrand().name() + "-" + tennis.getName();
    CarIndex item;

    if (content.containsKey(idName)) {
      item = content.get(idName); 
    } else {
      item = new CarIndex();
      item.setShoes(tennis);
    }

    item.setQuantity(item.getQuantity() + quantity);
    content.put(idName, item);

    for (var eventListener : subcribers) {
      eventListener.add(item);
    }
  }

  @Override
  public void removeFromCar(Tennis tennis) {
    var idName = tennis.getBrand().name() + "-" + tennis.getName();
    var item = content.get(idName);

    if (idName != null) {
      for (var eventListener : subcribers) {
        eventListener.delete(item);
      }

      content.remove(idName);
    }

  }

  @Override
  public void subscribe(EventListener<CarIndex> observer) {
    this.subcribers.add(observer);
  }
}
