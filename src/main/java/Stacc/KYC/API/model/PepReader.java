package Stacc.KYC.API.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PepReader {

    /**
     * @param csvFile a csv file on the same format as the pep.csv file.
     * @return a list of names and aliases (text strings)
     */
    public static List<String> CSVToNameList(String csvFile) {
        ArrayList<String> names = new ArrayList<>();

        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                List<String> values = Arrays.asList(line.replaceAll("[\"]","").split(","));

                String name = values.get(2);
                names.add(name);

                List<String> aliases = Arrays.asList(values.get(3).split(";"));
                for (String alias : aliases) {
                    if (!alias.isBlank()) {
                        names.add(alias);
                    }
                }

            }
        } catch (Exception e){
            System.out.println(e);
        }
        return names;
    }

    /**
     * @param csvFile a csv file on the same format as the pep.csv file.
     * @return a list of Person-objects.
     */
    public static List<Person> CSVToPOJOList(String csvFile) {
        ArrayList<Person> persons = new ArrayList<>();

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

                persons.add(person);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return persons;
    }

}
