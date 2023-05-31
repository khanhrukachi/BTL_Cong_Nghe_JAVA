package library.management;

import javax.swing.*;

import library.management.com.example.icons.IconSet;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginGUI extends JFrame {
    private JFrame frame;
    private JTextField textUserName;
    private JLabel userName, password, messageLogin;
    private JPasswordField textPassword;
    private JCheckBox showPassCheckboxLogin;
    private JButton loginButton, createButton;
    Connection conn = null;

    public LoginGUI() {
        frame = new JFrame("Login");
        frame.getContentPane().setBackground(new Color(90, 220, 255));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
		ImageIcon logoIcon = IconSet.getHomeIcon(40,40);
        frame.setIconImage(logoIcon.getImage());
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                 ImageIcon icon = IconSet.getLogin(getWidth(), getHeight());
                 icon.paintIcon(this, g, 0, 0);
            }
        };
        panel.setBounds(0, 0, 340, 443);

        textUserName = new JTextField();
        textUserName.setBounds(377, 133, 216, 20);
        textUserName.setColumns(10);
        textUserName.setBorder(null); // Xóa viền
        textUserName.setBackground(new Color(199, 255, 255)); // Đặt màu nền

        userName = new JLabel("User name: ");
        userName.setBounds(377, 108, 93, 14);

        textPassword = new JPasswordField(20);
        textPassword.setBounds(377, 189, 216, 20);
        textPassword.setColumns(10);
        textPassword.setBorder(null); // Xóa viền
        textPassword.setBackground(new Color(199, 255, 255)); // Đặt màu nền

        password = new JLabel("Password: ");
        password.setBounds(377, 164, 96, 14);

        loginButton = new JButton("Login");
        loginButton.setBounds(377, 246, 96, 23);
        loginButton.setBackground(new Color(76, 175, 80));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(null);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        createButton = new JButton("Create");
        createButton.setBounds(497, 246, 96, 23);
        createButton.setBackground(new Color(76, 175, 80));
        createButton.setForeground(Color.WHITE);
        createButton.setBorder(null);
        createButton.setFocusPainted(false);
        createButton.setFont(new Font("Arial", Font.BOLD, 14));

        messageLogin = new JLabel();
        messageLogin.setBounds(377, 280, 216, 23);

        showPassCheckboxLogin = new JCheckBox("Show password");
        showPassCheckboxLogin.setBounds(377, 216, 216, 23);
        showPassCheckboxLogin.setBorder(null); // Xóa viền
        showPassCheckboxLogin.setOpaque(false); // Màu trong suốt
        showPassCheckboxLogin.setFocusPainted(false);

        frame.getContentPane().add(panel);
        frame.getContentPane().add(textUserName);
        frame.getContentPane().add(userName);
        frame.getContentPane().add(textPassword);
        frame.getContentPane().add(password);
        frame.getContentPane().add(loginButton);
        frame.getContentPane().add(createButton);
        frame.getContentPane().add(messageLogin);
        frame.getContentPane().add(showPassCheckboxLogin);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String username = textUserName.getText();
                String password = new String(textPassword.getPassword());

                if (checkLogin(username, password)) {
                	MainFrame yourObject = new MainFrame();
                    yourObject.updateComboBox();
                    frame.dispose();
                } else {
                    messageLogin.setText("<html><b><font color='red'>Username or password incorrect</font></b></html>");
                }
            }
        });

        showPassCheckboxLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPassCheckboxLogin.isSelected()) {
                    textPassword.setEchoChar((char) 0);
                } else {
                    textPassword.setEchoChar('*');
                }
            }
        });

        // Handle Enter key press for login
        textPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new CreateGUI();
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    public boolean checkLogin(String username, String password) {
        conn = ConnectDB.getConnection();
        Statement stmt = null;
        String query = "SELECT * FROM dangnhap WHERE taiKhoan = ? AND matKhau = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI();
            }
        });
    }
}
