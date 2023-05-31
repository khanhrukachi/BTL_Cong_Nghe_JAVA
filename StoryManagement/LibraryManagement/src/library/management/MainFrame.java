package library.management;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;
import library.management.com.example.icons.IconSet;
public class MainFrame extends JFrame{
	
	StoryModify storyModify = new StoryModify();
	ReaderModify readerModify = new ReaderModify();
	LoanModify loanModify = new LoanModify();
	PunishmentModify punishmentModify = new PunishmentModify();
	StatisticModify statisticModify = new StatisticModify();
	private Container cont;
	private DefaultTableModel tblModel;
	private JTextField tfSearchStory, tfStoryName, tfPageNo, tfLanguage, tfPrice, tfAmount, tfAuthor, 
						tfPublisher, tfReaderName, tfIdentityCard, tfPhoneNumber, tfSurname, tfReaderId,
						tfStoryId, tfSearchLoan, tfDay, tfMonth, tfYear, tfSearchReader, tfDateTime,tfChapterNo;
	
	private JButton btnSearchStory, btnAddStory, btnResetStory, btnUpdateStory, btnDeleteStory,
						btnCheckReaderId, btnCheckStoryId, btnLoanStory, btnReturnStory, btnSearchLoan,
						btnResetLoan, btnPunish, btnAddReader, btnUpdateReader, btnDeleteReader, btnResetReader, btnSearchReader,btnAddCategory,btnDeleteCategory;
	private JLabel lblStoryName, lblPageNo, lblPrice, lblAmount, lblPublishYear,
					lblType, lblAuthor, lblPublisher, lblLanguage,lblChapterNo, lblReaderName, lblIdentityCard, 
					lblPhoneNumber, lblSurname, lblReaderId, lblStoryId, lblReturnAppointmentDate, 
					lblOutputReader, lblOutputStory, lblStatisticTotalStory, lblStatisticLoan, lblStatisticPunish, lblStatisticTotalLoan;
	private JScrollPane scrollPane;
	private JComboBox<String> cbbFindBy, cbbPublishYear, cbbSort, cbbType;
	private JTable table;
	private JPanel pnlStoryManagement, pnlReaderManagement, pnlLoan, pnlStatistical; 
	private JTabbedPane tabbedPane;
	private JDateChooser dc;
	private DateFormat df;
	private static final String filePath = "genres.txt";
	
	
	public MainFrame() {
		cont = this.getContentPane();
		cont.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 680);
		ImageIcon logoIcon = IconSet.getHomeIcon(40,40);
        setIconImage(logoIcon.getImage());
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
        
