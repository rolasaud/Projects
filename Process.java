package m;

import java.util.Random;

public class Process {
private static int counter =1;
private String name;
private int p;
public Process () {
   Random random = new Random();
      p = random.nextInt(50);
      name= "P"+counter;
      counter++;}
public int getPriorty() {
return p;
} 
public String getName() {
return name;
}
}