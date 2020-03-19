import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Visitor {
    private String firstName, lastName, comments, visitorName;
    private int age;
    private String dateOfVisit;
    private String timeOfVisit;
    static Scanner sc = new Scanner(System.in);
    private final static Logger logger = LogManager.getLogger(Visitor.class.getName());

    public Visitor(String firstName,String lastName, int age, String dateOfVisit, String timeOfVisit, String comments,String visitorName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfVisit = dateOfVisit;
        this.timeOfVisit = timeOfVisit;
        this.comments = comments;
        this.visitorName = visitorName;
    }

    public static void save(String firstName,String lastName, String age, String dateOfVisit, String timeOfVisit, String comments, String visitorName) throws IOException{
        try{
            Scanner sc = new Scanner(System.in);
            File file = new File("visitorsFiles/visitor_"+firstName.toLowerCase()+"_"+lastName.toLowerCase()+".txt");

            if(file.exists()== false){
                System.out.println("File created.");
                logger.debug("File created.");
            }
            else {
                System.out.println("Files not created.");
                logger.debug("Files not created.");
                System.out.println(0);
            }
            BufferedWriter add = new BufferedWriter(new FileWriter (file, true));
            add.write("Names: "+firstName+" ");
            add.write(lastName+"\n");
            add.write("Age: "+age+"\n");
            add.write("Visit date: "+dateOfVisit+"\n");
            add.write("Visit time: " + timeOfVisit+"\n");
            add.write("Comments: "+comments+"\n");
            add.write("Assistant Name: "+visitorName);

            add.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            logger.debug("Operation failed.");
        }
    }

    public static void load(String firstName,String lastName)throws IOException{
        try{
            FileReader fr = new FileReader("visitorsFiles/visitor_"+firstName+"_"+lastName+".txt");
            BufferedReader br = new BufferedReader(fr);
            int i;

            while (((i=br.read()) != -1)){
                System.out.print((char)i);
            }
            br.close();
            fr.close();
            logger.info("File opened.");

        }
        catch (IOException e){
            System.out.println(e.getMessage());
            logger.error("File not opened.");
        }
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Enter 1 to record a visit");
        System.out.println("Enter 2 to view a visit");
        int part = sc.nextInt();

        execute(part);
    }

    public static void execute(int part) throws IOException {

        //Record the visit
        if(part == 1){
            //Declarations
            String name, surname, comments, assistantName, visitDate, visitTime, age;

            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter time = DateTimeFormatter.ofPattern("HH.mm.ss");
            LocalDateTime currentDate = LocalDateTime.now();
            LocalDateTime currentTime = LocalDateTime.now();

            //Record the assistant's name
            System.out.println("Welcome assistant, what is your name.?");
            assistantName = sc.nextLine();

            //Record the visitor's first name
            System.out.println("Please enter visitor's first name");
            name = sc.nextLine();

            //Record the visitor's last name
            System.out.println("Please enter visitor's last name");
            surname = sc.nextLine();

            //Record the visitor's age
            System.out.println("Please enter visitor's age");
            age = sc.nextLine();

            //Assign the visit's comment
            System.out.println("What are the comments for the visit.?");
            comments = sc.nextLine();

            //assign date and time
            visitDate = currentDate.format(date);
            visitTime = currentTime.format(time);


            try{
                Visitor.save(name, surname, age, visitDate, visitTime, comments, assistantName);
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }

        //View a visit
        }else if(part == 2){

            String passln = sc.nextLine();
            System.out.println("Enter name");
            String firstName = sc.nextLine();

            System.out.println("Enter Surname");
            String lastName = sc.nextLine();

            load(firstName,lastName);
        }else{
            logger.error("Invalid selection, error executing.");
        }
    }
}