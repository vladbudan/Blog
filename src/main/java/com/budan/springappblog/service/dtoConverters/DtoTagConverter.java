package com.budan.springappblog.service.dtoConverters;

import com.budan.springappblog.dto.tag.DtoAddTag;
import com.budan.springappblog.dto.tag.DtoEditTag;
import com.budan.springappblog.dto.tag.DtoShowTag;
import com.budan.springappblog.model.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoTagConverter {

    public Tag fromAddToModel(final DtoAddTag dtoAddTag) {
        Tag tag = new Tag();

        tag.setName(dtoAddTag.getName());

        return tag;
    }

    public Tag mergeEditAndModel(final DtoEditTag dtoEditTag, final Tag tag) {
        tag.setName(dtoEditTag.getName());

        return tag;
    }

    public DtoShowTag fromModelToShow(final Tag tag) {
        DtoShowTag dtoShowTag = new DtoShowTag();

        dtoShowTag.setId(tag.getId());
        dtoShowTag.setName(tag.getName());

        return dtoShowTag;
    }

    public List<DtoShowTag> fromListModelToListShow(final List<Tag> tagList) {
        List<DtoShowTag> dtoShowTagsList = new ArrayList<>(tagList.size());

        for(Tag tag: tagList) {
            dtoShowTagsList .add(this.fromModelToShow(tag));
        }

        return dtoShowTagsList;
    }
}
