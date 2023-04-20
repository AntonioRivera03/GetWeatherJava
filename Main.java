
public class Main {
/*Preface
All code uses built in java imports. No dependencies needed
*/

    public static void main(String[] args){
        //Reference to the call class
        call str = new call();

        //Narrowing casting to turn Fahrenheit into integer from string because who wants F in decimal.
        int temp = (int) Float.parseFloat(str.getTemp());
        //First print statement uses the class object str to print the return statement from the getRequest Class
        // The return statement being the body of the api response.
        System.out.println(str.getRequest());
        //Second statement prints a formatted string that prints the product of getLocal(Location) and temperature
        System.out.println(String.format("%s: %d°F",str.getLocal(), temp));
    }
}