package module;

public class Procent {
    private float sum;

    public Procent(float newSum) {
        sum = newSum;
    }

    public float countPr(int pr) {
        return sum * ((float) pr / 100);
    }

    public float countSum(float sum, float pr) {
        return sum + (sum * pr / 100);
    }

    public int countSumrnd(int pr) {
        return Math.round(sum + (sum * ((float) pr / 100)));
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}