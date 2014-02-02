/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication7;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author sree
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button num0;
    @FXML
    private Button num7;
    @FXML
    private Button num6;
    @FXML
    private Button num5;
    @FXML
    private Button num4;
    @FXML
    private Button num3;
    @FXML
    private Button num2;
    @FXML
    private Button num1;
    @FXML
    private Button subtract;
    @FXML
    private Button openBracket;
    @FXML
    private Button add;
    @FXML
    private Button num9;
    @FXML
    private Button num8;
    @FXML
    private Button multiply;
    @FXML
    private Button clear;
    @FXML
    private Button equal;
    @FXML
    private Button pow;
    @FXML
    private Button close;
    @FXML
    private Button division;
    @FXML
    private Button dot;
    @FXML
    private Button delete;
    @FXML
    private TextField TextBox;
    
   Boolean start =true;
        private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void insertAction(ActionEvent event) 
    {        
            String input = ((Button)event.getSource()).getText();
            if(TextBox.getText()=="ERROR")
            {
            TextBox.setText("");
            }
            if (start) {
                TextBox.setText(input);
                start = false;
            } else {
                TextBox.setText(TextBox.getText() + input);
                fullexpression = TextBox.getText();
            } 
    }

    @FXML
    private void clearAction(ActionEvent event) {
     TextBox.setText("");
     }

    @FXML
    private void commandAction(ActionEvent event) {
         System.out.println("the entire expression is " + fullexpression);
            fullexpression = fullexpression + "=";
            createstack(cal1, fullexpression);
            createstack(validation, fullexpression);
            validation();    
            TextBox.setText("");
            if(finalcalculation(cal1)==null)
            {
            TextBox.setText("ERROR");
            }
            else
            {
            TextBox.setText(getnext(finalcalculation(cal1), 0));
                    cal1.removeAll(cal1);
      
             }
                    }

        private void validation() {
        if(fullexpression.length()<4)
            {
            TextBox.setText("Error");
            }
          //  else  if(cal)
         
            

    }

    @FXML
    private void earseAction(ActionEvent event) {
     String clipped  = TextBox.getText().substring(0,TextBox.getText().length()-1);
     TextBox.setText(clipped);
        
    }
    
    
    
    
    
    public List<String> finalcalculation(List<String> cal)  {
        List<String> tempvalue = new ArrayList<String>();
        List<String> tempvalue1 = new ArrayList<String>();
        String value;
        int c = 0;
        int temp = cal.size();
        int i = 0;
        int j = 0;
        int one = 0;
        int zero = 0;
        Boolean te = true;
        int tempval = 0;
        System.out.println("new list created ");

        int k = i;

        i = 0;
        while (i < cal.size()) {
            value = cal.get(i);
            while (k < cal.size()) {
                value = cal.get(k);
                System.out.println("the value is " + value + " and the position is " + k);
                System.out.println("it is working properly");
                k++;
            }
            try
            {
            if (value == null || value == "=") {
                i++;
            } else if (operatorchecker("(", cal)) {
                System.out.println("there are brackets present and the current temp value " + tempval + "and its position " + j);
                j = i;
                te = true;
                while (te) {
                    if (cal.get(j).equals("(")) {
                       
                        if(bracketchecker(cal)==false)
                       {
                         return null;  
                       }
                       String prev = getprevious(cal, dpos - 1);
                       String next = getnext(cal, dpos + 1);
                        // validation is wrong
                      if(prev==")"||next==")"||next=="*"||next=="+"||next=="-"||next=="/"||next=="^")
                       {
                       return null;
                       }
                        if (tempval == 0) {
                            one = j;
                            System.out.println("value saved");
                        }
                        tempval++;
                        System.out.println("opening braces found and the tempvalue is " + tempval + "its position " + j);
                        j++;

                    } else if (cal.get(j).equals(")")) {
                         String prev = getprevious(cal, dpos - 1);
                       String next = getnext(cal, dpos + 1);
                      
                        if(prev=="("||prev=="*"||prev=="+"||prev=="-"||prev=="/"||prev=="^"||next=="(")
                        {
                        return null;
                        }
                        tempval--;
                        System.out.println("closing braces found and the tempvalue is  " + tempval + "its position" + j);

                        if (tempval == 0) {
                            zero = j;
                            te = false;
                        }
                        j++;
                    } else {
                        System.out.println("value or an operator found the position is  " + j + " the value is" + cal.get(j));
                        j++;
                    }

                }
                // one =j;
                // System.out.println("the size of the list is "+cal.size());
                System.out.println("the start and end position of the sub list" + (one + 1) + (zero - 1));
                c = one + 1;
                int q = 0;
                while (q < tempvalue.size()) {
//                    if(cal.get(d)==")")
//                    tempval--;
                    tempvalue.set(q, null);
                    q++;
                }
                while (c <= zero - 1) {
                    if (c != zero) {
                        tempvalue.add(cal.get(c));
                        c++;

                    } else {
                        tempvalue.add("=");
                        c++;

                    }
                }
                tempvalue1 = finalcalculation(tempvalue);
                String temp2 = getnext(tempvalue1, 0);
                k = 0;
                while (k < tempvalue.size()) {
                    value = tempvalue.get(k);

                    System.out.println("the value is " + value + " and the position is " + k);
                    k++;

                }
                k = 0;
                while (k < cal.size()) {
                    value = cal.get(k);
                    System.out.println("the value is " + value + " and the position is " + k);
                    k++;
                }
                System.out.println("the value inserted is " + temp2);
                int d = one + 1;
                while (d <= zero) {
                    cal.set(d, null);
                    d++;
                }
                k = 0;
                cal.set(one, temp2);
                while (k < cal.size()) {
                    value = cal.get(k);
                    System.out.println("the value is " + value + " and the position is " + k);
                    k++;
                }

                i++;
            } else if (operatorchecker("^", cal)) {
                String prev = getprevious(cal, dpos - 1);
                String next = getnext(cal, dpos + 1);
                if(prev=="("||prev=="*"||prev=="+"||prev=="-"||prev=="/"||prev=="^"||next==")"||next=="*"||next=="+"||next=="-"||next=="/"||next=="^")
                {
                return null;
                }
                    System.out.println("the previous and the next values are"
                        + prev + "\t" + next);
                Double d = pow(Double.valueOf(prev), Integer.parseInt(next));
                cal.set(dpos, String.valueOf(d));
                System.out.println("the inserted value is " + cal.get(dpos));
                cal.set(getprevious(dpos - 1, cal), null);
                cal.set(getnext(dpos + 1, cal), null);
                i++;
            } else if (operatorchecker("$", cal)) {
                String next = getnext(cal, dpos + 1);
                System.out.println("the next values are"
                        + "\t" + next);
                Double d = sqrt(Double.parseDouble(next));
                cal.set(dpos, String.valueOf(d));
                System.out.println("the inserted value is " + cal.get(dpos));
                cal.set((dpos + 1), null);
                i++;
            } else if (operatorchecker("/", cal)) {
                String prev = getprevious(cal, dpos - 1);
                String next = getnext(cal, dpos + 1);
                if(prev=="("||prev=="*"||prev=="+"||prev=="-"||prev=="/"||prev=="^"||next==")"||next=="*"||next=="+"||next=="-"||next=="/"||next=="^")
                {
                return null;
                }
                System.out.println("the previous and the next values are"
                        + prev + "\t" + next);
                Double d = Double.valueOf(prev) / Double.valueOf(next);
                cal.set(dpos, String.valueOf(d));
                System.out.println("the inserted value is " + cal.get(dpos));
                cal.set(getprevious(dpos - 1, cal), null);
                cal.set(getnext(dpos + 1, cal), null);
                i++;
            } else if (operatorchecker("*", cal)) {
                String prev = getprevious(cal, dpos - 1);
                String next = getnext(cal, dpos + 1);
                if(prev=="("||prev=="*"||prev=="+"||prev=="-"||prev=="/"||prev=="^"||next==")"||next=="*"||next=="+"||next=="-"||next=="/"||next=="^")
                {
                return null;
                }
                System.out.println("the previous and the next values are"
                        + prev + "\t" + next);
                Double d = Double.valueOf(prev) * Double.valueOf(next);
                cal.set(dpos, String.valueOf(d));
                System.out.println("the inserted value is " + cal.get(dpos));
                cal.set(getprevious(dpos - 1, cal), null);
                cal.set(getnext(dpos + 1, cal), null);
                i++;
            } else if (operatorchecker("+", cal)) {

                String prev = getprevious(cal, dpos - 1);
                String next = getnext(cal, dpos + 1);
                if(prev=="("||prev=="*"||prev=="+"||prev=="-"||prev=="/"||prev=="^"||next==")"||next=="*"||next=="+"||next=="-"||next=="/"||next=="^")
                {
                return null;
                }
                System.out.println("the previous and the next values are"
                        + prev + "\t" + next);
                Double d = Double.valueOf(prev) + Double.valueOf(next);
                cal.set(dpos, String.valueOf(d));
                System.out.println("the inserted value is " + cal.get(dpos));
                cal.set(getprevious(dpos - 1, cal), null);
                cal.set(getnext(dpos + 1, cal), null);
                i++;
            } else if (operatorchecker("-", cal)) {
                String prev = getprevious(cal, dpos - 1);
                String next = getnext(cal, dpos + 1);
                if(prev=="("||prev=="*"||prev=="+"||prev=="-"||prev=="/"||prev=="^"||next==")"||next=="*"||next=="+"||next=="-"||next=="/"||next=="^")
                {
                return null;
                }
                System.out.println("the previous and the next values are"
                        + prev + "\t" + next);
                Double d = Double.valueOf(prev) - Double.valueOf(next);
                cal.set(dpos, String.valueOf(d));
                System.out.println("the inserted value is " + cal.get(dpos));
                cal.set(getprevious(dpos - 1, cal), null);
                cal.set(getnext(dpos + 1, cal), null);
                i++;
            } else {
                i++;
            }
            // }
            // }
        }
            catch(Exception e)
                {
                    TextBox.setText("ERRROR");
                    return null;
                }
        }
        
        // display.setText("");
        System.out.println("the size of the array list is " + cal.size());
        //   display.setText(getnext(cal, 0));
        return cal;
    }


