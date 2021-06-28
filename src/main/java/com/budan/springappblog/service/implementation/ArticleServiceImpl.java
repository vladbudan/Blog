package com.budan.springappblog.service.implementation;

import com.budan.springappblog.constants.Abbreviation;
import com.budan.springappblog.dto.article.DtoAddArticle;
import com.budan.springappblog.dto.article.DtoEditArticle;
import com.budan.springappblog.dto.article.DtoShowArticle;
import com.budan.springappblog.model.Article;
import com.budan.springappblog.model.User;
import com.budan.springappblog.repository.ArticleRepository;
import com.budan.springappblog.repository.UserRepository;
import com.budan.springappblog.service.ArticleService;
import com.budan.springappblog.service.dtoConverters.DtoArticleConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final DtoArticleConverter dtoArticleConverter;

    @Override
    public void add(DtoAddArticle dtoAddArticle) {
        articleRepository.save(dtoArticleConverter.fromAddToModel(dtoAddArticle));
    }

    @Override
    public void update(DtoEditArticle dtoEditArticle) {
        Optional<Article> article = articleRepository.findById(dtoEditArticle.getId());

        this.exceptionOnNull(article);

        articleRepository.save(dtoArticleConverter.mergeEditAndModel(article.get(), dtoEditArticle));

    }

    @Override
    public void delete(Integer id) {
        Optional<Article> article = articleRepository.findById(id);

        this.exceptionOnNull(article);

        articleRepository.deleteById(id);
    }

    @Override
    public List<DtoShowArticle> getAllByUserId(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException(Abbreviation.ERROR_THAT_USER_NOT_EXIST);
        }
        return dtoArticleConverter.fromListToListShow(articleRepository.findAllByUser(user.get()));
    }

    @Override
    public DtoShowArticle getById(Integer id) {
        Optional<Article> article = articleRepository.findById(id);
        if(article.isEmpty()) {
            return null;
        }
        return dtoArticleConverter.fromModelToShow(article.get());
    }

    @Override
    public List<DtoShowArticle> getAllByCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return dtoArticleConverter.fromListToListShow(articleRepository.findAllByUser(user));
    }

    private void exceptionOnNull(Optional<Article> article) {
        if (article.isEmpty()) {
            throw new ResourceNotFoundException(Abbreviation.ERROR_THAT_OBJECT_NOT_EXIST);
        }
    }
}
