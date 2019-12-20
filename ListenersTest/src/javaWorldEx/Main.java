package javaWorldEx;

public class Main
{
//   public Main()
//   {
//      ObservableValue ov = new ObservableValue(0);
//      TextObserver to = new TextObserver(ov);
//      ov.addObserver(to);
//   }
   public static void main(String [] args)
   {
//      Main m = new Main();
      ObservableValue v = new ObservableValue(0);
      TextObserver o = new TextObserver(v);
      v.addObserver(o);
      System.out.println(v.hasChanged());
      v.setValue(0);
      //v.change();
      //o.update(v, o);
      System.out.println(v.hasChanged());

   }
}