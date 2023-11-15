package com.shop.tennis.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.shop.tennis.Main;
import com.shop.tennis.entity.Tennis;
import com.shop.tennis.service.ICarService;


public class DeleteDialog extends JDialog {
  private final ICarService carService;
  private Tennis shoes;

  public  DeleteDialog(ICarService carService, Tennis toDelete) {
    super(Main.getApp(), "Romeve shoes from the car", true);
    this.carService = carService;
    this.shoes = toDelete;

    this.setLayout(new BorderLayout());
    this.setLocationRelativeTo(null);

    this.setSize(300, 100);

    var message = new JLabel("Do you want to delete this form the car?");
    this.add(message, BorderLayout.CENTER);

    var buttons = new JPanel();
    buttons.setLayout(new FlowLayout());

    buttons.add(getCancel());
    buttons.add(getRemove());

    this.add(buttons, BorderLayout.SOUTH);
  }

  private JButton getCancel() {
    var res = new JButton("Cancel ");

    res.setBackground(Color.GREEN);
    res.setForeground(Color.BLACK);

    res.addActionListener(e -> {
      this.dispose();
    });

    return  res;
  }

  private JButton getRemove() {
    var res = new JButton("Delete ");

    res.setBackground(Color.RED);
    res.setForeground(Color.WHITE);

    res.addActionListener(e -> {
      this.carService.removeFromCar(shoes);
      this.dispose();
    });
    return res;
  }
}
