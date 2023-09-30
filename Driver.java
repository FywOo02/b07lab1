import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Driver {
    public static void main(String[] args) throws IOException {

        /*Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1_coe = {6,5,3};
        int[] c1_exp = {0,7,8};
        Polynomial p1 = new Polynomial(c1_coe, c1_exp);
        double [] c2_coe = {1,2,3,5};
        int[] c2_exp = {0,3,4,8};
        Polynomial p2 = new Polynomial(c2_coe, c2_exp);
        Polynomial m = p2.multiply(p2);
        System.out.println("Multiply_coe"+ Arrays.toString(m.coe));
        System.out.println("Multiply_exp: "+ Arrays.toString(m.exp));

         */



        /*
        double [] c1_coe = {6,-2,5};
        int[] c1_exp = {0,1,8};
        Polynomial p1 = new Polynomial(c1_coe, c1_exp);
        double [] c2_coe = {7,8};
        int[] c2_exp = {1,8};
        Polynomial p2 = new Polynomial(c2_coe, c2_exp);
        Polynomial m = p1.multiply(p2);

         */

        /*
        Polynomial p = new Polynomial(new File("E:\\Computer Science\\Java\\CSCB07\\Lab\\lab02\\b07lab1\\test.txt"));
        System.out.println("Multiply_coe"+ Arrays.toString(p.coe));
        System.out.println("Multiply_exp: "+ Arrays.toString(p.exp));

         */

        //Polynomial s = p1.add(p2);
        /*System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
        */

        /*
        Polynomial p = new Polynomial(new File("b07lab1/test.txt"));
        System.out.println(Arrays.toString(p.coe));
        System.out.println(Arrays.toString(p.exp));
        p.saveToFile("b07lab1/output.txt");
         */


        /*
        double [] c1_coe = {2,1,3,4,9,8};
        int[] c1_exp = {3,4,5,1,6,7};
        Polynomial p1 = new Polynomial(c1_coe, c1_exp);
        double [] c2_coe = {2,3,-7,-2};
        int[] c2_exp = {5,4,8,1};
        Polynomial p2 = new Polynomial(c2_coe, c2_exp);
        System.out.println("p1 coe"+Arrays.toString(p1.coe));
        System.out.println("p1 exp"+Arrays.toString(p1.exp));
        System.out.println("p2 coe"+Arrays.toString(p2.coe));
        System.out.println("p2 exp"+Arrays.toString(p2.exp));
        System.out.println("******************************************************8888");
        Polynomial n = p1.add(p2);
        Polynomial m = p1.multiply(p2);
        System.out.println("evaluate:"+p1.evaluate(0.1));
        System.out.println("add coe"+Arrays.toString(n.coe));
        System.out.println("add exp"+Arrays.toString(n.exp));
        System.out.println("mul coe"+Arrays.toString(m.coe));
        System.out.println("mul exp"+Arrays.toString(m.exp));

         */

    }
}
