
public class ContactBST {//class that represent the BST data structure

    private BSTNode root, current;

    public ContactBST() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public boolean full() {
        return false;
    }

    public Contact retrieve() {
        return current.data;
    }

    public void addContact(String k, Contact x) { //method that add contact to the BST 
        BSTNode q = current;
        BSTNode newContact = new BSTNode(k, x);

        if (empty()) { //empty tree?
            root = current = newContact;
            System.out.println("\nContact added successfully!");
            return;
        }

        if (findKey(k)) { //ensure that the key has been exited. Since the name is the key, we want to make each contact's name unique.
            current = q;
            System.out.println("\nContact already exists!");
            return;
        }

        if (findPhoneNo(root, (x.getPhoneNumber()))) { //check if phone number is already exits or not, phone number should be uniqe
            System.out.println("\nPhone number already exists!");
            return;
        }

        //insert new contact sorting alphabetically using compareTo
        if ((current.key).compareTo(k) > 0) {
            current.left = newContact;
        } else {
            current.right = newContact;
        }

        current = newContact;
        System.out.println("\nContact added successfully!");
        return;
    }

    public boolean findKey(String key) {//method that search if reseving key is already exists or not
        BSTNode temp1 = root, temp2 = root;

        if (empty()) {
            return false;
        }

        while (temp1 != null) { //loop that will search around the tree, if key found the method return true, if not we will return false
            temp2 = temp1;

            if ((temp1.key).compareTo(key) == 0) {
                current = temp1;
                return true;
            }
            if ((temp1.key).compareTo(key) > 0) {
                temp1 = temp1.left;
            } else {
                temp1 = temp1.right;
            }
        }
        current = temp2;
        return false;
    }

    public boolean findPhoneNo(BSTNode temp, String PN) {//method that search if reseving phone number is already exists or notbusing in order traversal
        if (temp != null) {
            if ((temp.data).getPhoneNumber().equals(PN)) {
                return true; // Found the phone number
            }

            if ((temp.data).getPhoneNumber().compareTo(PN) > 0) {
                return findPhoneNo(temp.left, PN);
            } else {
                return findPhoneNo(temp.right, PN);
            }
        }

        return false; // Phone number not found
    }

    public void add(String k, Contact x) {//method that adding contacts to bst
        BSTNode newContact = new BSTNode(k, x);

        if (empty()) {
            root = current = newContact;
            return;
        }

        if ((current.key).compareTo(k) > 0) {
            current.left = newContact;
        } else {
            current.right = newContact;
        }

        current = newContact;
    }

    public void searchContacts(int searchCriteria, String value) {

        ContactBST matchingContactsList = searchContacts(root, searchCriteria, value);

        if (matchingContactsList != null) {
            System.out.println("Contact found!");
            printInOrder(matchingContactsList.root);
        } else {
            System.out.println("contact not found!");
        }
    }

    private ContactBST searchContacts(BSTNode temp, int searchCriteria, String value) {//search for contacts with comparable attributes (email, address, notes, etc.) that the user selects and saves in a new BST
        ContactBST matchingContacts = new ContactBST();

        if (temp != null) {
            switch (searchCriteria) {
                case 1:
                    if (((Contact) temp.data).getName().equalsIgnoreCase(value)) {
                        matchingContacts.add(temp.key, temp.data);
                        return matchingContacts;
                    }
                    break;
                case 2:
                    if (((Contact) temp.data).getPhoneNumber().equalsIgnoreCase(value)) {
                        matchingContacts.add(temp.key, temp.data);
                        return matchingContacts;
                    }
                    break;
                case 3:
                    if (((Contact) temp.data).getEmail().equalsIgnoreCase(value)) {
                        matchingContacts.add(temp.key, temp.data);
                    }
                    break;
                case 4:
                    if (((Contact) temp.data).getAddress().equalsIgnoreCase(value)) {
                        matchingContacts.add(temp.key, temp.data);
                    }
                    break;
                case 5:
                    if (((Contact) temp.data).getBirthday().equalsIgnoreCase(value)) {
                        matchingContacts.add(temp.key, temp.data);
                    }
                    break;
            }

            // recursively search the left and right subtrees
            ContactBST leftMatches = searchContacts(temp.left, searchCriteria, value);
            ContactBST rightMatches = searchContacts(temp.right, searchCriteria, value);

            // add matching contacts from the left, right subtree
            if (leftMatches != null && !leftMatches.empty()) {
                matchingContacts.add(leftMatches.root.key, leftMatches.root.data);
            }
            if (rightMatches != null && !rightMatches.empty()) {
                matchingContacts.add(rightMatches.root.key, rightMatches.root.data);
            }
        }

        return matchingContacts.empty() ? null : matchingContacts; // if matchingContacts is empty return null otherwise return matchingContacts
    }

