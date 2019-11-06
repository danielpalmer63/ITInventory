package itinventory;

import java.util.ArrayList;
import java.util.Date;

public class Controller 
{
    private final ItemList itemList;
    private final StudentList studentList;
    private final ItemAndStudentList itemAndStudentList;
    private ItemListUI itemListUI;
    private final TableModel tableModel;
    private final TableModelStudent tableModelStudent;
    private ItemUI itemUI;
    private StudentUI studentUI;
    private boolean itemDeleted;
    private boolean studentDeleted;
    private boolean newStudent;
    private boolean newItem;
    private StudentListUI studentListUI;
    
    public Controller()
    {
        itemList = new ItemList();
        studentList = new StudentList();
        itemAndStudentList = new ItemAndStudentList(this);
        tableModel = new TableModel(itemAndStudentList.getItemAndStudentList());
        tableModelStudent = new TableModelStudent(studentList.getStudentList());
        itemListUI = new ItemListUI(this);
        itemUI = new ItemUI(this, 0);
        studentUI = new StudentUI(this, 0);
        studentListUI = new StudentListUI(this);
        itemDeleted = false;
        studentDeleted = false;
        newItem = false;
        newStudent = false;
        itemListUI.setVisible(true);
        studentListUI.setVisible(false);
        
    }
    
    public Item getItem(int selectedRow)
    {
        return itemList.getItemList().get(selectedRow);
    }
    
    public Student getStudent(int selectedRow)
    {
        return studentList.getStudentList().get(selectedRow);
    }
    
    public ItemAndStudent getItemAndStudent (int selectedRow)
    {
        return itemAndStudentList.getItemAndStudentList().get(selectedRow);
    }
    
    public ArrayList<Item> getItemList()
    {
        return itemList.getItemList();
    }
    
    public ArrayList<Student> getStudentList()
    {
        return studentList.getStudentList();
    }
    
    public TableModel getTableModel()
    {
        return tableModel;
    }    
    
    public TableModelStudent getTableModelStudent()
    {
        return tableModelStudent;
    }
    
    public ItemAndStudentList getItemAndStudentList()
    {
        return itemAndStudentList;
    }
    
    public void getItemUI(int selectedRow)
    {
        itemUI = new ItemUI(this, selectedRow);
        itemListUI.setVisible(false);
        itemUI.setVisible(true);
    }
    
    public void getStudentUI(int selectedRow)
    {
        studentUI = new StudentUI(this, selectedRow);
        studentListUI.setVisible(false);
        studentUI.setVisible(true);
    }
    
    public void itemUIDeleteButton()
    {
        itemDeleted = true;
    }
    
    public void studentUIDeleteButton()
    {
        studentDeleted = true;
    }
    
    public void itemUICheckoutButton(int selectedRow)
    {
        Date date1 = new Date();
        String ID = itemList.getItemList().get(selectedRow).getID();
        String Name = itemList.getItemList().get(selectedRow).getName();
        String studentID = studentList.getStudentList().get(selectedRow).getID();
        String firstName = studentList.getStudentList().get(selectedRow).getFirstName();
        String lastName = studentList.getStudentList().get(selectedRow).getLastName();
        int numOfItems = studentList.getStudentList().get(selectedRow).getNumOfItems();
        String itemUIID = itemUI.getStudentDisplayValue();
        String checkoutDate = date1.toString();
        
        for(int index = 0; index < studentList.getStudentList().size(); index++)
        {
            if(itemUIID.equals(studentList.getStudentList().get(index).getID()))
            {            
                studentList.getStudentList().remove(index);
                studentList.getStudentList().add(index, new Student(studentID, firstName, lastName, checkoutDate, "ITEM_CHECKED_OUT", numOfItems+1));
                itemList.getItemList().remove(selectedRow);
                itemList.getItemList().add(selectedRow, new Item(ID, Name, "Checked Out", date1.toString(), "NOT_YET_RETURNED"));
                itemAndStudentList.getItemAndStudentList().remove(selectedRow);
                itemAndStudentList.getItemAndStudentList().add(selectedRow, new ItemAndStudent(new Item(ID, Name, "Checked Out", date1.toString(), "NOT_YET_RETURNED"), studentList.getStudentList().get(index)));
            }           
        }
        itemListUI = new ItemListUI(this);
        itemUICloseButton();
    }
    
    public void itemUIReturnButton(int selectedRow)
    {
        Date date1 = new Date();
        String ID = itemList.getItemList().get(selectedRow).getID();
        String Name = itemList.getItemList().get(selectedRow).getName();
        String studentID = studentList.getStudentList().get(selectedRow).getID();
        String firstName = studentList.getStudentList().get(selectedRow).getFirstName();
        String lastName = studentList.getStudentList().get(selectedRow).getLastName();
        int numOfItems = studentList.getStudentList().get(selectedRow).getNumOfItems();
        String checkoutDate = itemList.getItemList().get(selectedRow).getCheckoutDate();
        String returnDate = date1.toString();
        
        
        itemList.getItemList().remove(selectedRow);
        itemAndStudentList.getItemAndStudentList().remove(selectedRow);
        itemList.getItemList().add(selectedRow, new Item(ID, Name, "Returned", checkoutDate, returnDate));
        itemAndStudentList.getItemAndStudentList().add(selectedRow, new ItemAndStudent(itemList.getItemList().get(selectedRow), new Student(studentID, firstName, lastName, checkoutDate, returnDate, numOfItems)));
        itemListUI = new ItemListUI(this);
        itemUICloseButton();
    }
    
