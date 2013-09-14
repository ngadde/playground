package sample.version;

public class Version {
private Version() {
// don't instantiate
}

public static String id() {
return "0.0.1-SNAPSHOT";
}

public static void main(String[] args) {
System.out.println(id());
}
}