package sistema;

public class TesteClass {
        
    public static String frase = "ola mundo";
    
    public static void main(String[] args) {
        String str = "teste";
        System.out.println("teste" + 2 + str + str.substring(str.indexOf("e"), 4));
        System.out.println(str.toLowerCase().toUpperCase().toString().chars());
        System.out.println("teste" + 2 + str + str.substring(str.indexOf("e"), 4 * 2 / (4 - 5)));
        System.out.println(str.toLowerCase().toUpperCase().toString().length());
        System.out.println(3.6 * 4.6 + 4.5);
        
        int i = frase.getBytes().length;
        
        int length = frase.length();
        
        char[] s = frase.toCharArray();
        
        for(int j = 0; i < 4; i++) {
            int frase = j;
            System.out.println(frase);
        }
        
        System.out.println(frase);
    }
}
