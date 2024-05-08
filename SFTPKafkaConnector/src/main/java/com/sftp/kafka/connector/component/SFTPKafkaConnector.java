package com.sftp.kafka.connector.component;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.sftp.kafka.connector.CSVFileds;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class SFTPKafkaConnector {
    private static final Logger log = LoggerFactory.getLogger(SFTPKafkaConnector.class);

    public void connectAndReadFileFromSFTP(){
        try(FileSystemManager fsManager = VFS.getManager()){
            log.info("Connecting to SFTP server...");
            FileObject csvDir = fsManager.resolveFile("");

            for(FileObject file: csvDir.getChildren()){
                if(file.getName().getExtension().equalsIgnoreCase("csv")) {
                    try (CSVReader reader = new CSVReader(new InputStreamReader(file.getContent().getInputStream()))) {
                        log.info("Reading CSV file: {}", file.getName().getBaseName());
                        reader.skip(1);
                        List<CSVFileds> csvData = reader.readAll().stream()
                                .map(fileds -> {
                                    /*Person person = new Person();
                                    person.setName(fields[0].trim());
                                    person.setAge(Integer.parseInt(fields[1].trim()));
                                    person.setCity(fields[2].trim());
                                    return person;*/
                                    return new CSVFileds();
                                }).toList();
                    } catch (CsvException e) {
                        log.error("Error reading CSV file: {}", e.getMessage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }catch(FileSystemException e){
            log.error("Error connecting to SFTP server: {}", e.getMessage());
        }
    }

    private void mapCSVDataToList(String line) {
        String[] fields = line.split(",");
        //POJO person = new Person();
        /*person.setName(fields[0].trim());
        person.setAge(Integer.parseInt(fields[1].trim()));
        person.setCity(fields[2].trim());
        people.add(person);*/
    }
}
