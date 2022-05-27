import java.util.Scanner;

import CPS.*;
import CPS.CPSException.PlaceIsOccupiedException;
import CPS.CPSException.ReadOnlyException;
import CPS.Street.Mode;
import CPS.Street.Side;

public class Driver {
    public static void main(String[] args) {
        System.out.printf("City Planing System\n\n Please enter the number of your choice:\n");
        System.out.printf("1. Automated Test\n2. User Input\n0. Exit");
        int input;
        
        while(true){
            Scanner terminlaIn = new Scanner(System.in);
            input = Integer.parseInt(terminlaIn.nextLine());

            if(input < 0 || input > 2)
                System.out.printf("Try again\n");

            else break;
        }

        if(input == 1){
            try {
                String[] silhouette;
                Street street = new Street(30);
                System.out.printf("\nA street of length 30 meters is created\n");

                street.setMode(Mode.Write);
                System.out.printf("Mode is set to Read/Write\n");

                street.add(new House(6, 5, "owner man", 10, "Blue"), 1, Side.Front);
                System.out.printf("Add a house 6 meters long, 5 meters high, 1 meters away from the left of the Front side\n");
                silhouette = street.getsilhouette();

                for(int i = 0; i < silhouette.length; ++i){
                    System.out.printf("%s\n", silhouette[i]);
                }

                street.add(new Market(6, 8, "owner man", 22, 30, 8, 30)  , 7, Side.Front);
                System.out.printf("Add a market 6 meters long, 8 meters high, 7 meters away from the left of the Front side\n");

                silhouette = street.getsilhouette();

                for(int i = 0; i < silhouette.length; ++i){
                    System.out.printf("%s\n", silhouette[i]);
                }

                street.add(new Office(10, 4, "owner man", "Useless Job"), 13, Side.Front);
                System.out.printf("Add an office 10 meters long, 4 meters high, 13 meters away from the left of the Front side\n");

                silhouette = street.getsilhouette();

                for(int i = 0; i < silhouette.length; ++i){
                    System.out.printf("%s\n", silhouette[i]);
                }

                street.add(new Office(8, 6, "owner man", "Useless Job"), 13, Side.Back);
                System.out.printf("Add an office 8 meters long, 4 meters high, 13 meters away from the left of the Back side\n");

                silhouette = street.getsilhouette();

                for(int i = 0; i < silhouette.length; ++i){
                    System.out.printf("%s\n", silhouette[i]);
                }

                street.add(new PlayGround(5), 21, Side.Back);
                System.out.printf("Add a playGround 5 meters long, 21 meters away from the left of the Back side\n");

                silhouette = street.getsilhouette();

                for(int i = 0; i < silhouette.length; ++i){
                    System.out.printf("%s\n", silhouette[i]);
                }
                
                try {
                    System.out.printf("Add a playGround 5 meters long, 23 meters away from the left of the Back side\n");
                    street.add(new PlayGround(6), 23, Side.Back);
                } catch (PlaceIsOccupiedException e) {
                    System.out.printf("Insuccessful, because: " + e.getMessage() + "\n");
                }

                silhouette = street.getsilhouette();

                for(int i = 0; i < silhouette.length; ++i){
                    System.out.printf("%s\n", silhouette[i]);
                }

                street.setMode(Mode.Read);
                System.out.printf("Mode is set to Read Only\n");

                try {
                    System.out.printf("Remove whatever building is in the area 7 meters away from the left of the Front side\n");
                    street.remove(7, Side.Front);
                } catch (ReadOnlyException e) {
                    System.out.printf("Insuccessful, because: " + e.getMessage() + "\n");
                }

                street.setMode(Mode.Write);
                System.out.printf("Mode is set to Read/Write\n");

                System.out.printf("Remove whatever building is in the area 7 meters away from the left of the Front side\n");
                street.remove(7, Side.Front);

                silhouette = street.getsilhouette();

                for(int i = 0; i < silhouette.length; ++i){
                    System.out.printf("%s\n", silhouette[i]);
                }

                System.out.printf("\nThe street has the length: %d\n", street.getLength());
                System.out.printf("The building in the area 2 meters away from the left of the front side is:\n%s\n", 
                                  street.getBuilding(2, Side.Front));

                System.out.printf("The number of playgrounds in the back side is: %d\n", street.countType(PlayGround.class, Side.Back));
                System.out.printf("The ratio of playgrounds in the street is: %f\n", street.playGroundRatio());

                System.out.printf("Focus on the building in the position 14 in the back side: %s\n", street.getBuilding(14, Side.Back).focus());

                System.out.printf("%s\n", street.toString());
                
            } catch (Exception e) {
                System.out.printf(e.getMessage());
            }
        }

        else if(input == 2){
            System.out.printf("To be Implemented\n");
            return;
        }

        else {
            return;
        }
    }
}
