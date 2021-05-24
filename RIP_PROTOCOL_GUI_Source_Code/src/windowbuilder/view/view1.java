package windowbuilder.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import windowbuilder.common.PROTOCAL;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class view1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JMenu mnShowPicture;
	private JMenuItem mntmShow;
	private JTextArea ConsoletextArea;
	private JButton btnReadyForRun;
	private JButton btnRetry;

	
	public  int n;
	public  int T;
	private JMenuItem mntmQuery;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view1 frame = new view1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public view1() {
		initComponents();
		createEvents();
	}
	private void initComponents() {
		setTitle("RIP_PROTOCOL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnShowPicture = new JMenu("Show Picture");
		mnShowPicture.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 30));
		menuBar.add(mnShowPicture);
		
		mntmShow = new JMenuItem("SHOW");
	
		mnShowPicture.add(mntmShow);
		
		JMenu mnQueryForDistance = new JMenu("Query for distance");
		mnQueryForDistance.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 30));
		menuBar.add(mnQueryForDistance);
		
		mntmQuery = new JMenuItem("Query");
		
		mnQueryForDistance.add(mntmQuery);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel Consolepanel = new JPanel();
		Consolepanel.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblFilestrc = new JLabel("enter file of struc:");
		lblFilestrc.setFont(new Font("宋体", Font.PLAIN, 28));
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.BOLD, 28));
	
		textField.setColumns(10);
		
		JLabel lblEnterInitialFile = new JLabel("enter initial state of routers:");
		lblEnterInitialFile.setFont(new Font("宋体", Font.PLAIN, 28));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.BOLD, 28));

		textField_1.setColumns(10);
		
		btnReadyForRun = new JButton("Ready for Run");
		
		JLabel lblEnterTheRouter = new JLabel("enter the router having emergency:");
		lblEnterTheRouter.setFont(new Font("宋体", Font.PLAIN, 28));
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.BOLD, 28));
		textField_2.setColumns(10);
		
		btnRetry = new JButton("Retry");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFilestrc, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
							.addGap(168))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEnterInitialFile, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
							.addGap(37))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
							.addGap(178))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
							.addGap(177))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnReadyForRun, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 220, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEnterTheRouter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField_2, 194, 194, 194)
							.addGap(283))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnRetry, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 316, Short.MAX_VALUE)))
					.addGap(5)
					.addComponent(Consolepanel, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFilestrc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEnterInitialFile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1)
							.addGap(31)
							.addComponent(btnReadyForRun, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
							.addGap(31)
							.addComponent(lblEnterTheRouter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRetry, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(31))
						.addComponent(Consolepanel, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
					.addGap(38))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_Consolepanel = new GroupLayout(Consolepanel);
		gl_Consolepanel.setHorizontalGroup(
			gl_Consolepanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
		);
		gl_Consolepanel.setVerticalGroup(
			gl_Consolepanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
		);
		
		ConsoletextArea = new JTextArea();
		ConsoletextArea.setFont(new Font("Monospaced", Font.PLAIN, 28));
		scrollPane.setViewportView(ConsoletextArea);
		Consolepanel.setLayout(gl_Consolepanel);
		contentPane.setLayout(gl_contentPane);
		
	}
	private void createEvents() {
		mntmShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pic frame2=new Pic();
				frame2.setVisible(true);
			}
		});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   String filenameStrc=textField.getText();
			   PROTOCAL.ReadRouters(filenameStrc);
			   redirectSystemStreams();
			   PROTOCAL.printStrucR();
			}
		});
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String filenameStrc=textField_1.getText();
				   PROTOCAL.ReadRips(filenameStrc);
				   redirectSystemStreams();
				   PROTOCAL.PrintRips();
				
			}
		});
		mntmQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Query q=new Query();
				q.setVisible(true);
			}
		});
		btnReadyForRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				     T=0;
			        n=0;
			        redirectSystemStreams();
			        new Thread(new Runnable() {
			            @Override
			            public void run() {                                
			                // 比较耗时的计算与相应的打印内容代码写在这里   
			          	  while(true) {
					        	//每间隔3s 更新路由表
					        	try {
					        		Thread.sleep(3000);
					        		
					        	}catch(Exception e2) {
					        		e2.printStackTrace();
					        	}
					        	System.out.printf("\n------------------------第%d次更新------------------------\n",++T);
					        	try {
									if(PROTOCAL.update()) {
										PROTOCAL.PrintRips();
										n++;
									}
									else {
										System.out.printf("没有任何有价值的修改!说明前面%d次修改后,每一个路由器都得到了全局路由信息!已经收敛\n",n);
										/*
										//新加手动控制错误
										System.out.print("请手动添加哪一个路由器出现了问题,若没有问题则输入'0'：");
										textField_2.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												PROTOCAL.error=textField_2.getText().charAt(0);
											}
										});
										//PROTOCAL.error=input.next().charAt(0);
										if(PROTOCAL.error!='0') {
										System.out.printf("路由器%c发生错误", PROTOCAL.error);
										PROTOCAL.check1(PROTOCAL.error);
										System.out.println("显示出现路由器错误的路由表");
										PROTOCAL.PrintRips();
										
										//PROTOCAL.error=0;
										
										}
										
										else {
											System.out.printf("\n------------------------已经无错误发生------------------------\n");
											break;
										}
										*/
										break;
									}
									
								} catch (ClassNotFoundException e1) {
									// TODO 自动生成的 catch 块
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO 自动生成的 catch 块
									e1.printStackTrace();
								}
					        	//check();
					        }
			            }
			        }).start();

			
			}
		});
		btnRetry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 redirectSystemStreams();
				 new Thread(new Runnable() {
			            @Override
			            public void run() {                                
			            	PROTOCAL.error=textField_2.getText().charAt(0);
							 System.out.printf("The Router %c stopped in a sudden\n",PROTOCAL.error);
							 System.out.println("This is the rounter table after the emergency");
							 PROTOCAL.check1(PROTOCAL.error);
							 PROTOCAL.PrintRips();
							 while(true) {
							

						        	//每间隔1s 更新路由表
								 
						        	try {
						        		Thread.sleep(1000);
						        	}catch(Exception e) {
						        		e.printStackTrace();
						        	}
						        	System.out.printf("\n------------------------第%d次更新------------------------\n",++T);
						        	try {
										if(PROTOCAL.update()) {
											PROTOCAL.PrintRips();
											n++;
										}
										else {
											System.out.println("没有任何有价值的修改!说明经过前面修改后,每一个路由器都得到了全局路由信息!已经收敛");
											break;
											}
									} catch (ClassNotFoundException e) {
										// TODO 自动生成的 catch 块
										e.printStackTrace();
									} catch (IOException e) {
										// TODO 自动生成的 catch 块
										e.printStackTrace();
									}
						        		
						        	
						        	}                   
			            }
			        }).start();
				 
			        	
			}
		});
		
	}
	 private void updateTextArea(final String text) {
		    SwingUtilities.invokeLater(new Runnable() {
		      public void run() {
		    	  ConsoletextArea.append(text);
		      }
		    });
		  }

		  private void redirectSystemStreams() {
		    OutputStream out = new OutputStream() {
		      @Override
		      public void write(int b) throws IOException {
		        updateTextArea(String.valueOf((char) b));
		      }

		      @Override
		      public void write(byte[] b, int off, int len) throws IOException {
		        updateTextArea(new String(b, off, len));
		      }

		      @Override
		      public void write(byte[] b) throws IOException {
		        write(b, 0, b.length);
		      }
		    };

		    System.setOut(new PrintStream(out, true));
		    System.setErr(new PrintStream(out, true));
		  }
}