import dnd.Student;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(new Thread1());

        executor.execute(new Thread2());

        executor.execute(new Thread3());

        executor.shutdown();
    }
   static class Thread1 implements Runnable {
        @Override
        public void run() {
            try {
                File file = new File("");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName("Student");
                StringBuilder xmlContent = new StringBuilder();
            xmlContent.append("<Students>\n");

                          for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getElementsByTagName("id").item(0).getTextContent();
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String address = element.getElementsByTagName("address").item(0).getTextContent();
                    String dateOfBirth = element.getElementsByTagName("dateOfBirth").item(0).getTextContent();

                    System.out.println("ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Address: " + address);
                    System.out.println("Date of Birth: " + dateOfBirth);
                    System.out.println("---------------------------");

                    xmlContent.append("\t<Student>\n");
                    xmlContent.append("\t\t<id>").append(id).append("</id>\n");
                    xmlContent.append("\t\t<name>").append(name).append("</name>\n");
                    xmlContent.append("\t\t<address>").append(address).append("</address>\n");
                    xmlContent.append("\t\t<dateOfBirth>").append(dateOfBirth).append("</dateOfBirth>\n");
                    xmlContent.append("\t</Student>\n");
                }
            }

            xmlContent.append("</Students>");

            writeToFile(xmlContent.toString(), "kq.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    
        }

         private static void writeToFile(String content, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();
            System.out.println("File " + fileName + " đã được tạo thành công.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

   

    static class Thread2 implements Runnable {
        private static Student student;
        private int encodedAge;
        
        
         public void setStudent(Student student) {
            this.student = student;
        }
          public int getEncodedAge() {
            return encodedAge;
        }
        public static void processStudentData(Student data) {
            student = data;
        }

        @Override
       public void run() {
            if (student != null) {
              
                Date currentDate = new Date();
                long ageFuntionMili = currentDate.getTime() - student.getDateOfBirth().getTime();
                long ageYear = ageFuntionMili / (1000L * 60 * 60 * 24 * 365);

                encodedAge = encodeAge((int) ageYear);
            }
        }

        private int encodeAge(int age) {
            
            return age + 5;
        }
    }

    static class Thread3 extends Thread {
        private int encodedAge;
        private boolean isPrime;

        public void setEncodedAge(int encodedAge) {
            this.encodedAge = encodedAge;
        }

        public boolean isPrime() {
            return isPrime;
        }

        @Override
        public void run() {
            // Check if the sum of digits is prime
            int sumOfDigits = sumOfDigits(encodedAge);
            isPrime = isPrime(sumOfDigits);
        }

        private int sumOfDigits(int number) {
            int sum = 0;
            while (number != 0) {
                sum += number % 10;
                number /= 10;
            }
            return sum;
        }

        private boolean isPrime(int number) {
            if (number <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
    
    

}