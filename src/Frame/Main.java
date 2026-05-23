package Frame;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main extends javax.swing.JFrame {

    
    private static final Color BG_TOP = new Color(30, 30, 35);
    private static final Color BG_BOTTOM = new Color(20, 20, 25);
    private static final Color ACCENT = new Color(255, 160, 60);
    private static final Color CARD_BG = new Color(45, 45, 52);
    private static final Color CARD_HOVER = new Color(60, 60, 68);
    private static final Color TEXT_WHITE = new Color(230, 230, 230);
    private static final Color TEXT_GRAY = new Color(160, 160, 170);
    private static final Color EXIT_RED = new Color(220, 70, 70);
    private static final Color EXIT_HOVER = new Color(240, 90, 90);

    
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 30);
    private static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font CARD_TITLE_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font CARD_DESC_FONT = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);

    private Timer fadeTimer;
    private float opacity = 0f;
    private JPanel mainPanel;

    public Main() {
        setTitle("Kantin Binus • Food Court");
        setUndecorated(false);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        startFadeIn();
    }

    private void initComponents() {

        
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                
                GradientPaint gradient = new GradientPaint(
                    0, 0, BG_TOP,
                    0, getHeight(), BG_BOTTOM
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                
                g2d.setColor(new Color(255, 255, 255, 3));
                for (int i = 0; i < getWidth(); i += 30) {
                    for (int j = 0; j < getHeight(); j += 30) {
                        g2d.fillRect(i, j, 1, 1);
                    }
                }

                
                if (opacity < 1f) {
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                    g2d.setColor(BG_BOTTOM);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(440, 560));
        mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        
        JLabel iconLabel = new JLabel("🍜");
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 50));
        iconLabel.setBounds(190, 20, 60, 60);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        
        JLabel titleLabel = new JLabel("Kantin Binus");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_WHITE);
        titleLabel.setBounds(0, 85, 440, 40);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitleLabel = new JLabel("Food Court • Pilih Tenant Favoritmu");
        subtitleLabel.setFont(SUBTITLE_FONT);
        subtitleLabel.setForeground(TEXT_GRAY);
        subtitleLabel.setBounds(0, 125, 440, 20);
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(255, 255, 255, 30));
        sep.setBounds(60, 158, 320, 1);

        
        TenantCard dmasCard = new TenantCard(
            "🏪  DMas",
            "Rice Bowl — Ayam Katsu & Teriyaki",
            "⭐ 4.8  •  📍 Lantai 1  •  🕐 07:00-19:00"
        );
        dmasCard.setBounds(35, 175, 370, 68);

        TenantCard lalapankuCard = new TenantCard(
            "🍗  Lalapanku",
            "Nasi Ayam Bakar, Kremes & Sayur",
            "⭐ 4.6  •  📍 Lantai 1  •  🕐 07:00-19:00"
        );
        lalapankuCard.setBounds(35, 255, 370, 68);

        TenantCard baksoCard = new TenantCard(
            "🍲  Bakso Urat Mending",
            "Bakso, Mie Ayam & Mie Campur",
            "⭐ 4.9  •  📍 Lantai 2  •  🕐 07:00-19:00"
        );
        baksoCard.setBounds(35, 335, 370, 68);

        
        dmasCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { openTenant("DMas"); }
        });
        lalapankuCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { openTenant("Lalapanku"); }
        });
        baksoCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { openTenant("Bakso Urat Mending"); }
        });

        
        RoundedButton exitButton = new RoundedButton("Keluar Aplikasi");
        exitButton.setBounds(110, 440, 220, 42);
        exitButton.setFont(BUTTON_FONT);
        exitButton.setBackground(EXIT_RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.setHoverColor(EXIT_HOVER);
        exitButton.addActionListener(e -> {
            Timer fadeOut = new Timer(15, new AbstractAction() {
                float alpha = 1f;
                @Override
                public void actionPerformed(ActionEvent evt) {
                    alpha -= 0.05f;
                    if (alpha <= 0f) {
                        ((Timer) evt.getSource()).stop();
                        System.exit(0);
                    }
                    setOpacity(alpha);
                }
            });
            fadeOut.start();
        });

        
        mainPanel.add(iconLabel);
        mainPanel.add(titleLabel);
        mainPanel.add(subtitleLabel);
        mainPanel.add(sep);
        mainPanel.add(dmasCard);
        mainPanel.add(lalapankuCard);
        mainPanel.add(baksoCard);
        mainPanel.add(exitButton);

        setContentPane(mainPanel);
        pack();
    }

    
    private void startFadeIn() {
        fadeTimer = new Timer(20, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.04f;
                if (opacity >= 1f) {
                    opacity = 1f;
                    fadeTimer.stop();
                }
                mainPanel.repaint();
            }
        });
        fadeTimer.start();
    }

    private void openTenant(String choice) {
        this.dispose();
        switch (choice) {
            case "DMas":
                new Dmas().setVisible(true);
                break;
            case "Lalapanku":
                new Lalapanku().setVisible(true);
                break;
            case "Bakso Urat Mending":
                new BaksoUratMending().setVisible(true);
                break;
        }
    }

    
    private static class TenantCard extends JPanel {
        private boolean hovered = false;
        private Color currentBg = CARD_BG;

        public TenantCard(String title, String desc, String info) {
            setLayout(null);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(CARD_TITLE_FONT);
            titleLabel.setForeground(TEXT_WHITE);
            titleLabel.setBounds(15, 8, 340, 22);

            JLabel descLabel = new JLabel(desc);
            descLabel.setFont(CARD_DESC_FONT);
            descLabel.setForeground(TEXT_GRAY);
            descLabel.setBounds(15, 30, 340, 16);

            JLabel infoLabel = new JLabel(info);
            infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            infoLabel.setForeground(new Color(150, 150, 160));
            infoLabel.setBounds(15, 48, 340, 14);

            add(titleLabel);
            add(descLabel);
            add(infoLabel);

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    hovered = true;
                    currentBg = CARD_HOVER;
                    setBounds(getX(), getY() - 2, getWidth(), getHeight());
                    repaint();
                }
                public void mouseExited(MouseEvent e) {
                    hovered = false;
                    currentBg = CARD_BG;
                    setBounds(getX(), getY() + 2, getWidth(), getHeight());
                    repaint();
                }
                public void mousePressed(MouseEvent e) {
                    currentBg = new Color(80, 80, 90);
                    repaint();
                }
                public void mouseReleased(MouseEvent e) {
                    currentBg = hovered ? CARD_HOVER : CARD_BG;
                    repaint();
                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(370, 68);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

           
            g2d.setColor(new Color(0, 0, 0, 40));
            g2d.fill(new RoundRectangle2D.Double(2, 4, getWidth() - 4, getHeight() - 4, 15, 15));

           
            g2d.setColor(currentBg);
            g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));

           
            g2d.setColor(ACCENT);
            g2d.fill(new RoundRectangle2D.Double(0, 0, 4, getHeight(), 4, 4));

           
            g2d.setColor(new Color(255, 255, 255, 15));
            g2d.setStroke(new BasicStroke(1f));
            g2d.draw(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        }
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

            Color bg = hovered && hoverColor != null ? hoverColor : getBackground();
            g2d.setColor(bg);
            g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));

            super.paintComponent(g);
        }
    }

   
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ex) {}

        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        EventQueue.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}