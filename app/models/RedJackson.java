package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RedJackson {

	public final static double[][] MATRIZ_PROBABILIDADES={{-0.99,0.0,0.0,0.0,0.0,0.0},{0.99,-0.95,0.03,0.01,0.0,0.03},{0.0,0.95,-0.99,0.0,0.0,0.0},{0.0,0.0,0.96,-0.95,0.0,0.0},{0.0,0.0,0.0,0.94,-1.0,0.0},{0.0,0.0,0.0,0.0,1.0,-1.0}};
	public final static double[][]VECTOR_INICIAL_DERECHO={{-20.0},{0.0},{0.0},{0.0},{0.0},{0.0}};
	
	public Estacion est1;
	public Estacion est2;
	public Estacion est3;
	public Estacion est4;
	public Estacion est6;
	public Double tasaEntrada;
	public Double lq_inf;
	public Double ls_inf;
	public Double l_inf;
	Integer cuelloB;
	
	public RedJackson(Integer []s,Double[]miu){
		System.out.println("ppppp");
		 est1=new Estacion(s[0],miu[0],1);
		 est2=new Estacion(s[1],miu[1],2);
		 est3=new Estacion(s[2],miu[2],3);
		 est4=new Estacion(s[3],miu[3],4);
		 est6=new Estacion(s[4],miu[5],6);
		System.out.println("ddfdfdf");
		double[][] minversa=gaussJordan(MATRIZ_PROBABILIDADES);
		
		double[][] lambdas1=multiplicar(minversa,VECTOR_INICIAL_DERECHO );
	
		Integer max=-1;
		Double maxS=-1.0;
		Estacion g=null;
		est1.setTasaEntrada(lambdas1[0][0]);
		if(est1.ro>maxS){
			max=0;
			maxS=est1.ro;
			g=est1;
		}
		est2.setTasaEntrada(lambdas1[1][0]);
		if(est2.ro>maxS){
			max=1;
			maxS=est2.ro;
			g=est2;
		}
		est3.setTasaEntrada(lambdas1[2][0]);
		if(est3.ro>maxS){
			max=2;
			maxS=est3.ro;
			g=est3;
		}
		est4.setTasaEntrada(lambdas1[3][0]);
		if(est4.ro>maxS){
			max=3;
			maxS=est4.ro;
			g=est4;
		}
		est6.setTasaEntrada(lambdas1[5][0]);
		if(est6.ro>maxS){
			max=5;
			maxS=est6.ro;
			g=est6;
		}
		cuelloB=max;
		System.out.println(max);
		double[][]m=new double[7][7];
		double[][]b=new double[7][1];
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				m[i][j]=MATRIZ_PROBABILIDADES[i][j];
			}
		}
		for(int i=0;i<7;i++){
			if(i!=max){
			m[6][i]=0.0;
			}else{
				m[6][i]=1.0;
			}
			m[i][6]=0.0;
		}
		m[0][6]=1.0;
		
		for(int j=0;j<6;j++){
			b[j][0]=0.0;
		}
		b[6][0]=0.95*g.servidores*g.tasaServicio;
		
		double[][]inversa=gaussJordan(m);
		double[][]tasasReales=multiplicar(inversa,b);
		est1.setTasaEntrada(tasasReales[0][0]);
		est2.setTasaEntrada(tasasReales[1][0]);
		est3.setTasaEntrada(tasasReales[2][0]);
		est4.setTasaEntrada(tasasReales[3][0]);
		est6.setTasaEntrada(tasasReales[5][0]);
		tasaEntrada=(tasasReales[6][0]);
		
		for(int i=0;i<7;i++){
			System.out.println(tasasReales[i][0]);
		}
		
		lq_inf=0.0;
		ls_inf=tasasReales[4][0]/miu[4];
		l_inf=ls_inf;
	}
	
	public Double darLqRed(){
		Double r=est1.darLq()+est2.darLq()+est3.darLq()+est4.darLq()+lq_inf+est6.darLq();
		return r;
	}
	public Double darLsRed(){
		Double r=est1.darLs()+est2.darLs()+est3.darLs()+est4.darLs()+ls_inf+est6.darLs();
		return r;
	}
	public Double darLRed(){
		Double r=est1.darL()+est2.darL()+est3.darL()+est4.darL()+l_inf+est6.darL();
		return r;
	}
	public Double darWqRed(){
		Double r=darLqRed()/tasaEntrada;
		return r;
	}
	public Double darWsRed(){
		Double r=darLsRed()/tasaEntrada;
		return r;
	}
	public Double darWRed(){
		Double r=darLRed()/tasaEntrada;
		return r;
	}
	

	// Tomado de la ancheta de algoritmos del equipo Math-O-matic
	private  double[][] gaussJordan(double[][] matriz)
	{
		
		if (matriz.length==0) throw new IllegalArgumentException("La matriz no puede ser vacia.");
		if (matriz.length!=matriz[0].length) throw new IllegalArgumentException("La matriz debe ser cuadrada.");
		int n=matriz.length,m=n*2;
		double epsilon=1e-12;
		double[][] extendida=new double[n][];
		for (int i=0; i<n; i++) 
		{
			extendida[i]=Arrays.copyOf(matriz[i],m);
			extendida[i][i+n]=1.0;
		}
		for (int i=0; i<n; i++)
		{
			int w=i;
			for (int u=i+1; u<n; u++) 
			{
				if (Math.abs(extendida[u][i])>Math.abs(extendida[w][i])) 
				{
					w=u;
				}
			}
			if (Math.abs(extendida[w][i])<epsilon)
			{
				return null; // La matriz no es invertible
			}
			if (w!=i) 
			{
				double[] tmp=extendida[w];
				extendida[w]=extendida[i];
				extendida[i]=tmp;
			}
			double z=extendida[i][i];
			for (int v=i; v<m; v++) 
			{
				extendida[i][v]/=z;
			}
			for (int u=0; u<n; u++)
			{
				z=extendida[u][i];
				if (u!=i&&Math.abs(z)>=epsilon) 
				{
					for (int v=i; v<m; v++)
					{
						extendida[u][v]-=z*extendida[i][v];
					}
				}
			}
		}
		for (int i=0; i<n; i++)
		{
			extendida[i]=Arrays.copyOfRange(extendida[i],n,m);
		}
		return extendida;
	}


	/**
	 * multiplica las matrices recibidas por parmetro y devuelve el resultado
	 */
	private  double[][] multiplicar(double[][] matriz1, double[][] matriz2)
	{
		if (matriz1.length==0) throw new IllegalArgumentException("La primera matriz no puede ser vacia.");
		if (matriz2.length==0) throw new IllegalArgumentException("La segunda matriz no puede ser vacia.");
		if (matriz1[0].length!=matriz2.length) throw new IllegalArgumentException("El numero de filas de la primera matriz debe ser igual al numero de columnas de la segunda.");
		int a=matriz1.length,b=matriz2.length,c=matriz2[0].length;
		double[][] respuesta=new double[a][c];
		for (int i=0; i<a; i++)
		{
			for (int k=0; k<c; k++) 
			{
				for (int j=0; j<b; j++) 
				{
					respuesta[i][k]+=matriz1[i][j]*matriz2[j][k];
				}
			}
		}
		return respuesta;
	}
	

}