		//scrollpane
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 321, 1246, 315);

		//table
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setBackground(new Color(220, 247, 255));
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		cont.add(scrollPane);
		getContentPane().setBackground(new Color(119, 187, 255));
		
		//tabbedpane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 1246, 301);
		tabbedPane.setBackground(new Color(199, 199, 255));
		tabbedPane.setBorder(null);
		findAllStory();
		tabbedPane.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				switch(tabbedPane.getSelectedIndex())
				{
					case 0: 
						findAllStory();
						break;
					case 1:
						findAllReader();
						break;
					case 2:
						findAllLoan();
						break;
					case 3:
						tblModel = new DefaultTableModel();
						table.setModel(tblModel);
						getStatistic();
						break;
				}
			}
		});
		
		// picture
		ImageIcon pikachuIcon = IconSet.getPikachu(1246, 301);
		JLabel pikachuLabel = new JLabel(pikachuIcon);
		pikachuLabel.setBounds(10, -10, 1246, 301);
		
		ImageIcon pikachu1Icon = IconSet.getPikachu1(1246, 301);
		JLabel pikachu1Label = new JLabel(pikachu1Icon);
		pikachu1Label.setBounds(10, -10, 1246, 301);
		
		ImageIcon pikachu2Icon = IconSet.getPikachu2(1246, 301);
		JLabel pikachu2Label = new JLabel(pikachu2Icon);
		pikachu2Label.setBounds(10, 0, 1246, 281);
		
		ImageIcon pikachu3Icon = IconSet.getPikachu2(1246, 301);
		JLabel pikachu3Label = new JLabel(pikachu3Icon);
		pikachu3Label.setBounds(10, 0, 1246, 281);
				
		//quan ly doc gia
		pnlReaderManagement = new JPanel();
		pnlReaderManagement.setBounds(9, 40, 1166, 270);
		pnlReaderManagement.setLayout(null);
		pnlReaderManagement.setBackground(new Color(199, 199, 255));
		pnlReaderManagement.add(pikachu3Label);
		
		lblReaderName = new JLabel("Tên:");
		lblIdentityCard = new JLabel("CMND:");
		lblPhoneNumber = new JLabel("Số điện thoại:");
		lblSurname = new JLabel("Họ đệm: ");
		
		tfIdentityCard = new JTextField();
		tfReaderName = new JTextField();
		tfPhoneNumber = new JTextField();
		tfSurname = new JTextField();
		tfSearchReader = new JTextField();

		btnAddReader = new JButton("Thêm độc giả");
		btnSearchReader = new JButton("Tìm kiếm");
		
		btnUpdateReader = new JButton("Cập nhật");
		btnUpdateReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				btnUpdateReaderActionPerformed(evt);
			}
		});
		
		btnDeleteReader = new JButton("Xóa độc giả");
		btnDeleteReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnDeleteReaderActionPerformed(evt);
			}
		});
		
		btnResetReader = new JButton("Nhập lại");
		btnResetReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnResetReaderActionPerformed(evt);
			}
		});
		
		lblReaderName.setBounds(64, 55, 80, 26);
		lblIdentityCard.setBounds(64, 88, 80, 26);
		lblPhoneNumber.setBounds(64, 124, 90, 29);
		lblSurname.setBounds(64, 19, 80, 26);
		lblReaderName.setBorder(null);
        lblIdentityCard.setBorder(null);
        lblPhoneNumber.setBorder(null);
        lblSurname.setBorder(null);

		tfReaderName.setBounds(170, 55, 246, 26);
		tfIdentityCard.setBounds(170, 91, 246, 26);
		tfPhoneNumber.setBounds(170, 127, 246, 26);
		tfSearchReader.setBounds(545, 233, 500, 31);
		tfSurname.setBounds(170, 19, 246, 26);
		tfReaderName.setBorder(null);
        tfIdentityCard.setBorder(null);
        tfPhoneNumber.setBorder(null);
        tfSearchReader.setBorder(null);
        tfSurname.setBorder(null);
		
		tfIdentityCard.setBackground(new Color(199, 255, 255));
		tfReaderName.setBackground(new Color(199, 255, 255));
		tfPhoneNumber.setBackground(new Color(199, 255, 255));
		tfSurname.setBackground(new Color(199, 255, 255));
		tfSearchReader.setBackground(new Color(199, 255, 255));
		
		btnAddReader.setBounds(64, 188, 153, 26);
		btnAddReader.setBackground(new Color(76, 175, 80));
		btnAddReader.setForeground(Color.WHITE);
		btnAddReader.setBorder(null);
		btnAddReader.setFocusPainted(false);
		btnAddReader.setFont(new Font("Arial", Font.BOLD, 14));

		btnUpdateReader.setBounds(263, 188, 153, 26);
		btnUpdateReader.setBackground(new Color(76, 175, 80));
		btnUpdateReader.setForeground(Color.WHITE);
		btnUpdateReader.setBorder(null);
		btnUpdateReader.setFocusPainted(false);
		btnUpdateReader.setFont(new Font("Arial", Font.BOLD, 14));

		btnDeleteReader.setBounds(64, 237, 153, 26);
		btnDeleteReader.setBackground(new Color(76, 175, 80));
		btnDeleteReader.setForeground(Color.WHITE);
		btnDeleteReader.setBorder(null);
		btnDeleteReader.setFocusPainted(false);
		btnDeleteReader.setFont(new Font("Arial", Font.BOLD, 14));

		btnResetReader.setBounds(263, 237, 153, 26);
		btnResetReader.setBackground(new Color(76, 175, 80));
		btnResetReader.setForeground(Color.WHITE);
		btnResetReader.setBorder(null);
		btnResetReader.setFocusPainted(false);
		btnResetReader.setFont(new Font("Arial", Font.BOLD, 14));
		
		btnSearchReader.setBounds(1050, 233, 100, 31);
		btnSearchReader.setBackground(new Color(76, 175, 80));
		btnSearchReader.setForeground(Color.WHITE);
		btnSearchReader.setBorder(null);
		btnSearchReader.setFocusPainted(false);
		btnSearchReader.setFont(new Font("Arial", Font.BOLD, 14));
		
		new ButtonGroup();
		
		//quan li truyen
		pnlStoryManagement = new JPanel();
		pnlStoryManagement.setBounds(10, 41, 1166, 270);
		pnlStoryManagement.setLayout(null);
		pnlStoryManagement.setBackground(new Color(199, 199, 255));
		pnlStoryManagement.add(pikachu1Label);
		
				lblStoryName = new JLabel("Tên truyện:");
				lblPageNo = new JLabel("Số trang:");
				lblLanguage = new JLabel("Ngôn ngữ:");
				lblPrice = new JLabel("Giá trị:");
				lblAmount = new JLabel("Số lượng:");
				lblPublishYear = new JLabel("Năm xuất bản:");
				lblType = new JLabel("Thể loại:");
				lblAuthor = new JLabel("Tác giả:");
				lblPublisher = new JLabel("Nhà xuất bản:");
				lblChapterNo = new JLabel("Số chương");
				
					tfStoryName = new JTextField(null);
					tfPageNo = new JTextField(null);
					tfLanguage = new JTextField(null);
					tfPrice = new JTextField();
					tfPrice.setText("0");
					tfAmount = new JTextField(null);
					tfSearchStory= new JTextField();
					tfChapterNo = new JTextField((String) null);
					
							cbbPublishYear = new JComboBox<String>();
							tfChapterNo = new JTextField(null);
							cbbType = new JComboBox<String>();
							cbbSort = new JComboBox<String>();
							cbbSort.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									sortAZPageNo();
									
								}
							});
							cbbFindBy = new JComboBox<String>();

							tfAuthor = new JTextField(null);
							tfPublisher = new JTextField(null);
							
							btnAddStory= new JButton("Thêm truyện");
							btnDeleteStory= new JButton("Xóa truyện");
							btnUpdateStory= new JButton("Cập nhật");
							btnResetStory= new JButton("Nhập lại");
							btnSearchStory= new JButton("Tìm kiếm");
							btnAddCategory = new JButton("Thêm thể loại");
							btnDeleteCategory = new JButton("Xóa thể loại");
							
							lblStoryName.setBounds(10, 17, 73, 26);
							lblPageNo.setBounds(10, 53, 73, 26);
							lblLanguage.setBounds(10, 89, 73, 26);
							lblPrice.setBounds(10, 125, 81, 26);
							lblAmount.setBounds(10, 161, 73, 19);
							lblPublishYear.setBounds(269, 57, 95, 19);
							lblType.setBounds(269, 86, 81, 26);
							lblAuthor.setBounds(269, 125, 86, 26);
							lblPublisher.setBounds(269, 155, 86, 26);
							lblChapterNo.setBounds(269, 17, 73, 26);
							
							tfStoryName.setBounds(90, 21, 123, 19);
							tfStoryName.setBackground(new Color(199, 255, 255));
							tfStoryName.setBorder(null);

							tfPageNo.setBounds(90, 57, 123, 19);
							tfPageNo.setBackground(new Color(199, 255, 255));
							tfPageNo.setBorder(null);

							tfLanguage.setBounds(90, 93, 123, 19);
							tfLanguage.setBackground(new Color(199, 255, 255));
							tfLanguage.setBorder(null);

							tfPrice.setBounds(90, 129, 123, 19);
							tfPrice.setBackground(new Color(199, 255, 255));
							tfPrice.setBorder(null);

							tfAmount.setBounds(90, 161, 123, 19);
							tfAmount.setBackground(new Color(199, 255, 255));
							tfAmount.setBorder(null);

							tfChapterNo.setBounds(370, 21, 123, 19);
							tfChapterNo.setBorder(null);
							
							tfChapterNo.setBounds(370, 21, 123, 19);
							tfChapterNo.setBackground(new Color(199, 255, 255));
							tfChapterNo.setBorder(null);

							tfAuthor.setBounds(370, 125, 123, 19);
							tfAuthor.setBackground(new Color(199, 255, 255));
							tfAuthor.setBorder(null);

							tfPublisher.setBounds(370, 161, 123, 19);
							tfPublisher.setBackground(new Color(199, 255, 255));
							tfPublisher.setBorder(null);

							cbbPublishYear.setBounds(370, 58, 123, 20);
							cbbPublishYear.setBackground(new Color(199, 255, 255));
							cbbPublishYear.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

							cbbType.setBounds(370, 87, 123, 23);
							cbbType.setBackground(new Color(199, 255, 255));
							cbbType.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

							cbbFindBy.setBounds(923, 233, 113, 31);
							cbbFindBy.setBackground(new Color(199, 255, 255));
							cbbFindBy.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

							cbbSort.setBounds(570, 195, 234, 26);
							cbbSort.setBackground(new Color(199, 255, 255));
							cbbSort.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

							btnAddStory.setBounds(10, 195, 113, 26);
							btnAddStory.setBackground(new Color(76, 175, 80));
							btnAddStory.setForeground(Color.WHITE);
							btnAddStory.setBorder(null);
							btnAddStory.setFocusPainted(false);
							btnAddStory.setFont(new Font("Arial", Font.BOLD, 14));

							btnDeleteStory.setBounds(147, 195, 113, 26);
							btnDeleteStory.setBackground(new Color(76, 175, 80));
							btnDeleteStory.setForeground(Color.WHITE);
							btnDeleteStory.setBorder(null);
							btnDeleteStory.setFocusPainted(false);
							btnDeleteStory.setFont(new Font("Arial", Font.BOLD, 14));

							btnUpdateStory.setBounds(284, 195, 100, 26);
							btnUpdateStory.setBackground(new Color(76, 175, 80));
							btnUpdateStory.setForeground(Color.WHITE);
							btnUpdateStory.setBorder(null);
							btnUpdateStory.setFocusPainted(false);
							btnUpdateStory.setFont(new Font("Arial", Font.BOLD, 14));

							btnResetStory.setBounds(408, 195, 86, 26);
							btnResetStory.setBackground(new Color(76, 175, 80));
							btnResetStory.setForeground(Color.WHITE);
							btnResetStory.setBorder(null);
							btnResetStory.setFocusPainted(false);
							btnResetStory.setFont(new Font("Arial", Font.BOLD, 14));

							btnAddCategory.setBounds(570, 86, 123, 26);
							btnAddCategory.setBackground(new Color(76, 175, 80));
							btnAddCategory.setForeground(Color.WHITE);
							btnAddCategory.setBorder(null);
							btnAddCategory.setFocusPainted(false);
							btnAddCategory.setFont(new Font("Arial", Font.BOLD, 14));
							
							btnDeleteCategory.setBounds(570,123,123,26);
							btnDeleteCategory.setBackground(new Color(76, 175, 80));
							btnDeleteCategory.setForeground(Color.WHITE);
							btnDeleteCategory.setBorder(null);
							btnDeleteCategory.setFocusPainted(false);
							btnDeleteCategory.setFont(new Font("Arial", Font.BOLD, 14));
							
							tfSearchStory.setBounds(10, 233, 900, 31);
							tfSearchStory.setBackground(new Color(199, 255, 255));
					        tfSearchStory.setBorder(null); // Không viền
							
							btnSearchStory.setBounds(1050, 233, 100, 31);
							btnSearchStory.setBackground(new Color(76, 175, 80)); // Màu nền
						    btnSearchStory.setForeground(Color.WHITE); // Màu chữ
						    btnSearchStory.setBorder(null); // Không viền
						    btnSearchStory.setFocusPainted(false); // Loại bỏ viền focus khi nút được chọn
						    btnSearchStory.setFont(new Font("Arial", Font.BOLD, 14)); // Font chữ và cỡ chữ		
						
							cbbFindBy.setMaximumRowCount(7);
							cbbPublishYear.setMaximumRowCount(13);
							
							cbbPublishYear.setModel(new DefaultComboBoxModel<String>(new String[] {"2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011"}));
