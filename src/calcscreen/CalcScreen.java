package calcscreen;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class CalcScreen extends JDialog implements ActionListener{
    private javax.swing.JTextField sequenceTextField,boundInitial,boundFinal;
    private javax.swing.JLabel sequenceLabel,initialLabel,finalLabel,titleLabel,resultLabel;
    private javax.swing.JButton calcBut;
    private static final long serialVersionUID = 1L;
    public CalcScreen( Frame owner ){
        super( owner, true );
        setContentPane( createContent() );
        setSize(550, 250);
        setLocationRelativeTo (null);
        calcBut.addActionListener(this);
    }
    private Container createContent(){
        JPanel result = new JPanel();
        result.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
        // Create the layout
        GroupLayout layout = new GroupLayout( result );
        result.setLayout( layout );
        layout.setAutoCreateGaps( true );
        // Create the components we will put in the form
        titleLabel = new JLabel( "Series Calculator" );
        sequenceLabel = new JLabel( "Enter Sequence using \"n\":" );
        sequenceTextField = new JTextField( 20 );
        initialLabel = new JLabel( "Enter First Bound:" );
        boundInitial = new JTextField( 20 );
        finalLabel = new JLabel( "Enter Last Bound" );
        boundFinal = new JTextField( 20 );
        calcBut = new JButton( "Calculate" );
        resultLabel=new JLabel( " " );
        // Horizontally, we want to align the labels and the text fields
        // along the left (LEADING) edge
        layout.setHorizontalGroup( layout.createSequentialGroup()
                                       .addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
                                                          .addComponent( sequenceLabel )
                                                          .addComponent( initialLabel )
                                                          .addComponent( finalLabel ) 
                                                          )
                                       .addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
                                                          .addComponent(titleLabel)
                                                          .addComponent( sequenceTextField )
                                                          .addComponent( boundInitial )
                                                          .addComponent( boundFinal ) 
                                                          .addComponent( calcBut )
                                                          .addComponent( resultLabel))
        );
        // Vertically, we want to align each label with his textfield
        // on the baseline of the components
        layout.setVerticalGroup( layout.createSequentialGroup()
                                     .addComponent( titleLabel )
                                     .addGroup( layout.createParallelGroup( GroupLayout.Alignment.BASELINE )
                                                        .addComponent( sequenceLabel )
                                                        .addComponent( sequenceTextField ) )
                                     .addGroup( layout.createParallelGroup( GroupLayout.Alignment.BASELINE )
                                                        .addComponent( initialLabel )
                                                        .addComponent( boundInitial ) )
                                     .addGroup( layout.createParallelGroup( GroupLayout.Alignment.BASELINE )
                                                        .addComponent( finalLabel )
                                                        .addComponent( boundFinal ) 
                                                         )
                                     .addComponent( calcBut )
                                     .addComponent( resultLabel )
        );
        /*calcBut.addActionListener((ActionEvent e) -> {
            String sequence = sequenceTextField.getText ();
            try{
                int init = (Integer.parseInt(sequenceTextField.getText ()));
                int fin = (Integer.parseInt(sequenceTextField.getText ()));
            }
            catch(IllegalArgumentException a){
                resultLabel.setText("Bounds should be integers");
            }
            //Calculator calculator=new Calculator(sequence);
        });*/
        return result;
}
  /*
   *  Member data
   */
    public static void main( String[] args ){
        CalcScreen dialog = new CalcScreen( null );
        dialog.pack();
        dialog.setVisible( true );  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sequence = sequenceTextField.getText ();
            try{
                int init = (Integer.parseInt(boundInitial.getText ()));
                int fin = (Integer.parseInt(boundFinal.getText ()));
                resultLabel.setText("hey");
            }
            catch(IllegalArgumentException a){
                resultLabel.setText("Bounds should be integers");
            }
            //Calculator calculator=new Calculator(sequence);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cem
 */
class Calculator {
    private String DirInput,formatted;
    private int init,fin;
    public Calculator(String sequence, int i, int f) {
        // TODO code application logic here
        DirInput=sequence;
        init=i;
        fin=f;
        formatted=format(DirInput);
        process();
    }
    public String process(){
        return(calculate(formatted));
    }
    public static String calculate(String str){
        if(str.length()==0)
            return "";
        if(str.indexOf('(')==-1)
            return (String)(Ord3(Ord2(Ord1(Ord0(separate(str))))).get(0));
        else 
            return calculate(str.substring(0,str.indexOf('('))+calculate(str.substring(str.indexOf('(')+1,findMatch(str.indexOf('('),str)))+str.substring(findMatch(str.indexOf('('),str)+1,str.length()));
    }
    public static int findMatch(int pos, String str){
        int open=0;
        for(int i=pos; i<str.length();i++){
            if(str.charAt(i)=='(')
                open++;
            else if(str.charAt(i)==')')
                open--;
            if(open==0)
                return i;
        }
        return -1;
    }
    public static boolean hasPar(ArrayList list){
        boolean a=false;
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals("(")){
                a=true;
                break;
            }
        }
        return a;
    }
    public static ArrayList Ord0(ArrayList list){
        for (int i=0;i<list.size();i++){
            if(list.get(i).equals("sin")){
                list.set(i,Double.toString(Math.sin(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
            else if(list.get(i).equals("cos")){
                list.set(i,Double.toString(Math.cos(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
            else if(list.get(i).equals("tan")){
                list.set(i,Double.toString(Math.tan(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
            else if(list.get(i).equals("cot")){
                list.set(i,Double.toString(1/Math.tan(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
            else if(list.get(i).equals("sec")){
                list.set(i,Double.toString(1/Math.cos(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
            else if(list.get(i).equals("csc")){
                list.set(i,Double.toString(1/Math.sin(Double.parseDouble((String)(list.get(i+1))))));
                list.remove(i+1);
            }
        }
        return list;
    }
    public static ArrayList Ord1(ArrayList list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals('^')){
                String one=(String) list.get(i-1);
                String two=(String) list.get(i+1);
                list.set(i-1,Double.toString(Math.pow(Double.parseDouble(one),Double.parseDouble(two))));
                list.remove(i);
                list.remove(i);
                i--;
            }
        }
        return list;
    }
    public static ArrayList Ord2(ArrayList list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals('*')){
                String one=(String) list.get(i-1);
                String two=(String) list.get(i+1);
                list.set(i-1,Double.toString(Double.parseDouble(one)*Double.parseDouble(two)));
                list.remove(i);
                list.remove(i);
                i--;
            }
            else if(list.get(i).equals('/')){
                String one=(String) list.get(i-1);
                String two=(String) list.get(i+1);
                list.set(i-1,Double.toString(Double.parseDouble(one)/Double.parseDouble(two)));
                list.remove(i);
                list.remove(i);
                i--;
            }
            else if(list.get(i).equals('%')){
                String one=(String) list.get(i-1);
                String two=(String) list.get(i+1);
                list.set(i-1,Double.toString(Double.parseDouble(one)%Double.parseDouble(two)));
                list.remove(i);
                list.remove(i);
                i--;
            }
        }
        return list;
    }
    
    public static ArrayList Ord3(ArrayList list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals('+')){
                String one=(String) list.get(i-1);
                String two=(String) list.get(i+1);
                list.set(i-1,Double.toString(Double.parseDouble(one)+Double.parseDouble(two)));
                list.remove(i);
                list.remove(i);
                i--;
            }
            else if(list.get(i).equals('-')){
                String one=(String) list.get(i-1);
                String two=(String) list.get(i+1);
                list.set(i-1,Double.toString(Double.parseDouble(one)-Double.parseDouble(two)));
                list.remove(i);
                list.remove(i);
                i--;
            }
        }
        return list;
    }
    public static ArrayList separate(String str){
        int lastInd=0;
        ArrayList list=new ArrayList();
        if(str.charAt(0)=='+')
            str=str.substring(1,str.length());
        int a=0;    
        if(str.charAt(0)=='-')
                a++;
        for(int i=a;i<str.length();i++){
            if(isOperant(str.charAt(i))){
                list.add(str.substring(lastInd, i));
                list.add(str.charAt(i));
                lastInd=i+1;
            }
            else if(str.length()-i>3&&((str.substring(i,i+3)).equals("sin") || (str.substring(i,i+3)).equals("cos") || (str.substring(i,i+3)).equals("tan") || (str.substring(i,i+3)).equals("cot") ||(str.substring(i,i+3)).equals("sec") ||(str.substring(i,i+3)).equals("csc"))){
                //list.add(str.substring(lastInd, i));
                list.add(str.substring(i,i+3));
                lastInd=i+3;
                System.out.println(list);
            }
        }
        for(int i=str.length()-1;i>0;i--){
            if(isOperant(str.charAt(i))){
                list.add(str.substring(i+1,str.length()));
                break;
            }
            else if(str.length()-i>3 &&((str.substring(i,i+3)).equals("sin") || (str.substring(i,i+3)).equals("cos") || (str.substring(i,i+3)).equals("tan") || (str.substring(i,i+3)).equals("cot") ||(str.substring(i,i+3)).equals("sec") ||(str.substring(i,i+3)).equals("csc"))){
                list.add(str.substring(i+3,str.length()));
                System.out.println(list);
                break;
            }
            
        }
        return list;
    }
    public static boolean isOperant(char b){
        return (b=='+'||b=='*'||b=='-'||b=='/'||b=='^'||b=='%');
    }
    public static String format(String str){
        str=str.toLowerCase();
        String spaceless = "";
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) != ' ')
               spaceless += str.charAt(i);
        }
        String corrections = "";
        for (int i = 0; i < spaceless.length(); i++) {
            if(spaceless.charAt(i) == 'x')
                if (i == 0 || !isNumeric(spaceless.charAt(i-1)+""))
                    corrections += "1";
            corrections += spaceless.charAt(i);
            if(spaceless.charAt(i) == 'x')
                if (i == spaceless.length() - 1 || spaceless.charAt(i+1) != '^')
                    corrections += "^1";
        }
        return "0"+corrections;
    }
    public static boolean isNumeric(String str)  {  
        try  {  
            int d = Integer.parseInt(str);  
        }  
        catch(NumberFormatException nfe) {  
            return false;  
        }  
        return true;  
    }
    public static int findMatch(String str, int pos) {
        int open = 1;
        for (int i = pos + 1; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                open++;
            if (str.charAt(i) == ')')
                open--;
            if (open == 0)
                return i;
        }
        return pos + 2;
    }
}