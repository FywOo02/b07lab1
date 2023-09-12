public class Polynomial {
    public double[] coe;

    public Polynomial(){
        this.coe = new double[]{0};
    }

    public Polynomial(double[] coe) {
        this.coe = coe;
    }

    public Polynomial add(Polynomial input){
        double[] result = new double[this.coe.length+input.coe.length];

        int count = 0;
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
        return new Polynomial(result);
    }

    public double evaluate(double x){
        double result = 0;
        for(int count = 0; count < this.coe.length; count++){
            result += this.coe[count] * Math.pow(x,count);
        }
        return result;
    }

    public boolean hasRoot(double num){
        return evaluate(num) == 0;
    }
}
