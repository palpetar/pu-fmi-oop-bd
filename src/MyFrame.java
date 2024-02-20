import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MyFrame extends JFrame {

    Connection connection = null;
    PreparedStatement state = null;
    ResultSet result = null;
    int id = -1;

    JButton addButton = new JButton("ADD");
    JButton delButton = new JButton("DEL");
    JButton updateButton = new JButton("UPDATE");

    JPanel upPanel = new JPanel();
    JPanel midPanel = new JPanel();
    JPanel downPanel = new JPanel();

    JLabel fNameLabel = new JLabel("First Name:");
    JLabel lNameLabel = new JLabel("Last Name:");
    JLabel genderLabel = new JLabel("Gender:");
    JLabel ageLabel = new JLabel("Age:");
    JLabel salaryLabel = new JLabel("Salary:");

    JTextField fNameTF = new JTextField();
    JTextField lNameTF = new JTextField();
    JTextField ageTF = new JTextField();
    JTextField salaryTF = new JTextField();

    String[] genders = {"Male", "Female"};
    JComboBox<String> genderComboBox = new JComboBox<>(genders);

    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane(table);
    public MyFrame() {

        this.setSize(400, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLayout(new GridLayout(3, 1));

        //upPanel
        upPanel.setLayout(new GridLayout(5, 2));
        upPanel.add(fNameLabel);
        upPanel.add(fNameTF);

        upPanel.add(lNameLabel);
        upPanel.add(lNameTF);

        upPanel.add(genderLabel);
        upPanel.add(genderComboBox);

        upPanel.add(ageLabel);
        upPanel.add(ageTF);

        upPanel.add(salaryLabel);
        upPanel.add(salaryTF);

        //midPanel
        midPanel.add(addButton);
        midPanel.add(delButton);
        midPanel.add(updateButton);

        addButton.addActionListener(new AddAction());

        //downPanel
        downPanel.add(table);
        scrollPane.setPreferredSize(new Dimension(350, 150));
        downPanel.add(scrollPane);

        this.add(upPanel);
        this.add(midPanel);
        this.add(downPanel);


        this.setVisible(true);
    }

    class AddAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println(fNameTF.getText() + " " + lNameTF.getText() + " " + genderComboBox.getSelectedItem().toString() + " " + ageTF.getText() + " " + salaryTF.getText());

            connection = DBConnection.getConnection();
            String sql = "insert into person(fname, lname, sex, age, salary) values(?, ?, ?, ?, ?)";
            try {
                state = connection.prepareStatement(sql);
                state.setString(1, fNameTF.getText());
                state.setString(2, lNameTF.getText());
                state.setString(3, genderComboBox.getSelectedItem().toString());
                state.setInt(4, Integer.parseInt(ageTF.getText()));
                state.setFloat(5, Float.parseFloat(salaryTF.getText()));
                state.execute();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
