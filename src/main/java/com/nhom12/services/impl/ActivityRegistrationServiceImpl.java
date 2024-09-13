/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhom12.pojo.Activity;
import com.nhom12.pojo.ActivityRegistration;
import com.nhom12.pojo.User;
import com.nhom12.repositories.ActivityRegistrationRepository;
import com.nhom12.repositories.ActivityRepository;
import com.nhom12.repositories.UserRepository;
import com.nhom12.services.ActivityRegistrationService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Service
public class ActivityRegistrationServiceImpl implements ActivityRegistrationService {

    @Autowired
    private ActivityRegistrationRepository joinedActivityRepo;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ActivityRepository activityRepo;

    @Override
    public List<Object[]> getJoinedList() {
        return this.joinedActivityRepo.getJoinedList();
    }

    @Override
    public void addRegistration(ActivityRegistration a) {
        if (!a.getFile().isEmpty() && a.getProof() == null) {
            try {
                Map res = this.cloudinary.uploader().upload(a.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                a.setProof(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ActivityRegistrationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.joinedActivityRepo.addRegistration(a);
    }

    @Override
    public List<ActivityRegistration> getJoinedActivityList(Map<String, String> params) {
        return this.joinedActivityRepo.getJoinedActivityList(params);
    }

    @Override
    public void loadCsv(MultipartFile file, int activityId) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            
            for (CSVRecord csvRecord : csvParser) {
//                Map<String, String> csvMap = csvRecord.toMap();
                String email = csvRecord.get("email");
                String username = csvRecord.get("username");
                String proofJoining = csvRecord.get("proof");
                
              
                User student = userRepo.getUserByEmail(email, username);

                if (student != null) {
                    Activity activity = this.activityRepo.getActivityById(activityId);
                    ActivityRegistration activityRegis = new ActivityRegistration();
                    activityRegis.setActivityId(activity);
                    activityRegis.setStudentId(student);
                    activityRegis.setProof(proofJoining);
                    activityRegis.setRegisteredDate(new Date());
                    this.joinedActivityRepo.save(activityRegis);
                } else {
                    Logger.getLogger(ActivityRegistrationServiceImpl.class.getName()).log(Level.SEVERE, "Student Info didn't exist or missed");
                }
            }

        } catch (IOException e) {
            Logger.getLogger(ActivityRegistrationServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

    @Override
    public void save(ActivityRegistration a
    ) {
        this.joinedActivityRepo.save(a);
    }
}
