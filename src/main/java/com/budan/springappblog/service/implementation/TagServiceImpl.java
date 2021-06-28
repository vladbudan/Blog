package com.budan.springappblog.service.implementation;

import com.budan.springappblog.dto.tag.DtoAddTag;
import com.budan.springappblog.dto.tag.DtoEditTag;
import com.budan.springappblog.dto.tag.DtoShowTag;
import com.budan.springappblog.repository.TagRepository;
import com.budan.springappblog.service.TagService;
import com.budan.springappblog.service.dtoConverters.DtoTagConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final DtoTagConverter dtoTagConverter;
    private final TagRepository tagRepository;

    @Override
    public List<DtoShowTag> getAll() {
        return dtoTagConverter.fromListModelToListShow(tagRepository.findAll());
    }

    @Override
    public void add(DtoAddTag dtoAddTag) {
        tagRepository.save(dtoTagConverter.fromAddToModel(dtoAddTag));
    }

    @Override
    public void update(DtoEditTag dtoEditTag) {
        tagRepository.save(dtoTagConverter.mergeEditAndModel(dtoEditTag, tagRepository.findById(dtoEditTag.getId()).get()));
    }

    @Override
    public List<DtoShowTag> searchByName(String name) {
        return dtoTagConverter.fromListModelToListShow(tagRepository.findAllByName(name));
    }
}
