package com.example.project;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class TestEncryptor{
    @Test
    public void testDetermineColumns_ExactMultiple() {
        // Test case where message length is an exact multiple of rows
        int rows = 5;
        int expectedColumns = 2; // 10 / 5 = 2
        assertEquals(expectedColumns, Encryptor.determineColumns(10, rows));
    }

    @Test
    public void testGenerateEncryptArray_NeedsPadding() {
        // Test case where message needs padding with "="
        String message = "HELLO";
        int rows = 3;
        String[][] expected = {
            {"H", "E"},
            {"L", "L"},
            {"O", "="}
        };
        assertArrayEquals(expected, Encryptor.generateEncryptArray(message, rows));
    }

    @Test
    public void testGenerateEncryptArray_EmptyMessage() {
        // Test case with an empty message
        String message = "";
        int rows = 2;
        String[][] expected = {
            {"="},
            {"="}
        };
        assertArrayEquals(expected, Encryptor.generateEncryptArray(message, rows));
    }

    @Test
    public void testDetermineColumns_NotExactMultiple() {
        // Test case where message length is not an exact multiple of rows
        int rows = 4;
        int expectedColumns = 3; // 10 / 4 = 2.5 -> rounded up to 3
        assertEquals(expectedColumns, Encryptor.determineColumns(10, rows));
    }

    @Test
    public void testDetermineColumns_EmptyMessage() {
        // Test case with an empty message
        int rows = 3;
        int expectedColumns = 0; 
        assertEquals(expectedColumns, Encryptor.determineColumns(0, rows));
    }

    @Test
    public void testDetermineColumns_MessageLengthLessThanRows() {
        // Test case where message length is less than rows
        int rows = 10;
        int expectedColumns = 1; // 5 / 10 = 0.5 -> rounded up to 1
        assertEquals(expectedColumns, Encryptor.determineColumns(5, rows));
    }

    @Test
    public void testDetermineColumns_MessageLengthOne() {
        // Test case where message length is 1
        int rows = 3;
        int expectedColumns = 1; 
        assertEquals(expectedColumns, Encryptor.determineColumns(1, rows));
    }

    @Test
    public void testGenerateEncryptArray_ExactFit() {
        // Test case where message fits exactly into the 2D array
        String message = "HELLOWORLD";
        int rows = 2;
        String[][] expected = {
            {"H", "E", "L", "L", "O"},
            {"W", "O", "R", "L", "D"}
        };
        assertArrayEquals(expected, Encryptor.generateEncryptArray(message, rows));
    }

    @Test
    public void test1(){
        String ans = Encryptor.encryptMessage("Hello how are you today? it is very nice outside!", 2);
        String expected = " =?!yeaddiostt uuoo ye ceirna  ywroehv  oslil etHi";
        assertEquals(expected,ans);
    }

    @Test
    public void test2(){
        String actualEncrypted = Encryptor.encryptMessage("Hello how are you today? it is very nice outside!", 3);
        String expectedEncrypted = "ur=oe=yv!  eesdriia s ttwiuo oh?  yeoacldilonet H y";
        assertEquals(expectedEncrypted,actualEncrypted);
    }

  
    
    @Test
    public void test3(){
        String actualEncrypted = Encryptor.encryptMessage("Hello how are you today? it is very nice outside!", 4);
        String expectedEncrypted = "eic=r i=a?n= y !wayeodrdhoei tvso  tlusuloioey  H te";
        assertEquals(expectedEncrypted,actualEncrypted);
    }

    @Test
    public void test4(){
        String actualEncrypted = Encryptor.encryptMessage("Hello how are you today? it is very nice outside!", 5);
        String expectedEncrypted = " ose=wtic!o  iehutnd oi ioy ysl ?rtleyeueravoHad  ";
        assertEquals(expectedEncrypted,actualEncrypted);
    }

    @Test
    public void test5(){
        String actualEncrypted = Encryptor.encryptMessage("Hello how are you today? it is very nice outside!", 6);
        String expectedEncrypted = "w t s=ouiyt=ho ru= y?eo=o yv =lea e!lrdsceeaoiidH t ni";
        assertEquals(expectedEncrypted,actualEncrypted);
    }

    @Test
    public void test6(){
        String actualEncrypted = Encryptor.encryptMessage("Hello, Sam!", 2);
        String expectedEncrypted = ",=o!lmlaeSH ";
        assertEquals(expectedEncrypted,actualEncrypted);
    }

    @Test
    public void test7(){
        String actualEncrypted = Encryptor.encryptMessage("Eeny, meeny, miny, moe, catch a tiger by the toe. If he hollers, let him go, eeny, meeny, miny, moe.", 4);
        String expectedEncrypted = "c o. .ge,e oeommoti m h, e y,htnytein lmiy  mb,,  sy,rrnyeeengleeilmeto m h, a y, enyhhenc eetf EaI,";
        assertEquals(expectedEncrypted,actualEncrypted);
    }

    @Test
    public void test8(){
        String actualEncrypted = Encryptor.encryptMessage("Eeny, meeny, miny, moe, catch a tiger by the toe. If he hollers, let him go, eeny, meeny, miny, moe.", 7);
        String expectedEncrypted = "i  lo =mhelg,= cho y=,tthmn=ya  ie=ncyehe.e bh mee,  t omerfe,m oeIly ,mg  n,y i.,eyn,teseney or iEnate,m";
        assertEquals(expectedEncrypted,actualEncrypted);
    }

    @Test
    public void test9(){
        String actualEncrypted = Encryptor.encryptMessage("Eeny, meeny, miny, moe, catch a tiger by the toe. If he hollers, let him go, eeny, meeny, miny, moe.", 1);
        String expectedEncrypted = ".eom ,ynim ,yneem ,ynee ,og mih tel ,srelloh eh fI .eot eht yb regit a hctac ,eom ,ynim ,yneem ,yneE";
        assertEquals(expectedEncrypted,actualEncrypted);
    }

    
    @Test
    public void test10(){
        String actualEncrypted = Encryptor.encryptMessage("Eeny, meeny, miny, moe, catch a tiger by the toe. If he hollers, let him go, eeny, meeny, miny, moe.", 11);
        String expectedEncrypted = "nm y lhn .=e hb.l e,e=e,c eoteyo=mytrohe nm= naet l,e =,icg e oe,=ym ieh,gmy=n ,th s  n=e,e tfrm,i=Eyoa Ieiym=";
        assertEquals(expectedEncrypted,actualEncrypted);
    }

    

    
}