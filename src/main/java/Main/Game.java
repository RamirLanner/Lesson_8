package Main;

import MainView.MainWindow;
import TicTacToe.TicTacToe;

import java.awt.*;

public class Game {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mainWindow =new MainWindow();
            }
        });
    }
}
