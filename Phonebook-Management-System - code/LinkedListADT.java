
public class LinkedListADT<T> {//Linked list have a generic type, so we can use it for more then one type, we use it in our project to generate a linked list of event class and contact class

    private Node<T> head;
    private Node<T> current;

    public LinkedListADT() {
        head = current = null;
    }

    public boolean empty() {
        return head == null;
    }

    public void findfirst() {
        current = head;
    }

    public void findnext() {
        current = current.next;
    }

    public boolean full() {
        return false;
    }

    public boolean last() {
        return current.next == null;
    }

    public T retrieve() {
        return current.data;
    }

    public void update(T x) {
        current.data = x;

    }

    public void addEvents(String names, T x, ContactBST contactsList, int option) {//addEvents help to add event with one or multiple contacts
        String[] namesArray = names.split(", ");//to have array have a names entered by user when he/she enters multiple names spreated by comma

        if (namesArray.length == 1) {//user entered one contact name.
            addEvent(namesArray[0], x, contactsList, option);
            return;
        }
        // Print the resulting array
        for (int i = 0; i < namesArray.length; i++) {//user enterd multiple names.
            addEvent(namesArray[i], x, contactsList, option);
        }
    }

    public void addEvent(String name, T x, ContactBST contactsList, int option) {//method that added event to the events list
        Node<T> temp1 = head;
        Node<T> temp2 = head;

        if (!contactsList.findKey(name)) {//contact is exist?
            System.out.println("\nContact " + name + " is not found!");
            return;

        }
        if (option == 1) {//if the user entered 1 in the menu, attribut "isAppointment" set to be false, else "isAppointment" set to be true
            ((Event) x).setIsAppointment(false);
        } else if (option == 2) {
            ((Event) x).setIsAppointment(true);
        }

        Node<T> newEvent = new Node<T>(x);
        Node<T> temp = this.head;

        if (temp == null) { //events list is empty?
            this.head = this.current = newEvent;
            ((Event) x).addContactsInside(contactsList.retrieve());
            if (((Event) x).isIsAppointment() == false) {
                System.out.println("Event scheduled successfully!");
            } else {
                System.out.println("Appointment scheduled successfully!");
            }
            return;
        }

        boolean find = true;
        while (temp != null) { //check if there is conflict or not
            if (((Event) temp.data).getDateAndTime().equalsIgnoreCase(((Event) x).getDateAndTime())) {//if the statement was true, method will check if the event user tried to add is already exists (by check of the title name).
                if (((Event) temp.data).getEventTitle().equalsIgnoreCase(((Event) x).getEventTitle())) {//if the statment was true, thats mean event/appointment is already exists
                    if (((Event) x).isIsAppointment() == false) {//event can have more than one contact, unlike appointment its valid to add one conatct only
                         System.out.println("Event scheduled successfully!");
                        ((Event) temp.data).addContactsInside(contactsList.retrieve());
                    } else {
                        System.out.println("\nThe appointment you tried to add is alrady exists!");
                    }
                    return;

                } else {
                    find = false;
                }
            }
            temp = temp.next;
        }

        if (!find) {//if the event does not already exist and has a time conflict with the event added before.
            System.out.println("Conflict time! there is another event/appointment that have the same time and date. ");
            return;
        }

//to add events sorting by title (by using compareTo)
        if (((Event) x).compareTo(((Event) this.head.data)) <= 0) {//to add in the head
            newEvent.next = head;
            head = newEvent;
            ((Event) x).addContactsInside(contactsList.retrieve());
            if (((Event) x).isIsAppointment() == false) {
                System.out.println("Event scheduled successfully!");
            } else {
                System.out.println("Appointment scheduled successfully!");
            }

            return;
        }

        while (temp1 != null) {

            if (((Event) x).compareTo(((Event) temp1.data)) >= 0) {
                temp2 = temp1;
                temp1 = temp1.next;
            } else {
                newEvent.next = temp1;
                temp2.next = newEvent;
                current = newEvent;
                ((Event) x).addContactsInside(contactsList.retrieve());

                if (((Event) x).isIsAppointment() == false) {
                    System.out.println("Event scheduled successfully!");
                } else {
                    System.out.println("Appointment scheduled successfully!");
                }

                return;

            }
        }
        //to add event in the last of list
        newEvent.next = temp1;
        temp2.next = newEvent;
        current = newEvent;
        ((Event) x).addContactsInside(contactsList.retrieve());
        if (((Event) x).isIsAppointment() == false) {
            System.out.println("Event scheduled successfully!");
        } else {
            System.out.println("Appointment scheduled successfully!");
        }

    }

    public Contact SearchByName(String name, LinkedListADT<Contact> contactsList) {//method that search if the contact is already exists or not
        Node<Contact> temp = contactsList.head;

        while (temp != null) {
            if (((Contact) temp.data).getName().equalsIgnoreCase(name)) {
                return (Contact) temp.data;//if the contact is exists, method will return node of this contact
            } else {
                temp = temp.next;
            }
        }
        return null;

    }
   public void deleteEvent(Event p, LinkedListADT<Event> EventList, Contact contact) {//delete event/appontment from event list
    Node<Event> cur = EventList.head;
    Node<Event> previous = null;

    while (cur != null) {
        if (cur.data.equals(p)) {// Found the event to be deleted

            if (previous == null) {// Event is the first one in the list
                EventList.head = cur.next;
            } else {
                previous.next = cur.next;
            }
            return;
        }

        previous = cur;
        cur = cur.next;
    }

}


}
