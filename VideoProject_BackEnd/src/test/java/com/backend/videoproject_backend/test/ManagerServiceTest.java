package com.backend.videoproject_backend.test;

import com.backend.videoproject_backend.dao.FollowDao;
import com.backend.videoproject_backend.dto.TbAssociationEntity;
import com.backend.videoproject_backend.dto.TbUserEntity;
import com.backend.videoproject_backend.service.AssociationService;
import com.backend.videoproject_backend.service.UserService;
import com.backend.videoproject_backend.service.impl.ManagerServiceImpl;
import com.tencentcloudapi.tiems.v20190416.models.PredictInput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ManagerServiceTest {

    @Autowired
    ManagerServiceImpl managerService;

    @Autowired
    AssociationService associationService;

    @Autowired
    UserService userService;

    @Test
    public void testjoinClub() {
        int[] as = new int[]{25, 26,1234, -1,100000000,25,1234};
        int[] userid = new int[]{14,4,123,14,10,-1,100000000};

        String[] res=new  String[]{"User has joined","joinClub Success","No such association No such User ","No such association ","No such association ",
                                    "No such User ","No such association No such User "};

        String output;
        int num=1;

        for (int i=0;i<userid.length;i++){
            Optional<TbAssociationEntity> AsE= associationService.findAssociationById(as[i]);
            Optional<TbUserEntity> UsE=userService.findUserById(userid[i]);

            output=managerService.joinClub(AsE,UsE);

            if (!Objects.equals(output, res[i])) {
                System.out.println("The "+num+" Test Failed");
                System.out.println("Predict:" + res[i]+" But: "+output);
            }

            num++;
        }

    }

    @Test
    public void testDeleteManager(){
        int[] as = new int[]{25,25,25,25,-1,-1,-1,-1,25,25,25,25,-1,-1,-1,-1};
        int[] userid = new int[]{16,21,14,-1,15,4,14,-1,15,4,14,10,4,4,14,-1};
        int[] managerid = new int[]{15,4,14,-1,4,15,-1,14,14,10,16,4,-1,14,4,15};

        String[] res=new  String[]{"No permissions！","DeleteManager Success","Proprietor quit","No such User No such manager ",
                                    "No such association ","No such association ","No such association No such manager ",
                                    "No such association No such User ","DeleteManager Success"," No such manager in association ",
                                    "No permissions！"," No such User in association ","No such association No such manager ",
                                    "No such association ","No such association ","No such association No such User "};

        String output;
        int num=1;
        for (int i=0;i<userid.length;i++){
            Optional<TbAssociationEntity> AsE= associationService.findAssociationById(as[i]);
            Optional<TbUserEntity> UsE=userService.findUserById(userid[i]);
            Optional<TbUserEntity> MaE=userService.findUserById(managerid[i]);

            output=managerService.DeleteManager(AsE,UsE,MaE);

            if (!Objects.equals(output, res[i])) {
                System.out.println("The "+num+" Test Failed");
                System.out.println("Predict:" + res[i]+" But: "+output);
            }

            num++;
        }
    }

}
