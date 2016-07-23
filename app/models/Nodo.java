package models;

import java.util.ArrayList;


public class Nodo {
	
	public ArrayList<Integer>decisiones;
	public Nodo(Integer numeroTrabajadores){
		decisiones=new ArrayList<Integer>(numeroTrabajadores);
		for(int i=0;i<numeroTrabajadores;i++){
			Integer n=new Integer(0);
			decisiones.add(n);
		}
	}
	public String toString(){
		return decisiones.toString();
		
	}
	
	public Integer suma(){
		Integer s=0;
		for(int i=0;i<decisiones.size();i++){
			s+=decisiones.get(i);
		}
		return s;
	}
	public Integer sumaInvertida(){
		Integer s=0;
		for(int i=0;i<decisiones.size();i++){
			s+=(1-decisiones.get(i));
		}
		return s;
	}
}
