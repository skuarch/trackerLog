package model.database;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * HibernateConnection, please don't create an object only use a
 * instance. example of use "new HibernateConnection().method();"
 *
 * @author skuarch
 */
public final class HibernateConnection {

    private Session session = null;
    private SessionFactory sessionFactory = null;
    private static final String PARAMETER_OBJECT_IS_NULL = "the parameter object is null";

    //==========================================================================    
    public HibernateConnection(SessionFactory sessionFactory) throws HibernateException {
        this.sessionFactory = sessionFactory;
    }

    //==========================================================================
    /**
     * save object in database.
     *
     * @param object Object
     * @return long (id)
     * @throws HibernateException if something is wrong
     */
    public long createObject(Object object) throws HibernateException  {

        if (object == null) {
            throw new IllegalArgumentException(PARAMETER_OBJECT_IS_NULL);
        }

        long id = 0;

        try {

        	openSession();
            id = (long) session.save(object);
            commitTransaction();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return id;

    } // end create

    //==========================================================================
    /**
     * delete object in database.
     *
     * @param object Object
     * @throws HibernateException if something is wrong
     */
    public void deleteObject(Object object) throws HibernateException  {

        if (object == null) {
            throw new IllegalArgumentException(PARAMETER_OBJECT_IS_NULL);
        }

        try {

        	openSession();
            session.delete(object);
            commitTransaction();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }
    } // end delete

    //==========================================================================    
    /**
     * execute an named query.
     *
     * @param <T> type
     * @param queryName String
     * @param c class
     * @return List
     * @throws HibernateException if something is wrong
     */
    @SuppressWarnings("unchecked")
	public <T> List<T> executeNamedQuery(String queryName, Class<T> c) throws HibernateException  {

        if (queryName == null || queryName.length() < 1) {
            throw new NullPointerException("queryName is null or empty");
        }

        if (c == null) {
            throw new NullPointerException("c is null");
        }

        Query query;
        List<T> list = null;

        try {

            query = session.getNamedQuery(queryName);
            query.setProperties(c);
            list = query.list();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return list;

    } // end executeQuery

    //==========================================================================
    /**
     *
     * @param <T> type
     * @param queryName String
     * @param parameters HashMap
     * @param c class
     * @return List
     * @throws HibernateException if something is wrong.
     */
    @SuppressWarnings("unchecked")
	public <T> List<T> executeNamedQuery(String queryName, Map<String, String> parameters, Class<T> c) throws HibernateException {

        if (queryName == null || queryName.length() < 1 || parameters.isEmpty() || c == null) {
            throw new IllegalArgumentException("incorrect parameters, one or more parameters are incorrect, empty or null");
        }

        String key;
        String value;
        Query query;
        List<T> list = null;

        try {

            query = session.getNamedQuery(queryName);
            query.setProperties(c);

            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                query.setString(key, value);
            }

            list = query.list();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return list;

    } // end query

    //==========================================================================
    /**
     * return the hole list of objects.
     *
     * @param <T> type
     * @param c Class
     * @return Generic Object.
     * @throws HibernateException if something is wrong
     */
    @SuppressWarnings("unchecked")
	public <T> List<T> getList(Class<T> c) throws HibernateException {

        if (c == null) {
            throw new IllegalArgumentException(PARAMETER_OBJECT_IS_NULL);
        }

        List<T> list = null;

        try {

            list = session.createQuery("from " + c.getCanonicalName()).list();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return list;

    } // end getList

    //==========================================================================
    /**
     * find object in the data base;
     *
     * @param <T> type
     * @param id long
     * @param c Class
     * @return Generic Object.
     * @throws HibernateException if something is wrong
     */
    @SuppressWarnings("unchecked")
	public <T> T getObject(long id, Class<T> c) throws HibernateException {

        if (c == null) {
            throw new IllegalArgumentException(PARAMETER_OBJECT_IS_NULL);
        }

        if (id < 1) {
            throw new IllegalArgumentException("the parameter id is less than 1");
        }

        T t = null;

        try {

            t = (T) session.get(c, id);
            session.beginTransaction().commit();

        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

        return t;

    } // end get

    //==========================================================================
    /**
     * update a object in database.
     *
     * @param object Object
     * @throws HibernateException if something is wrong
     */
    public void updateObject(Object object) throws HibernateException {

        if (object == null) {
            throw new IllegalArgumentException(PARAMETER_OBJECT_IS_NULL);
        }

        try {
            session.update(object);
            session.beginTransaction().commit();
        } catch (HibernateException he) {
            throw he;
        } finally {
            closeSession();
        }

    } // end update
    
    //==========================================================================
    private void openSession() {

    	if(sessionFactory == null){
    		throw new NullPointerException("sessionFactory is null");
    	}
    	
        try {

            session = sessionFactory.openSession();

        } catch (HibernateException he) {
            closeSession();
            throw he;
        }
    } // end startSession

    //==========================================================================
    private void commitTransaction() {

        try {

            session.beginTransaction().commit();

        } catch (HibernateException he) {
            closeSession();
            throw he;
        }

    }

    //==========================================================================
    private void closeSession() {
    	if (session != null && session.isOpen()) {
            session.close();
        }
    }


} // end class
