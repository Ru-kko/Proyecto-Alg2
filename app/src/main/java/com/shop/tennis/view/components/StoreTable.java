package com.shop.tennis.view.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.shop.tennis.entity.Tennis;
import com.shop.tennis.service.ICarService;
import com.shop.tennis.service.IStorageService;

public class StoreTable extends AbstractTableModel {
  private final Integer columns = 3;
  private List<Tennis> content;

  public StoreTable(JTable table, ICarService carService, IStorageService storageService) {
    this.content = storageService.getProducts();

    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent me) {
        JTable target = (JTable) me.getSource();
        int row = target.getSelectedRow();

        var dialog = new BuyDialog(carService, content.get(row));
        dialog.setVisible(true);
      }
    });
  }

  public void updateConetnt(List<Tennis> content) {
    this.content = content;
    this.fireTableDataChanged();
  }

  @Override
  public int getRowCount() {
    return this.content.size();
  }

  @Override
  public int getColumnCount() {
    return this.columns;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Tennis shoes = this.content.get(rowIndex);

    switch (columnIndex) {
      case 0:
        return shoes.getName();
      case 1:
        return shoes.getBrand().name();
      case 2:
        return shoes.getPrice().toString();
      default:
        return null;
    }
  }

  @Override
  public String getColumnName(int column) {
    switch (column) {
      case 0:
        return "Model";
      case 1:
        return "Brand";
      case 2:
        return "Price";
      default:
        return null;
    }
  }
}
