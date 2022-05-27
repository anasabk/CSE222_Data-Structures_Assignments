import java.util.Scanner;

import CPS.Building.*;
import CPS.Exceptions.CPSException.*;
import CPS.Street.*;
import CPS.Street.Street.*;

public class Driver {
    public static int intInput(Scanner terminlaIn, String failMessage, int min, int max){
        int input;
        while(true){
            input = Integer.parseInt(terminlaIn.nextLine());
            if(input < min || input > max)
                System.out.printf(failMessage);

            else return input;
        }
    }

    public static void main(String[] args) {
        System.out.printf("\nCity Planing System\n");
        int input;
        Scanner terminlaIn = new Scanner(System.in);
        Street testStreet;
        
        while(true){
            System.out.printf("\n Please Choose from the following:\n");
            System.out.printf("1. Automated Test\n2. User Input\n0. Exit\n");
            input = intInput(terminlaIn, "Try again\n", 0, 2);
        
            if(input == 1){
                System.out.printf("\nPlease enter Which version to test:\n");
                System.out.printf("1. ArrayListStreet\n2. LinkedListStreet\n3. LDLinkedStreet\n0. Go Back\n");
                input = intInput(terminlaIn, "Try again\n", 0, 3);

                if(input == 1)
                    testStreet = new ArrayListStreet(40);

                else if(input == 2)
                    testStreet = new LinkedListStreet(40);

                else if(input == 3)
                    testStreet = new LDLinkedStreet(40);

                else continue;

                try {
                    String silhouette;
                    System.out.printf("\nA street of length 0 meters is created\n");

                    testStreet.setMode(Mode.Read_Write);
                    System.out.printf("\nMode is set to Read/Write\n");

                    testStreet.add(new House(6, 5, "owner man", 10, "Blue"), 1, Side.Front);
                    System.out.printf("\nAdd a house 6 meters long, 5 meters high, 1 meters away from the left of the Front side\n");
                    silhouette =testStreet.getsilhouette();
                    System.out.printf(silhouette);

                    testStreet.add(new Market(6, 8, "owner man", 22, 30, 8, 30), 7, Side.Front);
                    System.out.printf("\nAdd a market 6 meters long, 8 meters high, 7 meters away from the left of the Front side\n");
                    silhouette = testStreet.getsilhouette();
                    System.out.printf(silhouette);

                    testStreet.add(new Office(10, 4, "owner man", "Useless Job"), 15, Side.Front);
                    System.out.printf("\nAdd an office 10 meters long, 4 meters high, 15 meters away from the left of the Front side\n");
                    silhouette = testStreet.getsilhouette();
                    System.out.printf(silhouette);

                    testStreet.add(new Office(8, 6, "owner man", "Useless Job"), 13, Side.Back);
                    System.out.printf("\nAdd an office 8 meters long, 4 meters high, 13 meters away from the left of the Back side\n");
                    silhouette = testStreet.getsilhouette();
                    System.out.printf(silhouette);

                    System.out.printf("\nAdd a playGround 5 meters long, 21 meters away from the left of the Back side\n");
                    testStreet.add(new PlayGround(5), 21, Side.Back);
                    silhouette = testStreet.getsilhouette();
                    System.out.printf(silhouette);

                    System.out.printf("\nAdd a playGround 6 meters long, 23 meters away from the left of the Back side\n");
                    if( !testStreet.add(new PlayGround(6), 23, Side.Back) )
                        System.out.printf("Insuccessful, because the place is occupied\n");
                    silhouette = testStreet.getsilhouette();
                    System.out.printf(silhouette);

                    testStreet.setMode(Mode.Read_Only);
                    System.out.printf("\nMode is set to Read Only\n");

                    try {
                        System.out.printf("\nRemove whatever building is in the area 7 meters away from the left of the Front side\n");
                        testStreet.remove_Position(7, Side.Front);
                    } catch (ReadOnlyException e) {
                        System.out.printf("Insuccessful, because: " + e.getMessage() + "\n");
                    }
                    testStreet.setMode(Mode.Read_Write);
                    System.out.printf("\nMode is set to Read/Write\n");

                    System.out.printf("\nRemove whatever building is in the area 7 meters away from the left of the Front side\n");
                    testStreet.remove_Position(7, Side.Front);
                    silhouette =testStreet.getsilhouette();
                    System.out.printf(silhouette);

                    System.out.printf("\nRemove the second building from the Front side\n");
                    testStreet.remove(1, Side.Front);
                    silhouette =testStreet.getsilhouette();
                    System.out.printf(silhouette);

                    System.out.printf("The building 7 meters away from the left of the front side is:\n%s\n", 
                                       testStreet.get_Position(6, Side.Front));

                    System.out.printf("The number of playgrounds in the back side is: %d\n",testStreet.countType(PlayGround.class.getSimpleName(), Side.Back));
                    System.out.printf("The ratio of playgrounds in the street is: %f\n",testStreet.playGroundRatio());

                    System.out.printf("Focus on the building in the position 14 in the back side: %s\n", 
                                       testStreet.get_Position(14, Side.Back).focus());

                    System.out.printf("%s\n",testStreet.toString());
                    
                } catch (Exception e) { System.out.printf(e.getMessage()); }
            }

            else if(input == 2){
                System.out.printf("To be Implemented\n");
                terminlaIn.close();
                return;
            }

            else {
                terminlaIn.close();
                break;
            }
        }
    }
}
