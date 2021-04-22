import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Babyproof extends JFrame {
  private static final long serialVersionUID = 1L;

  public static void main(final String args[]) {
    new Babyproof().setVisible(true);
  }

  private static final String PASSWORD = "babyproof";
  private static final char PASSWORD_HEAD = PASSWORD.charAt(0);
  private final StringBuilder input = new StringBuilder(PASSWORD.length());

  public Babyproof() {
    super("Babyproof");
    setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setUndecorated(true);
    setBackground(new Color(0x00000000, true));

    final MessageLabel label = new MessageLabel();
    add(label);
    final Timer timer = new Timer(20, label);
    timer.start();

    addKeyListener(new KeyListener() {
      public void keyPressed(final KeyEvent e) {
      }
      public void keyReleased(final KeyEvent e) {
      }
      public void keyTyped(final KeyEvent e) {
        final char c = e.getKeyChar();
        input.append(c);
        if (input.length() >= PASSWORD.length() && input.toString().startsWith(PASSWORD)) {
          System.exit(0);
        }
        if (!PASSWORD.startsWith(input.toString())) {
          input.setLength(0);
          input.append(c);
          if (c != PASSWORD_HEAD) {
            label.restart();
          }
        }
      }
    });
  }

  private static final class MessageLabel extends JLabel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private MessageLabel() {
      super("Type `" + PASSWORD + "' to exit.");
      setFont(getFont().deriveFont(Font.BOLD, 32.0f));
      setForeground(new Color(0x00000000, true));
      setBackground(new Color(0x00000000, true));
      setHorizontalAlignment(JLabel.LEFT);
      setVerticalAlignment(JLabel.TOP);
    }

    private int mode = 0;
    private int alpha = 0;

    public void restart() {
      if (mode == 0) {
        this.mode = 1;
      }
    }

    public void actionPerformed(final ActionEvent e) {
      switch (mode) {
      case 1:
        alpha += 4;
        if (alpha >= 0x100) {
          mode = -1;
        } else {
          setForeground(new Color(0xff, 0xff, 0xff, alpha));
        }
        break;
      case -1:
        alpha -= 4;
        if (alpha > 0) {
          setForeground(new Color(0xff, 0xff, 0xff, alpha));
        } else {
          setForeground(new Color(0xff, 0xff, 0xff, 0));
          mode = 0;
        }
        break;
      default:
      }
    }
  }
}