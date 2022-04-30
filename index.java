package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

class Myframe extends JFrame implements ActionListener {

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JTextField t1, t2, t3;
    JRadioButton male, female, other;
    JComboBox day, month, year;
    JCheckBox terms;
    JButton submit;
    JLabel msg;
    JTextArea screen;
    JLabel background;

    Myframe() {
        setTitle("Registration Form");
        setSize(600, 780);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(null);

        // HEADING
        JLabel l = new JLabel("Registration Form");
        l.setFont(new Font("Serif", Font.BOLD, 18));
        l.setForeground(Color.BLUE);
        l.setBounds(120, -24, 500, 100);
        c.add(l);

        // FULL NAME
        label1 = new JLabel("full name");
        label1.setBounds(20, 50, 100, 20);
        c.add(label1);

        t1 = new JTextField();
        t1.setBounds(90, 50, 200, 20);
        c.add(t1);

        // EMAIL
        label2 = new JLabel("email address");
        label2.setBounds(20, 100, 100, 20);
        c.add(label2);

        t2 = new JTextField();
        t2.setBounds(110, 100, 200, 20);
        c.add(t2);

        // PHONE NUMBER
        label3 = new JLabel("phone number");
        label3.setBounds(20, 150, 100, 20);
        c.add(label3);

        t3 = new JTextField();
        t3.setBounds(120, 150, 200, 20);
        c.add(t3);

        // GENDER SELECTION
        label4 = new JLabel("Gender");
        label4.setBounds(20, 200, 100, 20);
        c.add(label4);

        male = new JRadioButton("male");
        female = new JRadioButton("female");
        other = new JRadioButton("other");

        male.setBounds(80, 200, 60, 20);
        female.setBounds(160, 200, 80, 20);
        other.setBounds(250, 200, 80, 20);

        c.add(male);
        c.add(female);
        c.add(other);

        // functionality to select one radio button at once
        ButtonGroup gender = new ButtonGroup();
        gender.add(male);
        gender.add(female);
        gender.add(other);

        // DATE OF BIRTH
        label5 = new JLabel("DOB");
        label5.setBounds(20, 250, 100, 20);
        c.add(label5);

        String[] days = {"1", "2", "3", "4", "5", "6", "7"};
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] years = {"2022", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010"};

        day = new JComboBox(days);
        month = new JComboBox(months);
        year = new JComboBox(years);

        day.setBounds(70, 250, 50, 20);
        month.setBounds(130, 250, 50, 20);
        year.setBounds(190, 250, 60, 20);

        c.add(day);
        c.add(month);
        c.add(year);

        // CHECKBOX
        terms = new JCheckBox("Please accept our terms and conditions");
        terms.setBounds(20, 300, 250, 20);
        c.add(terms);

        // SUBMIT BUTTON
        submit = new JButton("Submit");
        submit.setBounds(20, 350, 80, 20);
        c.add(submit);

        submit.addActionListener(this);

        // REGISTRATION SUCCESSFUL MESSAGE
        msg = new JLabel("");
        msg.setBounds(20, 400, 250, 20);
        msg.setForeground(Color.BLUE);
        c.add(msg);

        // OUTPUT TEXT-AREA
        label6 = new JLabel("Your Details");
        label6.setBounds(20, 450, 100, 20);
        c.add(label6);

        screen = new JTextArea();
        screen.setBounds(20, 490, 300, 200);
        c.add(screen);


        // setting background colour
        c.setBackground(Color.MAGENTA);


        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (t1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your full name");
            screen.setText("");
        }
        if (t2.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your email address");
            screen.setText("");
        }
        if (t3.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your phone number");
            screen.setText("");
        }
        if (!male.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please choose your gender");
            screen.setText("");
        }
        if (!(male.isSelected() || female.isSelected() || other.isSelected())) {
            JOptionPane.showMessageDialog(null, "Please choose your gender");
            screen.setText("");
        }
        if (!terms.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please accept our terms and conditions");
            screen.setText("");
        }
        if (!(t1.getText().trim().isEmpty()) && !(t2.getText().trim().isEmpty()) && !(t3.getText().trim().isEmpty()) && !(t3.getText().trim().isEmpty()) && (male.isSelected() || female.isSelected() || other.isSelected()) && terms.isSelected()) {
            msg.setText("Registration Successful!");

            // getting user's name, email and phone number.
            String name = t1.getText();
            String email = t2.getText();
            String phone = t3.getText();

            // checking which gender the user has selected
            String gender = "";
            if (male.isSelected()) {
                gender = "male";
            } else if (female.isSelected()) {
                gender = "female";
            } else if (other.isSelected()) {
                gender = "other";
            }

            // getting date of birth
            String dob = day.getSelectedItem() + "/" + month.getSelectedItem() + "/" + year.getSelectedItem();

            // displaying the user's detail inside the textarea
            screen.setText("Full Name :- " +name+ "\n" +"Email Address :- "+email+ "\n" +"Phone Number :- "+phone+ "\n" + "Gender :- " +gender+ "\n" + "Date of Birth :- "+dob+"\n");

            try {
                File file = new File("output.txt");

                if(!file.exists()) {
                    file.createNewFile();
                }
                PrintWriter pw = new PrintWriter(file);
                pw.println(name);
                pw.println(email);
                pw.println(phone);
                pw.println(gender);
                pw.println(dob);
                pw.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


    }
}

class RegistrationForm {
    public static void main(String[] args) {
        Myframe frame = new Myframe();
    }
}
