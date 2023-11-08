package com.shop.tennis;

import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JFrame;

import com.shop.tennis.service.ICarService;
import com.shop.tennis.service.IStorageService;
import com.shop.tennis.service.impl.CarService;
import com.shop.tennis.service.impl.TennisShopService;
import com.shop.tennis.view.StorePanel;
import com.shop.tennis.view.UserPanel;

public class Main extends JFrame {
    private static final Main App = new Main();

    private Main() {
        this.setTitle("Tennis Shop");
        
        this.setSize(500, 500);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);

        this.setLayout(new GridLayout(1, 2));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static Frame getApp() {
        return Main.App;
    }

    public static void main(String[] args) {
        IStorageService store = new TennisShopService();
        ICarService car = new CarService();

        Main.App.add(StorePanel.getInstance(store, car));
        Main.App.add(UserPanel.getInstance(car));

        Main.App.setVisible(true);
    }
}