package mooc.vandy.java4android.diamonds.logic;

import java.util.ArrayList;

import mooc.vandy.java4android.diamonds.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early
 * Android interactions.  Designing the assignments this way allows
 * you to first learn key 'Java' features without having to beforehand
 * learn the complexities of Android.
 */
public class Logic
       implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface [MainActivity.java].
     * <p>
     * It is called 'out' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'out' is good enough).
     */
    private OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance (which
     * implements [OutputInterface]) to 'out'.
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labeled 'Process...' is pressed.
     */
    public void process(int size) {
        String[] solution = new String[size*2+1];
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilderMiddleEqual = new StringBuilder();
        StringBuilder stringBuilderMiddleDash = new StringBuilder();

        firstAndLastLine(stringBuilder, solution, size);
        constructStringForInsert(stringBuilderMiddleEqual, stringBuilderMiddleDash, size);

        //InsertMiddle returns if the size is odd or even which will help in making the diamond.
        makeDiamond(stringBuilderMiddleEqual, stringBuilderMiddleDash, solution, size, insertMiddle(solution, stringBuilderMiddleEqual, stringBuilderMiddleDash, size));
        printAll(solution);

        // TODO -- add your code here

        
    }

    public boolean isEven(int num){
        return (num&1)==0;
    }

    public void firstAndLastLine(StringBuilder stringBuilder, String[] line, int size){
        for(int i = 0; i < size*2;i++){
            stringBuilder.append("-");
        }
        line[0] = "+" + stringBuilder.toString() + "+";
        line[line.length-1] =  "+" + stringBuilder.toString() + "+";
    }

    public void constructStringForInsert(StringBuilder equal, StringBuilder dash, int size){
        for(int i = 1; i < size*2-1; ++i){
            equal.append("=");
            dash.append("-");
        }
    }
    public boolean insertMiddle(String[] solution, StringBuilder equal,
                             StringBuilder dash, int size){
        boolean isOdd = !isEven(size);

        if(isOdd){
            solution[size] = "|<" + equal.toString() + ">|";
            return true;
        }
        else{
            solution[size] = "|<" + dash.toString() + ">|";
            return false;
        }
    }

    public void makeDiamond(StringBuilder equal, StringBuilder dash, String[] solution, int size, boolean isOdd){
        StringBuilder spaces = new StringBuilder(" ");
        for(int i = 1; i <size;++i){
            equal.delete(0,2);
            dash.delete(0,2);
            if(isOdd){
                if(isEven(i)){
                    solution[size-i] = "|" + spaces + "/" + equal + "\\" + spaces + "|";
                    solution[size+i] = "|" + spaces + "\\" + equal + "/" + spaces + "|";

                }
                else{
                    solution[size-i] = "|" + spaces + "/" + dash + "\\" + spaces + "|";
                    solution[size+i] = "|" + spaces + "\\" + dash + "/" + spaces + "|";
                }
            }
            else{
                if(isEven(i)){
                    solution[size-i] = "|" + spaces + "/" + dash + "\\" + spaces + "|";
                    solution[size+i] = "|" + spaces + "\\" + dash + "/" + spaces + "|";

                }
                else{
                    solution[size-i] = "|" + spaces + "/" + equal + "\\" + spaces + "|";
                    solution[size+i] = "|" + spaces + "\\" + equal + "/" + spaces + "|";
                }
            }
            spaces.append(" ");
        }
    }

    public void printAll(String[] solution){
        for(int i = 0; i < solution.length; ++i){
            mOut.println(solution[i]);
        }
    }


    // TODO -- add any helper methods here

    
}
