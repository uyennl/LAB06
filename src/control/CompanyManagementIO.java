package control;

import model.Company;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.function.Function;
import model.Customer;

import javax.imageio.metadata.IIOInvalidTreeException;

public class CompanyManagementIO {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public<T> boolean savefile(String path,List<T> list ,Function<T,String> f) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            for (T t: list) {
                bw.write(f.apply(t));
                bw.write(NEW_LINE_SEPARATOR);
            }

        } catch (IOException e) {
            System.out.println("Error When save file !!!");
            return false;
        } finally {
            try {
                bw.flush();
                bw.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                return false;
            }
        }
        return true;
    }

    public  void readFile(String path) throws ParseException {

        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                List<String> strLine = parseCsvLine(line);
                String id = strLine.get(0);
                String name = strLine.get(1);
                String phone = strLine.get(2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
                formatter.withResolverStyle(ResolverStyle.STRICT);
                LocalDate birthDay = LocalDate.parse(strLine.get(3), formatter);
                Customer s = new Customer(id, name, phone, birthDay);
                Company.customerList.add(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }catch (DateTimeParseException e){
            System.out.println("Lỗi");
        }
     catch (NoSuchElementException e) {
        System.out.println("Lỗi");
    }
        catch (IllegalArgumentException e){
            System.out.println("Lỗi");
        }

        finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static List<String> parseCsvLine(String csvLine) {
        List<String> result = new ArrayList<>();
        if (csvLine != null) {
            String[] splitData = csvLine.split(COMMA_DELIMITER);
            for (int i = 0; i < splitData.length; i++) {
                result.add(splitData[i]);
            }
        }
        return result;
    }
}
