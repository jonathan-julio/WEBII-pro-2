package com.jonathan.springmvcapp.service.Avatar;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jonathan.springmvcapp.model.Avatar;
import com.jonathan.springmvcapp.repository.AvatarRepostory;




@Component
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    AvatarRepostory avatarRepostory;


    @SuppressWarnings("null")
    @Override
    public boolean salvarAvatar(Avatar avatar){
        try {
            Avatar avatar2 = avatarRepostory.findByIdAluno(avatar.getIdAluno());
            if (avatar2 != null) {
                return false;
            }
            avatarRepostory.save(avatar) ;
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
         
    }

    @Override
    public List<Avatar> getAllAvatar(){
        return avatarRepostory.findAll();
    }


    
}