    private void printInOrder(BSTNode temp) { //print Ascending(in-order) 

        if (temp != null) {
            printInOrder(temp.left);
            System.out.println(temp.data);
            printInOrder(temp.right);
        }

    }
    private boolean contactFound = false;

    public void searchByFirstName(String name) {
        if (root == null) {
            System.out.println("Empty List");
            return;
        }

        contactFound = false;

        searchByFirstName(root, name);

        if (!contactFound) {//if there is not conatct have first name same as user enterd, condition will be true
            System.out.println("There is no contact have a first name " + name);
        }
    }

    private void searchByFirstName(BSTNode p, String firstName) {//to search for a contact by a first name
        if (p == null) {
            // No contact found
            return;
        }

        searchByFirstName(p.left, firstName);

        String CName = p.key;
        String foundFirstName;

        int spaceIndex = CName.indexOf(' ');//search the first space in the contacts name

        if (spaceIndex != -1) {//check if contact name does not have a space
            foundFirstName = CName.substring(0, spaceIndex);
        } else {
            foundFirstName = CName;
        }

        if (foundFirstName.equalsIgnoreCase(firstName)) {

            if (!contactFound) {
                System.out.println("Contact found!");
                contactFound = true;
            }

            System.out.println(p.data.toString());
        }

        searchByFirstName(p.right, firstName);
    }

    public void deleteContact(String name, LinkedListADT<Event> EventList) {
        BSTNode temp = root;
        BSTNode parent = null;

        if (root == null) {
            System.out.println("Empty List");
            return;
        }

        // Search for the contact if there exists in BST of contacts or not
        while (temp != null) {
            if (temp.key.equals(name)) {
                break;
            }

            parent = temp;

            if (temp.key.compareTo(name) > 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }

        if (temp == null) {//contact does not exists?
            System.out.println("Contact not found.");
            return;
        }
        deleteNode(temp, parent, EventList);// if the contact found in the tree, then we call deleteNode to delete the contact from the tree

    }

    private void deleteNode(BSTNode node, BSTNode parent, LinkedListADT<Event> EventList) {
        BSTNode delContact = node;
        removeEventsForContact(delContact.data, EventList);

        if (node.left == null && node.right == null) { //if the contact have no child
            if (parent == null) {//if the contact have no child and its root at the same time
                root = null;
            } else if (parent.left == node) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (node.left == null) { //if the contact have one child from the right
            if (parent == null) {
                root = node.right;
            } else if (parent.left == node) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
        } else if (node.right == null) { //if the contact have one child from the left
            if (parent == null) {
                root = node.left;
            } else if (parent.left == node) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        } else { //if the contact have two child
            BSTNode successorParent = node;
            BSTNode successorNode = node.right;

            while (successorNode.left != null) {
                successorParent = successorNode;
                successorNode = successorNode.left;
            }

            if (successorParent != node) {
                successorParent.left = successorNode.right;
            } else {
                node.right = successorNode.right;
            }

            node.key = successorNode.key;
            node.data = successorNode.data;
        }

        System.out.println("Contact Removed successfully!");
    }

    private void removeEventsForContact(Contact contact, LinkedListADT<Event> EventList) {//method that will get all events/appointment inside the contact that i delete it and sent to method removeContactForEvent
        contact.findFirstEvInside();
        Node<Event> currentEventNode = contact.getEventsInside();

        while (currentEventNode != null) {
            Event currentEvent = currentEventNode.data;
            removeContactForEvent(contact, currentEvent, EventList);
            currentEventNode = currentEventNode.next;
        }
    }

    private void removeContactForEvent(Contact contact, Event event, LinkedListADT<Event> EventList) {//method that will delete event from eventsList if the contact i delete it is the only contact inside this event, if the event have more then conatcts inside it, the method just delete the conatct inside the event,And if its appointment its delete from eventsList since the appointment aloow to have one conatct 
        Node<Contact> currentContactNode = event.getContactsInside();
        Node<Contact> previousContactNode = null;

        while (currentContactNode != null) {
            if (currentContactNode.data.equals(contact)) {
                if (previousContactNode == null) {
                    // Contact is the first one in the list
                    event.setContactsInside(currentContactNode.next);
                } else {
                    previousContactNode.next = currentContactNode.next;
                }
                break;
            }
            previousContactNode = currentContactNode;
            currentContactNode = currentContactNode.next;
        }

        if (event.getContactsInside() == null) {
            // No contacts inside the event, delete the event from the list
            EventList.deleteEvent(event, EventList, contact);
        }
    }

}
