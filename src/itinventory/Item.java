package itinventory;

public class Item 
{
    private final String ID;
    private final String name;
    private final String status;
    private final String checkOutDate;
    private final String returnDate;

    public Item(String ID, String name, String status, String checkOutDate, String returnDate) 
    {
        this.ID = ID;
        this.name = name;
        this.status = status;
        this.checkOutDate = checkOutDate;
        this.returnDate = returnDate;
    }

    public String getID() 
    {
        return ID;
    }

    public String getName() 
    {
        return name;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getCheckoutDate() 
    {
        return checkOutDate;
    }

    public String getReturnDate() 
    {
        return returnDate;
    }   

    @Override
    public String toString() 
    {
        return "Item{" + "ID=" + ID + ", name=" + name + ", status=" + status + ", checkOutDate=" + checkOutDate + ", returnDate=" + returnDate + '}';
    } 
}