    public void itemUISaveButton(int selectedRow)
    {    
        if (itemDeleted == true)
        {
            ArrayList<Item> list = itemList.getItemList();
            ArrayList<ItemAndStudent> list2 = itemAndStudentList.getItemAndStudentList();
            list.remove(selectedRow);
            list2.remove(selectedRow);
            itemListUI = new ItemListUI(this);
        }
        else if (newItem != false)
        {
            for(int index = 0; index < studentList.getStudentList().size(); index++)
            {
                if(itemUI.getStudentDisplayValue().equals(studentList.getStudentList().get(index).getID()))
                {
                    String checkoutDate = itemList.getItemList().get(selectedRow).getCheckoutDate();
                    String returnDate = itemList.getItemList().get(selectedRow).getReturnDate();
                    itemList.getItemList().remove(selectedRow);
                    itemAndStudentList.getItemAndStudentList().remove(selectedRow);
                    itemList.getItemList().add(selectedRow, new Item(itemUI.getIDDisplayValue(), itemUI.getNameDisplayValue(), itemUI.getStatusDisplayValue(), checkoutDate, returnDate));
                    itemAndStudentList.getItemAndStudentList().add(selectedRow, new ItemAndStudent(new Item(itemUI.getIDDisplayValue(), itemUI.getNameDisplayValue(), itemUI.getStatusDisplayValue(), checkoutDate, returnDate), studentList.getStudentList().get(index)));
                    itemListUI = new ItemListUI(this);
                }           
            }
            studentList.getStudentList().remove(getStudentList().size()-1);
        }
        else
        {
            for(int index = 0; index <= studentList.getStudentList().size()-1; index++)
            {
                if(itemUI.getStudentDisplayValue().equals(studentList.getStudentList().get(index).getID()))
                {
                    String checkoutDate = itemList.getItemList().get(selectedRow).getCheckoutDate();
                    String returnDate = itemList.getItemList().get(selectedRow).getReturnDate();
                    itemList.getItemList().remove(selectedRow);
                    itemAndStudentList.getItemAndStudentList().remove(selectedRow);
                    itemList.getItemList().add(selectedRow, new Item(itemUI.getIDDisplayValue(), itemUI.getNameDisplayValue(), itemUI.getStatusDisplayValue(), checkoutDate, returnDate));
                    itemAndStudentList.getItemAndStudentList().add(selectedRow, new ItemAndStudent(new Item(itemUI.getIDDisplayValue(), itemUI.getNameDisplayValue(), itemUI.getStatusDisplayValue(), checkoutDate, returnDate), studentList.getStudentList().get(index)));
                    itemListUI = new ItemListUI(this);
                }           
            }
        }
    }
    
    public void studentUISaveButton(int selectedRow)
    {
        ArrayList<Student> list = studentList.getStudentList();
        String checkoutDate = list.get(selectedRow).getCheckoutDate();
        String returnDate = list.get(selectedRow).getReturnDate();
        list.remove(selectedRow);
        if (studentDeleted != false)
        {
            studentListUI = new StudentListUI(this);
        }
        else if (newStudent == true)
        {
            Student student = new Student(studentUI.getIDDisplayValue(), studentUI.getFirstNameDisplayValue(), studentUI.getLastNameDisplayValue(), checkoutDate, returnDate, studentUI.getNumDisplayValue());
            list.add(selectedRow, student);
            studentListUI = new StudentListUI(this);
        }
        else
        {
            list.add(selectedRow, new Student(studentUI.getIDDisplayValue(), studentUI.getFirstNameDisplayValue(), studentUI.getLastNameDisplayValue(), checkoutDate, returnDate, studentUI.getNumDisplayValue()));
            studentListUI = new StudentListUI(this);
        }
    }
    
    public void studentUICloseButton()
    {
        studentUI.setVisible(false);
        studentListUI.setVisible(true);
    }
    
    public void itemListUINewButton()
    {
        ArrayList<Student> list1 = studentList.getStudentList();
        ArrayList<Item> list2 = itemList.getItemList();
        ArrayList<ItemAndStudent> list3 = itemAndStudentList.getItemAndStudentList();
        Item item = new Item("ID", "NAME", "STATUS", "NOT_CHECKED_OUT", "NOT_CHECKED_OUT");
        Student student = new Student("STUDENTID", "FIRSTNAME", "LASTNAME", "CHECKOUTDATE", "RETURNDATE", 0); 
        list1.add(student);
        list2.add(item);
        ItemAndStudent itemAndStudent = new ItemAndStudent(item, student);
        list3.add(itemAndStudent);
        newItem = true;
    }
    
    public void studentListUINewButton()
    {
        ArrayList<Student> list1 = studentList.getStudentList();
        Student student = new Student("STUDENTID", "FIRSTNAME", "LASTNAME", "CHECKOUTDATE", "RETURNDATE", 0); 
        list1.add(student);
        newStudent = true;
    }
    
    public void itemUICloseButton()
    {
        itemUI.setVisible(false);
        studentListUI = new StudentListUI(this);
        itemListUI.setVisible(true);
    }
    
    public void studentListUICloseButton()
    {
        studentListUI.setVisible(false);
        itemListUI.setVisible(true);
    }
    
    public void itemListUIStudentViewButton()
    {
        itemListUI.setVisible(false);
        studentListUI = new StudentListUI(this);
        studentListUI.setVisible(true);
    }
}
