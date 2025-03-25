
import java.util.Scanner;

public class Phonebook {

    static Scanner input = new Scanner(System.in);
    static LinkedListADT<Event> EventList = new LinkedListADT<Event>();//A linked list stores Event
    static ContactBST contactTree = new ContactBST();//A BST stores contacts

    public static void main(String[] args) {
        int choice;
        System.out.println("Welcome to the LinkedTreePhonebook!");
        do {//Command-line menu
            System.out.println("""
                               \nPlease choose an option:
                               1. Add a contact
                               2. Search for a contact
                               3. Delete a contact   
                               4. Schedule an event/appointment  
                               5. Print event details 
                               6. Print contacts by first name
                               7. Print all events alphabetically
                               8. Exit\n""");

            System.out.println("Enter your choice:");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1://Add a contact
                    newContact();
                    break;
                case 2://Search for a contact
                    searchContact();
                    break;
                case 3://Delete a contact  
                    System.out.println("To remove a contact, write its full name:");
                    String name = input.nextLine();
                    contactTree.deleteContact(name, EventList);

                    break;
                case 4:// Schedule an event/appointment 
                    System.out.println("Enter type:");
                    System.out.println("1.event\n2.appointment");

                    System.out.println("Enter your choice: ");
                    int ch = input.nextInt();

                    switch (ch) {
                        case 1:
                        case 2:
                            newEvent(ch);
                            break;
                        default:
                            System.out.println("Invalid choice");
                    }
                    break;
                case 5://print event details by select critira(Contact name/Event title)

                    System.out.println("Enter search criteria: ");
                    System.out.println("1.contact name");
                    System.out.println("2.Event title");
                    System.out.print("\nEnter your choice: ");
                    int Option = input.nextInt();
                    input.nextLine();

                    switch (Option) {
                        case 1:
                            System.out.println("\nEnter the Contact name:");
                            String cn = input.nextLine();   //cn=contact name
                            if (!contactTree.findKey(cn)) {
                                System.out.println("There is no event for this Contact");
                            } else {

                                System.out.println("Contact name: " + cn);
                                printEvenstForContact();
                            }
                            break;

                        case 2:
                            System.out.print("\nEnter the event title: ");
                            String eventTitle = input.nextLine();

                            eventsSharingTitle(EventList, eventTitle);
                            break;

                        default:
                            System.out.println("Invalid choice");
                    }
                    break;

                case 6://Print contacts by first name

                    System.out.println("Enter the first name: ");
                    String shareName = input.nextLine();

                    searchByFristName(shareName);
                    break;

                case 7://Print all events alphabetically
                    printEvent(EventList);
                    break;
                case 8://Exit

                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 8);
    }

    public static void newContact() {//method for taking new contact information from the user to add to the contacts BST

        System.out.println("Enter the contact's name:");
        String name = input.nextLine();
        System.out.println("Enter the contact's phone number:");
        String phone = input.nextLine();
        System.out.println("Enter the contact's email address:");
        String email = input.nextLine();
        System.out.println("Enter the contact's address:");
        String address = input.nextLine();
        System.out.println("Enter the contact's birthday(Format = dd/mmm/yyyy):");
        String birthday = input.nextLine();
        System.out.println("Enter any notes for the contact:");
        String note = input.nextLine();

        contactTree.addContact(name, new Contact(name, phone, email, address, birthday, note));
    }

    public static void newEvent(int ch) { //method for taking new Event information from the user to add to the Events list
        String nameOfContact;
        System.out.println("Enter event title : ");
        input.nextLine();
        String title = input.nextLine();
        if (ch == 1) {
            System.out.println("Enter contact name sparated by a comma : ");
            nameOfContact = input.nextLine();
        } else {
            System.out.println("Enter contact name : ");
            nameOfContact = input.nextLine();
        }
        System.out.println("Enter event data and time (Format: MM/DD/YYYY HH:MM) : ");
        String dAndT = input.nextLine();
        System.out.println("Enter event location : ");
        String loc = input.nextLine();

        EventList.addEvents(nameOfContact, new Event(title, dAndT, loc), contactTree, ch);
    }

