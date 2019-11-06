package itinventory;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelStudent extends AbstractTableModel
{
    private final String[] columnNames ={"ID", "First Name", "Last Name", "Checkout Date", "Return Date", "# of Items"};
    private final ArrayList<Student> studentList;

    public TableModelStudent(ArrayList<Student> studentList)
    {
        this.studentList = studentList;
    }
    
    @Override
    public int getRowCount() 
    {
        return studentList.size();
    }

    @Override
    public int getColumnCount() 
    {
        return columnNames.length; 
    }

    @Override
    public String getValueAt(int row, int col)
    {
        switch(col)
        {
            case 0: return studentList.get(row).getID();
            case 1: return studentList.get(row).getFirstName();
            case 2: return studentList.get(row).getLastName();
            case 3: return studentList.get(row).getCheckoutDate();
            case 4: return studentList.get(row).getReturnDate();
            case 5: return String.valueOf(studentList.get(row).getNumOfItems());
            default: return null;
        }
    }
    
    public String[] getColumnNames()
    {
        return columnNames;
    }
}
