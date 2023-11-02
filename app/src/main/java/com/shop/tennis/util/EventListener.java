package com.shop.tennis.util;

public interface EventListener<T> {
  public void delete(T item);
  public void add(T item);
}
