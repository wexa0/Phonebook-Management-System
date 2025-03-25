//A class contact is represented a single contact; each contact has many information (name, phone number,email,etc.).

public class Contact implements Comparable<Contact> {//class contact Inherit interface comparable to use compareTo method

    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String address;
    private String birthday;
    private String notes;
    private Node<Event> move;
    private Node<Event> EventsInside;

    public Contact(String name, String phoneNumber, String emailAddress, String address, String birthday, String notes) {//constructors

        this.name = name;

        this.phoneNumber = phoneNumber;

        this.emailAddress = emailAddress;

        this.address = address;

        this.birthday = birthday;

        this.notes = notes;

        this.EventsInside = null;

    }

//this method connects each event/appointment with the contact associated with
//method will receive events that will be added to the specific contact and associate with previous events if the contact has more than one event.
//each contact may have one or more events   
    public void addEventsInside(Event x) {
        Node<Event> newEvent = new Node<Event>(x);
        if (EventsInside == null) {//contact do not have any event?

            EventsInside = newEvent;

        } else {

            newEvent.next = EventsInside;//if the contact have more then one Event Inside it, event nodes connect to each other like a list inside their own contact.

            EventsInside = newEvent;
        }

    }

    @Override

    //compareTo is a method we can use to compare two thing that we want(x.compareTo(y)) then returns value (positive, nigative , zero)
    //return positive if x > y
    //return negative if x < y
    //return zero if x = y
    public int compareTo(Contact other) {//needed to sort contacts alphabetically (by name) in the contacts BST
        return this.name.compareTo(other.name);
    }

    @Override

    public String toString() {

        return "name: " + name + "\n"
                + "phoneNumber: " + phoneNumber + "\n"
                + "emailAddress: " + emailAddress + "\n"
                + "address: " + address + "\n"
                + "birthday: " + birthday + "\n"
                + "notes: " + notes + "\n";

    }

    //Setters and Getters
    public String getName() {

        return name;

    }

    public String getPhoneNumber() {

        return phoneNumber;

    }

    public String getEmail() {

        return emailAddress;

    }

    public String getAddress() {

        return address;

    }

    public String getBirthday() {

        return birthday;

    }

    public String getNotes() {

        return notes;

    }

    public Node<Event> getEventsInside() {

        return EventsInside;

    }

    public void setEventsInside(Node<Event> EventsInside) {

        this.EventsInside = EventsInside;

    }

    public void findFirstEvInside() {//this method help in the classes worked as a user, to give an indicate to first contact(work like the same as findfirst in linked list).

        move = EventsInside;

    }

    public void findNextEvInside() {//this method help in the classes worked as a user, to move to the next contact(if any),(work like the same as findnext in linked list)

        move = move.next;

    }

    public boolean lastEvnInside() {//mathod work as a same as method last() in linkedlist

        return move.next == null;

    }

    public boolean hasEvents() {
        return EventsInside != null;
    }
}
