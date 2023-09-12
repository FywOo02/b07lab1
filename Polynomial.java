public class Polynomial {
    public double[] coe;

    public Polynomial(){
        this.coe = new double[]{0};
    }

    public Polynomial(double[] coe) {
        this.coe = coe;
    }

    public Polynomial add(Polynomial input){
        double[] longer;
        if(this.coe.length > input.coe.length){
            longer = this.coe;
        }else {
            longer = input.coe;
        }

        double[] result = new double[(this.coe.length>input.coe.length) ? this.coe.length : input.coe.length];
        int index =(this.coe.length<input.coe.length) ? this.coe.length : input.coe.length;

        for(int i=0; i<result.length; i++){
            if(index != 0){
                result[i] = this.coe[i] + input.coe[i];
            }else{
                result[i] = longer[i];
            }
            index--;
        }

        /*int count = 0;
        int index = 0;
        while(count < this.coe.length){
            result[index] = this.coe[count];
            index++;
            count++;
        }
        count = 0;
        while (count < input.coe.length){
            result[index] = input.coe[count];
            index++;
            count++;
        }

         */

        return new Polynomial(result);
    }

    public double evaluate(double x){
        double result = 0;
        double temp_result = 1;

        for(int count = 0; count < this.coe.length; count++){
            temp_result = 1;
            int temp_count = count;
            while(temp_count != 0){
                temp_result *= x;
                temp_count --;
            }
            result += this.coe[count] * temp_result;
        }
        return result;
    }

    public boolean hasRoot(double num){
        return evaluate(num) == 0;
    }
}
