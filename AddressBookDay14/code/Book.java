package basics;

import java.util.*;
import java.util.stream.Collectors;

public class Book {
    static {
		  System.out.println("\t \t \t\t"+"WELCOME TO THE ADDRESS BOOK MANAGER");
	  }
    static Scanner sc = new Scanner(System.in);

    ArrayList<SetGetContact> contactList;
    public Map<String,ArrayList<SetGetContact>> ContactByState;
    public Map<String,ArrayList<SetGetContact>> ContactByCity;

    
    public Book() {
        contactList = new ArrayList<>();
        ContactByState = new HashMap<>();
        ContactByCity = new HashMap<>();
    }
     public ArrayList<SetGetContact> addContact(){

        System.out.println("Enter First Name: ");
        String firstname = sc.next();

        checkDuplicate();

        System.out.println("Enter last name: ");
        String lastname = sc.next();

        System.out.println("Enter Address: ");
        String address = sc.next();

        sc.nextLine();

        System.out.println("Enter City: ");
        String city = sc.next();

        System.out.println("Enter State: ");
        String state =sc.next();

        System.out.println("Enter Zip Code: ");
        int zip = sc.nextInt();

        System.out.println("Enter Phone Number:");
        long phonenumber = sc.nextLong();

        System.out.println("Enter Email: ");
        String email = sc.next();

        SetGetContact contactObj = new SetGetContact(firstname,lastname,address,city,state,zip,phonenumber,email);

        contactList.add(contactObj);

        if(!ContactByState.containsKey(state)){
            ContactByState.put(state,new ArrayList<SetGetContact>());
        }
        ContactByState.get(state).add(contactObj);

        if(!ContactByCity.containsKey(city)){
            ContactByCity.put(city,new ArrayList<>());
        }
        ContactByCity.get(city).add(contactObj);

        return contactList;
    }
     
     
     
     
    public void setList(ArrayList<SetGetContact> list)
    {
        this.contactList = contactList;
    }

    public ArrayList<SetGetContact> getContact_list() {
        return contactList;
    }

    
   

    public boolean editContact(String Name)
    {
        int flag = 0;
        for(SetGetContact contact: contactList)
        {
            if(contact.getFirst_name().equals(Name))
            {
                System.out.println("Enter the detail which needs to be updated:");

                System.out.println("press 1 .First Name");
                System.out.println("press 2 .Last Name");
                System.out.println("press 3 .Address");
                System.out.println("press 4 .City");
                System.out.println("press 5 .State");
                System.out.println("press 6 .ZipCode");
                System.out.println("press 7 .Phone Number");

                System.out.println("Choose Option");

                int choice = sc.nextInt();
                
                // switch case
                switch(choice)
                {
                    case 1:
                    {
                        System.out.println("Enter First Name: ");
                        String firstname = sc.next();
                        contact.setFirst_name(firstname);
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Enter last name: ");
                        String lastname = sc.next();
                        contact.setLast_name(lastname);
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Enter Address: ");
                        String address = sc.next();
                        contact.setAddress(address);
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Enter City: ");
                        String city = sc.next();
                        contact.setCity(city);
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Enter State: ");
                        String state =sc.next();
                        contact.setState(state);
                        break;
                    }
                    case 6:
                    {
                        System.out.println("Enter Zip Code: ");
                        int zip = sc.nextInt();
                        contact.setZip_code(zip);
                        break;
                    }
                    case 7:
                    {
                        System.out.println("Enter Phone Number:");
                        long phonenumber = sc.nextLong();
                        contact.setPhone_number(phonenumber);
                        break;
                    }
                }

                flag = 1;
                break;
            }
        }
        if(flag==1)
            return true;
        else
            return false;
    }
    
    // delete contacts 
    public boolean deleteContact(String name) {
        int flag = 0;
        for(SetGetContact contact: contactList)
        {
            if(contact.getFirst_name().equals(name))
            {
                contactList.remove(contact);
                flag = 1;
                break;
            }
        }
        if(flag==1)
            return true;
        else
            return false;
    }

    // check
    public void checkDuplicate(){
        Set<String> ContactSet = new HashSet<>();
        Set<SetGetContact> FilterSet = contactList.stream().filter( n -> !ContactSet.add(n.getFirst_name())).collect(Collectors.toSet());

        for(SetGetContact contact:FilterSet){
            System.out.println("The Duplicate Contact is: "+contact.getFirst_name()+" "+contact.getLast_name());
        }
    }
    
    
    // get person name 

    public void getPersonNameByState(String State) {
        List<SetGetContact> list  = contactList.stream().filter(findState ->findState.getCity().equals(State)).collect(Collectors.toList());
        for(SetGetContact contact: list){
            System.out.println("First Name: "+contact.getFirst_name());
            System.out.println("Last Name: "+contact.getLast_name());
        }

    }

    public void getPersonNameByCity(String cityName) {
        List<SetGetContact> list  = contactList.stream().filter(city ->city.getCity().equals(cityName)).collect(Collectors.toList());
        for(SetGetContact contact: list){
            System.out.println("First Name: "+contact.getFirst_name());
            System.out.println("Last Name: "+contact.getLast_name());

        }
    }
}