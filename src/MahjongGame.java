import javax.swing.*;

public class MahjongGame extends JFrame {
    private MahjongBoard board;

    public MahjongGame() {
        setTitle("Mahjong Solitaire");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        board = new MahjongBoard();
        add(board);
        setVisible(true);
    }
}
