import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class MahjongBoard extends JPanel {
    private static final int ROWS = 5;
    private static final int COLS = 8;
    private MahjongTile[][] tiles;
    private MahjongTile selectedTile = null;

    public MahjongBoard() {
        setBackground(new Color(50, 100, 50));
        setLayout(new GridLayout(ROWS, COLS, 5, 5));

        initializeTiles();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    private void initializeTiles() {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < (ROWS * COLS) / 2; i++) {
            values.add(i);
            values.add(i); // Each tile has a pair
        }
        Collections.shuffle(values);

        tiles = new MahjongTile[ROWS][COLS];
        removeAll(); // Clear the board

        int index = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                tiles[row][col] = new MahjongTile(values.get(index++));
                add(tiles[row][col]);
            }
        }
        revalidate();
        repaint();
    }

    private void handleMouseClick(MouseEvent e) {
        Component clicked = getComponentAt(e.getPoint());
        if (clicked instanceof MahjongTile) {
            MahjongTile tile = (MahjongTile) clicked;

            if (!tile.isRemoved()) {
                if (selectedTile == null) {
                    selectedTile = tile;
                    tile.setSelected(true);
                } else if (selectedTile == tile) {
                    selectedTile.setSelected(false);
                    selectedTile = null;
                } else if (selectedTile.getValue() == tile.getValue()) {
                    selectedTile.removeTile();
                    tile.removeTile();
                    selectedTile = null;
                    checkWinCondition();
                } else {
                    selectedTile.setSelected(false);
                    selectedTile = tile;
                    tile.setSelected(true);
                }
            }
        }
    }

    private void checkWinCondition() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (!tiles[row][col].isRemoved()) {
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Félicitations ! Vous avez gagné !");
        initializeTiles(); // Restart the game
    }
}
