public class zadanie1 {
  public int x = 1;
  double y = 0.1;
  boolean z = false;
  char q = '\u0000';

  int j = 4 + 5;
  double i = 0.4 + 0.5;

  boolean a = true && true;
  boolean b = false || false;

  public static void main(String[] args) {
    System.err.println("hello world");

    int l = 2;

    if (l >= 0) {
      System.err.println("positvie");
    } 

    if (l % 2 == 0) {
      System.err.println("even");
    } else {
      System.err.println("odd");
    }

    switch (l) {
      case 1:  System.err.println("hello world 1");
               break;
      case 2:  System.err.println("hello world 2");
               break;
      case 3:  System.err.println("hello world 3");
               break;
      case 4:  System.err.println("hello world 4");
               break;
    }

    for (int i = 1; i <= 10; i++) {
      System.err.println("Count is: " + i);
    }

    int o = 10;

    while (o >= 0) {
      System.err.println("Count is: " + o);
      o--;
    }

    int p = 5;

    do {
      System.err.println("hello world");
      p--;
    } while (p >=1);

    int[] anArray;

    anArray = new int[5];

    for (int i = 1; i <= 5; i++) {
      anArray[i-1] = i;
    }

    for (int i = 1; i <= 5; i++) {
      System.err.println(anArray[i-1]);
    }

    String text1 = "text";  
    String text2 = "1";  

    String text3 = text1 + text2;

    if (text1 == text2) {
      System.err.println("equal");
    }

    int fac = 1;

    for (int n = 6; n > 0; n--){
      fac = fac * n;
    }
    System.err.println(fac);

    int max = 0;

    for (int q = anArray.length; q > 0; q--) {
      if (q == anArray.length) {
        max = anArray[q-1];
      }

      if (max < anArray[q-1]) {
        max = anArray[q-1];
      }
    }
    System.err.println(max);

    String str= "word", nstr="";
    char ch;
      
    for (int i=0; i<str.length(); i++) {
      ch= str.charAt(i); 
      nstr= ch+nstr; 
    }
    System.out.println("Reversed word: "+ nstr);
  }
}
