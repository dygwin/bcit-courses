package comp2526_assign1c_1b;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Handles the Person ArrayList, including methods to add new People,
 * remove People, and return the index of a Person in the ArrayList.
 * There is also a method to return a copy of the ArrayList as a Person
 * array, containing all of the Person objects in the storage.
 * </p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class Database
{
    /**
     * <p>
     * Stores all of the People that will be added into the array.
     * </p>
     */
    private List<Person> storage;
    
    /**
     * <p>
     * The constructor for the Database. It sets up the ArrayList of
     * People objects, to store all of the People in the storage.
     * </p>
     */
    public Database()
    {
        storage = new ArrayList<Person>();
    }

    /**
     * <p>
     * Adds the passed in Person object to the storage array, and returns
     * the index of the Person after being added.
     * </p>
     * 
     * @param person the Person object to be added to storage
     * @return the index of the Person that was just added to the storage
     */
    public int add(final Person person)
    {
        storage.add(person);
        
        return (storage.size() - 1);
    }
    
    /**
     * <p>
     * Creates a copy of the storage, with all of the Person objects in the
     * array. This copy is then returned.
     * </p>
     *
     * @return an array with all of the Person objects in the storage
     */
    public Person[] getAll() {
        // the Person array to return all of the Person object in the storage
        final Person[] copy;

        copy = new Person[storage.size()];
        storage.toArray(copy);
        
        return (copy);
    }

    /**
     * <p>
     * Creates a copy of the storage, with all of the Person objects in the
     * array. This copy is then returned.
     * </p>
     *
     * @param name the name of the Person object to remove
     * @return an array with all of the Person objects in the storage
     */
    public Person removeByName(final String name) {
        // the Person object to be removed and returned
        final Person person;
        // the index of the Person object that is removed
        final int index;
        
        index = lookupByName(name);
        
        if(index > -1) {
            person = storage.remove(index);
        }
        else {
            person = null;
        }
        
        return (person);
    }
    
    /**
     * <p>
     * First invokes the lookupByName() method to search through the array of
     * People to find the one with the same name. It will return the index of
     * the Person object, or -1 if they weren't found. If the index is -1, it
     * will return a null Person object. Otherwise, it will return the Person
     * object at that index.
     * </p>
     * 
     * @param name the name of the Person object to find
     * @return the Person object if they are found, otherwise a null Person
     */
    public Person findByName(final String name) {
        // the Person object to be returned
        final Person person;
        // the index of the Person, assigned by the lookupByName() method
        final int index;
        
        index = lookupByName(name);
        
        if(index > -1) {
            person = storage.get(index);
        }
        else {
            person = null;
        }
        
        return (person);
    }
    
    /**
     * <p>
     * A support method for the findByName() and removeByName() methods.
     * </p>
     * <p>
     * Does a case insensitive search for a Person object with the given name
     * by iterating through an array of People and checking their names. If 
     * the Person is found, their index in the array is returned. If they 
     * aren't found, -1 is returned.
     * </p>
     * 
     * @param name the name of the Person object to search for
     * @return the index of the Person object in the array, -1 if never found
     */
    private int lookupByName(final String name){
        // the index of the Person object in the array, default to -1
        int location;
        // counts the current position in the array
        int i;
        
        location = -1;
        i = 0;
        
        for(final Person person : storage) {
            if(person.getName().equals(name)) {
                location = i;
                break;
            }
            
            i++;
        }
        
        return (location);
    }
}
