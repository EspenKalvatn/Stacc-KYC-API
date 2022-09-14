package Stacc.KYC.API.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class PepReader {

    /**
     * @param csvFile a csv file on the same format as the pep.csv file.
     * @return a HashMap with names/aliases as keys and a Person-object with the information stored as a value.
     */
    public static HashMap<String, Person> readCSV(String csvFile) {
        HashMap<String, Person> namePersonMap = new HashMap<>();

        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                List<String> values = Arrays.asList(line.replaceAll("[\"]","").split(","));

                Person person = new Person();
                person.setId(values.get(0));
                person.setSchema(values.get(1));
                person.setName(values.get(2));
                person.setAliases(values.get(3));
                person.setBirth_date(values.get(4));
                person.setCountries(values.get(5));
                person.setAddresses(values.get(6));
                person.setIdentifiers(values.get(7));
                person.setSanctions(values.get(8));
                person.setPhones(values.get(9));
                person.setEmails(values.get(10));
                person.setDataset(values.get(11));
                person.setLast_seen(values.get(12));
                person.setFirst_seen(values.get(13));

                namePersonMap.put(person.getName(), person);
                for (String alias : person.getAliases()) {
                    namePersonMap.put(alias, person);
                }
            }
        } catch (Exception e){
            System.err.println(e);
        }
        return namePersonMap;
    }

}
