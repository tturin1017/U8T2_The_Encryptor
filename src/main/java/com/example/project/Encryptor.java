package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        int columns;
        // calculate correct number of columns based on message length
        if(messageLen==0){
            return 1;
        }
        if (messageLen % rows == 0) {
            columns = messageLen / rows;
        } else {
            columns = messageLen / rows + 1;
        }
        
        return columns;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        int messageLen = message.length();
        int columns = determineColumns(messageLen, rows);
        
        String[][] encryptor = new String[rows][columns];

        // fill 2D array with characters, padding any empty elements with "="
        int stringIdx = 0;
        for (int row = 0; row < encryptor.length; row++) {
            for (int col = 0; col < encryptor[0].length; col++) {
                if (stringIdx < messageLen) {
                    String nextCharacter = message.substring(stringIdx, stringIdx + 1);
                    encryptor[row][col] = nextCharacter;
                } else {
                    encryptor[row][col] = "=";
                }
                stringIdx++;
            }
        }

        return encryptor;
    }

    public static String encryptMessage(String message, int rows){
        // pull out characters by column going in reverse (right to left)
        String [][] encryptor = generateEncryptArray(message, rows);
        String encryptedMsg = "";
        for (int col = encryptor[0].length - 1; col >= 0; col--) {
            for (int row = 0; row < encryptor.length; row++) {
                encryptedMsg += encryptor[row][col];
            }
        }
        return encryptedMsg;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        int messageLen = encryptedMessage.length();
        int columns;
        // calculate correct number of columns based on message length
        if (messageLen % rows == 0) {
            columns = messageLen / rows;
        } else {
            columns = messageLen / rows + 1;
        }
        String[][] decryptor = new String[rows][columns];
        int stringIdx = 0;

        // fill 2D array with decrypted message, going in reverse by column
        for (int col = decryptor[0].length - 1; col >= 0; col--) {
            for (int row = 0; row < decryptor.length; row++) {
                if (stringIdx < messageLen) {
                    String nextCharacter = encryptedMessage.substring(stringIdx, stringIdx + 1);
                    decryptor[row][col] = nextCharacter;
                    stringIdx++;
                }
            }
        }
        String decryptedMsg = "";
        // pull out original message by reading off each row
        for (int row = 0; row < decryptor.length; row++) {
            for (int col = 0; col < decryptor[0].length; col++) {
                decryptedMsg += decryptor[row][col];
            }
        }

        // remove any trailing "=" symbols
        int i = decryptedMsg.length() - 1;
        while (decryptedMsg.substring(i, i + 1).equals("=")) {
            decryptedMsg = decryptedMsg.substring(0, i);
            i--;
        }
        return decryptedMsg;
    }

    public static void main(String[] args) {
        System.out.println("Testing");
       String[][] arr = generateEncryptArray("", 2);
        System.out.println(Arrays.deepToString(arr));

  
    }
}