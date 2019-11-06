package itinventory;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ItemUI extends JFrame
{
    private final Controller controller;
    private final int currentSelectedRow;
    private final ItemAndStudent currentItemAndStudent;
    private final JTextField IDDisplayValue = new JTextField(15);
    private final JTextField nameDisplayValue = new JTextField(15);
    private final JTextField statusDisplayValue = new JTextField(15);
    private final JTextField studentDisplayValue = new JTextField(15);
    private JPanel tablePanel;
    private JPanel buttonPanel;
    
    public ItemUI(Controller newController, int selectedRow)
    {
        controller = newController;
        currentSelectedRow = selectedRow;
        currentItemAndStudent = controller.getItemAndStudent(selectedRow);
        initComponents();
        parseCurrentItems();
    }
    
    private void initComponents()
    {
        setTitle("Item");
        setSize(625, 300);
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tablePanel = new JPanel(new GridLayout(5, 1));
        tablePanel.add(new JLabel("ID"));
        tablePanel.add(IDDisplayValue);
        tablePanel.add(new JLabel("Name"));
        tablePanel.add(nameDisplayValue);
        tablePanel.add(new JLabel("Status"));
        tablePanel.add(statusDisplayValue);
        tablePanel.add(new JLabel("Student"));
        tablePanel.add(studentDisplayValue);
        
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton deleteButton = new JButton("Delete");
        JButton checkoutButton = new JButton("Check Out");        
        JButton returnButton = new JButton("Return");     
        JButton saveButton = new JButton("Save");
        JButton closeButton = new JButton("Close");

        deleteButton.addActionListener(event -> deleteButton());
        checkoutButton.addActionListener(event -> controller.itemUICheckoutButton(currentSelectedRow));
        returnButton.addActionListener(event -> controller.itemUIReturnButton(currentSelectedRow));
        saveButton.addActionListener(event -> controller.itemUISaveButton(currentSelectedRow));
        closeButton.addActionListener(event -> controller.itemUICloseButton());

        buttonPanel.add(deleteButton);
        buttonPanel.add(checkoutButton);  
        buttonPanel.add(returnButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(closeButton);
        
        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(tablePanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void parseCurrentItems()
    {
        IDDisplayValue.setText(currentItemAndStudent.getItem().getID());
        nameDisplayValue.setText(currentItemAndStudent.getItem().getName());
        statusDisplayValue.setText(currentItemAndStudent.getItem().getStatus());
        studentDisplayValue.setText(currentItemAndStudent.getStudent().getID());
    }
    
    private void deleteButton()
    {
        IDDisplayValue.setText("");
        nameDisplayValue.setText("");
        statusDisplayValue.setText("");
        studentDisplayValue.setText("");
        controller.itemUIDeleteButton();
    }
    
    public String getIDDisplayValue()
    {
        return IDDisplayValue.getText();
    }
    
    public String getNameDisplayValue()
    {
        return nameDisplayValue.getText();
    }
    
    public String getStatusDisplayValue()
    {
        return statusDisplayValue.getText();
    }
    
    public String getStudentDisplayValue()
    {
        return studentDisplayValue.getText();
    }
}
