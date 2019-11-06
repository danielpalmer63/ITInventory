package itinventory;

import java.util.ArrayList;

public class ItemList 
{
    private final ArrayList<Item> itemList;
    
    public ItemList()
    {
        itemList = new ArrayList<>();
        addItemsToList();
    }
    
    private void addItemsToList()
    {
        getItemList().add(new Item("ABC123", "Lenovo Laptop", "Returned", "2/10/19(11:15)", "2/12/19(11:15)"));
        getItemList().add(new Item("123DFG", "Macbook Air", "Returned", "2/11/19(11:30)", "2/13/19(11:15)"));
        getItemList().add(new Item("789BHS", "Dell Laptop", "Returned", "2/12/19(1:15)","2/25/19(15:22"));
    }
    
    public ArrayList<Item> getItemList()
    {
        return itemList;
    }
}
