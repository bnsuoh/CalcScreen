/*
    <<-----Series Calculator----->>>
    <------Calculus BC Term Project by Bensu Sicim
*/

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
    private javax.swing.JLabel sequenceLabel,initialLabel,finalLabel,titleLabel,resultLabel,nameLabel;
    private javax.swing.JButton calcBut;
    private static final long serialVersionUID = 1L;
    Calculator calculator=new Calculator();
    public CalcScreen( Frame owner ){
        super( owner, true );
        setContentPane( createContent() );
        setSize(600, 250);
        setResizable(false);
        setLocationRelativeTo (null);
        calcBut.addActionListener(this); //make the button functional
    }
    private Container createContent(){
        JPanel result = new JPanel();
        result.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
        GroupLayout layout = new GroupLayout( result ); //create the layout
        result.setLayout( layout );
        layout.setAutoCreateGaps( true );
        // Create the components we will put in the screen
        titleLabel = new JLabel( "Series Calculator" ); //title
        titleLabel.setFont(new Font("Sans", Font.PLAIN, 20));
        sequenceLabel = new JLabel( "Enter Sequence using \"x\":" ); 
        sequenceTextField = new JTextField( 25 ); //sequence field
        initialLabel = new JLabel( "Enter First Bound:" );
        boundInitial = new JTextField( 20 ); //first bound field
        finalLabel = new JLabel( "Enter Last Bound" );
        boundFinal = new JTextField( 20 ); //last bound field
        calcBut = new JButton( "Calculate" ); //button
        resultLabel=new JLabel( " " ); //result/message board
        resultLabel.setFont(new Font("Sans", Font.PLAIN, 20));
        nameLabel = new JLabel( "by Bensu Sicim" );
        nameLabel.setForeground(Color.red);
        // Horizontally, we want to align the labels and the text fields
        // along the left (LEADING) edge
        layout.setHorizontalGroup( layout.createSequentialGroup()
                                       .addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ) //left part
                                                          .addComponent( sequenceLabel )
                                                          .addComponent( initialLabel )
                                                          .addComponent( finalLabel ) 
                                                          .addComponent( nameLabel )
                                                          )
                                       .addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING ) //right part
                                                          .addComponent(titleLabel)
                                                          .addComponent( sequenceTextField )
                                                          .addComponent( boundInitial )
                                                          .addComponent( boundFinal ) 
                                                          .addComponent( calcBut )
                                                          .addComponent( resultLabel ) )
                                       
        );
        // Vertically, we want to align each label with his textfield
        // on the baseline of the components
        layout.setVerticalGroup( layout.createSequentialGroup()
                                     .addComponent( titleLabel ) //title
                                     .addGroup( layout.createParallelGroup( GroupLayout.Alignment.BASELINE ) //sequence
                                                        .addComponent( sequenceLabel )
                                                        .addComponent( sequenceTextField ) )
                                     .addGroup( layout.createParallelGroup( GroupLayout.Alignment.BASELINE ) //initial bound
                                                        .addComponent( initialLabel )
                                                        .addComponent( boundInitial ) )
                                     .addGroup( layout.createParallelGroup( GroupLayout.Alignment.BASELINE ) //final bound
                                                        .addComponent( finalLabel )
                                                        .addComponent( boundFinal ) 
                                                         )
                                     .addComponent( calcBut )
                                     .addComponent( resultLabel ) //result/message
                                     .addComponent( nameLabel )
                
        );
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
        int init=0;
        int fin=0;
        boolean success=true;
        String sequence = sequenceTextField.getText ();
            try{
                init = (Integer.parseInt(boundInitial.getText ())); //make the bound inputs integer
                fin = (Integer.parseInt(boundFinal.getText ()));
            }
            catch(IllegalArgumentException a){
                success=false;
                resultLabel.setText("Bounds should be integers"); //if input is not a number, display this message
            }
            finally {
                if(success && sequence!=" "){
                    try{
                        double result=0;
                        for(int i=init;i<=fin;i++){
                            result+=Double.parseDouble(calculator.calculate(calculator.deX(sequence,Integer.toString(i))));
                        }
                        resultLabel.setText(Double.toString(result));
                    }
                    catch(IllegalArgumentException b){
                        resultLabel.setText("Illegal sequence");
                    }
                }
            }
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
    public Calculator(){}
    /**
     * @param args the command line arguments
     */
    public double factorial(int i){
        if(i>1)
            return i*factorial(i-1); 
        return 1;
    }    
    public double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}
    public String deE(String str){
        String corrections="";
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='e'){
                if(i!=0&&isNumeric(Character.toString(str.charAt(i-1))))
                    corrections+="*"+round(Math.E,6);
                else
                    corrections+=round(Math.E,6);
            }
            else
                corrections+=str.charAt(i);
        }
        return corrections;
    }
    public String deX(String str,String X){
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='x'){
                str=str.substring(0,i)+X+str.substring(i+1,str.length());
            if(i>0 && isNumeric(str.substring(i-1,i)))
                str=str.substring(0,i)+"*"+str.substring(i,str.length());
            }}
        return str;
    }
    public String calculate(String str){
        String formatted=format(deE(str));
        String spaceless = "";
        for (int i = 0; i < formatted.length(); i++) {
            if(formatted.charAt(i) != ' ')
                spaceless += formatted.charAt(i);
        }
        if(spaceless.length()==0)
            return "";
        if(spaceless.indexOf('(')==-1)
            return (String)(Ord3(Ord2(Ord1(Ord0(separate(spaceless))))).get(0));
        else 
            return calculate(spaceless.substring(0,spaceless.indexOf('('))+calculate(spaceless.substring(spaceless.indexOf('(')+1,findMatch(spaceless.indexOf('('),spaceless)))+spaceless.substring(findMatch(spaceless.indexOf('('),spaceless)+1,spaceless.length()));
    }
    public int findMatch(int pos, String str){
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
    public boolean hasPar(ArrayList list){
        boolean a=false;
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals("(")){
                a=true;
                break;
            }
        }
        return a;
    }
    public ArrayList Ord0(ArrayList list){
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
    public ArrayList Ord1(ArrayList list){
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
    public ArrayList Ord2(ArrayList list){
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
    public ArrayList Ord3(ArrayList list){
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
    public ArrayList separate(String str){
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
            }
        }
        for(int i=str.length()-1;i>0;i--){
            if(isOperant(str.charAt(i))){
                list.add(str.substring(i+1,str.length()));
                break;
            }
            else if(str.length()-i>3 &&((str.substring(i,i+3)).equals("sin") || (str.substring(i,i+3)).equals("cos") || (str.substring(i,i+3)).equals("tan") || (str.substring(i,i+3)).equals("cot") ||(str.substring(i,i+3)).equals("sec") ||(str.substring(i,i+3)).equals("csc"))){
                list.add(str.substring(i+3,str.length()));
                break;
            }
            
        }
        return list;
    }
    public boolean isOperant(char b){
        return (b=='+'||b=='*'||b=='-'||b=='/'||b=='^'||b=='%');
    }
    public String format(String str){
        str=str.toLowerCase();
        String spaceless = "";
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) != ' ')
               spaceless += str.charAt(i);
        }
        String corrections = "";
        for (int i = 0; i < spaceless.length(); i++) {
            if(i>0&&spaceless.charAt(i)=='-'&&spaceless.charAt(i-1)=='+')
                i++;
            if(spaceless.charAt(i)=='+'&&spaceless.charAt(i+1)=='-')
                i++;
            if(spaceless.charAt(i) == 'x')
                if (i == 0 || !isNumeric(spaceless.charAt(i-1)+""))
                    corrections += "1";
            corrections += spaceless.charAt(i);
            if(spaceless.charAt(i) == 'x')
                if (i == spaceless.length() - 1 || spaceless.charAt(i+1) != '^')
                    corrections += "^1";
        }
        if(str.charAt(0)=='-')
            return "0"+corrections;
        return "0+"+corrections;
    }
    public boolean isNumeric(String str)  {  
        try  {  
            int d = Integer.parseInt(str);  
        }  
        catch(NumberFormatException nfe) {  
            return false;  
        }  
        return true;  
    }
    public int findMatch(String str, int pos) {
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
