package com.projectnull.dao;

import com.projectnull.dto.HospitalDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class HospitalDAO {

    private Properties prop = new Properties();

    public HospitalDAO() {

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/projectnull/mapper/menu-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertHospital(HospitalDTO hospitalDTO, Connection con){



    }
}
