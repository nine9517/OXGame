

public class OX {
    private String[][] table = new String[3][3];
    private String player="X";
    private int countX,countO,countDraw;

    public OX() {
        reset();
    }
    public void reset(){
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = "-";
            }
        }
    }
    public String get(int col,int row){
        try {
            return table[row][col];
        }catch (ArrayIndexOutOfBoundsException ex){
            return null;
        }

    }

    public Boolean put(int col,int row){
        if (get(col,row) == "-" && (col < table.length && row < table.length && col >= 0 && row >= 0)) {
            table[row][col] = player;
            return true;
        }
        return false;
    }

    public String getTableString(){
        String result = "  ";

        for (int i = 0; i < table.length; i++) {
            result+=i + " ";
        }
        result+="\n";
        for (int i = 0; i < table.length; i++) {
            result+=i + " ";
            for (int j = 0; j < table[i].length; j++) {
                result+=table[i][j]+" ";
            }
            result+="\n";
        }
        return result;
    }
    public String getTableTest(){
        String result = "| |       ";

        for (int i = 0; i < table.length; i++) {
            result+=i + " ";
        }
        result+="\n";
        for (int i = 0; i < table.length; i++) {
            result+="| |     "+i + " ";
            for (int j = 0; j < table[i].length; j++) {
                result+=table[i][j]+" ";
            }
            result+="\n";
        }
        return result+"| |";
    }
    public void switchPlayer(){
        player=(player.equals("X"))?"O":"X";
    }
    public String getCurrentPlayer(){
        return player;
    }

    public int getCountX() {
        return countX;
    }

    public Boolean checkWin(int col,int row) {
        if(col==row){
            if(checkWinSlantLeft(col,row)){
                setScore(false);
                return true;
            }
        }
        if(col+row==(table.length-1)){
            if(checkWinSlantRight(col,row)) {
                setScore(false);
                return true;
            }
        }
        if(checkWinHorizontal(col,row) || checkWinVertical(col,row)) {
            setScore(false);
            return true;
        }

        return false;

    }

    public Boolean checkWinHorizontal(int col,int row) {
        for(int i=0;i<table[row].length;i++){
            if(!get(col,row).equals(get(i,row)) || get(i,row).equals("-")){
                return false;
            }
        }
        return true;
    }
    public Boolean checkWinVertical(int col,int row) {
        for(int i=0;i<table[col].length;i++){
            if(!get(col,row).equals(get(col,i)) || get(col,i).equals("-")){
                return false;
            }
        }
        return true;
    }
    public Boolean checkWinSlantLeft(int col,int row) {
        for(int i=0;i<table[col].length;i++){
            if(!get(col,row).equals(get(i,i)) || get(i,i).equals("-")){
                return false;
            }
        }
        return true;
    }
    public Boolean checkWinSlantRight(int col,int row) {
        int j = table.length-1;
        for(int i=0;i<table[col].length;i++){
            if(!get(col,row).equals(get(j,i)) || get(j,i).equals("-")){
                return false;
            }
            j--;
        }
        return true;
    }
    public String checkWinSlantRighttxt(int col,int row) {
        int j = table.length-1;
        for(int i=0;i<table[col].length;i++){

            if(!get(col,row).equals(get(j,i)) || get(j,i).equals("-")){
                return "col:"+col+",row:"+row+"\n"+"i:"+i+",j:"+j;
            }
            j--;
        }
        return "true";
    }


    public boolean checkSpace() {
        for (String[] row : table){
            for(String col : row){
                if (col.equals("-")) {
                    return true;
                }
            }
        }
        setScore(true);
        return false;
    }
    public void setScore(Boolean draw){
        if(draw){
            countDraw++;
        }else{
            if(player.equals("X")){
                countX++;
            }else{
                countO++;
            }
        }
    }
    public int getCountO() {
        return countO;
    }

    public int getCountDraw() {
        return countDraw;
    }
}
