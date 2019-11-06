package itinventory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StudentListUI extends JFrame
{
    private final Controller controller;
    
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JScrollPane tableScroller;
    private JTable studentListTable;
    private JButton updateButton;
    private JButton newButton;
    private JButton itemViewButton;

    public StudentListUI(Controller controller) 
    {
        this.controller = controller;
        initComponents();
    }
    
    private void initComponents()
    {
        setTitle("Student List");
        tablePanel = new JPanel();
        buttonPanel = new JPanel(new GridLayout(1, 4));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        updateButton = new JButton("Update");    
        updateButton.addActionListener(new StudentListUI.UpdateActionListener());
        
        newButton = new JButton("Add New");    
        newButton.addActionListener(new StudentListUI.NewActionListener());
        
        itemViewButton = new JButton("Item View");
        itemViewButton.addActionListener(event -> controller.studentListUICloseButton());
        
        buttonPanel.add(newButton);
        buttonPanel.add(itemViewButton);
        buttonPanel.add(updateButton);
        
        int size = controller.getStudentList().size();
        
        String headers[] = controller.getTableModelStudent().getColumnNames();
        String[][] rows = new String[size][6];
        for(int num = 0; num < size; num++)
        {
            rows[num][0] = controller.getTableModelStudent().getValueAt(num, 0);
            rows[num][1] = controller.getTableModelStudent().getValueAt(num, 1);
            rows[num][2] = controller.getTableModelStudent().getValueAt(num, 2); 
            rows[num][3] = controller.getTableModelStudent().getValueAt(num, 3);
            rows[num][4] = controller.getTableModelStudent().getValueAt(num, 4);
            rows[num][5] = controller.getTableModelStudent().getValueAt(num, 5);
        }

        studentListTable = new JTable(rows, headers); 
        tableScroller = new JScrollPane(studentListTable);
        studentListTable.setFillsViewportHeight(true);
        tableScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScroller.setPreferredSize(new Dimension(700,300));
        tablePanel.add(tableScroller);
        this.setSize(800,400);
        this.setLocationRelativeTo(null);
        this.setContentPane(new JPanel(new BorderLayout()));
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        this.getContentPane().add(tablePanel, BorderLayout.CENTER);
    }
    
    private class UpdateActionListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent evt) 
        {
            if(controller.getStudentList().size() >= 1 && studentListTable.getSelectedRow() > -1)
            {
                int selectedTableRow = studentListTable.getSelectedRow();
                int selectedModelRow = studentListTable.convertRowIndexToModel(selectedTableRow);
                StudentListUI.this.controller.getStudentUI(selectedModelRow);
            }
        }
    }
    
    private class NewActionListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent evt) 
        {
            controller.studentListUINewButton();
            int selectedTableRow = controller.getItemList().size();
            int selectedModelRow = studentListTable.convertRowIndexToModel(selectedTableRow);
            StudentListUI.this.controller.getStudentUI(selectedModelRow);
            
        }
    }
}




