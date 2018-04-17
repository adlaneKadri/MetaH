
public class Litteral {
    int num;
    int val;

    public Litteral(int num, int val){
        this.num = num;
        this.val = val;
    }

    public  Integer getLiteral(){
        if(this.val == 0) return (-1)*this.num;
        else return  this.num;
    }

    public int print(){
        if(this.val == 0) return (-1)*this.num;
        else return  this.num;
    }

}
