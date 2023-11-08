package com.shop.tennis.view.components;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.shop.tennis.Main;
import com.shop.tennis.entity.Tennis;
import com.shop.tennis.service.ICarService;

public class BuyDialog extends JDialog {
  private ICarService carService;
  private Tennis shoesToBuy;
  private Integer quantity = 1;

  public BuyDialog(ICarService carService, Tennis shoesToBuy) {
    super(Main.getApp(), "Buy " + shoesToBuy.getName(), true);
    this.carService = carService;
    this.shoesToBuy = shoesToBuy;

    this.setLayout(new BorderLayout());
    this.setLocationRelativeTo(null);
    this.setSize(300, 100);
    this.add(getSpinner(), BorderLayout.CENTER);

    var btns = new JPanel();
    btns.setLayout(new FlowLayout());
    btns.add(getBuyBtn());
    btns.add(getCloseBtn());

    this.add(btns, BorderLayout.SOUTH);
  }

  private JButton getCloseBtn() {
    var res = new JButton("Cancel ");

    res.setBackground(Color.RED);
    res.setForeground(Color.WHITE);

    res.addActionListener(e -> {
      this.dispose();
    });

    return  res;
  }
  private JButton getBuyBtn() {
    var res = new JButton("Buy");

    res.setBackground(Color.GREEN);

    res.addActionListener(e -> {
      this.carService.addToCar(quantity, shoesToBuy);
      this.dispose();
    });

    return res;
  }

  private JSpinner getSpinner() {
    var model = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
    var res = new JSpinner(model);

    res.addChangeListener(e -> {
      this.quantity = Integer.parseInt(res.getValue().toString());
    });
    return res;
  }
}
