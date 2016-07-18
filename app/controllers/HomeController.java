package controllers;

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

        return ok(formredjackson.render());
    }


    public Result formprogdina() {
        DynamicForm bindedForm = form().bindFromRequest();
        String a1 = bindedForm.get("Name").trim().replace(",", ".");
        Integer i=Integer.parseInt(a1);
        num=i;
        return ok(formprogdina.render(i));
    }

    public Result formprogdinalleno(){
        DynamicForm bindedForm = form().bindFromRequest();
        ArrayList<Trabajador> trabajadores=new ArrayList<Trabajador>(num);
        for(int i=1;i<=num;i++){
            String a1 = bindedForm.get(i+"B").trim().replace(",", ".");
            String a2 = bindedForm.get(i+"C").trim().replace(",", ".");
            String a3 = bindedForm.get(i+"D").trim().replace(",", ".");
            String a4 = bindedForm.get(i+"E").trim().replace(",", ".");
            String a5 = bindedForm.get(i+"F").trim().replace(",", ".");
            Double j1=Double.parseDouble(a1);
            Double j2=Double.parseDouble(a2);
            Double j3=Double.parseDouble(a3);
            Double j4=Double.parseDouble(a4);
            Double j5=Double.parseDouble(a5);
            Trabajador t=new Trabajador();
            t.t1=j1;
            t.t2=j2;
            t.t3=j3;
            t.t4=j4;
            t.t5=j5;
            System.out.println(t.t1);
            trabajadores.add(t);
        }

        return ok(index.render());
    }


    public Result formalgoritmolleno() {
        DynamicForm bindedForm = form().bindFromRequest();
        ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>(num);
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
        }
        return ok(index.render());
    }

    public Result formredjacksonlleno() {
        DynamicForm bindedForm = form().bindFromRequest();
        ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>(num);

        String a1 = bindedForm.get("T1").trim().replace(",", ".");
        String a2 = bindedForm.get("T2").trim().replace(",", ".");
        String a3 = bindedForm.get("T3").trim().replace(",", ".");
        String a4 = bindedForm.get("T4").trim().replace(",", ".");
        String a5 = bindedForm.get("T5").trim().replace(",", ".");
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


        return ok(index.render());
    }

}
