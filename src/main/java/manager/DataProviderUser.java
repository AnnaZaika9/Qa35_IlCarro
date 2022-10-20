package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider
    public Iterator<Object[]> datalogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"nik@gmail.com","123589$Nik"});
        list.add(new Object[]{"nik@gmail.com","123589$Nik"});
        list.add(new Object[]{"nik@gmail.com","123589$Nik"});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> dataModelUser(){
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{new User().withEmail("nik@gmail.com").withPassword("123589$Nik")});
        list.add(new Object[]{new User().withEmail("nik@gmail.com").withPassword("123589$Nik")});
        list.add(new Object[]{new User().withEmail("nik@gmail.com").withPassword("123589$Nik")});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> regDataValid() throws IOException {
        List<Object[]> list = new ArrayList<>();
     //   list.add(new Object[]{new User().withEmail(""));
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/registrationSuccess.csv"));
        String line = reader.readLine(); //"Zoa;Dow;zoa@gmail.com;Zz12345$"
        while (line!=null){
           String[] split = line.split(";");// ["Zoa"]["Dow"]["zoa@gmail.com"]["Zz12345$"]
            list.add(new Object[]{new User().withName(split[0]).withLastname(split[1]).withEmail(split[2]).withPassword(split[3])});
            line =reader.readLine(); //"Noa;Snow;noanw@ukr.net;Nn12345$"
        }


        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> xxx(){
        List<Object[]> list = new ArrayList<>();


        return list.iterator();
    }
}
