package itinventory;

public class Student 
{
    private final String ID;
    private final String firstName;
    private final String lastName;
    private final String checkoutDate;
    private final String returnDate;
    private final int numOfItems;

    public Student(String ID, String firstName, String lastName, String checkoutDate, String returnDate, int numOfItems) 
    {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
        this.numOfItems = numOfItems;
    }
    
    

    public String getID() 
    {
        return ID;
    }

    public String getFirstName() 
    {
        return firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public String getCheckoutDate() 
    {
        return checkoutDate;
    }

    public String getReturnDate() 
    {
        return returnDate;
    }

    public int getNumOfItems() 
    {
        return numOfItems;
    }

    @Override
    public String toString() 
    {
        return "Student{" + "ID=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", checkoutDate=" + checkoutDate + ", returnDate=" + returnDate + ", numOfItems=" + numOfItems + '}';
    }
    
    
}
