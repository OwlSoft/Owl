/**
 * Author: Manuel Tscholl
 * Date: 12.11.2011
 * 
 * Documentation of the object database framework can be found at: http://www.db4o.com/
 * 
 * This is a test class for db4o, all important database interactions can be found in this file
 * 
 * 
 * Conclusion:
 * Its easy to save and load objects from the database
 * 
 * update and delete may be tricky because the framework needs "ObjectSet" object to do this correctly (information from tutorial)
 * 
 */

package db4o;

import java.util.ArrayList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Main
{

    public static ObjectContainer _db;

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // the filename is the name of the database, if the database does not
        // exist it will be created
        _db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                "DB4OFILENAME");
        try
        {

            System.out.println();
            List<Pet> pets = new ArrayList<Pet>();
            Austrian manuel = new Austrian(pets, "Manuel", 123);

            // _db.store(manuel);

            Austrian newManuel = ((Austrian) _db.queryByExample(manuel).get(0));
            newManuel.getSlaves().add(new Pet("hansi"));
            _db.store(newManuel);

            Austrian newNewManuel = ((Austrian) _db.queryByExample(newManuel)
                    .get(0));
            //
            // System.out.println(newNewManuel.getSlaves().get(0).getName());

            //
            // _db.store(manuel);
            // Human humanManuel = new Human(pets, "Manuel");
            // _db.store(humanManuel);
            //
            // System.out.println("suche1");
            // Austrian suche1 = new Austrian(null, "Manuel", 0);
            //
            // for (Object item : _db.queryByExample(suche1))
            // {
            // System.out.println(((Austrian) item).getName() + ":"
            // + ((Austrian) item).getInsuranceNumber());
            // }
            //
            // System.out.println("suche2");
            // IHuman suche2 = new Human(null, "Manuel");
            // for (Object item : _db.queryByExample(suche2))
            // {
            // System.out.println(((IHuman) item).getName() + ":");
            // }

            // Test for Pilot --> this test does not have object references

            // save2TestingPilots();

            // listResult(loadAllPilots());

            // listResult(loadAllObjectsFromClass());

            // listResult(loadAllObjectsFromClassTypeSave());

            // listResult(getPilotByname("Rubens Barrichello"));

            // Test for Pets --> this test does have object references

            // List<Pet> pets = new ArrayList<Pet>();
            // pets.add(new Pet("Dana"));
            // Human manuel = new Human(pets, "Manuel");
            // _db.store(manuel);
            //
            // listResult(_db.query(Human.class));
            //
            // Human tempManuel = _db.query(Human.class).get(0);
            //
            // System.out.println(tempManuel.getName());
            // System.out.println(tempManuel.getSlaves().get(0).getName());
            //
            // listResult(_db.query(Human.class));
            //
            // tempManuel.getSlaves().get(0).setName("4711");
            //
            // _db.store(tempManuel);
            //
            // listResult(_db.query(Human.class));

        }
        finally
        {
            _db.close();
        }
    }

    /**
     * loading all objects which are like the example object (Pilot) because the
     * following prototype is empty we will recive all pilots
     * 
     * @return
     */
    public static ObjectSet loadAllPilots()
    {

        Pilot proto = new Pilot(null, 0);
        ObjectSet result = _db.queryByExample(proto);
        return result;
    }

    /**
     * Loads all example of the pilot class out of database
     * 
     * @return
     */
    public static ObjectSet loadAllObjectsFromClass()
    {
        return _db.queryByExample(Pilot.class);
    }

    /**
     * type save query
     * 
     * @return
     */
    public static List<Pilot> loadAllObjectsFromClassTypeSave()
    {
        return _db.query(Pilot.class);
    }

    /**
     * Saving objects to database works like this :-)
     */
    public static void save2TestingPilots()
    {

        Pilot pilot2 = new Pilot("Rubens Barrichello", 99);
        _db.store(pilot2);
        System.out.println("Stored " + pilot2);

        Pilot pilot1 = new Pilot("Michael Schumacher", 100);
        _db.store(pilot1);
        System.out.println("Stored " + pilot1);
    }

    /**
     * simply writes all elements in list to console
     * 
     * @param result
     */
    public static void listResult(List<?> result)
    {
        System.out.println(result.size());
        for (Object o : result)
        {
            System.out.println(o);
        }
    }

    public static ObjectSet<Object> getPilotByname(String name)
    {
        Pilot proto = new Pilot(name, 0);
        return _db.queryByExample(proto);
    }

    /**
     * adds points to a pilot
     * 
     * @param name
     */
    public static void updatePilot(String name)
    {
        ObjectSet result = _db.queryByExample(new Pilot(name, 0));
        Pilot found = (Pilot) result.next();
        found.addPoints(11);
        _db.store(found);
        System.out.println("Added 11 points for " + found);
    }

    /**
     * 
     * @param name
     */
    public static void deletePilot(String name)
    {
        ObjectSet result = _db.queryByExample(new Pilot(name, 0));
        Pilot found = (Pilot) result.next();
        _db.delete(found);
        System.out.println("Deleted " + found);
    }
}
