package model.component;

/**
 *
 * @author skuarch
 */
public interface DataAccessObjectInterface {
    
    public <T> T getObject(long id, Class<T> c) throws Exception;
    
    public long createObject(Object object) throws Exception;
    
    public void updateObject(Object object) throws Exception;
    
    public void deleteObject(Object object) throws Exception;

}
