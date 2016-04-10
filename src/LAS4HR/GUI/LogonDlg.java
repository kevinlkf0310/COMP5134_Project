package LAS4HR.GUI;

 
import LAS4HR.Person.Staff; 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import javax.swing.border.*;

public class LogonDlg  extends JDialog{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel lblUsername = new JLabel("Staff ID :");
    private final JLabel lblPassword = new JLabel("Password :");

    private final JTextField txtUsername = new JTextField(20);
    private final JPasswordField txtPassword = new JPasswordField();

    private final JButton btnLogon = new JButton("Login");
    private final JButton btnCancel = new JButton("Cancel");

    private final JLabel lblStatus = new JLabel(" ");

    private boolean blnIsLogon;
    
    public LogonDlg() {
        this(null, true);
    }

    public LogonDlg(final JFrame parent, boolean modal) {
        super(parent, modal);

        blnIsLogon = false;
        setTitle("LAS Logon");
        
        JPanel p3 = new JPanel(new GridLayout(2, 1));
        p3.add(lblUsername);
        p3.add(lblPassword);

        txtPassword.setEchoChar('*');
        JPanel p4 = new JPanel(new GridLayout(2, 1));
        p4.add(txtUsername);
        p4.add(txtPassword);

        JPanel p1 = new JPanel();
        p1.add(p3);
        p1.add(p4);
        p1.setBorder(new LineBorder(Color.GRAY));
        
        JPanel p2 = new JPanel();
        p2.add(btnLogon);
        p2.add(btnCancel);

        JPanel p5 = new JPanel(new BorderLayout());
        p5.add(p2, BorderLayout.CENTER);
        p5.add(lblStatus, BorderLayout.NORTH);
        lblStatus.setForeground(Color.RED);
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new BorderLayout());
        add(p1, BorderLayout.CENTER);
        add(p5, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnLogon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	Staff staff = new Staff();
            	
            	if (staff.logon(getUsername(),  getPassword()))
            	{
                     setVisible(false);
                     blnIsLogon = true;
               	}
            	else
            	{
            		blnIsLogon = false;
                    lblStatus.setText("Invalid username or password");
            	}   
            	
            	staff = null;
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	blnIsLogon = false;
                setVisible(false);
            }
        });
    }

    public String getUsername() {
        return txtUsername.getText().trim();
    }
 
    public String getPassword() {
        return new String(txtPassword.getPassword());
    }
 
    public boolean isLogon() {
        return blnIsLogon;
    }
    
 
}
