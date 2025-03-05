package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.dto.patch.GroupDTOPatch;
import com.example.schedule_composer.dto.post.GroupDTOPost;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GroupService implements CrudService<GroupDTOGet, GroupDTOPost, GroupDTOPatch, Long>{

    @Override
    public GroupDTOGet getById(Long aLong) {
        return null;
    }

    @Override
    public List<GroupDTOGet> getAll() {
        return null;
    }

    @Override
    public GroupDTOGet create(GroupDTOPost createDto) {
        return null;
    }

    @Override
    public GroupDTOGet update(Long aLong, GroupDTOPatch updateDto) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
