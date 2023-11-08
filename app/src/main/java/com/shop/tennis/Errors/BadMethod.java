package com.shop.tennis.Errors;

public class BadMethod extends Exception{
  public BadMethod(String useInstead) {
    super("That method usage, use instead " + useInstead);
  }
}
