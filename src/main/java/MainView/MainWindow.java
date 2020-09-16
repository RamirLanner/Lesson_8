package MainView;

import TicTacToe.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
    public static final int SIZE_MAP = 5;
    private static final int WIN_STACK = 4;
    private static final JPanel panelCommand = new JPanel();
    private static final JPanel panelMap = new JPanel();
    private static final JLabel label = new JLabel();
    private static final JButton[] jbs = new JButton[SIZE_MAP * SIZE_MAP];
    private static TicTacToe gameBoard;
    private static boolean GAME_OVER = false;

    public MainWindow() {
        gameBoard = new TicTacToe(SIZE_MAP,WIN_STACK);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setResizable(false);

        GridLayout gridLayout = new GridLayout(5, 1);
        GridLayout gridLayoutMap = new GridLayout(SIZE_MAP, SIZE_MAP);
        panelCommand.setLayout(gridLayout);
        panelMap.setLayout(gridLayoutMap);
        panelCommand.add(label, 0);
        add(panelCommand, BorderLayout.SOUTH);
        add(panelMap, BorderLayout.CENTER);

        label.setText("Game Start");
        mapInit(gameBoard);//инициализация кнопок

        setVisible(true);
    }

    public static void mapInit(TicTacToe gameBoard) {
        for (int i = 0; i < jbs.length; i++) {
            jbs[i] = new JButton("");
            jbs[i].setAction(new ActionButtons(i));
            panelMap.add(jbs[i]);
        }
        gameBoard.initGameMap();
        printGameMap(gameBoard.getGameMap());
    }

    private static void printGameMap(char[][] sybs) {
        for (int i = 0; i < sybs.length; i++) {
            for (int j = 0; j < sybs[i].length; j++) {
                jbs[i * sybs.length + j].setText(String.valueOf(sybs[i][j]));
                if (sybs[i][j] == 'X') {
                    jbs[i * sybs.length + j].setBackground(Color.green);
                } else if (sybs[i][j] == 'O') {
                    jbs[i * sybs.length + j].setBackground(Color.orange);
                } else {
                    jbs[i * sybs.length + j].setBackground(Color.white);
                }
            }
        }
    }

    protected static class ActionButtons extends AbstractAction {

        private final int position;

        public ActionButtons(int position) {
            this.position = position;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (GAME_OVER) return;
            //System.out.println(gameBoard.humanTurn(position));
            if (gameBoard.humanTurn(position)) {
                printGameMap(gameBoard.getGameMap());
                if (gameBoard.checkWinHuman()) {
                    GAME_OVER = true;
                    label.setText("Победил человек");
                    return;
                }
                if (gameBoard.isMapFull()) {
                    label.setText("Ничья. Игра закончена.");
                    GAME_OVER = true;
                    return;
                }
                label.setText("Ход АИ");
                gameBoard.aiTurn();
                printGameMap(gameBoard.getGameMap());
                if (gameBoard.checkWinAI()) {
                    GAME_OVER = true;
                    label.setText("Победил Искуственный Интеллект");
                    return;
                }
                if (gameBoard.isMapFull()) {
                    label.setText("Ничья. Игра закончена.");
                    GAME_OVER = true;
                    return;
                }
            }
            label.setText("Сделайте ход в свободную клетку");
        }

    }
}


