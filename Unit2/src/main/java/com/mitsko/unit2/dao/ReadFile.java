package main.java.com.mitsko.unit2.dao;

import main.java.com.mitsko.unit2.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile implements AutoCloseable{
    private Logger logger = LogManager.getLogger(ReadFile.class.getName());

    public ReadFile() {}

    public String readString(String filePath) throws DAOException{
        logger.info("loading string from file");
        String ret = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            ret = bufferedReader.readLine();
        }
        catch (IOException ex){
            throw new DAOException(ex);
        }
        return ret;
    }

    public ArrayList<String> readAllFile(String filePath) throws DAOException {
        logger.info("read all file " + filePath);
        ArrayList<String> retList = new ArrayList<String>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            do {
                retList.add(bufferedReader.readLine());
            }while(retList.get(retList.size() - 1) != null);
        }catch (FileNotFoundException ex){
            throw new DAOException(ex);
        }catch(IOException ex){
            throw new DAOException(ex);
        }
        retList.remove(retList.size() - 1);
        return retList;
    }

    @Override
    public void close() throws Exception {

    }
}
