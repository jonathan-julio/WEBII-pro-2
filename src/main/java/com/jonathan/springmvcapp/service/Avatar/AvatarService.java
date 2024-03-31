package com.jonathan.springmvcapp.service.Avatar;

import com.jonathan.springmvcapp.model.Avatar;

import java.util.List;


public interface AvatarService {

    public boolean salvarAvatar(Avatar avatar);

    public List<Avatar> getAllAvatar();
    
} 
