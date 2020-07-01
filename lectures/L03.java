public class L03{

    public static void main(String[] args){

    }

    public static int offOnBits(int x, int k){
        int mask = (1 << k);
        return (x|mask);
    }

    public static int onOffBits(int x, int k){
        int mask = (~(1<<k));
        return (x&mask);
    }

}