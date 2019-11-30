package com.mx.base.abstractions;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CatalogModelList<T> extends ArrayList<T> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="_PK")
	private long pk;
	
	@Column(name="_PK_REF")
	private long pkRef;

	
    public void push(T o) {
        add(o);
    }

    public T pop() {
        return remove(size() - 1);
    }

    public boolean empty() {
        return size() == 0;
    }

    public T peek() {
        return get(size() - 1);
    }	
	
	
}