    public static void searchContact() { //method that allow to user to chose what looking for exactly .

        String value;
        int choice;
        System.out.println("\nEnter search criteria: ");
        System.out.println("""
                               1. Name
                               2. Phone Number
                               3. Email Address
                               4. Address
                               5. Birthday\n""");
        System.out.println("Enter your choice: ");
        choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1:
                System.out.println("\nEnter the contact's name:");
                value = input.nextLine();
                break;
            case 2:
                System.out.println("\nEnter the contact's phone number:");
                value = input.nextLine();
                break;
            case 3:
                System.out.println("\nEnter the contact's email address:");
                value = input.nextLine();
                break;
            case 4:
                System.out.println("\nEnter the contact's address:");
                value = input.nextLine();
                break;
            case 5:
                System.out.println("\nEnter the contact's birthday:");
                value = input.nextLine();
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }
        System.out.println();//Aesthetic space

        contactTree.searchContacts(choice, value);
    }

    public static void printEvenstForContact() {

        Node<Event> temp = new Node<Event>();

        contactTree.retrieve().findFirstEvInside();
        temp = contactTree.retrieve().getEventsInside();
        if (temp == null) {
            System.out.println("\nThere is no Event/Appointment for this contact!");

        } else {
            System.out.println("\nEvent found!");

        }
        while (temp != null) {

            System.out.println(temp.data);

            if (temp.data.isIsAppointment()) {
                System.out.println("type: " + "Appointment" + "\n");
            } else {
                System.out.println("type: " + "Event" + "\n");
            }

            temp = temp.next;
        }
    }

    public static void eventsSharingTitle(LinkedListADT<Event> EventList, String EVT) {
        if (EventList.empty()) {
            System.out.println("Empty List");
        } else {
            Node<Contact> temp = new Node<Contact>();
            boolean eventFound = false;

            EventList.findfirst();
            while (!EventList.last()) {
                if (EventList.retrieve().getEventTitle().equalsIgnoreCase(EVT)) {
                    EventList.retrieve().findFirstCoInside();
                    temp = EventList.retrieve().getContactsInside();

                    System.out.println("\n" + EventList.retrieve());
                    System.out.print("Contacts names: ");
                    while (temp != null) {
                        System.out.print(temp.data.getName());
                        if (temp.next != null) {
                            System.out.print(", ");
                        }
                        temp = temp.next;
                    }
                    System.out.println();
                    if (EventList.retrieve().isIsAppointment()) {
                        System.out.println("type: " + "Appointment");
                    } else {
                        System.out.println("type: " + "Event");
                    }
                    eventFound = true;
                }

                EventList.findnext();
            }
            //for the last event
            if (EventList.retrieve().getEventTitle().equalsIgnoreCase(EVT)) {
                EventList.retrieve().findFirstCoInside();
                temp = EventList.retrieve().getContactsInside();

                System.out.println("\n" + EventList.retrieve());
                System.out.print("Contacts names: ");
                while (temp != null) {
                    System.out.print(temp.data.getName());
                    if (temp.next != null) {
                        System.out.print(", ");
                    }
                    temp = temp.next;
                }
                System.out.println();
                if (EventList.retrieve().isIsAppointment()) {
                    System.out.println("type: " + "Appointment");
                } else {
                    System.out.println("type: " + "Event");
                }
                eventFound = true;
            }

            if (!eventFound) {
                System.out.println("\nNo events found with the title: " + EVT);
            }
        }
    }

    public static void printEvent(LinkedListADT<Event> eventlist) { //method that print all event information available in events list alphabetically
        if (eventlist.empty()) {//there is no events in the list?
            System.out.println("Empty list!");
            return;
        }
        eventlist.findfirst();
        System.out.println("\nAll Events:");

        while (!eventlist.last()) {
            System.out.println();
            System.out.println(eventlist.retrieve());
            if (eventlist.retrieve().isIsAppointment()) {
                System.out.println("type: " + "" + "Appointment");
            } else {
                System.out.println("type: " + "" + "Event");
            }
            eventlist.findnext();
        }

        System.out.println("\n " + eventlist.retrieve());

        if (eventlist.retrieve().isIsAppointment()) {
            System.out.println("type: " + "" + "Appointment");
        } else {
            System.out.println("type: " + "" + "Event");
        }
    }

    public static void searchByFristName(String name) {//method that print all contacts that have the same first name by calling search metode from ContactBST class
        contactTree.searchByFirstName(name);
    }

}
