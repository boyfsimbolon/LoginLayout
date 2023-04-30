package loginlayout;
import menuframe.MenuFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import menuframe.MenuFrame;

public class LoginLayout extends JFrame {
    public LoginLayout() {
        setTitle("Halaman Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel("C:\\Users\\WINDOWS\\Downloads\\444.gif");
        panel.setLayout(new GridBagLayout());

        // Judul aplikasi
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\WINDOWS\\Downloads\\kalkutaor.png");
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel calculatorLabel = new JLabel("  KALKULATOR", scaledIcon, JLabel.CENTER);
        calculatorLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        calculatorLabel.setForeground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(10, 10, 20, 10);
        panel.add(calculatorLabel, c);

        // Input username
        JLabel labelUsername = new JLabel("Username:");
        labelUsername.setForeground(Color.WHITE);
        JTextField fieldUsername = new JTextField(20);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(labelUsername, c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 10, 10);
        panel.add(fieldUsername, c);

        // Input password
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setForeground(Color.WHITE);
        JPasswordField fieldPassword = new JPasswordField(20);
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(labelPassword, c);
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(10, 0, 10, 10);
        panel.add(fieldPassword, c);

        // Tombol show/hide password
        JToggleButton buttonShowHide = new JToggleButton("Show Password");
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(10, 0, 10, 10);
        panel.add(buttonShowHide, c);

        // Menambahkan aksi ketika tombol show/hide password diklik
        buttonShowHide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonShowHide.isSelected()) {
                    fieldPassword.setEchoChar((char) 0);
                    buttonShowHide.setText("Hide Password");
                                    } else {
                    fieldPassword.setEchoChar('*');
                    buttonShowHide.setText("Show Password");
                }
            }
        });

            // Tombol login
    JButton buttonLogin = new JButton("Login");
    c.gridx = 0;
    c.gridy = 3;
    c.gridwidth = 2;
    c.insets = new Insets(10, 10, 10, 10);
    panel.add(buttonLogin, c);

    // Menambahkan aksi ketika tombol login diklik
    buttonLogin.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = fieldUsername.getText();
            String password = new String(fieldPassword.getPassword());
            if (validateLogin(username, password)) {
                dispose(); // Menutup halaman login
                new MenuFrame();
                // Lanjutkan dengan aksi setelah login berhasil
            } else {
                JOptionPane.showMessageDialog(LoginLayout.this, "Username atau password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    // Menambahkan panel login ke panel utama
    getContentPane().add(panel, BorderLayout.CENTER);

    pack();
    setLocationRelativeTo(null);
    setVisible(true);
}

boolean validateLogin(String username, String password) {
    return username.equals("BoySimbolon") && password.equals("2281040");
}

public static void main(String[] args) {
    new LoginLayout();
}
}

class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(String imagePath) {
    try {
        backgroundImage = ImageIO.read(new File(imagePath));
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (backgroundImage != null) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

@Override
public Dimension getPreferredSize() {
    if (backgroundImage != null) {
        return new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this));
    }
    return super.getPreferredSize();
}

}

               