//							cbbType.setModel(new DefaultComboBoxModel(new String[] {"Lịch Sử ", "Quân Sự", "Đô Thị", "Võng Du", "Hệ Thống", "Trinh Thám ", "Cổ Đại", "Thám Hiểm", "Tiên Hiệp", "Kiếm Hiệp","+"}));
							
							cbbSort.setModel(new DefaultComboBoxModel<String>(new String[] {"Sắp xếp","Tăng dần theo Mã truyện","Giảm dần theo Mã truyện","Tên truyện A-Z","Tên truyện Z-A", "Tăng dần theo Số trang", "Giảm dần theo Số trang","Tên Ngôn ngữ A-Z","Tên Ngôn ngữ Z-A","Tăng dần theo Giá trị","Giảm dần theo Giá trị","Tăng dần theo Số lượng còn","Giảm dần theo Số lượng còn","Tăng dần theo Năm xuất bản","Giảm dần theo Năm xuất bản","Tên Thể loại A-Z","Tên Thể loại Z-A","Tên Tác giả A-Z","Tên Tác giả Z-A","Tên Nhà xuất bản A-Z","Tên Nhà xuất bản Z-A","Tăng dần theo Số chương","Giảm dần theo Số chương"}));
							cbbFindBy.setModel(new DefaultComboBoxModel<String>(new String[] {"Tất cả", "Tên truyện", "Tác giả", "Ngôn ngữ", "Năm xuất bản", "Thể loại", "NXB"}));
							
									pnlStoryManagement.add(lblStoryName);
									pnlStoryManagement.add(lblPageNo);
									pnlStoryManagement.add(lblLanguage);
									pnlStoryManagement.add(lblPrice);
									pnlStoryManagement.add(lblAmount);
									pnlStoryManagement.add(lblPublishYear);
									pnlStoryManagement.add(lblType);
									pnlStoryManagement.add(lblAuthor);
									pnlStoryManagement.add(lblPublisher);
									pnlStoryManagement.add(lblChapterNo);
									
									pnlStoryManagement.add(tfStoryName);
									pnlStoryManagement.add(tfPageNo);
									pnlStoryManagement.add(tfLanguage);		
									pnlStoryManagement.add(tfAmount);
									pnlStoryManagement.add(tfPrice);
									pnlStoryManagement.add(cbbPublishYear);
									pnlStoryManagement.add(tfAuthor);
									pnlStoryManagement.add(tfPublisher);
									pnlStoryManagement.add(tfChapterNo);
									
									pnlStoryManagement.add(btnAddStory);
									pnlStoryManagement.add(cbbType);
									pnlStoryManagement.add(btnDeleteStory);
									pnlStoryManagement.add(btnUpdateStory);
									pnlStoryManagement.add(btnAddCategory);
									pnlStoryManagement.add(btnDeleteCategory);
									
									pnlStoryManagement.add(cbbSort);
									pnlStoryManagement.add(cbbFindBy);
									pnlStoryManagement.add(tfSearchStory);
									pnlStoryManagement.add(btnSearchStory);
									pnlStoryManagement.add(btnResetStory);
									
									tabbedPane.addTab("Quản lý truyện", pnlStoryManagement);
									btnAddCategory.addActionListener(new ActionListener() {
							            @Override
							            public void actionPerformed(ActionEvent evt) {
							                String newCategory = JOptionPane.showInputDialog(null, "Nhập thể loại truyện mới:");
							                if (newCategory != null && !newCategory.isEmpty()) {
							                    // Ghi thể loại truyện mới vào tệp tin
							                    writeToFile(newCategory);

							                    // Cập nhật combobox
							                    updateComboBox();
							                }
							            }
							        });

							        // Xử lý sự kiện khi ấn nút "Xóa thể loại"
							        btnDeleteCategory.addActionListener(new ActionListener() {
							            @Override
							            public void actionPerformed(ActionEvent evt) {
							                String selectedCategory = (String) cbbType.getSelectedItem();
							                if (selectedCategory != null) {
							                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa thể loại này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
							                    if (confirm == JOptionPane.YES_OPTION) {
							                        // Xóa thể loại truyện
							                        deleteCategory(selectedCategory);

							                        // Cập nhật combobox
							                        updateComboBox();
							                    }
							                }
							            }
							        });
//									btnAddCategory.addActionListener(new ActionListener() {
//							            @Override
//							            public void actionPerformed(ActionEvent evt) {
//							                String newCategory = JOptionPane.showInputDialog(null, "Nhập thể loại truyện mới:");
//							                if (newCategory != null && !newCategory.isEmpty()) {
//							                    // Ghi thể loại truyện mới vào tệp tin
//							                    writeToFile(newCategory);
//
//							                    // Cập nhật combobox
//							                    updateComboBox();
//							                }
//							            }
//							        });
									
