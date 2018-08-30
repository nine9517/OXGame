import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OXTest {
    void printLine(){
        System.out.println("| |");
    }
    void printEnd(){
        System.out.println("|/");
        System.out.println("* -> End Test");
    }
    void printTxT(String val){
        System.out.println("| * -> "+val+" OK!");
    }
    void print(String val){
        System.out.println("| |     "+val);
    }
    void printArrow(String key,String val){
        System.out.println("| |     "+key+" => "+val);
    }
    void printTxT(String val,Boolean t){
        System.out.println("| * -> "+val+" Run !");
    }



    @Test
    void getTableString(){
        printLine();
        printTxT("getTableString()",true);
        printLine();
        OX ox = new OX();
        assertEquals("  0 1 2 \n0 - - - \n1 - - - \n2 - - - \n",ox.getTableString());
        System.out.println(ox.getTableTest());
        printTxT("getTableString()");
    }

    @Test
    void get() {
        printLine();
        printTxT("get()",true);
        printLine();
        OX ox = new OX();
        ox.put(1,1);
        System.out.println(ox.getTableTest());
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                assertEquals((i==1&&j==1)?"X":"-",ox.get(j,i));
                printArrow("GET row : "+i+", col : "+j,ox.get(j,i));
                printLine();
            }
        }

        assertNull(ox.get(3,3));
        printArrow("GET row : "+3+", col : "+3,ox.get(3,3));
        printLine();
        printTxT("get()");

    }


    @Test
    void put() {
        printLine();
        printTxT("put()",true);
        printLine();
        OX ox = new OX();
        boolean result = ox.put(-3,-3);
        assertEquals(false,result);
        printArrow("PUT row : -3, col : -3",""+result);
        printLine();
        System.out.println(ox.getTableTest());
        result = ox.put(3,3);
        assertEquals(false,result);
        printArrow("PUT row : -3, col : -3",""+result);
        printLine();
        System.out.println(ox.getTableTest());
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                result = ox.put(j,i);
                assertEquals(true,result);
                printArrow("PUT row : "+i+", col : "+j,""+result);
                printLine();
                System.out.println(ox.getTableTest());

            }
        }

        printTxT("put()");
    }


    @Test
    void getCurrentPlayer() {
        printLine();
        printTxT("getCurrentPlayer()",true);
        printLine();

        OX ox = new OX();
        assertEquals("X",ox.getCurrentPlayer());
        printArrow("GET Current Player",ox.getCurrentPlayer());
        printLine();
        printTxT("getCurrentPlayer()");
    }

    @Test
    void switchPlayer() {
        printLine();
        printTxT("switchPlayer()",true);
        printLine();
        OX ox = new OX();
        printArrow("GET Current Player",ox.getCurrentPlayer());
        printLine();
        ox.switchPlayer();
        print("Switch Player");
        printLine();
        assertEquals("O",ox.getCurrentPlayer());
        printArrow("GET Current Player",ox.getCurrentPlayer());
        printLine();
        ox.switchPlayer();
        print("Switch Player");
        printLine();
        assertEquals("X",ox.getCurrentPlayer());
        printArrow("GET Current Player",ox.getCurrentPlayer());
        printLine();
        printTxT("switchPlayer()");
    }
    @Test
    void checkWinHorizontal() {
        printLine();
        printTxT("checkWinHorizontal()",true);
        printLine();
        OX ox = new OX();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                ox.put(j,i);
                System.out.println(ox.getTableTest());
                assertEquals((j<2)?false:true,ox.checkWinHorizontal(j,i));
                printArrow("Win",""+ox.checkWinHorizontal(j,i));
                printLine();
            }

            ox.reset();
        }
        printTxT("checkWinHorizontal()");
    }

    @Test
    void checkWinVertical() {
        printLine();
        printTxT("checkWinVertical()",true);
        printLine();
        OX ox = new OX();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                ox.put(i,j);
                System.out.println(ox.getTableTest());
                assertEquals((j<2)?false:true,ox.checkWinVertical(i,j));
                printArrow("Win",""+ox.checkWinHorizontal(j,i));
                printLine();
            }

            ox.reset();
        }
        printTxT("checkWinVertical()");
        printEnd();
    }

    @Test
    void checkWinSlantLeft() {
        System.out.println("* -> OX Test");
        System.out.println("|\\");
        printTxT("checkWinSlantLeft()",true);
        printLine();
        OX ox = new OX();
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++) {
                ox.put(j,j);
                System.out.println(ox.getTableTest());
                assertEquals((j<2)?false:true,ox.checkWinSlantLeft(j,j));
                printArrow("Win",""+ox.checkWinSlantLeft(j,j));
                printLine();
            }

            ox.reset();
            ox.switchPlayer();
        }
        printTxT("checkWinSlantLeft()");
    }

    @Test
    void checkWinSlantRight() {
        printLine();
        printTxT("checkWinSlantRight()",true);
        printLine();
        OX ox = new OX();
        for(int i=0;i<2;i++){
            int l = 2;
            for(int j=0;j<3;j++) {
                ox.put(l, j);
                System.out.println(ox.getTableTest());
                assertEquals((j < 2) ? false : true, ox.checkWinSlantRight(l, j));
                printArrow("Win", "" + ox.checkWinSlantRight(l, j));
                printLine();
                l--;
            }
            ox.reset();
            ox.switchPlayer();
        }

        printTxT("checkWinSlantRight()");
    }

    @Test
    void checkSpace() {
        printLine();
        printTxT("checkSpace()",true);
        printLine();
        OX ox = new OX();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                assertEquals(true,ox.checkSpace());
                ox.put(j,i);
            }
            System.out.println(ox.getTableTest());
            printArrow("Table also has space", "" + ox.checkSpace());
            printLine();
        }
        assertEquals(false,ox.checkSpace());
        printTxT("checkSpace()");
    }

    @Test
    void checkWin() {
        printLine();
        printTxT("checkWin()",true);
        printLine();
        OX ox = new OX();
        ox.put(0,0);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(0,0));
        ox.put(0,1);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(0,1));
        ox.put(1,1);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(1,1));
        ox.put(2,0);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(2,0));
        ox.put(2,2);
        ox.switchPlayer();
        assertEquals(true,ox.checkWin(2,2));
        System.out.println(ox.getTableTest());
        printArrow("Win", "" + ox.checkWin(2,2));
        printLine();
        ox.reset();
        ox.put(0,0);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(0,0));
        ox.put(1,1);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(1,1));
        ox.put(0,1);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(0,1));
        ox.put(2,0);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(2,0));
        ox.put(0,2);
        ox.switchPlayer();
        System.out.println(ox.getTableTest());
        assertEquals(true,ox.checkWin(0,2));

        printArrow("Win", "" + ox.checkWin(0,2));
        printLine();
        ox.reset();
        ox.put(0,0);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(0,0));
        ox.put(2,2);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(2,2));
        ox.put(0,1);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(0,1));
        System.out.println(ox.getTableTest());
        printArrow("Win", "" + ox.checkWin(0,1));
        printLine();
        ox.reset();
        ox.put(0,0);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(0,0));
        ox.put(1,1);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(1,1));
        ox.put(1,0);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(1,0));
        ox.put(2,2);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(2,2));
        ox.put(2,0);
        ox.switchPlayer();
        assertEquals(true,ox.checkWin(2,0));
        System.out.println(ox.getTableTest());
        printArrow("Win", "" + ox.checkWin(2,0));
        printLine();
        ox.reset();
        ox.put(0,2);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(0,2));
        ox.put(0,1);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(0,1));
        ox.put(1,1);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(1,1));
        ox.put(0,0);
        ox.switchPlayer();
        assertEquals(false,ox.checkWin(0,0));
        ox.put(2,0);
        ox.switchPlayer();
        assertEquals(true,ox.checkWin(2,0));
        System.out.println(ox.getTableTest());

        printArrow("Win", "" + ox.checkWin(2,0));
        printLine();
        printTxT("checkWin()");
    }



    @Test
    void setScore() {
        printLine();
        printTxT("setScore()",true);
        printLine();
        OX ox = new OX();
        ox.setScore(false);
        assertEquals(1,ox.getCountX());
        printArrow("SET Score Player X",""+ox.getCountX());
        printLine();
        ox.switchPlayer();
        ox.setScore(false);
        assertEquals(1,ox.getCountO());
        printArrow("SET Score Player O",""+ox.getCountO());
        printLine();
        ox.setScore(true);
        assertEquals(1,ox.getCountDraw());
        printArrow("SET Score Draw",""+ox.getCountDraw());
        printLine();
        printTxT("setScore()");
    }

    @Test
    void getCountX() {
        printLine();
        printTxT("getCountX()",true);
        printLine();
        OX ox = new OX();
        printArrow("GET Score Player X",""+ox.getCountX());
        printLine();
        ox.setScore(false);
        printArrow("SET Score Player X","1");
        printLine();
        assertEquals(1,ox.getCountX());
        printArrow("GET Score Player X",""+ox.getCountX());
        printLine();
        printTxT("getCountX()");
    }

    @Test
    void getCountO() {
        printLine();
        printTxT("getCountO()",true);
        printLine();
        OX ox = new OX();
        ox.switchPlayer();
        printArrow("GET Score Player O",""+ox.getCountO());
        printLine();
        ox.setScore(false);
        printArrow("SET Score Player O","1");
        printLine();
        assertEquals(1,ox.getCountO());
        printArrow("GET Score Player O",""+ox.getCountO());
        printLine();
        printTxT("getCountO()");
    }

    @Test
    void getCountDraw() {
        printLine();
        printTxT("getCountDraw()",true);
        printLine();
        OX ox = new OX();
        printArrow("GET Score Draw",""+ox.getCountDraw());
        printLine();
        ox.setScore(true);
        printArrow("SET Score Draw","1");
        printLine();
        assertEquals(1,ox.getCountDraw());
        printArrow("GET Score Draw",""+ox.getCountDraw());
        printLine();
        printTxT("getCountDraw()");
    }
}