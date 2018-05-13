package writers;

import java.util.ArrayList;

public interface IFileWriter<T> {
    /**
     * method for writting objects to file
     *
     * @param objects - ArrayList objects
     * @param file    - path to file
     */
    void write(ArrayList<T> objects, String file);
}
