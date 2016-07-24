package models;

import java.util.ArrayList;


public class ProgramacionDinamica {
	Double[][]costos01;
	Double[][]costos12;
	Double[][]costos23;
	Double[][]costos34;
	Double[][]costos45;
	
	Double[][]f1;
	Double[][]f2;
	Double[][]f3;
	Double[][]f4;
	Double[][]f5;
	Double[][]f6;
	
	Integer[]pi1;
	Integer[]pi2;
	Integer[]pi3;
	Integer[]pi4;
	Integer[]pi5;
	Integer[]pi6;
	public Integer numeroTrabajadores;
	Integer[]g;
	Double[][] tt;
	
	public ProgramacionDinamica(Integer numeroTrabajadores, Integer[]g,Double [][]tt){
		this.numeroTrabajadores=numeroTrabajadores;
		this.g=g;
		this.tt=tt;
		System.out.println("tamano 1: ");
	}
	
	private ArrayList<ArrayList<Nodo>> crearNodos(){
		ArrayList<Nodo> nodos=new ArrayList<Nodo>();
		for(int i=0;i<(int) Math.pow(2, numeroTrabajadores);i++){
			Nodo n=new Nodo(numeroTrabajadores);
			nodos.add(n);
		}
		recursionNodo((int) Math.pow(2, numeroTrabajadores),nodos,0);
		
		ArrayList<ArrayList<Nodo>> nodosE=new ArrayList<ArrayList<Nodo>>();
		for(int i=0;i<5;i++){
			ArrayList<Nodo> n=new ArrayList<Nodo>();
			nodosE.add(n);
		}
		for(int i=0;i<nodos.size();i++){
			Nodo n=nodos.get(i);
			for(int j=0;j<5;j++){
				if(j+1<=n.suma() && 5-j-1<=n.sumaInvertida()){
					nodosE.get(j).add(n);

				}
			}
			
		}
		for(int i=0;i<5;i++){
			
			if(nodosE.get(i).size()==0){
				return null;
			}
		}
		
		return nodosE;
		
	}
	private void recursionNodo(int numero,ArrayList<Nodo> nodos,int c){
		ArrayList<Nodo> nodos1=new ArrayList<Nodo>();
		int n=(int) (numero/2.0);
		if(n>=1){
		for(int i=0;i<n;i++){
			nodos.get(i).decisiones.set(c, 0);
			nodos1.add(nodos.get(i));
			
		}
		int c1=c+1;
		recursionNodo(n,nodos1,c1);
		
		ArrayList<Nodo> nodos2=new ArrayList<Nodo>();
		for(int i=n;i<numero;i++){
			nodos.get(i).decisiones.set(c, 1);
			nodos2.add(nodos.get(i));
		}
		int c2=c+1;
		recursionNodo(n,nodos2,c2);
		}else{
			
		}
	}
	
	
	public Integer[][] determinarAsignacion(){
		ArrayList<ArrayList<Nodo>> nodos=crearNodos();
		if(nodos==null){return null;}

		f1=new Double [1][1];
		f2=new Double [nodos.get(0).size()][1];
		f3=new Double [nodos.get(1).size()][1];
		f4=new Double [nodos.get(2).size()][1];
		f5=new Double [nodos.get(3).size()][1];
		f6=new Double [nodos.get(4).size()][1];
		
		pi1=new Integer [1];
		pi2=new Integer [nodos.get(0).size()];
		pi3=new Integer [nodos.get(1).size()];
		pi4=new Integer [nodos.get(2).size()];
		pi5=new Integer [nodos.get(3).size()];
		pi6=new Integer [nodos.get(4).size()];
		
		costos01=new Double [1][nodos.get(0).size()];
		costos12=new Double [nodos.get(0).size()][nodos.get(1).size()];
		costos23=new Double [nodos.get(1).size()][nodos.get(2).size()];
		costos34=new Double [nodos.get(2).size()][nodos.get(3).size()];
		costos45=new Double [nodos.get(3).size()][nodos.get(4).size()];
		
		for(int i=0;i<nodos.get(0).size();i++){
			Double suma=0.0;
			for(int j=0;j<numeroTrabajadores;j++){
				if(nodos.get(0).get(i).suma()>g[0]){
				suma+=nodos.get(0).get(i).decisiones.get(j)*tt[0][j];
			}else{
				suma=null;
				break;
			}
			}
			costos01[0][i]=suma;
			//System.out.println(suma+"  "+nodos.get(0).get(i).toString());
		}
		
		
		for(int k=0;k<nodos.get(0).size();k++){
			for(int i=0;i<nodos.get(1).size();i++){

				Double suma=0.0;
				for(int j=0;j<numeroTrabajadores;j++){
					if(nodos.get(1).get(i).decisiones.get(j)-nodos.get(0).get(k).decisiones.get(j)>=0 && nodos.get(1).get(i).suma()-nodos.get(0).get(k).suma()>g[1]){
						suma+=(nodos.get(1).get(i).decisiones.get(j)-nodos.get(0).get(k).decisiones.get(j))*tt[1][j];
					}
					else{
						suma=null;
						break;
					}
				}
				//System.out.println(suma+"  "+nodos.get(0).get(k).toString()+" -- "+nodos.get(1).get(i).decisiones.toString());
				
				costos12[k][i]=suma;
			}
		}
		
		
		for(int k=0;k<nodos.get(1).size();k++){
			for(int i=0;i<nodos.get(2).size();i++){

				Double suma=0.0;
				for(int j=0;j<numeroTrabajadores;j++){
					if(nodos.get(2).get(i).decisiones.get(j)-nodos.get(1).get(k).decisiones.get(j)>=0 && nodos.get(2).get(i).suma()-nodos.get(1).get(k).suma()>g[2]){
						suma+=(nodos.get(2).get(i).decisiones.get(j)-nodos.get(1).get(k).decisiones.get(j))*tt[2][j];
					}
					else{
						suma=null;
						break;
					}
				}
				//System.out.println(suma+"  "+nodos.get(1).get(k).toString()+" -- "+nodos.get(2).get(i).decisiones.toString());
				
				costos23[k][i]=suma;
			}
		}
		
		for(int k=0;k<nodos.get(2).size();k++){
			for(int i=0;i<nodos.get(3).size();i++){

				Double suma=0.0;
				for(int j=0;j<numeroTrabajadores;j++){
					if(nodos.get(3).get(i).decisiones.get(j)-nodos.get(2).get(k).decisiones.get(j)>=0 && nodos.get(3).get(i).suma()-nodos.get(2).get(k).suma()>g[3]){
						suma+=(nodos.get(3).get(i).decisiones.get(j)-nodos.get(2).get(k).decisiones.get(j))*tt[3][j];
					}
					else{
						suma=null;
						break;
					}
				}
				//System.out.println(suma+"  "+nodos.get(2).get(k).decisiones.toString()+" -- "+nodos.get(3).get(i).decisiones.toString());
				
				costos34[k][i]=suma;
			}
		}
		
		
		for(int k=0;k<nodos.get(3).size();k++){
			for(int i=0;i<nodos.get(4).size();i++){

				Double suma=0.0;
				for(int j=0;j<numeroTrabajadores;j++){
					if(nodos.get(4).get(i).decisiones.get(j)-nodos.get(3).get(k).decisiones.get(j)>=0 && nodos.get(4).get(i).suma()-nodos.get(3).get(k).suma()>g[4]){
						suma+=(nodos.get(4).get(i).decisiones.get(j)-nodos.get(3).get(k).decisiones.get(j))*tt[4][j];
					}
					else{
						suma=null;
						break;
					}
				}
				//System.out.println(suma+"  "+nodos.get(3).get(k).decisiones.toString()+" -- "+nodos.get(4).get(i).decisiones.toString());
				
				costos45[k][i]=suma;
				
			}
		}

		ArrayList<Double[][]>costs=new ArrayList<Double[][]>();
		ArrayList<Double[][]>fs=new ArrayList<Double[][]>();
		ArrayList<Integer[]>pi=new ArrayList<Integer[]>();
		costs.add(costos01);
		costs.add(costos12);
		costs.add(costos23);
		costs.add(costos34);
		costs.add(costos45);
		
		fs.add(f1);
		fs.add(f2);
		fs.add(f3);
		fs.add(f4);
		fs.add(f5);
		fs.add(f6);
		
		pi.add(pi1);
		pi.add(pi2);
		pi.add(pi3);
		pi.add(pi4);
		pi.add(pi5);
		pi.add(pi6);
		System.out.println(funcionRecursivaPD(costs,0,0,fs,pi));
		System.out.println("tamano 2: ");
		Integer n1=pi1[0];
		Integer n2=pi2[pi1[0]];
		Integer n3=pi3[pi2[pi1[0]]];
		Integer n4=pi4[pi3[pi2[pi1[0]]]];
		Integer n5=pi5[pi4[pi3[pi2[pi1[0]]]]];
		Integer n6=pi6[pi5[pi4[pi3[pi2[pi1[0]]]]]];
		System.out.println("tamano 3: ");
		Integer epoca1=0;
		Integer epoca2=0;
		Integer epoca3=0;
		Integer epoca4=0;
		Integer epoca5=0;
		Integer [][]matrizResultado=new Integer[5][numeroTrabajadores];
		for(int k=0;k<numeroTrabajadores;k++){
			matrizResultado[0][k]=nodos.get(0).get(n1).decisiones.get(k);
			matrizResultado[1][k]=Math.max(0,nodos.get(1).get(n2).decisiones.get(k)-nodos.get(0).get(n1).decisiones.get(k));
			matrizResultado[2][k]=Math.max(0,nodos.get(2).get(n3).decisiones.get(k)-nodos.get(1).get(n2).decisiones.get(k));
			matrizResultado[3][k]=Math.max(0,nodos.get(3).get(n4).decisiones.get(k)-nodos.get(2).get(n3).decisiones.get(k));
			matrizResultado[4][k]=Math.max(0,nodos.get(4).get(n5).decisiones.get(k)-nodos.get(3).get(n4).decisiones.get(k));
			//System.out.println(matrizResultado[0][k]);
			
		}
		return matrizResultado;
		
	}
	
	public Double funcionRecursivaPD(ArrayList<Double[][]>costs,int e,int nod,ArrayList<Double[][]>fs,ArrayList<Integer[]>pi){
		Double max=-88888.0;
		Integer maxS=-1;
		String ruta="";
		if(e<5){
		for(int i=0;i<costs.get(e).length;i++){
			for (int j = 0; j < costs.get(e)[0].length; j++) {
				if(costs.get(e)[nod][j]!=null){
					Double fr=0.0;
					if(fs.get(e+1)[j][0]!=null && fs.get(e+1)[j][0]>0 ){
						fr=costs.get(e)[nod][j]+fs.get(e+1)[j][0];
					}else{
					 fr=costs.get(e)[nod][j]+funcionRecursivaPD(costs,e+1,j,fs,pi);
					}
				if(fr>=max){
					max=fr;
					maxS=j;
				}
				}
			}
		}
		fs.get(e)[nod][0]=max;
		pi.get(e)[nod]=maxS;
		return max;
		}else{
			f6[nod][0]=0.0;
			pi.get(e)[nod]=nod;
			return 0.0;
		}
	}
	


}
