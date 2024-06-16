import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Korisnici {

    private JFrame frmCloudEstate;
    private JTextField textFieldUserEmail;
    private JPasswordField passwordFieldUserPassword;
    private JTextField textFieldAgencyCode;
    private JPasswordField passwordFieldAgencyPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Korisnici window = new Korisnici();
                    window.frmCloudEstate.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Korisnici() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmCloudEstate = new JFrame();
        frmCloudEstate.setTitle("Cloud Estate");
        frmCloudEstate.setBounds(100, 100, 800, 600);
        frmCloudEstate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCloudEstate.getContentPane().setLayout(null);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Unos_Korisnika nw = new Unos_Korisnika();
                nw.newscreen();
            }
        });
        btnRegister.setBounds(109, 300, 136, 37);
        btnRegister.setForeground(Color.WHITE); // Postavljanje boje teksta na bijelu
        frmCloudEstate.getContentPane().add(btnRegister);

        JTextPane txtpnCloudEstate = new JTextPane();
        txtpnCloudEstate.setEditable(false);
        txtpnCloudEstate.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        txtpnCloudEstate.setBackground(new Color(30, 144, 255));
        txtpnCloudEstate.setText("Cloud Estate");
        txtpnCloudEstate.setBounds(346, 11, 97, 37);
        frmCloudEstate.getContentPane().add(txtpnCloudEstate);

        JButton btnLoginUser = new JButton("Login");
        btnLoginUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = textFieldUserEmail.getText();
                String password = new String(passwordFieldUserPassword.getPassword());
                if (validateUser(email, password)) {
                    Prikaz_Oglasa nw = new Prikaz_Oglasa();
                    nw.newscreen();
                } else {
                    JOptionPane.showMessageDialog(frmCloudEstate, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnLoginUser.setBounds(109, 230, 136, 37);
        btnLoginUser.setForeground(Color.WHITE); // Postavljanje boje teksta na bijelu
        frmCloudEstate.getContentPane().add(btnLoginUser);

        JTextPane txtpnUser = new JTextPane();
        txtpnUser.setText("Korisnik");
        txtpnUser.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        txtpnUser.setEditable(false);
        txtpnUser.setBackground(UIManager.getColor("Button.background"));
        txtpnUser.setBounds(109, 110, 97, 37);
        frmCloudEstate.getContentPane().add(txtpnUser);

        JButton btnLoginAgency = new JButton("Login");
        btnLoginAgency.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = textFieldAgencyCode.getText();
                String password = new String(passwordFieldAgencyPassword.getPassword());
                if (validateAgency(code, password)) {
                    Unos_Oglasa nw = new Unos_Oglasa();
                    nw.newscreen();
                } else {
                    JOptionPane.showMessageDialog(frmCloudEstate, "Invalid agency code or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnLoginAgency.setBounds(571, 230, 136, 37);
        btnLoginAgency.setForeground(Color.WHITE); // Postavljanje boje teksta na bijelu
        frmCloudEstate.getContentPane().add(btnLoginAgency);

        JTextPane txtpnAgency = new JTextPane();
        txtpnAgency.setText("Agencija");
        txtpnAgency.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        txtpnAgency.setEditable(false);
        txtpnAgency.setBackground(UIManager.getColor("Button.background"));
        txtpnAgency.setBounds(571, 110, 97, 37);
        frmCloudEstate.getContentPane().add(txtpnAgency);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 144, 255));
        panel.setBounds(0, 0, 784, 48);
        frmCloudEstate.getContentPane().add(panel);

        textFieldUserEmail = new JTextField();
        textFieldUserEmail.setBounds(109, 158, 179, 20);
        frmCloudEstate.getContentPane().add(textFieldUserEmail);
        textFieldUserEmail.setColumns(10);

        JLabel lblUserEmail = new JLabel("Email");
        lblUserEmail.setBounds(20, 161, 90, 14);
        frmCloudEstate.getContentPane().add(lblUserEmail);

        passwordFieldUserPassword = new JPasswordField();
        passwordFieldUserPassword.setBounds(109, 189, 179, 20);
        frmCloudEstate.getContentPane().add(passwordFieldUserPassword);

        JLabel lblUserPassword = new JLabel("Lozinka");
        lblUserPassword.setBounds(20, 192, 90, 14);
        frmCloudEstate.getContentPane().add(lblUserPassword);

        textFieldAgencyCode = new JTextField();
        textFieldAgencyCode.setBounds(571, 158, 179, 20);
        frmCloudEstate.getContentPane().add(textFieldAgencyCode);
        textFieldAgencyCode.setColumns(10);

        JLabel lblAgencyCode = new JLabel("Šifra agnecije");
        lblAgencyCode.setBounds(482, 161, 79, 14);
        frmCloudEstate.getContentPane().add(lblAgencyCode);

        passwordFieldAgencyPassword = new JPasswordField();
        passwordFieldAgencyPassword.setBounds(571, 189, 179, 20);
        frmCloudEstate.getContentPane().add(passwordFieldAgencyPassword);

        JLabel lblAgencyPassword = new JLabel("Lozinka");
        lblAgencyPassword.setBounds(482, 192, 79, 14);
        frmCloudEstate.getContentPane().add(lblAgencyPassword);

        // New button to display the list of agencies
        JButton btnShowAgencies = new JButton("Prikaz Agencija");
        btnShowAgencies.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> agencies = getAgencies();
                if (agencies.isEmpty()) {
                    JOptionPane.showMessageDialog(frmCloudEstate, "No agencies found", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder agencyList = new StringBuilder("Agencije:\n");
                    for (String agency : agencies) {
                        agencyList.append(agency).append("\n");
                    }
                    JOptionPane.showMessageDialog(frmCloudEstate, agencyList.toString(), "Agencies", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnShowAgencies.setBounds(571, 300, 136, 37);
        btnShowAgencies.setForeground(Color.WHITE); // Postavljanje boje teksta na bijelu
        frmCloudEstate.getContentPane().add(btnShowAgencies);
    }

    private boolean validateUser(String email, String password) {
        boolean isValid = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String url = "jdbc:mysql://ucka.veleri.hr/pmocibob";
            String username = "pmocibob";
            String dbPassword = "11";

            connection = DriverManager.getConnection(url, username
