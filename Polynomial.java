import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Polynomial {
    double[] coe;
    int[] exp;

    public Polynomial(){
        this.coe = new double[]{0};
        this.exp = new int[]{0};
    }

    public Polynomial(double[] coe, int[] exp) {
        this.coe = coe;
        this.exp = exp;
    }

    public Polynomial(File file) throws IOException {
        Scanner sc = new Scanner(file);
        String input = get_modified_poly(sc.nextLine());
        String[] data = input.split("\\+");
        this.coe = new double[data.length];
        this.exp = new int[data.length];

        for(int i=0; i<data.length; i++){
            if(!haveX(data[i])){
                this.exp[i] = 0;
                this.coe[i] = Double.parseDouble(data[i]);
            } else {
                String[] temp = data[i].split("x");
                this.exp[i] = Integer.parseInt(temp[1]);
                this.coe[i] = Double.parseDouble(temp[0]);
            }
        }
        sc.close();
     }
     public boolean haveX(String str){
        // no x in this string
         boolean check = false;
         for(int i=0; i<str.length(); i++){
             if(str.charAt(i) == 'x'){
                 check = true;
             }
         }
         return check;
     }

     public boolean isNum(String str, int index){
        boolean check = true;
        if(str.charAt(index) == '-' || str.charAt(index) == '+' ){
            check = false;
        }
        return check;
     }

     public String get_modified_poly(String input){
        ArrayList<Character> input_result = new ArrayList<Character>();

        for (int j=0; j<input.length(); j++){
            if(input.charAt(j) == '-' && j != 0){
                input_result.add('+');
            }
            if(input.charAt(j) == 'x'){
                if(j!=0 && j!=input.length()-1) {
                    if (!isNum(input, j - 1)) {
                        input_result.add('1');
                    }
                    if (!isNum(input, j + 1)) {
                        input_result.add('x');
                        input_result.add('1');
                        continue;
                    }
                }else if(j==0 || j==input.length()-1) {
                    if (j == 0 && j == input.length() - 1) {
                        input_result.add('1');
                        input_result.add('x');
                        input_result.add('1');
                        continue;
                    }
                    if (j == 0) {
                        input_result.add('1');
                        if (!isNum(input, j + 1)) {
                            input_result.add('x');
                            input_result.add('1');
                            continue;
                        }
                    }
                    if (j == input.length() - 1) {
                        if (!isNum(input, j - 1)) {
                            input_result.add('1');
                        }
                        input_result.add('x');
                        input_result.add('1');
                        continue;
                    }
                }
            }
            input_result.add(input.charAt(j));
        }

        StringBuilder temp = new StringBuilder();
        for(int i=0; i<input_result.size(); i++){
            temp.append(input_result.get(i));
        }
        String result = temp.toString();
        return result;
     }

    public int[] find_result_exp(Polynomial input){
        int[] combinedarray = new int[input.exp.length + this.exp.length];

        // copy input array to combinedarray
        for(int i=0; i<input.exp.length; i++){
            combinedarray[i] = input.exp[i];
        }
        int index = input.exp.length;

        // check this.exp
        for(int i=0; i<this.exp.length; i++){
            boolean check = false;
            for(int j=0; j<input.exp.length; j++){
                if(combinedarray[j] == this.exp[i]){
                    check = true;
                    break;
                }
            }

            if(!check){
                combinedarray[index] = this.exp[i];
                index++;
            }
        }

        // build result array
        int[] result = new int[index];
        for(int i=0; i<index; i++){
            result[i] = combinedarray[i];
        }

        // order the result array
        for(int i=0; i<result.length-1; i++){
            for(int j=0; j<result.length-i-1; j++){
                if(result[j] > result[j+1]){
                    int temp = result[j];
                    result[j] = result[j+1];
                    result[j+1] = temp;
                }
            }
        }
        return result;
    }

    public double[] find_result_coe(Polynomial input, int[] exp_result){
        double[] result = new double[exp_result.length];
        int index = 0;
        for(int i=0; i<input.exp.length; i++){
            for(int j=0; j<exp_result.length; j++){
                if(input.exp[i] == exp_result[j]){
                    index = j;
                    break;
                }
            }
            result[index] += input.coe[i];
        }

        for(int i=0; i<this.exp.length; i++){
            for(int j=0; j<exp_result.length; j++){
                if(this.exp[i] == exp_result[j]){
                    index = j;
                    break;
                }
            }
            result[index] += this.coe[i];
        }
        return result;
    }

    public Polynomial add(Polynomial input){
        // find the max exponent
        int this_max = this.exp[this.exp.length - 1];
        int input_max = input.exp[input.exp.length - 1];
        int max_exp = (this_max > input_max ? this_max : input_max);

        int[] exp_result = find_result_exp(input);
        double[] coe_result = find_result_coe(input, exp_result);


        return new Polynomial(coe_result, exp_result);
    }

    public double power(double x, int pow){
        double result = 1;
        while(pow != 0){
            result *= x;
            pow--;
        }
        return result;
    }

    public double evaluate(double x){
        double result = 0;
        for(int i=0; i<this.coe.length; i++){
            result += this.coe[i]*power(x, this.exp[i]);
        }
        return result;
    }

    public boolean hasRoot(double num){
        return evaluate(num) == 0;
    }

    public Polynomial multiply(Polynomial input){
        double[] ori_mul_coe = new double[input.coe.length * this.coe.length];
        int[] ori_mul_exp = new int[input.exp.length * this.exp.length];

        int index = 0;

        for(int i=0; i<this.coe.length; i++){
            for(int j=0; j<input.exp.length; j++){
                ori_mul_coe[index] = this.coe[i]*input.coe[j];
                ori_mul_exp[index] = this.exp[i]+input.exp[j];
                index++;
            }
        }

        // order arrays
        for(int i=0; i<ori_mul_exp.length-1; i++){
            for(int j=0; j<ori_mul_exp.length-i-1; j++){
                if(ori_mul_exp[j] > ori_mul_exp[j+1]){
                    int temp = ori_mul_exp[j];
                    ori_mul_exp[j] = ori_mul_exp[j+1];
                    ori_mul_exp[j+1] = temp;

                    double temp2 = ori_mul_coe[j];
                    ori_mul_coe[j] = ori_mul_coe[j+1];
                    ori_mul_coe[j+1] = temp2;
                }
            }
        }
        // check is have redundant value
        int i=0;
        int count=0;
        while (i<ori_mul_coe.length-1){
            int temp_index = 1;
            while(ori_mul_exp[i] == ori_mul_exp[i+temp_index]){
                ori_mul_coe[i] = ori_mul_coe[i]+ori_mul_coe[i+temp_index];
                ori_mul_coe[i+temp_index] = -1;
                ori_mul_exp[i+temp_index] = -1;
                temp_index++;
                count++;
            }
            i = i+temp_index;
        }

        //plugging none zero element into new array
        double[] result_mul_coe = new double[ori_mul_coe.length-count];
        int[] result_mul_exp = new int[ori_mul_exp.length-count];
        int idx = 0;
        for(int k=0; k<ori_mul_exp.length; k++){
            if(ori_mul_exp[k] != -1){
                result_mul_exp[idx] = ori_mul_exp[k];
                result_mul_coe[idx] = ori_mul_coe[k];
                idx++;
            }
        }
        return new Polynomial(result_mul_coe, result_mul_exp);
    }

    public void saveToFile(String fileName) throws FileNotFoundException {
        String output = "";
        for(int i=0; i<this.exp.length; i++){
            if(this.exp[i] == 0){
                if(this.coe[i] == (int)this.coe[i])
                    output += (int)this.coe[i]+"+";
                else
                    output += this.coe[i]+"+";
            }else {
                if(this.coe[i] == 1){
                    if(this.exp[i]==1)
                        output += "x+";
                    else
                        output += "x"+this.exp[i]+"+";
                } else if (this.coe[i] == -1) {
                    if(this.exp[i]==1)
                        output += "-x+";
                    else
                        output += "-x"+this.exp[i]+"+";
                }else {
                    if(this.coe[i] == (int)this.coe[i])
                        output += (int) this.coe[i]+"x"+this.exp[i]+"+";
                    else
                        output += this.coe[i]+"x"+this.exp[i]+"+";
                }
            }
        }

        ArrayList<Character> result = new ArrayList<Character>();

        for(int i=0; i<output.length()-1; i++){
            if(output.charAt(i) =='+' && output.charAt(i+1) == '-'){
                result.add('-');
                i++;
                continue;
            }
            result.add(output.charAt(i));
        }
        StringBuilder temp = new StringBuilder();
        for(int i=0; i<result.size(); i++){
            temp.append(result.get(i));
        }
        String result_str = temp.toString();

        PrintStream op = new PrintStream(fileName);
        op.print(result_str);
        op.close();
    }
}
