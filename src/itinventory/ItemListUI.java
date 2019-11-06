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

public class ItemListUI extends JFrame
{
    private final Controller controller;
    
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JScrollPane tableScroller;
    private JTable itemListTable;
    private JButton doneButton;
    private JButton updateButton;
    private JButton newButton;
    private JButton studentViewButton;

    public ItemListUI(Controller controller) 
    {
        this.controller = controller;
        initComponents();
    }
    
    private void initComponents()
    {
        setTitle("Inventory");
        tablePanel = new JPanel();
        buttonPanel = new JPanel(new GridLayout(1, 4));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        doneButton = new JButton("Done");
        doneButton.addActionListener(event -> System.exit(0));
        
        updateButton = new JButton("Update");    
        updateButton.addActionListener(new UpdateActionListener());
        
        newButton = new JButton("Add New");    
        newButton.addActionListener(new NewActionListener());
        
        studentViewButton = new JButton("Student View");
        studentViewButton.addActionListener(new StudentViewActionListener());
        
        buttonPanel.add(updateButton);
        buttonPanel.add(studentViewButton);
        buttonPanel.add(newButton);
        buttonPanel.add(doneButton);
        
        int size = controller.getItemList().size();
        
        String headers[] = controller.getTableModel().getColumnNames();
        String[][] rows = new String[size][6];
        for(int num = 0; num < size; num++)
        {
            rows[num][0] = controller.getTableModel().getValueAt(num, 0);
            rows[num][1] = controller.getTableModel().getValueAt(num, 1);
            rows[num][2] = controller.getTableModel().getValueAt(num, 2); 
            rows[num][3] = controller.getTableModel().getValueAt(num, 3);
            rows[num][4] = controller.getTableModel().getValueAt(num, 4);
            rows[num][5] = controller.getTableModel().getValueAt(num, 5);
        }

        itemListTable = new JTable(rows, headers); 
        tableScroller = new JScrollPane(itemListTable);
        itemListTable.setFillsViewportHeight(true);
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
            if(controller.getItemList().size() >= 1 && itemListTable.getSelectedRow() > -1)
            {
                int selectedTableRow = itemListTable.getSelectedRow();
                int selectedModelRow = itemListTable.convertRowIndexToModel(selectedTableRow);
                ItemListUI.this.controller.getItemUI(selectedModelRow);
            }
        }
    }
    
    private class NewActionListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent evt) 
        {
            controller.itemListUINewButton();
            int selectedTableRow = controller.getItemList().size()-1;
            int selectedModelRow = itemListTable.convertRowIndexToModel(selectedTableRow);
            ItemListUI.this.controller.getItemUI(selectedModelRow);
            
        }
    }
    
    private class StudentViewActionListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent evt) 
        {
            controller.itemListUIStudentViewButton();
        }
    }
}
