package basics;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressBookDay14 {

    static Scanner sc = new Scanner(System.in);
    public Map<String,basics.Book> listOfBook = new HashMap<>();



    public static void main(String[] args) {

        AddressBookDay14 addBook = new AddressBookDay14();
        boolean temp =true;
        
        
        while(temp)
        {
            System.out.println("press 1 to Add New Address Book");
            System.out.println("press 2 to Find Duplicate Entry in Address Book");
            System.out.println("press 3 to Search Contact from a city");
            System.out.println("press 4 to Search Contact from a State");
            System.out.println("press 5 to Count By State");
            System.out.println("press 6 to Count  By City");
            System.out.println("press 7 to Sort & Print in Alphabetically Order");
            System.out.println("press 8 to Sort Contact By City");
            System.out.println("press 9 to Sort Contact By State");
            System.out.println("press 10 to Sort Contact By Zip Code");
            System.out.println("press 11 to Exit");
            System.out.println("Enter the choice : ");
            int option = sc.nextInt();
            
            // switch case
            
            switch (option){
                case 1: {
                    System.out.println("Enter the Name of Address Book: ");
                    String bookName = sc.next();
                    if(addBook.listOfBook.containsKey(bookName)){
                        System.out.println("The Address book Already Exists");
                        break;
                    }else {
                        addBook.addAddressBook(bookName);
                        break;
                    }
                }
                case 2:{
                    for (Map.Entry<String,Book> entry: addBook.listOfBook.entrySet()){
                        Book value = entry.getValue();
                        System.out.println("Address Book Name: "+entry.getKey());
                        value.checkDuplicate();
                    }
                }
                case 3:{
                    System.out.println("Enter Name of City: ");
                    String CityName = sc.next();
                   addBook.searchPersonByCity(CityName);
                    break;
                }
                case 4:{
                    System.out.println("Enter Name of State: ");
                    String StateName = sc.next();
                    addBook.searchPersonByState(StateName);
                    break;
                }
                case 5:{
                    System.out.println("Enter Name of State: ");
                    String StateName = sc.next();
                    addBook.CountByState(StateName);
                    break;
                }
                case 6:{
                    System.out.println("Enter Name of City: ");
                    String CityName = sc.next();
                    addBook.CountByCity(CityName);
                    break;
                }
                case 7:{
                    addBook.sortContactByName();
                    break;
                }
                case 8:{
                    addBook.sortContactByCity();
                    break;
                }
                case 9:{
                    addBook.sortContactByState();
                    break;
                }
                case 10:{
                    addBook.sortContactByZipCode();
                    break;
                }
                case 11:{
                    temp = false;
                    break;
                }
            }
        }
    }
    public void addAddressBook(String bookName){
        boolean temp = true;
        Book addBookObj = new Book();
        while(temp){
            System.out.println("press 1 to Add Contact");

            System.out.println("press 2 to Edit Contact");

            System.out.println("press 3 to Delete Contact");

            System.out.println("press 4 to Exit");

            System.out.println("Enter the Choice: ");

            int option = sc.nextInt();

            switch (option)
            {
                case 1: {
                        addBookObj.addContact();
                        break;
                }
                case 2: {
                    System.out.println("Enter the Person First name to edit details: ");
                    String person_name = sc.next();
                    boolean b = addBookObj.editContact(person_name);
                    if (b) {
                        System.out.println("Details Updated");
                    } else {
                        System.out.println("Contact Not Found");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter the Contact to be deleted:");
                    String Name = sc.next();
                    boolean b1 = addBookObj.deleteContact(Name);
                    if (b1) {
                        System.out.println("Details Deleted");
                    } else {
                        System.out.println("Contact Not Found");
                    }
                    break;
                }
                case 4: {
                    temp =false;
                    break;
                }
            }
        }
        listOfBook.put(bookName,addBookObj);
        System.out.println("Address Book Added Successfully");
    }
	
	// --------------search person---------------- 

    private void searchPersonByState(String stateName) {
        for(Map.Entry<String,Book> entry: listOfBook.entrySet()){
            Book value = entry.getValue();
            System.out.println("The Address Book: "+entry.getKey());
            value.getPersonNameByState(stateName);
        }
    }

    private void searchPersonByCity(String cityName) {
        for(Map.Entry<String,Book> entry: listOfBook.entrySet()){
            Book value = entry.getValue();
            System.out.println("The Address Book: "+entry.getKey());
            value.getPersonNameByCity(cityName);
        }
    }
	
	//------------- count function--------------
    public void CountByState(String state) {
        int count = 0;
        for(Map.Entry<String, Book> entry: listOfBook.entrySet()){
            for(int i=0;i<(entry.getValue()).contactList.size();i++)
            {
                SetGetContact contact= entry.getValue().contactList.get(i);

                if(state.equals(contact.getState()))
                {
                    count++;
                }

            }
        }
        System.out.println("Total Person Count in state "+state+": "+count);
    }
    public void CountByCity(String city) {
        int count=0;
        for(Map.Entry<String, Book> entry: listOfBook.entrySet())
        {
            for(int i=0;i<(entry.getValue()).contactList.size();i++)
            {
                SetGetContact d= (SetGetContact)entry.getValue().contactList.get(i);

                if(city.equals(d.getCity()))
                {
                    count++;
                }

            }
        }
        System.out.println("Total number of people in this city "+city+": "+count);
    }
	
	// -------- sort the contacts----------------- 

    private void sortContactByName() {
        for (Map.Entry<String,Book>entry:listOfBook.entrySet()){
            Book value = entry.getValue();
            List<SetGetContact> sortedList = value.contactList.stream().sorted(Comparator.comparing(SetGetContact::getFirst_name)).collect(Collectors.toList());

            for(SetGetContact contact:sortedList){
                System.out.println("First Name: "+contact.getFirst_name());
                System.out.println("Last Name: "+contact.getLast_name());
            }
        }
    }
    private void sortContactByZipCode() {
        for (Map.Entry<String,Book>entry:listOfBook.entrySet()){
            Book value = entry.getValue();
            List<SetGetContact> sortedList = value.contactList.stream().sorted(Comparator.comparing(SetGetContact::getZip_code)).collect(Collectors.toList());

            for(SetGetContact contact:sortedList){
                System.out.println("First Name: "+contact.getFirst_name());
                System.out.println("Last Name: "+contact.getLast_name());
            }
        }
    }

    private void sortContactByState() {
        for (Map.Entry<String,Book>entry:listOfBook.entrySet()){
            Book value = entry.getValue();
            List<SetGetContact> sortedList = value.contactList.stream().sorted(Comparator.comparing(SetGetContact::getState)).collect(Collectors.toList());

            for(SetGetContact contact:sortedList){
                System.out.println("First Name: "+contact.getFirst_name());
                System.out.println("Last Name: "+contact.getLast_name());
            }
        }
    }

    private void sortContactByCity() {
        for (Map.Entry<String,Book>entry:listOfBook.entrySet()){
            Book value = entry.getValue();
            List<SetGetContact> sortedList = value.contactList.stream().sorted(Comparator.comparing(SetGetContact::getCity)).collect(Collectors.toList());

            for(SetGetContact contact:sortedList){
                System.out.println("First Name: "+contact.getFirst_name());
                System.out.println("Last Name: "+contact.getLast_name());
            }
        }
    }
}