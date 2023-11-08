package com.shop.tennis.view;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.shop.tennis.Errors.BadMethod;
import com.shop.tennis.service.ICarService;
import com.shop.tennis.view.components.CarTable;

public class UserPanel extends BaseLayout {
  private static UserPanel INSTANCE;
  private final ICarService carService;
  private CarTable tableModel;

  private UserPanel(ICarService carService) {
    this.carService = carService;
    this.setLayout(new BorderLayout());

    this.add(this.buildTable(), BorderLayout.CENTER);
  }

  private JScrollPane buildTable() {
    var table = new JTable();
    this.tableModel = new CarTable(carService, table);

    table.setModel(this.tableModel);
    return new JScrollPane(table);
  }

  public static UserPanel getInstance(ICarService car) {
    if (UserPanel.INSTANCE != null) {
      return UserPanel.INSTANCE;
    }

    UserPanel.INSTANCE = new UserPanel(car);
    return UserPanel.INSTANCE;
  }

  public static UserPanel getInstance() throws BadMethod {
    if (UserPanel.INSTANCE != null) {
      return UserPanel.INSTANCE;
    }

    throw new BadMethod("StorePanel.getInstance(IStorageService,ICarService)");
  }
}
