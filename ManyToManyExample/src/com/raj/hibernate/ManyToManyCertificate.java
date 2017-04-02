package com.raj.hibernate;

public class ManyToManyCertificate {
	private int id;
	private String name;
	public ManyToManyCertificate(){
		
	}
	public ManyToManyCertificate(String name){
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean equals(Object obj){
		if(obj == null) return false;
		if(!this.getClass().equals(obj.getClass())) return false;
		ManyToManyCertificate obj2 = (ManyToManyCertificate) obj;
		if((this.id == obj2.getId()) && (this.name.equals(obj2.getName()))) return true;
		return false;
	}
	public int hashCode(){
		int tmp = 0;
		tmp = (id+name).hashCode();
		return tmp;
	}
}
