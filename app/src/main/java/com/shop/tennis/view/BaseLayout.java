package com.shop.tennis.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class BaseLayout extends JPanel {
  private static final Integer RIGHT_LEFT_PADDING = 15;
  private static final Integer TOP_BOTTOM_PADDING = 40;

  BaseLayout() {
    var layout = new BorderLayout(5, 5);
    this.setBorder(new EmptyBorder(TOP_BOTTOM_PADDING, RIGHT_LEFT_PADDING,
        TOP_BOTTOM_PADDING, RIGHT_LEFT_PADDING));
    this.setLayout(layout);
  }
}
