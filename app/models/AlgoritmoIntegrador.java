package models;

import java.util.ArrayList;

public class AlgoritmoIntegrador {
	public RedJackson redJ;
	public ProgramacionDinamica prog;
	public Integer[][] m;
	
	public void algoritmo(Integer numeroTrabajadores,Double [][]tt){
		Integer[] g=new Integer[5];
		for(int i=0;i<5;i++){
			g[i]=0;
		}
		Double tasa=-1.0;
		boolean seguir=true;
		int nn=0;
		ProgramacionDinamica pr=new ProgramacionDinamica(numeroTrabajadores, g, tt);
		ArrayList<ArrayList<Nodo>>nodo=pr.nodos();
		while(seguir && nn<=0){
			try{
				System.out.println(nn+"  nn!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
				ProgramacionDinamica p=new ProgramacionDinamica(numeroTrabajadores, g, tt);

				nn++;

				Integer[][]matriz=p.determinarAsignacion(nodo);

				Double[]miu=new Double[6];
				Integer[]s=new Integer[5];
				for(int i=0;i<5;i++){
					miu[i]=0.0;
					s[i]=0;
				}

				miu[5]=0.0;
				for(int i=0;i<5;i++){
					for(int j=0;j<numeroTrabajadores;j++){
						miu[i]+=matriz[i][j]*tt[i][j];
						s[i]+=matriz[i][j];
						
					}


				}
				miu[5]=miu[4];
				miu[4]=0.2;

				RedJackson red=new RedJackson(s, miu);
				if(nn==0){
					prog=p;
					redJ=red;
					m=matriz;
				}
				if(red.tasaEntrada>tasa){
					if(red.cuelloB==5){
						g[red.cuelloB-1]+=1;
					}else{
						g[red.cuelloB]+=1;
					}
					prog=p;
					redJ=red;
					m=matriz;
					tasa=red.tasaEntrada;
				}else{
					seguir=false;
				}
			}catch(Throwable e){
				seguir=false;
			}
		}

	}
	


}
