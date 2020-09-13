package MainView;

import TicTacToe.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    public static int SIZE_MAP = TicTacToe.SIZE;
    public static String userCommand = "Game Start";
    private static String response;
    private static JPanel panelCommand = new JPanel();
    private static JPanel panelMap = new JPanel();
    private static JLabel label = new JLabel();
    private static JButton[] jbs = new JButton[SIZE_MAP * SIZE_MAP];

    public MainWindow() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);

        GridLayout gridLayout = new GridLayout(5, 1);
        GridLayout gridLayoutMap = new GridLayout(SIZE_MAP, SIZE_MAP);
        panelCommand.setLayout(gridLayout);
        panelMap.setLayout(gridLayoutMap);

        label.setText(userCommand);
        panelCommand.add(label, 0);

        add(panelCommand, BorderLayout.SOUTH);
        add(panelMap, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void mapInit() {
        for (int i = 0; i < jbs.length; i++) {
            jbs[i] = new JButton("");
            jbs[i].setActionCommand(String.valueOf(i));
            panelMap.add(jbs[i]);
        }
    }

    public static void printGameMap (char[][] sybs) {
        for (int i = 0; i < sybs.length; i++) {
            for (int j = 0; j < sybs[i].length; j++) {
                jbs[i * sybs.length + j].setText(String.valueOf(sybs[i][j]));
                if(sybs[i][j]=='X'){
                    jbs[i * sybs.length + j].setBackground(Color.green);
                }else if(sybs[i][j]=='O'){
                    jbs[i * sybs.length + j].setBackground(Color.orange);
                }else {
                    jbs[i * sybs.length + j].setBackground(Color.white);
                }
            }
        }
    }

    public static String buttonClickListener() {
        for (int i = 0; i < SIZE_MAP; i++) {
            for (int j = 0; j < SIZE_MAP; j++) {
                jbs[i * SIZE_MAP + j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        response = e.getActionCommand();
                    }
                });
            }
        }
        return response;
    }

    public static void outToCommand(String command){
        label.setText(command);
    }


}
