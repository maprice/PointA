package com.maprice.pointa;

import java.util.Collection;

public interface IPServerObject {


	public void save();
	public void saveAsync();
	
	public void remove(String pKey);
	public void removeAll(Collection<?> pCollection);
	
	public void delete();
	public void deleteAsync();
	
	public IPServerObject fetch();
	
	public boolean containsKey(String pKey);
	
	public void add(String pKey, Object pObject);
	public void addAll(String pKey, Collection<?> pCollection);
	
}
