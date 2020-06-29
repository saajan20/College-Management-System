package Login;

public enum option {
    Admin,Library;
    private option(){}
    public String value(){
        return name();
    }
    public static  option fromvalue(String v){
        return valueOf(v);
    }





}
