package readers;

import java.io.*;
import java.util.ArrayList;

/**
 * reading objects from a file
 *
 * @author Denis
 * 25.03.2018
 */
public class FileReader<T> implements IFileReader {

    @Override
    public ArrayList<T> read(String file) {

        ArrayList<T> objects = new ArrayList<>();
        try {
            T tmpObject;
            try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
                while ((tmpObject = (T) in.readObject()) != null) {
                    objects.add(tmpObject);
                }
            } catch (EOFException e) {
//                System.out.println(e.getMessage()); // null
            }

        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
        }

        return objects;
    }
}
