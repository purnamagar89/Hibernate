package com.raj.hibernate;

public class OneToManyCertificate {
	private int id;
	private String name;
	public OneToManyCertificate(){
		
	}
	public OneToManyCertificate(String name){
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
		OneToManyCertificate obj2 = (OneToManyCertificate)obj;
		if((this.getId() == obj2.getId()) && (this.name.equals(obj2.getName()))){
			return true;
		}
		return false;
	}
	public int hashcode(){
		int tmp = 0;
		return tmp = (id+name).hashCode();
	}
}
