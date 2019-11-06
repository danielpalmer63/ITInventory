package itinventory;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel
{
    private final String[] columnNames ={"ID", "Name", "Status", "Checkout Date", "Return Date", "Student"};
    private final ArrayList<ItemAndStudent> listOfItemAndStudent;

    public TableModel(ArrayList<ItemAndStudent> listOfItemAndStudent)
    {
        this.listOfItemAndStudent = listOfItemAndStudent;
    }
    
    @Override
    public int getRowCount() 
    {
        return listOfItemAndStudent.size();
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
            case 0: return listOfItemAndStudent.get(row).getItem().getID();
            case 1: return listOfItemAndStudent.get(row).getItem().getName();
            case 2: return listOfItemAndStudent.get(row).getItem().getStatus();
            case 3: return listOfItemAndStudent.get(row).getItem().getCheckoutDate();
            case 4: return listOfItemAndStudent.get(row).getItem().getReturnDate();
            case 5: return listOfItemAndStudent.get(row).getStudent().getID();
            default: return null;
        }
    }
    
    public String[] getColumnNames()
    {
        return columnNames;
    }
}
