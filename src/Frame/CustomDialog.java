package Frame;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class CustomDialog extends JDialog {

    
    public enum DialogType {
        ERROR, SUCCESS, INFO, WARNING
    }

    
    private static final Color BG_DARK = new Color(35, 35, 42);
    private static final Color BG_CARD = new Color(45, 45, 55);
    private static final Color TEXT_WHITE = new Color(235, 235, 240);
    private static final Color TEXT_GRAY = new Color(160, 160, 170);

    private static final Color ERROR_COLOR = new Color(255, 75, 75);
    private static final Color ERROR_BG = new Color(255, 75, 75, 20);
    private static final Color SUCCESS_COLOR = new Color(80, 220, 100);
    private static final Color SUCCESS_BG = new Color(80, 220, 100, 20);
    private static final Color INFO_COLOR = new Color(70, 150, 240);
    private static final Color INFO_BG = new Color(70, 150, 240, 20);
    private static final Color WARNING_COLOR = new Color(255, 190, 60);
    private static final Color WARNING_BG = new Color(255, 190, 60, 20);

    
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 15);
    private static final Font MESSAGE_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font DETAIL_FONT = new Font("Segoe UI", Font.PLAIN, 12);

    private float opacity = 0f;
    private Timer fadeTimer;
    private Timer shakeTimer;
    private JPanel dialogPanel;
    private int shakeCount = 0;
    private Point originalLocation;

    
    public static void showError(JFrame parent, String title, String message) {
        CustomDialog dialog = new CustomDialog(parent, DialogType.ERROR, title, message, null);
        dialog.showDialog();
    }

    
    public static void showSuccess(JFrame parent, String title, String message) {
        CustomDialog dialog = new CustomDialog(parent, DialogType.SUCCESS, title, message, null);
        dialog.showDialog();
    }

    
    public static void showOrderSuccess(JFrame parent, String nama, String makanan, String waktu) {
        String title = "✅ Pesanan Berhasil!";
        String message = "Pesanan kamu sudah tercatat di antrian.";
        String details = "👤 " + nama + "\n🍽️ " + makanan + "\n🕐 " + waktu;
        CustomDialog dialog = new CustomDialog(parent, DialogType.SUCCESS, title, message, details);
        dialog.showDialog();
    }

    
    public static void showInfo(JFrame parent, String title, String message) {
        CustomDialog dialog = new CustomDialog(parent, DialogType.INFO, title, message, null);
        dialog.showDialog();
    }

    
    public static void showWarning(JFrame parent, String title, String message) {
        CustomDialog dialog = new CustomDialog(parent, DialogType.WARNING, title, message, null);
        dialog.showDialog();
    }

    
    private CustomDialog(JFrame parent, DialogType type, String title, String message, String details) {
        super(parent, true);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        initComponents(type, title, message, details);

        pack();
        setLocationRelativeTo(parent);
        originalLocation = getLocation();
    }

    private void initComponents(DialogType type, String title, String message, String details) {

        
        Color accentColor;
        Color accentBg;
        String icon;
        String buttonText;

        switch (type) {
            case ERROR:
                accentColor = ERROR_COLOR;
                accentBg = ERROR_BG;
                icon = "⚠️";
                buttonText = "OK, Mengerti";
                break;
            case SUCCESS:
                accentColor = SUCCESS_COLOR;
                accentBg = SUCCESS_BG;
                icon = "✅";
                buttonText = "OK, Sip!";
                break;
            case WARNING:
                accentColor = WARNING_COLOR;
                accentBg = WARNING_BG;
                icon = "⚠️";
                buttonText = "OK, Paham";
                break;
            default: 
                accentColor = INFO_COLOR;
                accentBg = INFO_BG;
                icon = "ℹ️";
                buttonText = "OK";
                break;
        }

        
        dialogPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                
                g2d.setColor(new Color(0, 0, 0, 80));
                g2d.fill(new RoundRectangle2D.Double(3, 5, getWidth() - 6, getHeight() - 6, 25, 25));

                
                g2d.setColor(BG_CARD);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));

                
                g2d.setColor(accentColor);
                g2d.setStroke(new BasicStroke(2f));
                g2d.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, 25, 25));

                
                if (opacity < 1f) {
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                    g2d.setColor(BG_CARD);
                    g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));
                }
            }
        };
        dialogPanel.setLayout(null);
        dialogPanel.setOpaque(false);

        int dialogWidth = details != null ? 380 : 340;
        int dialogHeight = details != null ? 210 : 160;
        dialogPanel.setPreferredSize(new Dimension(dialogWidth, dialogHeight));

        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        iconLabel.setBounds(25, 25, 50, 50);

        
        JPanel iconCircle = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(accentBg);
                g2d.fillOval(0, 0, getWidth(), getHeight());
            }
        };
        iconCircle.setOpaque(false);
        iconCircle.setBounds(20, 20, 55, 55);
        dialogPanel.add(iconCircle);
        dialogPanel.add(iconLabel);

        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_WHITE);
        titleLabel.setBounds(90, 20, dialogWidth - 110, 22);

        
        JLabel messageLabel = new JLabel("<html><body style='width: " + (dialogWidth - 120) + "px'>"
            + message + "</body></html>");
        messageLabel.setFont(MESSAGE_FONT);
        messageLabel.setForeground(TEXT_GRAY);
        messageLabel.setBounds(90, 45, dialogWidth - 120, 40);

        
        if (details != null) {
            JPanel detailCard = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(BG_DARK);
                    g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 12, 12));
                }
            };
            detailCard.setLayout(null);
            detailCard.setOpaque(false);
            detailCard.setBounds(25, 95, dialogWidth - 50, 55);

            JLabel detailLabel = new JLabel("<html>" + details.replace("\n", "<br>") + "</html>");
            detailLabel.setFont(DETAIL_FONT);
            detailLabel.setForeground(TEXT_WHITE);
            detailLabel.setBounds(15, 8, dialogWidth - 80, 40);
            detailCard.add(detailLabel);
            dialogPanel.add(detailCard);
        }

        
        int buttonY = details != null ? 165 : 110;
        RoundedButton okButton = new RoundedButton(buttonText);
        okButton.setFont(BUTTON_FONT);
        okButton.setBackground(accentColor);
        okButton.setForeground(Color.WHITE);
        okButton.setHoverColor(accentColor.brighter());
        okButton.setBounds((dialogWidth - 160) / 2, buttonY, 160, 35);
        okButton.addActionListener(e -> closeDialog());

        
        dialogPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "close");
        dialogPanel.getActionMap().put("close", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeDialog();
            }
        });
        dialogPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "close");
        dialogPanel.getActionMap().put("close", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeDialog();
            }
        });

        
        dialogPanel.add(titleLabel);
        dialogPanel.add(messageLabel);
        dialogPanel.add(okButton);

        setContentPane(dialogPanel);

        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                if (!dialogPanel.getBounds().contains(point)) {
                    closeDialog();
                }
            }
        });
    }

    
    private void showDialog() {
        // Fade in
        opacity = 0f;
        fadeTimer = new Timer(15, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.06f;
                if (opacity >= 1f) {
                    opacity = 1f;
                    fadeTimer.stop();
                }
                dialogPanel.repaint();
            }
        });
        fadeTimer.start();

        setVisible(true);
    }

    
    private void closeDialog() {
        Timer fadeOut = new Timer(12, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= 0.08f;
                if (opacity <= 0f) {
                    opacity = 0f;
                    ((Timer) e.getSource()).stop();
                    dispose();
                }
                dialogPanel.repaint();
            }
        });
        fadeOut.start();
    }

    
    private static class RoundedButton extends JButton {
        private Color hoverColor;
        private Color originalBg;
        private boolean hovered = false;

        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setHorizontalTextPosition(SwingConstants.CENTER);

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    hovered = true;
                    repaint();
                }
                public void mouseExited(MouseEvent e) {
                    hovered = false;
                    repaint();
                }
                public void mousePressed(MouseEvent e) {
                    setBackground(getBackground().darker());
                    repaint();
                }
                public void mouseReleased(MouseEvent e) {
                    setBackground(hovered ? hoverColor : originalBg);
                    repaint();
                }
            });
        }

        public void setHoverColor(Color c) {
            this.hoverColor = c;
        }

        @Override
        public void setBackground(Color bg) {
            if (originalBg == null) originalBg = bg;
            super.setBackground(bg);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(hovered && hoverColor != null ? hoverColor : getBackground());
            g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 18, 18));
            super.paintComponent(g);
        }
    }

    
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(400, 300);
        testFrame.setLocationRelativeTo(null);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setVisible(true);

        
        Timer t1 = new Timer(500, e -> showError(testFrame, "Nama Kosong!", "Silakan isi nama kamu terlebih dahulu ya!"));
        t1.setRepeats(false);
        t1.start();
    }
}