import javax.swing.*;
import java.awt.*;

public class TicTacToeGUI {
    private JFrame frame;
    private char currentPlayer;
    private int xWins = 0;
    private int oWins = 0;
    private int draws = 0;
    private JLabel scoreLabel;
    private Board board;

    public TicTacToeGUI() {
        frame = new JFrame("Tic Tac Toe");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentPlayer = 'X';
        board = new Board();

        frame.setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = board.getButton(row, col);
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                int r = row, c = col;
                button.addActionListener(e -> handleClick(r, c));
                gridPanel.add(button);
            }
        }

        frame.add(gridPanel, BorderLayout.CENTER);

        scoreLabel = new JLabel("X Wins: 0 | O Wins: 0 | Draws: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        frame.add(scoreLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void handleClick(int row, int col) {
        if (board.getButton(row, col).getText().equals("")) {
            board.getButton(row, col).setText(String.valueOf(currentPlayer));
            if (checkWin()) {
                JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                if (currentPlayer == 'X') xWins++;
                else oWins++;
                updateScore();
                board.resetBoard();
                currentPlayer = 'X';
            } else if (isDraw()) {
                JOptionPane.showMessageDialog(frame, "It's a draw!");
                draws++;
                updateScore();
                board.resetBoard();
                currentPlayer = 'X';
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkWin() {
        // rows
        for (int row = 0; row < 3; row++) {
            if (board.getButton(row, 0).getText().equals(String.valueOf(currentPlayer)) &&
                board.getButton(row, 1).getText().equals(String.valueOf(currentPlayer)) &&
                board.getButton(row, 2).getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        // columns
        for (int col = 0; col < 3; col++) {
            if (board.getButton(0, col).getText().equals(String.valueOf(currentPlayer)) &&
                board.getButton(1, col).getText().equals(String.valueOf(currentPlayer)) &&
                board.getButton(2, col).getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        // diagonals
        if (board.getButton(0, 0).getText().equals(String.valueOf(currentPlayer)) &&
            board.getButton(1, 1).getText().equals(String.valueOf(currentPlayer)) &&
            board.getButton(2, 2).getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        if (board.getButton(0, 2).getText().equals(String.valueOf(currentPlayer)) &&
            board.getButton(1, 1).getText().equals(String.valueOf(currentPlayer)) &&
            board.getButton(2, 0).getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        return false;
    }

    private boolean isDraw() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (board.getButton(row, col).getText().equals(""))
                    return false;
        return true;
    }

    private void updateScore() {
        scoreLabel.setText("X Wins: " + xWins + " | O Wins: " + oWins + " | Draws: " + draws);
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