public String getnext(List<String> cal, int i) {
        while (i <= cal.size()) {
            if (cal.get(i) != null && cal.get(i) != "=") {
                nextpos = i;
                return cal.get(i);
            }
            i++;
        }
        return null;
    }

    public int getnext(int i, List<String> cal) {
        while (i <= cal.size()) {
            if (cal.get(i) != null && cal.get(i) != "=") {
                nextpos = i;
                return i;
            }
            i++;
        }
        return 0;
    }

    public String getprevious(List<String> cal, int i) {
        while (i >= 0) {
            if (cal.get(i) != null && cal.get(i) != "=") {
                prevpos = i;
                return cal.get(i);
            }
            i--;
        }
        return null;
    }

    public int getprevious(int i, List<String> cal) {
        while (i >= 0) {
            if (cal.get(i) != null && cal.get(i) != "=") {
                prevpos = i;
                return i;
            }
            i--;
        }
        return 0;
    }

    public Boolean operatorchecker(String operator, List<String> cal) {
        int j = 0;
        while (j < cal.size()) {
            if (cal.get(j) == null) {
                j++;
            } else if (cal.get(j).equals(operator)) {
                dpos = j;
                return true;
            } else {
                j++;
            }

        }
        return false;
    }
   public int operatorchecker(String operator, int position, List<String> cal) {
        int j = position;
        while (j < cal.size()) {
            if (cal.get(j) == null) {
                j++;
            } else if (cal.get(j).equals(operator)) {

                return j;
            } else {
                j++;
            }

        }
        return -1;
    }
    public Boolean operatorchecker1(String operator, int position, List<String> cal) {
        int j = position;
        while (j < cal.size()) {
            if (cal.get(j) == null) {
                j++;
            } else if (cal.get(j).equals(operator)) {

                return true;
            } else {
                j++;
            }

        }
        return false;
    }
     
    public Boolean bracketchecker(List<String> cal)
    {
        for(int i = 0; i<cal.size();i++)
        {
        if(cal.get(i)=="(")
        {
        Openingbrackets++;
        }
        if(cal.get(i)==")")
        {
        Closingbrackets++;  
        }
        }
        if(Closingbrackets==Openingbrackets)
        return true;
        else
        return false;
        
            
    }
    
    
    public void createstack(List<String> cal1, String fullexpression) {
        fullexpressionLength = fullexpression.length();
        int i = 0;
        System.out.println("the size of expression" + fullexpressionLength);
        while (i < fullexpressionLength) {
            charat = fullexpression.charAt(i);
            if (charat == '(') {
                cal1.add(String.valueOf(charat));
                i++;
            } else if (charat == '+' || charat == '-' || charat == '*'
                    || charat == '/' || charat == '=' || charat == ')' || charat == '^') {

                if (concat != "") {
                    cal1.add(concat);
                }
                cal1.add(String.valueOf(charat));
                concat = "";
                i++;
            } else {
                concat = concat + String.valueOf(charat);
                i++;
            }
        }

    }
   private int Openingbrackets=0;
    private int Closingbrackets=0;
    private int dpos;
    private String concat = "";
    private char charat;
    private double result;
    private String lastCommand;
    private String fullexpression;
    private int fullexpressionLength;
    private int prevpos;
    private int nextpos;
    private List<String> cal1 = new ArrayList<String>();
    private List<String> validation = new ArrayList<String>();
    Boolean endbracketchecker;
    private int check; 

}
