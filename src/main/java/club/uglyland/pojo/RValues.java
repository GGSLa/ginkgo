package club.uglyland.pojo;

/**
 * @Author : ZGQ
 * @Date : 2020/5/8 20:18
 * @Version : 1.0
 */
public class RValues {
    private int rValue3;
    private int rValue5;
    private int rValue4;

    public int getrValue3() {
        return rValue3;
    }

    public void setrValue3(int rValue3) {
        this.rValue3 = rValue3;
    }

    public int getrValue5() {
        return rValue5;
    }

    @Override
    public String toString() {
        return "RValues{" +
                "rValue3=" + rValue3 +
                ", rValue5=" + rValue5 +
                ", rValue4=" + rValue4 +
                '}';
    }

    public void setrValue5(int rValue5) {
        this.rValue5 = rValue5;
    }

    public int getrValue4() {
        return rValue4;
    }

    public void setrValue4(int rValue4) {
        this.rValue4 = rValue4;
    }
}