//									btnDeleteCategory.addActionListener(new ActionListener() {
//									    @Override
//									    public void actionPerformed(ActionEvent e) {
//									        String categoryToDelete = JOptionPane.showInputDialog(null, "Nhập thể loại cần xóa:", "Xóa thể loại", JOptionPane.QUESTION_MESSAGE);
//									        if (categoryToDelete != null && !categoryToDelete.isEmpty()) {
//									            deleteCategory(categoryToDelete);
//									        }
//									    }
//									});
//									btnDeleteCategory.addActionListener(new ActionListener() {
//									    @Override
//									    public void actionPerformed(ActionEvent evt) {
//									        String selectedGenre = cbbType.getSelectedItem().toString();
//									        
//									        int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa thể loại này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
//									        
//									        if (option == JOptionPane.YES_OPTION) {
//									            deleteGenre(selectedGenre);
//									        }
//									    }
//									});

									
									btnResetStory.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent evt) {
											
											btnResetStoryActionPerformed(evt);
										}
									});
									
									btnSearchStory.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent evt) 
										{
											btnSearchStoryActionPerformed(evt);
										}
									});
									
									btnDeleteStory.addActionListener(new ActionListener() 
									{
										@Override
										public void actionPerformed(ActionEvent evt) 
										{
											btnDeleteStoryActionPerformed(evt);
										}
									});
									
									btnUpdateStory.addActionListener(new ActionListener()
									{
										@Override
										public void actionPerformed(ActionEvent evt) 
										{
											btnUpdateStoryActionPerformed(evt);
										}
									});
									
									btnAddStory.addActionListener(new ActionListener()
									{
										@Override
										public void actionPerformed(ActionEvent evt) 
										{
											btnAddStoryActionPerformed(evt);
										}
									});
		
		pnlReaderManagement.add(lblReaderName);
		pnlReaderManagement.add(lblIdentityCard);
		pnlReaderManagement.add(lblPhoneNumber);
		
		pnlReaderManagement.add(lblSurname);
		pnlReaderManagement.add(tfReaderName);
		pnlReaderManagement.add(tfIdentityCard);
		pnlReaderManagement.add(tfPhoneNumber);
		pnlReaderManagement.add(tfSurname);
		
		pnlReaderManagement.add(tfSearchReader);
		pnlReaderManagement.add(btnAddReader);
		pnlReaderManagement.add(btnUpdateReader);
		pnlReaderManagement.add(btnDeleteReader);
		pnlReaderManagement.add(btnResetReader);
		pnlReaderManagement.add(btnSearchReader);
		
		tabbedPane.addTab("Quản lý độc giả", pnlReaderManagement);
		
		//muon tra truyen
		pnlLoan = new JPanel();
		pnlLoan.setLayout(null);
		tabbedPane.addTab("Mượn trả truyện", pnlLoan);
		pnlLoan.setBackground(new Color(199, 199, 255));
		pnlLoan.add(pikachu2Label);
		
		lblReaderId = new JLabel("Mã độc giả:");
		lblStoryId = new JLabel("Mã truyện:");
		lblReturnAppointmentDate = new JLabel("Ngày hẹn trả:");
		lblOutputReader = new JLabel();
		lblOutputStory= new JLabel();
		tfReaderId = new JTextField();
		tfStoryId = new JTextField();
		tfSearchLoan = new JTextField();
		
		tfDay = new JTextField();
		tfMonth = new JTextField();
		tfYear = new JTextField();
		tfDateTime = new JTextField();
		
		dc = new JDateChooser();
		df = new SimpleDateFormat("yyyy-MM-dd");
		
		btnCheckReaderId = new JButton("Kiểm tra");
		btnCheckReaderId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnTestReaderIdActionPerformed(evt);
				
			}
		});
		
		btnCheckStoryId = new JButton("Kiểm tra");
		btnCheckStoryId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnTestStoryIdActionPerformed(evt);
			}
		});
		
		btnLoanStory= new JButton("Mượn truyện");
		btnLoanStory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
					btnAddLoanActionPerformed(evt);
			}
		});
		
		btnReturnStory= new JButton("Trả truyện");
		btnReturnStory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnReturnStoryActionPerformed(evt);
			}
		});
		
		btnSearchLoan = new JButton("Tìm kiếm");
		btnSearchLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSearchLoanActionPerformed(evt);
			}
		});
		
		btnResetLoan = new JButton("Nhập lại");
		btnResetLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnResetLoanActionPerformed(evt);
			}
		});
		
		btnPunish = new JButton("Danh sách trễ hạn");
		btnPunish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				btnPunishActionPerformed(evt);
			}
		});
		
		new JComboBox<String>();
		
		lblReaderId.setBounds(65, 22, 100, 20);
		lblStoryId.setBounds(65, 76, 100, 20);
		lblReturnAppointmentDate.setBounds(65, 130, 148, 20);
		lblOutputReader.setBounds(175, 46, 221, 20);
		lblOutputStory.setBounds(175, 100, 221, 20);
		
		tfReaderId.setBounds(175, 22, 138, 20);
		tfReaderId.setBackground(new Color(199, 255, 255));
		tfReaderId.setBorder(null);

		tfStoryId.setBounds(175, 76, 138, 20);
		tfStoryId.setBackground(new Color(199, 255, 255));
		tfStoryId.setBorder(null);

		tfDateTime.setBounds(217, 127, 55, 28);
		tfDateTime.setBorder(null);

		tfSearchLoan.setBounds(545, 233, 500, 31);
		tfSearchLoan.setBackground(new Color(199, 255, 255));
		tfSearchLoan.setBorder(null);

		tfDay.setBounds(217, 127, 55, 28);
		tfDay.setBorder(null);

		tfMonth.setBounds(282, 127, 55, 28);
		tfMonth.setBorder(null);

		tfYear.setBounds(347, 127, 49, 28);
		tfYear.setBorder(null);
		
		btnCheckReaderId.setBounds(323, 22, 90, 20);
		btnCheckReaderId.setBackground(new Color(76, 175, 80));
		btnCheckReaderId.setForeground(Color.WHITE);
		btnCheckReaderId.setBorder(null);
		btnCheckReaderId.setFocusPainted(false);
		btnCheckReaderId.setFont(new Font("Arial", Font.BOLD, 14));

		btnCheckStoryId.setBounds(323, 76, 90, 20);
		btnCheckStoryId.setBackground(new Color(76, 175, 80));
		btnCheckStoryId.setForeground(Color.WHITE);
		btnCheckStoryId.setBorder(null);
		btnCheckStoryId.setFocusPainted(false);
		btnCheckStoryId.setFont(new Font("Arial", Font.BOLD, 14));

		btnLoanStory.setBounds(65, 183, 111, 28);
		btnLoanStory.setBackground(new Color(76, 175, 80));
		btnLoanStory.setForeground(Color.WHITE);
		btnLoanStory.setBorder(null);
		btnLoanStory.setFocusPainted(false);
		btnLoanStory.setFont(new Font("Arial", Font.BOLD, 14));

		btnReturnStory.setBounds(313, 183, 100, 28);
		btnReturnStory.setBackground(new Color(76, 175, 80));
		btnReturnStory.setForeground(Color.WHITE);
		btnReturnStory.setBorder(null);
		btnReturnStory.setFocusPainted(false);
		btnReturnStory.setFont(new Font("Arial", Font.BOLD, 14));

		btnSearchLoan.setBounds(1050, 233, 100, 31);
		btnSearchLoan.setBackground(new Color(76, 175, 80));
		btnSearchLoan.setForeground(Color.WHITE);
		btnSearchLoan.setBorder(null);
		btnSearchLoan.setFocusPainted(false);
		btnSearchLoan.setFont(new Font("Arial", Font.BOLD, 14));

		btnResetLoan.setBounds(197, 183, 94, 28);
		btnResetLoan.setBackground(new Color(76, 175, 80));
		btnResetLoan.setForeground(Color.WHITE);
		btnResetLoan.setBorder(null);
		btnResetLoan.setFocusPainted(false);
		btnResetLoan.setFont(new Font("Arial", Font.BOLD, 14));

		btnPunish.setBounds(65, 233, 348, 28);
		btnPunish.setBackground(new Color(76, 175, 80));
		btnPunish.setForeground(Color.WHITE);
		btnPunish.setBorder(null);
		btnPunish.setFocusPainted(false);
		btnPunish.setFont(new Font("Arial", Font.BOLD, 14));
		
		dc.setForeground(Color.RED);
		dc.getDateEditor().getUiComponent().setBackground(new Color(199, 255, 255));
		dc.getDateEditor().getUiComponent().setBorder(null);
		dc.setBounds(175, 130, 138, 20);
		dc.setBackground(new Color(199, 255, 255));
		dc.getCalendarButton().setBorder(BorderFactory.createEmptyBorder()); // Không viền cho nút hiển thị lịch
		dc.getCalendarButton().setFocusPainted(false); // Loại bỏ viền focus khi nút được chọn
		dc.getCalendarButton().setContentAreaFilled(false); // Không vẽ màu nền cho nút hiển thị lịch
		dc.setDateFormatString("yyyy-MM-dd");
		
		pnlLoan.add(lblReaderId);
		pnlLoan.add(lblStoryId);
		pnlLoan.add(lblReturnAppointmentDate);
		pnlLoan.add(tfReaderId);
		pnlLoan.add(tfStoryId);
		pnlLoan.add(btnCheckReaderId);
		pnlLoan.add(btnCheckStoryId);
		pnlLoan.add(btnLoanStory);
		pnlLoan.add(btnReturnStory);

		pnlLoan.add(tfSearchLoan);
		pnlLoan.add(btnSearchLoan);

		pnlLoan.add(lblOutputReader);
		pnlLoan.add(lblOutputStory);
		pnlLoan.add(btnResetLoan);
		pnlLoan.add(btnPunish);
		
		pnlLoan.add(dc);

		//thong ke
		pnlStatistical = new JPanel();
		pnlStatistical.setLayout(null);
		pnlStatistical.setBackground(new Color(199, 199, 255));
		pnlStatistical.add(pikachuLabel);
		tabbedPane.addTab("Thống kê", pnlStatistical);
		cont.add(tabbedPane);

		lblStatisticTotalStory= new JLabel();
		lblStatisticTotalLoan = new JLabel();
		lblStatisticLoan = new JLabel();
		lblStatisticPunish = new JLabel(); 
		
		lblStatisticTotalStory.setBounds(389,10,200,28);
		lblStatisticTotalLoan.setBounds(389,51,200,28);
		lblStatisticLoan.setBounds(389,89,200,28);
		lblStatisticPunish.setBounds(389,127,200,28);
		
		pnlStatistical.add(lblStatisticTotalStory);
		pnlStatistical.add(lblStatisticTotalLoan);
		pnlStatistical.add(lblStatisticLoan);
		pnlStatistical.add(lblStatisticPunish);
		
		btnSearchReader.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				findAllReader();
			}
		});
		
		btnAddReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnAddReaderActionPerformed(evt);
			}
		});
		
		setVisible(true);
	}
	
	public void getStatistic() {
	    lblStatisticTotalStory.setText("<html><span style='white-space: nowrap;'>Tổng số truyện: " + statisticModify.getStatisticTotalStory() + "</span></html>");
	    lblStatisticTotalLoan.setText("<html><span style='white-space: nowrap;'>Tổng số phiếu mượn truyện: " + statisticModify.getStatisticTotalLoan() + "</span></html>");
	    lblStatisticLoan.setText("<html><span style='white-space: nowrap;'>Truyện đang cho mượn: " + statisticModify.getStatisticLoan() + "</span></html>");
	    lblStatisticPunish.setText("<html><span style='white-space: nowrap;'>Truyện bị trễ hạn trả: " + statisticModify.getStatisticPunish() + "</span></html>");

	    // Tùy chỉnh kiểu dáng và bố cục
	    Font font = new Font("Arial", Font.BOLD, 20);
	    lblStatisticTotalStory.setFont(font);
	    lblStatisticTotalLoan.setFont(font);
	    lblStatisticLoan.setFont(font);
	    lblStatisticPunish.setFont(font);

	    lblStatisticTotalStory.setForeground(Color.BLUE);
	    lblStatisticTotalLoan.setForeground(Color.RED);
	    lblStatisticLoan.setForeground(Color.YELLOW);
	    lblStatisticPunish.setForeground(Color.BLACK);
	    

	    // Đặt vị trí cho các đối tượng
	    int xPosition = 460; // Vị trí cách 100 pixel từ đầu khung hình
	    int yPosition = 70; // Vị trí dọc theo trục y

	    lblStatisticTotalStory.setBounds(xPosition, yPosition, lblStatisticTotalStory.getPreferredSize().width, lblStatisticTotalStory.getPreferredSize().height);
	    lblStatisticTotalLoan.setBounds(xPosition, yPosition + 30, lblStatisticTotalLoan.getPreferredSize().width, lblStatisticTotalLoan.getPreferredSize().height);
	    lblStatisticLoan.setBounds(xPosition, yPosition + 60, lblStatisticLoan.getPreferredSize().width, lblStatisticLoan.getPreferredSize().height);
	    lblStatisticPunish.setBounds(xPosition, yPosition + 90, lblStatisticPunish.getPreferredSize().width, lblStatisticPunish.getPreferredSize().height);
	}

	private void btnSearchStoryActionPerformed(ActionEvent evt)
	{
		String sql;
		String parameter;
		if(tfSearchStory.getText().equals(""))
		{
			findAllStory();
		} else
		{
			if(cbbFindBy.getSelectedItem().equals("Tên truyện"))
			{
				sql = "call sp_findStoryByName(?)";
				parameter = tfSearchStory.getText();
				findStoryBy(sql,parameter);
			} 
			else if(cbbFindBy.getSelectedItem().equals("Tác giả"))
			{
				sql = "call sp_findStoryByAuthor(?)";
				parameter = tfSearchStory.getText();
				findStoryBy(sql,parameter);
			} 
			else if(cbbFindBy.getSelectedItem().equals("Ngôn ngữ"))
			{
				sql = "call sp_findStoryByLanguage(?)";
				parameter = tfSearchStory.getText();
				findStoryBy(sql,parameter);
			} 
			else if(cbbFindBy.getSelectedItem().equals("Năm xuất bản"))
			{
				sql = "call sp_findStoryByPublishYear(?)";
				parameter = tfSearchStory.getText();
				findStoryBy(sql,parameter);
			} 
			else if(cbbFindBy.getSelectedItem().equals("Thể loại"))
			{
				sql = "call sp_findStoryByType(?)";
				parameter = tfSearchStory.getText();
				findStoryBy(sql,parameter);
			} 
			else if(cbbFindBy.getSelectedItem().equals("NXB"))
			{
				sql = "call sp_findStoryByPublisher(?)";
				parameter = tfSearchStory.getText();
				findStoryBy(sql,parameter);
			} 
			else if(cbbFindBy.getSelectedItem().equals("Tất cả"))
			{
				sql = "call sp_findByAllStory(?)";
				parameter = tfSearchStory.getText();
				findStoryBy(sql, parameter);
			}
		}	
	}
	
	private void btnResetStoryActionPerformed(ActionEvent evt)
	{
		tfStoryName.setText(null);
		tfPageNo.setText(null);
		tfLanguage.setText(null);
		tfPrice.setText("0");
		tfAmount.setText(null);
		cbbPublishYear.setSelectedIndex(0);
		cbbType.setSelectedIndex(0);
		tfAuthor.setText(null);
		tfPublisher.setText(null);
	}
	
	private void btnDeleteStoryActionPerformed(ActionEvent evt)
	{
		try
		{
			storyModify.deleteStory(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
			findAllStory();
		} catch(IndexOutOfBoundsException e)
		{
			JOptionPane.showInternalMessageDialog(cont,"Vui lòng chọn hàng cần xóa");
		}
	}
	
	private void btnUpdateStoryActionPerformed(ActionEvent evt)
	{
		Story story = new Story();
		if(tfStoryName.getText().length()==0  || tfAmount.getText().length()==0)
		{
			JOptionPane.showInternalMessageDialog(cont,"Tên truyện và số lượng truyện không được để trống");
		} 
		else
		{
			story.setStoryName(tfStoryName.getText());
			if(tfPageNo.getText().length()==0)
			{
				story.setPageNo(null);
			}
			else
			{
				story.setPageNo(tfPageNo.getText());
			}
			
			if(tfLanguage.getText().length()==0)
			{
				story.setLanguage(null);
			}
			else
			{
				story.setLanguage(tfLanguage.getText());
			}
			story.setPrice(Integer.parseInt(tfPrice.getText()));
			story.setAmount(Integer.parseInt(tfAmount.getText()));
			story.setPublishYear(String.valueOf(cbbPublishYear.getSelectedItem()));
			story.setType(String.valueOf(cbbType.getSelectedItem()));
			if(tfAuthor.getText().length()==0)
			{
				story.setAuthor(null);
			}
			else
			{
				story.setAuthor(tfAuthor.getText());
			}
			
			if(tfPublisher.getText().length()==0)
			{
				story.setPublisher(null);
			}
			else
			{
				story.setPublisher(tfPublisher.getText());
			}
			story.setStoryId(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
			storyModify.updateStory(story);
			findAllStory();
		}
	}
	
	private void btnAddStoryActionPerformed(ActionEvent evt)
	{
		Story story = new Story();
		if(tfStoryName.getText().length()==0  || tfAmount.getText().length()==0)
		{
			JOptionPane.showInternalMessageDialog(cont,"Tên truyện và số lượng truyện không được để trống");
		} 
		else
		{
			story.setStoryName(tfStoryName.getText());
			if(tfPageNo.getText().length()==0)
			{
				story.setPageNo(null);
			}
			else
			{
				story.setPageNo(tfPageNo.getText());
			}
			
			if(tfLanguage.getText().length()==0)
			{
				story.setLanguage(null);
			}
			else
			{
				story.setLanguage(tfLanguage.getText());
			}
			story.setPrice(Integer.parseInt(tfPrice.getText()));
			story.setAmount(Integer.parseInt(tfAmount.getText()));
			story.setPublishYear(String.valueOf(cbbPublishYear.getSelectedItem()));
			story.setType(String.valueOf(cbbType.getSelectedItem()));
			if(tfAuthor.getText().length()==0)
			{
				story.setAuthor(null);
			}
			else
			{
				story.setAuthor(tfAuthor.getText());
			}
			
			if(tfPublisher.getText().length()==0)
			{
				story.setPublisher(null);
			}
			else
			{
				story.setPublisher(tfPublisher.getText());
			}
			storyModify.addStory(story);
			findAllStory();
		}
	}
	
	private void btnAddReaderActionPerformed(ActionEvent evt)
	{
		Reader reader = new Reader();
		if(tfReaderName.getText().length()==0  || tfIdentityCard.getText().length()==0 || tfPhoneNumber.getText().length()==0)
		{
			JOptionPane.showInternalMessageDialog(cont,"Vui lòng điền đầy đủ thông tin");
		} 
		else
		{
			if(tfSurname.getText().length()==0)
			{
				reader.setSurname(null);
			}
			else
			{
				reader.setSurname(tfSurname.getText());
			}
			reader.setName(tfReaderName.getText());
			reader.setIdentityCard(tfIdentityCard.getText());
			reader.setPhoneNo(tfPhoneNumber.getText());

			readerModify.addReader(reader);
			findAllReader();
		}

	}
	
	private void btnUpdateReaderActionPerformed(ActionEvent evt)
	{
		Reader reader = new Reader();
		if(tfReaderName.getText().length()==0  || tfIdentityCard.getText().length()==0 || tfPhoneNumber.getText().length()==0)
		{
			JOptionPane.showInternalMessageDialog(cont,"Vui lòng điền đầy đủ thông tin");
		} 
		else
		{
			if(tfSurname.getText().length()==0)
			{
				reader.setSurname(null);
			}
			else
			{
				reader.setSurname(tfSurname.getText());
			}
			reader.setName(tfReaderName.getText());
			reader.setIdentityCard(tfIdentityCard.getText());
			reader.setPhoneNo(tfPhoneNumber.getText());

			reader.setReaderId(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
			readerModify.updateReader(reader);
			findAllReader();
		}
		
	}
	
	private void btnDeleteReaderActionPerformed(ActionEvent evt)
	{
		try
		{
			readerModify.deleteReader(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
			findAllReader();
		} catch(IndexOutOfBoundsException e)
		{
			JOptionPane.showInternalMessageDialog(cont,"Vui lòng chọn hàng cần xóa");
		}
	}
	
	private void btnResetReaderActionPerformed(ActionEvent evt)
	{
		tfSurname.setText(null);
		tfReaderName.setText(null);
		tfIdentityCard.setText(null);
		tfPhoneNumber.setText(null);
	}
	
	private void btnSearchLoanActionPerformed(ActionEvent evt)
	{
		findAllLoan();
	}
	
	private void btnAddLoanActionPerformed(ActionEvent evt)
	{
		Loan loan = new Loan();
		try
		{
			loan.setReaderId(Integer.parseInt(tfReaderId.getText()));
			loan.setStoryId(Integer.parseInt(tfStoryId.getText()));
			try
			{
				loan.setStoryReturnAppointmentDate(df.format(dc.getDate()));
				if(lblOutputStory.getText().equals("Không tìm thấy truyện") || lblOutputReader.getText().equals("Không tìm thấy độc giả"))
				{
					JOptionPane.showInternalMessageDialog(cont, "Mượn truyện thất bại\nVui lòng kiểm tra mã độc giả và mã truyện");
				} else
				{

				String checkTimeSpace = loanModify.checkTimeSpace(tfReaderId.getText());
				if(checkTimeSpace == null || Integer.parseInt(checkTimeSpace)>=7)
				{
					int addLoan = loanModify.addLoan(loan);
					if(addLoan ==0 )
					{
						JOptionPane.showMessageDialog(this, "Truyện này đã hết trong kho");
					}
				} 
				else
				{
					JOptionPane.showInternalMessageDialog(cont, "Mỗi độc giả chỉ được mượn tối đa 5 quyển truyện trong 1 tuần");
				}
				}
				
				findAllLoan();
			} catch(Exception e)
			{
				JOptionPane.showInternalMessageDialog(cont, "Ngày hẹn trả không hợp lệ");
			}

		} catch(Exception e)
		{
			JOptionPane.showInternalMessageDialog(cont, "Vui lòng nhập đầy đủ thông tin");
		}
	}
	
	private void btnReturnStoryActionPerformed(ActionEvent evt)
	{
		try
		{
		loanModify.returnStory(Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))));
		findAllLoan();
		} catch(Exception e)
		{
			JOptionPane.showInternalMessageDialog(cont, "Vui lòng chọn mã mượn của truyện cần trả");
		}
	}
	
	private void btnTestReaderIdActionPerformed(ActionEvent evt)
	{
		try
		{
			Reader reader = loanModify.testReaderId(Integer.parseInt(tfReaderId.getText()));
			if(reader.getName().equals(""))
			{
				lblOutputReader.setText("Không tìm thấy độc giả");
				lblOutputReader.setForeground(Color.RED);
			}
			else
			{
				lblOutputReader.setText(reader.getName());
				lblOutputReader.setForeground(Color.BLUE);
			}
		} catch(Exception e)
		{
			lblOutputReader.setText("Không tìm thấy độc giả");
			lblOutputReader.setForeground(Color.RED);
		}
	}
	
	private void btnTestStoryIdActionPerformed(ActionEvent evt)
	{
		try
		{
			Story story = loanModify.testStoryId(Integer.parseInt(tfStoryId.getText()));
			if(story.getStoryName().equals(""))
			{
				lblOutputStory.setText("Không tìm thấy truyện");
				lblOutputStory.setForeground(Color.RED);
			}
			lblOutputStory.setText(story.getStoryName());
			lblOutputStory.setForeground(Color.BLUE);
		} catch(Exception e)
		{
			lblOutputStory.setText("Không tìm thấy truyện");
			lblOutputStory.setForeground(Color.RED);
		}
	}
	
	private void btnResetLoanActionPerformed(ActionEvent evt)
	{
		tfReaderId.setText(null);
		tfStoryId.setText(null);
		lblOutputReader.setText(null);
		lblOutputStory.setText(null);
//		dc.setCalendar(null);
	}
	
	private void btnPunishActionPerformed(ActionEvent evt)
	{
		punish();
	}
	
	public void sortAZPageNo()
	{
		Vector<Story> storyList = new Vector<Story>();
		if(cbbSort.getSelectedItem().equals("Tăng dần theo Số trang"))
		{
			storyList = storyModify.sort("call sp_sortAZPageNo");
		}
		else
		if(cbbSort.getSelectedItem().equals("Giảm dần theo Số trang"))
		{
			storyList = storyModify.sort("call sp_sortZAPageNo");
		}
		else
			if(cbbSort.getSelectedItem().equals("Tăng dần theo Mã truyện"))
			{
				storyList = storyModify.sort("call sp_sortmatruyenAZ");
			}
			else
				if(cbbSort.getSelectedItem().equals("Giảm dần theo Mã truyện"))
				{
					storyList = storyModify.sort("call sp_sortmatruyenZA");
				}
				else
					if(cbbSort.getSelectedItem().equals("Tên truyện A-Z"))
					{
						storyList = storyModify.sort("call sp_sorttentruyenAZ");
					}
					else
						if(cbbSort.getSelectedItem().equals("Tên truyện Z-A"))
						{
							storyList = storyModify.sort("call sp_sorttentruyenZA");
						}
						else
							if(cbbSort.getSelectedItem().equals("Tên Ngôn ngữ A-Z"))
							{
								storyList = storyModify.sort("call sp_sorttenngonnguAZ");
							}
							else
								if(cbbSort.getSelectedItem().equals("Tên Ngôn ngữ Z-A"))
								{
									storyList = storyModify.sort("call sp_sorttenngonnguZA");
								}
								else
									if(cbbSort.getSelectedItem().equals("Tăng dần theo Giá trị"))
									{
										storyList = storyModify.sort("call sp_sortgiatriAZ");
									}
									else
										if(cbbSort.getSelectedItem().equals("Giảm dần theo Giá trị"))
										{
											storyList = storyModify.sort("call sp_sortgiatriZA");
										}
										else
											if(cbbSort.getSelectedItem().equals("Tăng dần theo Số lượng còn"))
											{
												storyList = storyModify.sort("call sp_sortsoluongconAZ");
											}
											else
												if(cbbSort.getSelectedItem().equals("Giảm dần theo Số lượng còn"))
												{
													storyList = storyModify.sort("call sp_sortsoluongconZA");
												}
												else
													if(cbbSort.getSelectedItem().equals("Tăng dần theo Năm xuất bản"))
													{
														storyList = storyModify.sort("call sp_sortNXBAZ");
													}
													else
														if(cbbSort.getSelectedItem().equals("Giảm dần theo Năm xuất bản"))
														{
															storyList = storyModify.sort("call sp_sortNXBZA");
														}
														else
															if(cbbSort.getSelectedItem().equals("Tên Thể loại A-Z"))
															{
																storyList = storyModify.sort("call sp_sorttheloaiAZ");
															}
															else
																if(cbbSort.getSelectedItem().equals("Tên Thể loại Z-A"))
																{
																	storyList = storyModify.sort("call sp_sorttheloaiZA");
																}
																else
																	if(cbbSort.getSelectedItem().equals("Tên Tác giả A-Z"))
																	{
																		storyList = storyModify.sort("call sp_sorttacgiaAZ");
																	}
																	else
																		if(cbbSort.getSelectedItem().equals("Tên Tác giả Z-A"))
																		{
																			storyList = storyModify.sort("call sp_sorttacgiaZA");
																		}
																		else
																			if(cbbSort.getSelectedItem().equals("Tên Nhà xuất bản A-Z"))
																			{
																				storyList = storyModify.sort("call sp_sortNhaXBAZ");
																			}
																			else
																				if(cbbSort.getSelectedItem().equals("Tên Nhà xuất bản Z-A"))
																				{
																					storyList = storyModify.sort("call sp_sortNhaXBZA");
																				}
																				else
																					if(cbbSort.getSelectedItem().equals("Tăng dần theo Số chương"))
																					{
																						storyList = storyModify.sort("call sp_sortsochuongAZ");
																					}
																					else
																						if(cbbSort.getSelectedItem().equals("Giảm dần theo Số chương"))
																						{
																							storyList = storyModify.sort("call sp_sortsochuongZA");
																						}
		String[] columnNames = {"Mã truyện","Tên truyện","Số trang","Ngôn ngữ","Giá trị","Số lượng còn","Năm xuất bản","Thể loại","Tác giả","Nhà xuất bản", "Số chương"};
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(columnNames);
		table.setModel(tblModel);
		for(Story story: storyList)
		{
			tblModel.addRow(new Object[] {story.getStoryId(),story.getStoryName(),story.getPageNo(),story.getLanguage(),story.getPrice(),
					story.getAmount(),story.getPublishYear(),story.getType(),story.getAuthor(),story.getPublisher(),story.getChapterNo()});
		}
	}
	//find information
	
	public void findAllStory()
	{
		Vector<Story> storyList = storyModify.getStoryList();
		String[] columnNames = {"Mã truyện","Tên truyện","Số trang", "Ngôn ngữ", "Giá trị", "Số lượng còn", "Năm xuất bản", "Thể loại", "Tác giả", "Nhà xuất bản", "Số chương"}; 
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(columnNames);
		table.setModel(tblModel);	
		for(Story story: storyList)
		{
			tblModel.addRow(new Object[] {story.getStoryId(),story.getStoryName(),story.getPageNo(),story.getLanguage(),story.getPrice(),
					story.getAmount(),story.getPublishYear(),story.getType(),story.getAuthor(),story.getPublisher(),story.getChapterNo()});
		}
	}
	
	public void findStoryBy(String sql, String parameter)
	{
		Vector<Story> storyList = storyModify.findStoryBy(sql, parameter);
		String[] columnNames = {"Mã truyện","Tên truyện","Số trang", "Ngôn ngữ", "Giá trị", "Số lượng còn", "Năm xuất bản", "Thể loại", " Tác giả", " Nhà xuất bản", "Số chương"}; 
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(columnNames);
		table.setModel(tblModel);
		
		for(Story story: storyList)
		{
			tblModel.addRow(new Object[] {story.getStoryId(),story.getStoryName(),story.getPageNo(),story.getLanguage(),story.getPrice(),
					story.getAmount(),story.getPublishYear(),story.getType(),story.getAuthor(),story.getPublisher(),story.getChapterNo()});
		}
	}
	
	public void findAllReader()
	{
		Vector<Reader> readerList = null;
		if(btnSearchReader.getText().equals(""))
		{
			readerList = readerModify.getReaderList();
		} else
		{
			readerList = readerModify.findReaderByAll(tfSearchReader.getText());
		}
		String[] columnNames = {"Mã độc giả", "Họ đệm", 
				"Tên", "CMND", "SĐT", "Ngày cấp thẻ"};
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(columnNames);
		table.setModel(tblModel);
		for(Reader reader: readerList)
		{
			tblModel.addRow(new Object[] {reader.getReaderId(), reader.getSurname(), reader.getName(), reader.getIdentityCard(), reader.getPhoneNo(), reader.getCardIssueDate()});
		}
	}
	
	public void findAllLoan()
	{
		Vector<Loan> loanList = null;
		if(tfSearchLoan.getText().equals(""))
		{
			loanList = loanModify.getLoanlist();
		} else
		{
			loanList = loanModify.findLoanByAll(tfSearchLoan.getText());
		}
		String[] columnNames = {"Mã mượn","Mã độc giả","Mã truyện", "Số lượng mượn", "Ngày mượn", "Ngày hẹn trả", "Ngày trả", "Trạng thái"};
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(columnNames);
		table.setModel(tblModel);
		for(Loan loan: loanList)
		{
			tblModel.addRow(new Object[] {loan.getLoanId(), loan.getReaderId(), loan.getStoryId(), loan.getLoanNo(), loan.getLoanDate(), loan.getStoryReturnAppointmentDate(), loan.getStoryReturnDate(), loan.getStatus()});
		}
	}
	
	public void punish()
	{
		Vector<Punishment> punishmentList1 = punishmentModify.getPunishmentList();
		Vector<Punishment> punishmentList2 = punishmentModify.getPunishmentListReturnYet();
		String[] columnNames = {"Mã mượn","Mã độc giả","Tên độc giả","Mã truyện","Tên truyện","Số lượng mượn","Quá hạn (ngày)","Thành tiền (đồng)", "Trạng thái truyện"};
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(columnNames);
		table.setModel(tblModel);
		for(Punishment punishment : punishmentList1)
		{
			tblModel.addRow(new Object[] {punishment.getLoanId(),punishment.getReaderId(),punishment.getFullname(),punishment.getStoryId(),punishment.getStory(),punishment.getLoanNo(),punishment.getDaysLate(),punishment.getTotal(),punishment.getStatus()});
		}
		
		for(Punishment punishment: punishmentList2)
		{
			tblModel.addRow(new Object[] {punishment.getLoanId(),punishment.getReaderId(),punishment.getFullname(),punishment.getStoryId(),punishment.getStory(),punishment.getLoanNo(),punishment.getDaysLate(),punishment.getTotal(),punishment.getStatus()});
		}
	}
	
	public void updateComboBox() {
        // Xóa các item cũ trong combobox
        cbbType.removeAllItems();

        // Đọc thể loại truyện từ tệp tin và thêm vào combobox
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cbbType.addItem(line);
            }
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi đọc từ tệp tin: " + e.getMessage());
        }
    }

    public void writeToFile(String category) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(category);
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Thể loại truyện đã được thêm vào.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi!: " + e.getMessage());
        }
    }

    public void deleteCategory(String category) {
        try {
            File inputFile = new File(filePath);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = category;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // Kiểm tra nếu dòng hiện tại không phải là dòng cần xóa
                if (!currentLine.equals(lineToRemove)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }

            writer.close();
            reader.close();

            // Xóa tệp tin gốc
            if (inputFile.delete()) {
                // Đổi tên tệp tin tạm thành tên tệp tin gốc
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Đã xảy ra lỗi khi đổi tên tệp tin.");
                }
            } else {
                System.out.println("Đã xảy ra lỗi khi xóa tệp tin.");
            }

            JOptionPane.showMessageDialog(null, "Thể loại truyện đã được xóa.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi xóa thể loại truyện: " + e.getMessage());
        }
    }
}




