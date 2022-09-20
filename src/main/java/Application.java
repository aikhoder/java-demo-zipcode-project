import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.zipcode.data.DTO.ZipCodeDTO;
import com.kenzie.zipcode.http.HTTPConnector;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try {
            //declare variable
            String BASE_URL = "https://api.zippopotam.us/us/";
            Scanner scanner = new Scanner(System.in);
            String recipientName;
            String urlPath;
            String streetAddress;
            String city;
            String state;
            String zipCode;


            // Use scanner - read in user input
            System.out.println("Enter recipient name : ");
            recipientName = scanner.nextLine();

            System.out.println("Enter street Address : ");
            streetAddress = scanner.nextLine();


            System.out.println("Enter the city name : ");
            city = scanner.nextLine();

            System.out.println("Enter the state name : (two letter abbreviation) ");
            state = scanner.nextLine();
            // Clean user input
            // replace spaces with %20
            city = city.replaceAll(" ", "%20");

            // Create URL
            urlPath = BASE_URL + state + "/" + city;

            // Call HTTP GET
            String httpResponse = HTTPConnector.makeGETRequest(urlPath);
            if (httpResponse.equals("\\{\\}"))
                System.out.println("No valid Zip Code ");
            else {
                // Use Object on the DTO

                //1. Instantiate  ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();

                //2. declare DTO object
                ZipCodeDTO zipObj;

                // read data - readValue()
                zipObj = objectMapper.readValue(httpResponse, ZipCodeDTO.class);


                if (zipObj.getPlaces().size() == 1)
                    zipCode = zipObj.getPlaces().get(0).getPostCode();
                else {
                    System.out.println("Choose a zip code : ");
                    for (int i = 0; i < zipObj.getPlaces().size(); i++) {
                        System.out.println(i+1 +") " +zipObj.getPlaces().get(i).getPostCode());
                    }

                    // read in choice
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    // set zipcode to choice :
                    zipCode=zipObj.getPlaces().get(choice-1).getPostCode();

                }
                System.out.println("User choose zipcode  "+zipCode);
                // Address Info
                System.out.println("Name : "+recipientName);
                System.out.println("Street address : "+streetAddress);
                System.out.println("city : "+city);
                System.out.println("state : "+state);
                System.out.println("Zip : "+zipCode);


            }




        // Use ObjectMapper

        // Loop and ask user for zipcode if more than one

        }catch(Exception e){
            System.out.println("unexpected exception"+e.getMessage());
        }

    }



    public static void main_backup(String[] args) {
        try {
            // API https://api.zippopotam.us/us/ca/fontana

            // Declare variable
            final String TEST_URL = "https://api.zippopotam.us/us/ca/fontana";
            String httpResponseStr;


            // Connect to zippopotam.us and get the zipcode
            httpResponseStr = HTTPConnector.makeGETRequest(TEST_URL);

            System.out.println(httpResponseStr);

            //1. Instantiate  ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            //2. declare DTO object
            ZipCodeDTO zipObj;

            // read data - readValue()
            zipObj = objectMapper.readValue(httpResponseStr, ZipCodeDTO.class);


        }
        catch(Exception e){
            System.out.println("unexpected exception"+e);
        }

    }
}
