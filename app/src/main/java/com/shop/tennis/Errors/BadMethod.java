package com.shop.tennis.Errors;

public class BadMethod extends Exception{
  public BadMethod(String useIntead) {
    super("Bafd method usage, use instead " + useIntead);
  }
}
