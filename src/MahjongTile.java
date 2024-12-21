import javax.swing.*;
import java.awt.*;

public class MahjongTile extends JLabel {
    private int value;
    private boolean removed = false;

    public MahjongTile(int value) {
        this.value = value;
        setPreferredSize(new Dimension(80, 80));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true);
        setBackground(new Color(200, 200, 200));
        setFont(new Font("Arial", Font.BOLD, 20));
        updateText();
    }

    public int getValue() {
        return value;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void removeTile() {
        removed = true;
        setText("");
        setBackground(new Color(50, 100, 50));
        setBorder(null);
    }

    public void setSelected(boolean selected) {
        if (!removed) {
            setBackground(selected ? new Color(255, 200, 0) : new Color(200, 200, 200));
        }
    }

    private void updateText() {
        setText(String.valueOf(value));
    }
}
