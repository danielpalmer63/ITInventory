package itinventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemAndStudentList 
{
    private final ArrayList<ItemAndStudent> itemAndStudentList;
    private final Controller controller;
    
    public ItemAndStudentList(Controller newController)
    {
        controller = newController;
        itemAndStudentList = new ArrayList<>();
        addItemsAndStudentsToList();
        addItemsAndStudentsToMap();
    }
    
    private void addItemsAndStudentsToList()
    {
        for(int i = 0; i <= controller.getItemList().size()-1; i++)
        {
            getItemAndStudentList().add(new ItemAndStudent(controller.getItem(i), controller.getStudent(i)));
        }
    }
    
    //This method exists to serve any additional program requesting information about the items a student currently has checked out. 
    //The results of this map are located in the command prompt.
    private void addItemsAndStudentsToMap()
    {
        Map<Student, Item> itemsAndStudentsMap = new HashMap<Student, Item>()
        {
            {
                for (int num = 0; num < controller.getStudentList().size(); num++)
                {
                    for(int index = 0; index < controller.getItemList().size(); index++)
                    {
                        Student student = controller.getStudentList().get(num);
                        
                        if (itemAndStudentList.get(index).getStudent() == student)
                        {
                            put(student, itemAndStudentList.get(index).getItem());
                        }
                    }    
                }
            }
        };
        
        for (Map.Entry<Student, Item> entry : itemsAndStudentsMap.entrySet()) 
        {
            System.out.println(entry.getKey().toString() + ":" + entry.getValue().toString());
        }
        
    }
    
    public ArrayList<ItemAndStudent> getItemAndStudentList()
    {
        return itemAndStudentList;
    }
}
