package com.shop.tennis.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.shop.tennis.Errors.BadMethod;
import com.shop.tennis.entity.Brands;
import com.shop.tennis.entity.Tennis;
import com.shop.tennis.service.ICarService;
import com.shop.tennis.service.IStorageService;
import com.shop.tennis.view.components.StoreTable;

public class StorePanel extends BaseLayout {
  private static StorePanel INSTANCE;
  private final ICarService carService;
  private final IStorageService storageService;
  private StoreTable tableModel;

  private StorePanel(IStorageService storageService, ICarService carService) {
    this.carService = carService;
    this.storageService = storageService;

    this.add(getTitleText(), BorderLayout.NORTH);
    this.add(buildTable(), BorderLayout.CENTER);
    this.add(getFilterPanel(), BorderLayout.SOUTH);
  }

  private Component getFilterPanel() {
    var res = new JPanel();
    res.setLayout(new BoxLayout(res, BoxLayout.X_AXIS));

    var filterPanel = new JPanel();
    filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));

    var nameSearchPanel = new JPanel();
    var nameSearchLabel = new JLabel("Name: ", JLabel.LEFT);
    var textBox = new JTextArea();

    nameSearchPanel.setLayout(new BoxLayout(nameSearchPanel, BoxLayout.X_AXIS));
    nameSearchPanel.add(nameSearchLabel);
    nameSearchPanel.add(textBox);

    var brandSearchPanel = new JPanel();
    var brandSearchLabel = new JLabel("Brand: ", JLabel.LEFT);
    var brandsSelector = new JComboBox<String>();

    brandsSelector.addItem("NONE");
    for (var b : Brands.values()) {
      brandsSelector.addItem(b.name());
    }
    brandSearchPanel.setLayout(new BoxLayout(brandSearchPanel, BoxLayout.X_AXIS));
    brandSearchPanel.add(brandSearchLabel);
    brandSearchPanel.add(brandsSelector);

    filterPanel.add(nameSearchPanel);
    filterPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    filterPanel.add(brandSearchPanel);

    var buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

    var clearBtn = new JButton("Clear");
    clearBtn.addActionListener(a -> {
      tableModel.updateContent(storageService.getProducts());
    });

    var fillBtn = getjButton(brandsSelector, textBox);

    buttonsPanel.add(fillBtn);
    buttonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    buttonsPanel.add(clearBtn);

    res.add(filterPanel);
    res.add(Box.createRigidArea(new Dimension(10, 0)));
    res.add(buttonsPanel);
    return res;
  }

  private JButton getjButton(JComboBox<String> brandsSelector, JTextArea textBox) {
    var fillBtn = new JButton(" Fill ");
    fillBtn.addActionListener(a -> {
      var tennis = new Tennis();
      var brand = brandsSelector.getSelectedItem();

      try {
        assert brand != null;
        tennis.setBrand(Brands.valueOf(brand.toString()));
      } catch (IllegalArgumentException ignored) {}

      try {
        var name = textBox.getText();

        tennis.setName(name);
      } catch (NullPointerException ignored) {}

      tableModel.updateContent(storageService.fill(tennis));
    });
    return fillBtn;
  }

  private JScrollPane buildTable() {
    var table = new JTable();
    this.tableModel = new StoreTable(table, carService, storageService);

    table.setModel(this.tableModel);
    return new JScrollPane(table);
  }

  private JLabel getTitleText() {
    var title = new JLabel("Products", JLabel.CENTER);
    title.setFont(new Font("Sans", Font.BOLD, 20));

    return title;
  }

  public static StorePanel getInstance(IStorageService storage, ICarService car) {
    if (StorePanel.INSTANCE != null) {
      return StorePanel.INSTANCE;
    }

    StorePanel.INSTANCE = new StorePanel(storage, car);
    return StorePanel.INSTANCE;
  }

  public static StorePanel getInstance() throws BadMethod {
    if (StorePanel.INSTANCE != null) {
      return StorePanel.INSTANCE;
    }

    throw new BadMethod("StorePanel.getInstance(IStorageService,ICarService)");
  }
}
