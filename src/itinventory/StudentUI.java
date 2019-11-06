package itinventory;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StudentUI extends JFrame
{
    private final Controller controller;
    private final int currentSelectedRow;
    private final Student currentStudent;
    private final JTextField firstNameDisplayValue = new JTextField(15);
    private final JTextField lastNameDisplayValue = new JTextField(15);
    private final JTextField IDDisplayValue = new JTextField(15);
    private final JTextField numDisplayValue = new JTextField(15);
    private JPanel tablePanel;
    private JPanel buttonPanel;
    
    public StudentUI(Controller newController, int selectedRow)
    {
        controller = newController;
        currentSelectedRow = selectedRow;
        currentStudent = controller.getStudent(selectedRow);
        initComponents();
        parseCurrentItems();
    }
    
    private void initComponents()
    {
        setTitle("Student");
        setSize(625, 300);
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tablePanel = new JPanel(new GridLayout(5, 1));
        tablePanel.add(new JLabel("First Name"));
        tablePanel.add(firstNameDisplayValue);
        tablePanel.add(new JLabel("Last Name"));
        tablePanel.add(lastNameDisplayValue);
        tablePanel.add(new JLabel("ID"));
        tablePanel.add(IDDisplayValue);
        tablePanel.add(new JLabel("# of Items Checked Out"));
        tablePanel.add(numDisplayValue);
        
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton deleteButton = new JButton("Delete");     
        JButton saveButton = new JButton("Save");
        JButton closeButton = new JButton("Close");

        deleteButton.addActionListener(event -> deleteButton());
        saveButton.addActionListener(event -> controller.studentUISaveButton(currentSelectedRow));
        closeButton.addActionListener(event -> controller.studentUICloseButton());

        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(closeButton);
        
        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(tablePanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void parseCurrentItems()
    {
        firstNameDisplayValue.setText(currentStudent.getFirstName());
        lastNameDisplayValue.setText(currentStudent.getLastName());
        IDDisplayValue.setText(currentStudent.getID());
        numDisplayValue.setText(String.valueOf(currentStudent.getNumOfItems()));
    }
    
    private void deleteButton()
    {
        firstNameDisplayValue.setText("");
        lastNameDisplayValue.setText("");
        IDDisplayValue.setText("");
        numDisplayValue.setText("");
        controller.studentUIDeleteButton();
    }
    
    public String getFirstNameDisplayValue()
    {
        return firstNameDisplayValue.getText();
    }
    
    public String getLastNameDisplayValue()
    {
        return lastNameDisplayValue.getText();
    }
    
    public String getIDDisplayValue()
    {
        return IDDisplayValue.getText();
    }
    
    public int getNumDisplayValue()
    {
        return Integer.parseInt(numDisplayValue.getText());
    }
}
