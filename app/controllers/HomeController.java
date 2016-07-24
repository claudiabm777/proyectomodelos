package controllers;

import models.AlgoritmoIntegrador;
import models.ProgramacionDinamica;
import models.RedJackson;
import models.Trabajador;
import play.data.DynamicForm;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;

import static play.data.Form.form;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

public class HomeController extends Controller {
    public Integer num=0;
    public Integer pedido=0;
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render());
    }

    public Result formalgoritmo() {

        DynamicForm bindedForm = form().bindFromRequest();
        String a1 = bindedForm.get("Name").trim().replace(",", ".");
        Integer i=Integer.parseInt(a1);
        num=i;
        return ok(formalgoritmo.render(i));
    }
    public Result formredjackson() {
        DynamicForm bindedForm = form().bindFromRequest();
        String a1 = bindedForm.get("Name").trim().replace(",", ".");
        Integer i=Integer.parseInt(a1);
        pedido=i;
        return ok(formredjackson.render());
    }


    public Result formprogdina() {
        DynamicForm bindedForm = form().bindFromRequest();
        String a1 = bindedForm.get("Name").trim().replace(",", ".");
        Integer i=Integer.parseInt(a1);
        num=i;
        return ok(formprogdina.render(i));
    }

    public Result formprogdinalleno() {
        try {
            DynamicForm bindedForm = form().bindFromRequest();
            ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>(num);
            Double[][] tt = new Double[5][num];
            for (int i = 1; i <= num; i++) {
                String a1 = bindedForm.get(i + "B").trim().replace(",", ".");
                String a2 = bindedForm.get(i + "C").trim().replace(",", ".");
                String a3 = bindedForm.get(i + "D").trim().replace(",", ".");
                String a4 = bindedForm.get(i + "E").trim().replace(",", ".");
                String a5 = bindedForm.get(i + "F").trim().replace(",", ".");
                Double j1 = Double.parseDouble(a1);
                Double j2 = Double.parseDouble(a2);
                Double j3 = Double.parseDouble(a3);
                Double j4 = Double.parseDouble(a4);
                Double j5 = Double.parseDouble(a5);
                Trabajador t = new Trabajador();
                t.t1 = j1;
                t.t2 = j2;
                t.t3 = j3;
                t.t4 = j4;
                t.t5 = j5;
                System.out.println(t.t1);
                trabajadores.add(t);
                tt[0][i - 1] = 1 / j1;
                tt[1][i - 1] = 1 / j2;
                tt[2][i - 1] = 1 / j3;
                tt[3][i - 1] = 1 / j4;
                tt[4][i - 1] = 1 / j5;
            }
            Integer[] g = {0, 0, 0, 0, 0};

            ProgramacionDinamica p = new ProgramacionDinamica(num, g, tt);
            Integer[][] m = p.determinarAsignacion();
            Double[] miu = new Double[6];
            Integer[] s = new Integer[5];
            for (int i = 0; i < 5; i++) {
                miu[i] = 0.0;
                s[i] = 0;
            }
            miu[5] = 0.0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < num; j++) {
                    miu[i] += m[i][j] * tt[i][j];
                    s[i] += m[i][j];

                }
                System.out.println(s[i]+" -- "+i);
                System.out.println(miu[i]+" -- "+i);

            }
            miu[5]=miu[4];
            miu[4]=0.2;

                return ok(resultadopd.render(m, num, s[0], s[1], s[2], s[3], s[4], miu[0], miu[1], miu[2], miu[3], miu[4], miu[5]));
            }catch(Throwable e){
                e.printStackTrace();
                return ok(error.render());
            }
        }



    public Result formalgoritmolleno() {
        try {
            DynamicForm bindedForm = form().bindFromRequest();
            ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>(num);
            Double[][] tt = new Double[5][num];
            for (int i = 1; i <= num; i++) {
                String a1 = bindedForm.get(i + "B").trim().replace(",", ".");
                String a2 = bindedForm.get(i + "C").trim().replace(",", ".");
                String a3 = bindedForm.get(i + "D").trim().replace(",", ".");
                String a4 = bindedForm.get(i + "E").trim().replace(",", ".");
                String a5 = bindedForm.get(i + "F").trim().replace(",", ".");
                Double j1 = Double.parseDouble(a1);
                Double j2 = Double.parseDouble(a2);
                Double j3 = Double.parseDouble(a3);
                Double j4 = Double.parseDouble(a4);
                Double j5 = Double.parseDouble(a5);
                Trabajador t = new Trabajador();
                t.t1 = j1;
                t.t2 = j2;
                t.t3 = j3;
                t.t4 = j4;
                t.t5 = j5;
                System.out.println(t.t1);
                trabajadores.add(t);
                tt[0][i-1] = 1/j1;
                tt[1][i-1] = 1/j2;
                tt[2][i-1] = 1/j3;
                tt[3][i-1] = 1/j4;
                tt[4][i-1] = 1/j5;
            }
            AlgoritmoIntegrador a=new AlgoritmoIntegrador();

            a.algoritmo(num, tt);
            ProgramacionDinamica p=a.prog;
            RedJackson red=a.redJ;
            Integer[][] m = p.determinarAsignacion();



            return ok(resultadoalgor.render(num,m,red.ls_inf*red.ls_inf*0.2,red.tasaEntrada*60*8,red.darLqRed(),red.darLsRed(),red.darLRed(),red.darWqRed(),red.darWsRed(),red.darWRed(),red.est1,red.est2,red.est3,red.est4,red.est6,red.ls_inf));
        }catch (Throwable e){
            e.printStackTrace();
            return ok(error.render());
        }
    }

    public Result formredjacksonlleno() {
        try {
            DynamicForm bindedForm = form().bindFromRequest();
            ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>(num);
            Integer[] b = new Integer[5];
            String a1 = bindedForm.get("T1").trim().replace(",", ".");
            String a2 = bindedForm.get("T2").trim().replace(",", ".");
            String a3 = bindedForm.get("T3").trim().replace(",", ".");
            String a4 = bindedForm.get("T4").trim().replace(",", ".");
            String a5 = bindedForm.get("T5").trim().replace(",", ".");
            Integer j1 = Integer.parseInt(a1);
            Integer j2 = Integer.parseInt(a2);
            Integer j3 = Integer.parseInt(a3);
            Integer j4 = Integer.parseInt(a4);
            Integer j5 = Integer.parseInt(a5);
            Trabajador t = new Trabajador();


            b[0] = j1;
            b[1] = j2;
            b[2] = j3;
            b[3] = j4;
            b[4] = j5;
            System.out.println(t.t1);
            trabajadores.add(t);
            Double[] miu = {0.25, 0.166666666666667, 0.125, 0.142857142857143, 0.2, 0.166666666666667};
            RedJackson red = new RedJackson(b, miu);
            Double dias=((pedido/red.tasaEntrada)/60)/8;
            return ok(resultadored.render(red.ls_inf*red.ls_inf*0.2,red.tasaEntrada*60*8,dias,red.darLqRed(),red.darLsRed(),red.darLRed(),red.darWqRed(),red.darWsRed(),red.darWRed(),red.est1,red.est2,red.est3,red.est4,red.est6,red.ls_inf,b[0],b[1],b[2],b[3],b[4]));
        }catch(Throwable e){
            return ok(error.render());
        }

    }

}
