package com.shop.tennis.view.components;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;

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
  private Tennis shoewsToBuy;
  private Integer quantity = 1;

  public BuyDialog(ICarService carService, Tennis shoewsToBuy) {
    super(Main.getApp(), "Buy " + shoewsToBuy.getName(), true);
    this.carService = carService;
    this.shoewsToBuy = shoewsToBuy;

    this.setLayout(new BorderLayout());
    this.setLocationRelativeTo(null);
    this.setSize(300, 100);
    this.add(getSpinner(), BorderLayout.CENTER);

    var btns = new JPanel();
    btns.setLayout(new FlowLayout());
    btns.add(getBuyBtn());
    btns.add(new Button("Btn b"));

    this.add(btns, BorderLayout.SOUTH);
  }

  private JButton getBuyBtn() {
    var res = new JButton("Buy");

    res.addActionListener(e -> {
      this.carService.addToCar(quantity, shoewsToBuy);
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
