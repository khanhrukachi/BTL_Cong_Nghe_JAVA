package library.management;

import javax.swing.*;

import library.management.com.example.icons.IconSet;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateGUI extends JFrame {
	private JFrame frame;
    private JTextField textNewUserName, textEmail;
    private JLabel newUserName, createPassword, confirmPassword, email, messageCreate;
    private JPasswordField  textCreatePassword, textConfirmPassword;
    private JCheckBox showPassCheckboxCreate;
    private JButton createAccountButton;
    Connection conn = null;
    public CreateGUI() {
    	frame = new JFrame("Create Account");
    	frame.getContentPane().setBackground(new Color(90, 220, 255));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(IconSet.getHomeIcon(46, 46).getImage());
        frame.setSize(640, 480);
	    frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        // Đặt layout cho frame
        frame.getContentPane().setLayout(null);
        
        // Tạo đường dẫn của tệp hình ảnh
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = IconSet.getLogin(getWidth(), getHeight());
                icon.paintIcon(this, g, 0, 0);
            }
        };
        panel.setBounds(0, 0, 340, 443);
        
     // Tạo các component mới cho chức năng Create
        textNewUserName = new JTextField();
        textNewUserName.setBounds(377, 80, 216, 20);
        textNewUserName.setColumns(10);
        textNewUserName.setBorder(null); // Xóa viền
        textNewUserName.setBackground(new Color(199, 255, 255)); // Đặt màu nền

        newUserName = new JLabel("New user name: ");
        newUserName.setBounds(377, 60, 116, 14);

        textCreatePassword = new JPasswordField();
        textCreatePassword.setBounds(377, 130, 216, 20);
        textCreatePassword.setColumns(10);
        textCreatePassword.setBorder(null); // Xóa viền
        textCreatePassword.setBackground(new Color(199, 255, 255)); // Đặt màu nền

        createPassword = new JLabel("Create password: ");
        createPassword.setBounds(377, 110, 116, 14);

        textConfirmPassword = new JPasswordField();
        textConfirmPassword.setBounds(377, 180, 216, 20);
        textConfirmPassword.setColumns(10);
        textConfirmPassword.setBorder(null); // Xóa viền
        textConfirmPassword.setBackground(new Color(199, 255, 255)); // Đặt màu nền

        confirmPassword = new JLabel("Confirm password: ");
        confirmPassword.setBounds(377, 160, 130, 14);
        
        showPassCheckboxCreate = new JCheckBox("Show password");
        showPassCheckboxCreate.setBounds(377, 210, 150, 23);
        showPassCheckboxCreate.setBorder(null); // Xóa viền
        showPassCheckboxCreate.setOpaque(false);
        showPassCheckboxCreate.setFocusPainted(false);
        
        textEmail = new JTextField();
        textEmail.setBounds(377, 260, 216, 20);
        textEmail.setColumns(10);
        textEmail.setBorder(null); // Xóa viền
        textEmail.setBackground(new Color(199, 255, 255)); // Đặt màu nền
        textEmail.setText("rukachi@gmail.com"); // Thêm text mặc định

        // Đặt màu chữ cho text
        textEmail.setForeground(Color.GRAY);

        textEmail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Xóa text mặc định khi người dùng ấn vào trường
                if (textEmail.getText().equals("rukachi@gmail.com")) {
                    textEmail.setText("");
                    // Đặt màu chữ lại khi người dùng bắt đầu nhập
                    textEmail.setForeground(Color.BLACK);
                }
            }
        });

        textEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                // Thêm lại text mặc định nếu trường không có giá trị và không nhận focus
                if (textEmail.getText().isEmpty() && !textEmail.hasFocus()) {
                    textEmail.setText("rukachi@gmail.com");
                    // Đặt màu chữ lại khi trường không có giá trị
                    textEmail.setForeground(Color.GRAY);
                }
            }
        });

        email = new JLabel("Email: ");
        email.setBounds(377, 240, 93, 14);

        createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(377, 290, 216, 23);
        createAccountButton.setBackground(new Color(76, 175, 80));
        createAccountButton.setForeground(Color.WHITE);
        createAccountButton.setBorder(null);
        createAccountButton.setFocusPainted(false);
        createAccountButton.setFont(new Font("Arial", Font.BOLD, 14));

        messageCreate = new JLabel();
        messageCreate.setBounds(377, 320, 216, 23);
        
        JButton backButton = new JButton("Quay lại");
        backButton.setBounds(377, 385, 216, 23);
        backButton.setBackground(new Color(76, 175, 80));
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(null);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Đặt layout cho frame
        frame.getContentPane().add(panel);
        frame.getContentPane().add(textNewUserName);
        frame.getContentPane().add(newUserName);
        frame.getContentPane().add(textCreatePassword);
        frame.getContentPane().add(createPassword);
        frame.getContentPane().add(textConfirmPassword);
        frame.getContentPane().add(confirmPassword);
        frame.getContentPane().add(showPassCheckboxCreate);
        frame.getContentPane().add(textEmail);
        frame.getContentPane().add(email);
        frame.getContentPane().add(createAccountButton);
        frame.getContentPane().add(messageCreate);
        frame.getContentPane().add(backButton);
        
        showPassCheckboxCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JCheckBox cb = (JCheckBox) e.getSource();
                if (cb.isSelected()) {
                    textCreatePassword.setEchoChar((char) 0);
                    textConfirmPassword.setEchoChar((char) 0);
                } else {
                    textCreatePassword.setEchoChar('*');
                    textConfirmPassword.setEchoChar('*');
                }
            }
        });
       
        // Thêm chức năng xác nhận tạo tài khoản
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Lấy thông tin từ các field
                String newUserName = textNewUserName.getText();
                String createPassword = new String(textCreatePassword.getPassword());
                String confirmPassword = new String(textConfirmPassword.getPassword());
                String email = textEmail.getText();

                // Kiểm tra mật khẩu nhập lại
                if (!createPassword.equals(confirmPassword)) {
                    messageCreate.setText("<html><b><font color='red'>Password doesn't match!</font></b></html>");
                    return;
                }
                if (newUserName.equals("")) {
                	messageCreate.setText("<html><b><font color='red'>UserName doesn't match!</font></b></html>");
                	return;
                }
                
                if (email.equals("")) {
                	messageCreate.setText("<html><b><font color='red'>Email cannot be blank!</font></b></html>");
                    return;
                } else if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                	messageCreate.setText("<html><b><font color='red'>Enter incorrect email format!</font></b></html>");
                    return;
                }
                if (createAccount(newUserName, createPassword, email)) {
                    messageCreate.setText("<html><b><font color='red'>Account created successfully!</font></b></html>");
                } else {
                    messageCreate.setText("<html><b><font color='red'>Failed to create account!</font></b></html>");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new LoginGUI(); // Mở giao diện LoginGUI
                        frame.dispose(); // Đóng giao diện hiện tại
                    }
                });
            }
        });
        
        // Hiển thị JFrame
        frame.setVisible(true);
    }
    public boolean createAccount(String userName, String password, String email) {
        try {
            conn = ConnectDB.getConnection();
            String query = "INSERT INTO dangnhap (taiKhoan, matKhau, email) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, password);
            statement.setString(3, email);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}