package readers;

import java.util.ArrayList;

public interface IFileReader<T> {
    /**
     * method for getting objects from file
     *
     * @param file - path to file
     * @return objects in ArrayList
     */
    ArrayList<T> read(String file);
}
