package itinventory;

public class ItemAndStudent 
{
    private final Student student;
    private final Item item;

    public ItemAndStudent(Item item, Student student) 
    {
        this.student = student;
        this.item = item;
    }

    public Student getStudent() 
    {
        return student;
    }

    public Item getItem() 
    {
        return item;
    }
    
    
    
    
}
