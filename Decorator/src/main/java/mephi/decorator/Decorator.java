package mephi.decorator;

import gui.OrderFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author 79031
 */
public class Decorator {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OrderFrame().setVisible(true));
    }
}
