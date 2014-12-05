package org.dataart.qdump.config;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

public class GlobalInterceptor extends EmptyInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4605249677380841090L;

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		System.out.println("On Flush");
		return super.onFlushDirty(entity, id, currentState, previousState,
				propertyNames, types);
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		System.out.println("On Save");
		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public void postFlush(Iterator entities) {
		System.out.println("Post Flush");
		super.postFlush(entities);
	}

	@Override
	public void preFlush(Iterator entities) {
		System.out.println("Pre Flush");
		super.preFlush(entities);
	}

	@Override
	public void afterTransactionBegin(Transaction tx) {
		System.out.println("After Begin");
		super.afterTransactionBegin(tx);
	}

	@Override
	public void afterTransactionCompletion(Transaction tx) {
		System.out.println("After Completion");
		super.afterTransactionCompletion(tx);
	}

	@Override
	public void beforeTransactionCompletion(Transaction tx) {
		System.out.println("Before Completion");
		super.beforeTransactionCompletion(tx);
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		System.out.println("On Delete");
		super.onDelete(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		System.out.println("On Load");
		return super.onLoad(entity, id, state, propertyNames, types);
	}

	@Override
	public Boolean isTransient(Object entity) {
		System.out.println("Is Transient");
		return super.isTransient(entity);
	}

	@Override
	public Object instantiate(String entityName, EntityMode entityMode,
			Serializable id) {
		System.out.println("instantiate");
		return super.instantiate(entityName, entityMode, id);
	}

	@Override
	public int[] findDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		System.out.println("Find Dirty");
		return super.findDirty(entity, id, currentState, previousState, propertyNames,
				types);
	}

	@Override
	public String getEntityName(Object object) {
		System.out.println(object.getClass().getCanonicalName());
		return super.getEntityName(object);
	}

	@Override
	public Object getEntity(String entityName, Serializable id) {
		System.out.println("Entity");
		return super.getEntity(entityName, id);
	}

	@Override
	public String onPrepareStatement(String sql) {
		System.out.println("Prepare Statement");
		return super.onPrepareStatement(sql);
	}

	@Override
	public void onCollectionRemove(Object collection, Serializable key)
			throws CallbackException {
		System.out.println("Collection Remove");
		super.onCollectionRemove(collection, key);
	}

	@Override
	public void onCollectionRecreate(Object collection, Serializable key)
			throws CallbackException {
		System.out.println("Collection Recreate");
		super.onCollectionRecreate(collection, key);
	}

	@Override
	public void onCollectionUpdate(Object collection, Serializable key)
			throws CallbackException {
		System.out.println("Collection Update");
		super.onCollectionUpdate(collection, key);
	}

	
}
