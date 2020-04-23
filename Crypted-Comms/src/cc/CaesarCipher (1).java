package cc;

//public class CaesarCipher {
//	
//	public static StringBuffer encrypt(String text, int s) {
//        StringBuffer result= new StringBuffer(); 
//  
//        for (int i=0; i<text.length(); i++) { 
//            if (Character.isUpperCase(text.charAt(i))) { 
//                char ch = (char)(((int)text.charAt(i) + 
//                                  s - 65) % 26 + 65); 
//                result.append(ch); 
//            } 
//            else { 
//                char ch = (char)(((int)text.charAt(i) + 
//                                  s - 97) % 26 + 97); 
//                result.append(ch); 
//            } 
//        } 
//        return result; 
//    }
//
//	public static void main(String[] args) {
//		String text = "ATTACKATONCE"; 
//        int s = 4; 
//        System.out.println("Text  : " + text); 
//        System.out.println("Shift : " + s); 
//        System.out.println("Cipher: " + encrypt(text, s)); 
//
//	}
//
//}









// https://code.sololearn.com/cWQnECk5390t/#java

public class CaesarCipher
{
    /* encrypt and decrypt a text using a simple algorithm of offsetting the letters */
    
    static char[] chars = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
        'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
        'y', 'z', '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'A', 'B', 'C', 'D',
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@',
        '#', '$', '%', '^', '&', '(', ')', '+',
        '-', '*', '/', '[', ']', '{', '}', '=',
        '<', '>', '?', '_', '"', '.', ',', ' '
    };
    
    public static void main(String[] args) 
    {
        String text = "This is awesome!";
        int offset = 5;
        
        String enc = encrypt(text, offset);
        System.out.println("Encrypted text: " + enc);
        
        String dec = decrypt(enc, offset);
        System.out.println("Decrypted text: " + dec);
    }

    // Caesar cipher
    static String encrypt(String text, int offset)
    {
        char[] plain = text.toCharArray();

        for (int i = 0; i < plain.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (j <= chars.length - offset) {
                    if (plain[i] == chars[j]) {
                        plain[i] = chars[j + offset];
                        break;
                    }
                } 
                else if (plain[i] == chars[j]) {
                    plain[i] = chars[j - (chars.length - offset + 1)];
                }
            }
        }
        return String.valueOf(plain);
    }

    static String decrypt(String cip, int offset)
    {
        char[] cipher = cip.toCharArray();
        for (int i = 0; i < cipher.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (j >= offset && cipher[i] == chars[j]) {
                    cipher[i] = chars[j - offset];
                    break;
                }
                if (cipher[i] == chars[j] && j < offset) {
                    cipher[i] = chars[(chars.length - offset +1) + j];
                    break;
                }
            }
        }
        return String.valueOf(cipher);
    }
}
