package models;

public class Estacion {
	public Integer servidores;
	public Double tasaServicio;
	public Integer id;
	public Double tasaEntrada;
	public Double ro;
	public Double lq;
	public Double ls;
	public Double l;
	public Double wq;
	public Double ws;
	public Double w;
	
	public Estacion(Integer servidores,Double tasaServicio,Integer id){
		this.servidores=servidores;
		this.tasaServicio=tasaServicio;
		this.id=id;
	}
	
	public void setTasaEntrada(Double tasaEntrada){
		this.tasaEntrada=tasaEntrada;
		this.ro=tasaEntrada/(servidores*tasaServicio);
	}
	
	private Double calcularPiCero(){
		Double termino1=0.0;
		for(int i=0;i<servidores;i++){
			termino1+=Math.pow(servidores*ro, i)/factorial(i);
		}
		
		Double termino2=(Math.pow(servidores*ro, servidores))/(factorial(servidores)*(1-ro));
		
		return (1/(termino1+termino2));
	}
	
	
	private Double factorial(Integer i){
		Double r=1.0;
		for(int j=1;j<=i;j++){
			r=r*j;
		}
		return r;
	}
	
	public Double calcularProbabilidadMayorServidores(){
		Double s=(((Math.pow(servidores*ro, servidores))/(factorial(servidores)*(1-ro)))*calcularPiCero());
		return s;
	}
	
	public Double darLq(){
		Double r=(calcularProbabilidadMayorServidores()*ro)/(1-ro);
		lq=r;
		wq=lq/tasaEntrada;
		return r;
	}
	
	public Double darLs(){
		Double r=servidores*ro;
		ls=r;
		ws=ls/tasaEntrada;
		return r;
	}
	
	public Double darL(){
		Double r=darLq()+darLs();
		l=r;
		w=l/tasaEntrada;
		return r;
	}
	

}
