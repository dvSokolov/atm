package writers;

import readers.IFileReader;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * writing objects to a file
 * Denis
 * 25.03.2018
 */
public class FileWriter<T> implements IFileWriter<T> {

    @Override
    public void write(ArrayList<T> objects, String file) {
        try {
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            for (T object : objects) {
                objectOutputStream.writeObject(object);
            }
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
