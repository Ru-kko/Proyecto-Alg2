package com.shop.tennis.view.components;

import com.shop.tennis.entity.CarIndex;
import com.shop.tennis.service.ICarService;
import com.shop.tennis.util.EventListener;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarTable extends AbstractTableModel implements EventListener<CarIndex> {
  private Map<String, CarIndex> content;
  
  private List<CarIndex> entryList;
  private final ICarService carService;
  
  public CarTable(ICarService carService, JTable table) {
    this.carService = carService;
    this.content = new HashMap<>();
    this.entryList = this.carService.getCarContent();
    this.carService.subscribe(this);
    
    for (var i : entryList) {
      var idName = i.getShoes().getBrand().name() + "-" + i.getShoes().getName();
      this.content.put(idName, i);
    }

    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        int row = target.getSelectedRow();
        var dialog = new DeleteDialog(carService, entryList.get(row).getShoes());

        dialog.setVisible(true);
        // TODO delete a item
      }
    });
  }
  
  @Override
  public int getRowCount() {
    return this.content.size();
  }
  
  @Override
  public int getColumnCount() {
    return 5;
  }
  
  @Override
  public Object getValueAt(int rowIndex, int columIndex) {
    var item = entryList.get(rowIndex);
    
    return switch (columIndex) {
      case 0 -> item.getShoes().getName();
      case 1 -> item.getShoes().getBrand().toString();
      case 2 -> item.getShoes().getPrice();
      case 3 -> item.getQuantity();
      case 4 -> item.getTotalPrice();
      default -> "";
    };
  }
  
  @Override
  public void delete(CarIndex item) {
    var idName = item.getShoes().getBrand().name() + "-" + item.getShoes().getName();
    this.content.remove(idName);
    this.entryList = this.content.values().stream().toList();
    this.fireTableDataChanged();
  }
  
  @Override
  public void add(CarIndex item) {
    var idName = item.getShoes().getBrand().name() + "-" + item.getShoes().getName();
    this.content.put(idName, item);
    this.entryList = this.content.values().stream().toList();
    this.fireTableDataChanged();
  }

  @Override
  public String getColumnName(int column) {
    return switch (column) {
      case 0 -> "Model";
      case 1 -> "Brand";
      case 2 -> "Unit Price";
      case 3 -> "Quantity";
      case 4 -> "Total Price";
      default -> null;
    };
  }
}
