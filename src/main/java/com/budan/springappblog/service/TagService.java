package com.budan.springappblog.service;

import com.budan.springappblog.dto.tag.DtoAddTag;
import com.budan.springappblog.dto.tag.DtoEditTag;
import com.budan.springappblog.dto.tag.DtoShowTag;

import java.util.List;

public interface TagService {
    List<DtoShowTag> getAll();

    void add(DtoAddTag dtoAddTag);

    void update(DtoEditTag dtoEditTag);

    List<DtoShowTag> searchByName(String name);
}
