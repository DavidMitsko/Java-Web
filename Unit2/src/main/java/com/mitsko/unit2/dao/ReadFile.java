package main.java.com.mitsko.unit2.dao;

import main.java.com.mitsko.unit2.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    private BufferedReader bufferedReader;

    private Logger logger = LogManager.getLogger(ReadFile.class.getName());

    public ReadFile(String filePath) throws DAOException {
        try {
                bufferedReader = new BufferedReader(new FileReader(filePath));
            }
            catch (FileNotFoundException ex){
                throw new DAOException(ex);
            }
            finally {

        }
    }

    public String readString() throws DAOException{
        logger.info("loading string from file");
        String ret = null;
        try {
            ret = bufferedReader.readLine();
        }
        catch (IOException ex){
            throw new DAOException(ex);
        }
        return ret;
    }
}
