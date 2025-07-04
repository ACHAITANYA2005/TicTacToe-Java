import javax.swing.JButton;

public class Board {
    private JButton[][] buttons;

    public Board() {
        buttons = new JButton[3][3];
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                buttons[row][col] = new JButton("");
    }

    public JButton getButton(int row, int col) {
        return buttons[row][col];
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public void resetBoard() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                buttons[row][col].setText("");
    }
}
