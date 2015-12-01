package model.component;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.database.HibernateConnection;

/**
 * Data Access Object Generic.
 *
 * @author skuarch
 */
@Component
public class DataAccessObject implements DataAccessObjectInterface {

	@Autowired
	private SessionFactory sessionFactory;
	
	//==========================================================================
	public DataAccessObject() {		
	}
	
    //==========================================================================
    @Override
    public <T> T getObject(long id, Class<T> c) throws Exception {

        if (id < 1) {
            throw new IllegalArgumentException("id is less than 1");
        }

        T t = null;

        try {

            t = new HibernateConnection(sessionFactory).getObject(id, c);

        } catch (Exception e) {
            throw e;
        }

        return t;

    }

    //==========================================================================
    @Override
    public long createObject(Object object) throws Exception {

        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }

        long id;

        try {

            id = new HibernateConnection(sessionFactory).createObject(object);

        } catch (Exception e) {
            throw e;
        }

        return id;

    }
    
    //==========================================================================
    @Override
    public void updateObject(Object object) throws Exception {

        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }

        try {

            new HibernateConnection(sessionFactory).updateObject(object);

        } catch (Exception e) {
            throw e;
        }

    }
    
    
    //==========================================================================
    @Override
    public void deleteObject(Object object) throws Exception {

        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }

        try {

            new HibernateConnection(sessionFactory).deleteObject(object);

        } catch (Exception e) {
            throw e;
        }

    }
    
}
