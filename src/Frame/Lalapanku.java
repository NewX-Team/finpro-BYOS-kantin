package Frame;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class Lalapanku extends javax.swing.JFrame {

    private static final Color BG_TOP = new Color(28, 30, 35);
    private static final Color BG_BOTTOM = new Color(18, 20, 25);
    private static final Color ACCENT = new Color(100, 200, 80);
    private static final Color ACCENT_HOVER = new Color(130, 220, 110);
    private static final Color CARD_BG = new Color(40, 42, 48);
    private static final Color INPUT_BG = new Color(50, 52, 58);
    private static final Color INPUT_BORDER = new Color(70, 72, 78);
    private static final Color TEXT_WHITE = new Color(230, 230, 230);
    private static final Color TEXT_GRAY = new Color(150, 150, 160);
    private static final Color TEXT_PLACEHOLDER = new Color(100, 100, 110);
    private static final Color BTN_VIEW = new Color(60, 120, 200);
    private static final Color BTN_VIEW_HOVER = new Color(80, 145, 220);
    private static final Color BTN_BACK = new Color(100, 100, 110);
    private static final Color BTN_BACK_HOVER = new Color(130, 130, 140);

    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font INPUT_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 13);

    private JPanel mainPanel;
    private JTextField nameField;
    private JComboBox<String> foodCombo;
    private JComboBox<String> timeCombo;
    private float opacity = 0f;
    private Timer fadeTimer;

    public Lalapanku() {
        setTitle("Lalapanku • Nasi Ayam");
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

                g2d.setColor(new Color(255, 255, 255, 2));
                for (int i = 0; i < getWidth(); i += 25) {
                    for (int j = 0; j < getHeight(); j += 25) {
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
        mainPanel.setPreferredSize(new Dimension(400, 520));
        mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        JLabel iconLabel = new JLabel("🍗");
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 45));
        iconLabel.setBounds(175, 15, 50, 55);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel titleLabel = new JLabel("Lalapanku");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_WHITE);
        titleLabel.setBounds(0, 68, 400, 35);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitleLabel = new JLabel("Nasi Ayam • Indonesian Comfort Food");
        subtitleLabel.setFont(SUBTITLE_FONT);
        subtitleLabel.setForeground(TEXT_GRAY);
        subtitleLabel.setBounds(0, 100, 400, 18);
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(255, 255, 255, 20));
        sep.setBounds(50, 130, 300, 1);

        JPanel formCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(CARD_BG);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
                g2d.setColor(new Color(255, 255, 255, 8));
                g2d.setStroke(new BasicStroke(1f));
                g2d.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 20, 20));
            }
        };
        formCard.setLayout(null);
        formCard.setOpaque(false);
        formCard.setBounds(30, 145, 340, 280);

        JLabel nameLabel = new JLabel("👤  Nama");
        nameLabel.setFont(LABEL_FONT);
        nameLabel.setForeground(TEXT_WHITE);
        nameLabel.setBounds(20, 20, 100, 20);

        nameField = new JTextField();
        nameField.setFont(INPUT_FONT);
        nameField.setForeground(TEXT_WHITE);
        nameField.setBackground(INPUT_BG);
        nameField.setCaretColor(ACCENT);
        nameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(INPUT_BORDER, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        nameField.setBounds(20, 45, 300, 40);
        nameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (nameField.getText().equals("Ketik nama kamu...")) {
                    nameField.setText("");
                    nameField.setForeground(TEXT_WHITE);
                }
            }
            public void focusLost(FocusEvent e) {
                if (nameField.getText().isEmpty()) {
                    nameField.setText("Ketik nama kamu...");
                    nameField.setForeground(TEXT_PLACEHOLDER);
                }
            }
        });
        nameField.setText("Ketik nama kamu...");
        nameField.setForeground(TEXT_PLACEHOLDER);

        JLabel menuLabel = new JLabel("🍽️  Pilih Menu");
        menuLabel.setFont(LABEL_FONT);
        menuLabel.setForeground(TEXT_WHITE);
        menuLabel.setBounds(20, 100, 120, 20);

        foodCombo = new JComboBox<>(new String[] {
            "🍗 Nasi Ayam Bakar",
            "🍗 Nasi Ayam Kremes",
            "🥬 Nasi Sayur"
        });
        styleComboBox(foodCombo);
        foodCombo.setBounds(20, 125, 300, 38);

        JLabel timeLabel = new JLabel("🕐  Waktu Ambil");
        timeLabel.setFont(LABEL_FONT);
        timeLabel.setForeground(TEXT_WHITE);
        timeLabel.setBounds(20, 178, 120, 20);

        timeCombo = new JComboBox<>(new String[] {
            "07:00", "09:00", "11:00", "13:00", "15:00", "17:00", "19:00"
        });
        styleComboBox(timeCombo);
        timeCombo.setBounds(20, 203, 300, 38);

        formCard.add(nameLabel);
        formCard.add(nameField);
        formCard.add(menuLabel);
        formCard.add(foodCombo);
        formCard.add(timeLabel);
        formCard.add(timeCombo);

        RoundedButton viewButton = new RoundedButton("👁  Lihat Antrian");
        viewButton.setFont(BUTTON_FONT);
        viewButton.setBackground(BTN_VIEW);
        viewButton.setForeground(Color.WHITE);
        viewButton.setHoverColor(BTN_VIEW_HOVER);
        viewButton.setBounds(30, 445, 160, 38);
        viewButton.addActionListener(e -> {
            new read("lalapanku").setVisible(true);
        });

        RoundedButton orderButton = new RoundedButton("✅ Pesan Sekarang");
        orderButton.setFont(BUTTON_FONT);
        orderButton.setBackground(ACCENT);
        orderButton.setForeground(Color.WHITE);
        orderButton.setHoverColor(ACCENT_HOVER);
        orderButton.setBounds(210, 445, 160, 38);
        orderButton.addActionListener(e -> placeOrder());

        RoundedButton backButton = new RoundedButton("↩ Kembali");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        backButton.setBackground(BTN_BACK);
        backButton.setForeground(Color.WHITE);
        backButton.setHoverColor(BTN_BACK_HOVER);
        backButton.setBounds(140, 495, 120, 30);
        backButton.addActionListener(e -> {
            new Main().setVisible(true);
            this.dispose();
        });

        mainPanel.add(iconLabel);
        mainPanel.add(titleLabel);
        mainPanel.add(subtitleLabel);
        mainPanel.add(sep);
        mainPanel.add(formCard);
        mainPanel.add(viewButton);
        mainPanel.add(orderButton);
        mainPanel.add(backButton);

        setContentPane(mainPanel);
        pack();
    }

    private void styleComboBox(JComboBox<String> combo) {
        combo.setFont(INPUT_FONT);
        combo.setForeground(TEXT_WHITE);
        combo.setBackground(INPUT_BG);
        combo.setFocusable(false);

        combo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrow = new JButton("▼");
                arrow.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                arrow.setForeground(ACCENT);
                arrow.setBackground(INPUT_BG);
                arrow.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                arrow.setFocusPainted(false);
                return arrow;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
            }
        });

        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
                label.setFont(INPUT_FONT);
                label.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
                if (isSelected) {
                    label.setBackground(ACCENT);
                    label.setForeground(Color.WHITE);
                } else {
                    label.setBackground(INPUT_BG);
                    label.setForeground(TEXT_WHITE);
                }
                return label;
            }
        });
    }

    private void placeOrder() {
        String nama = nameField.getText().trim();

        if (nama.isEmpty() || nama.equals("Ketik nama kamu...")) {
            CustomDialog.showError(this,
                "⚠️  Nama Tidak Boleh Kosong!",
                "Silakan isi nama kamu terlebih dahulu sebelum memesan.");
            nameField.requestFocus();
            return;
        }

        String makanan = foodCombo.getSelectedItem().toString();
        String waktu = timeCombo.getSelectedItem().toString();

        write write = new write();
        write.write("lalapanku", nama, makanan, waktu);

        CustomDialog.showOrderSuccess(this, nama, makanan, waktu);

        nameField.setText("Ketik nama kamu...");
        nameField.setForeground(TEXT_PLACEHOLDER);
        foodCombo.setSelectedIndex(0);
        timeCombo.setSelectedIndex(0);
    }

    private void startFadeIn() {
        fadeTimer = new Timer(18, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.05f;
                if (opacity >= 1f) {
                    opacity = 1f;
                    fadeTimer.stop();
                }
                mainPanel.repaint();
            }
        });
        fadeTimer.start();
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
            g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

            super.paintComponent(g);
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ex) {}

        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        EventQueue.invokeLater(() -> new Lalapanku().setVisible(true));
    }
}