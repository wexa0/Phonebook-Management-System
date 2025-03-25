
//A class Event is represent events or appointment that can be scheduled with a contact; each event has many information (title, date,time,etc.).
public class Event implements Comparable {//class Event Inherit interface comparable to use compareTo method

    private String eventTitle;
    private String dateAndTime;
    private String location;
    private Node<Contact> contactsInside;
    private Node<Contact> move;
    private boolean isAppointment;

    public Event(String eventTitle, String dateAndTime, String location) {//constructors
        this.eventTitle = eventTitle;
        this.dateAndTime = dateAndTime;
        this.location = location;
        this.contactsInside = null;
    }

//compareTo is a method we can use to compare two thing that we want(x.compareTo(y)) then returns value (positive, nigative , zero)
    //return positive if x > y
    //return negative if x < y
    //return zero if x = y
    public int compareTo(Event other) {//needed to sort event alphabetically (by title) in the Events list
        return this.eventTitle.compareTo(other.eventTitle);
    }
//this method connects each contact with the event associated with
//method will receive Contact that will be added to the specific event and associate with previous event if the event has more than one contact
//each evnet may have more than one contact 

    public void addContactsInside(Contact c) {
        Node<Contact> newContact = new Node<Contact>(c);
        if (contactsInside == null) {//event do not have any contact before?
            contactsInside = newContact;
        } else {
            newContact.next = contactsInside;
            contactsInside = newContact;
        }
        c.addEventsInside(this);//to add certain event to contact c (from the parameter)
    }

    @Override
    public String toString() {
        return "Title: " + eventTitle + "\n"
                + "date And Time: " + dateAndTime + "\n"
                + "location: " + location;
    }

    public void findFirstCoInside() {//this method help in the classes worked as a user, to give an indicate to first contact(work like the same as findfirst in linked list).
        move = contactsInside;
    }

    public void findNextCoInside() {//this method help in the classes worked as a user, to move to the next contact(if any),(work like the same as findnext in linked list)
        move = move.next;
    }

    public boolean lastConInside() {//mathod work as a sama as method last() in linkedlist
        return move.next == null;
    }

    //Setter and Getters:
    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Node<Contact> getContactsInside() {
        return this.contactsInside;
    }

    public void setContactsInside(Node<Contact> contactsInside) {
        this.contactsInside = contactsInside;
    }

    public boolean isIsAppointment() {
        return isAppointment;
    }

    public void setIsAppointment(boolean isAppointment) {
        this.isAppointment = isAppointment;
    }

    public void removeContact(Contact contact) {// Method to remove a specific contact from the event's list of contacts
        Node<Contact> current = contactsInside;
        Node<Contact> previous = null;

        while (current != null) {
            if (current.data.equals(contact)) {
                if (previous == null) {
                    contactsInside = current.next;
                } else {
                    previous.next = current.next;
                }
                break;
            }

            previous = current;
            current = current.next;
        }
    }

    public boolean hasContactsInside() {
        return contactsInside != null;
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
