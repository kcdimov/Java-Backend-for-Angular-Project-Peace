package com.example.angular.services.impl;

import com.example.angular.model.dto.PostDto;
import com.example.angular.model.entity.PostEntity;
import com.example.angular.repository.PostRepository;
import com.example.angular.services.PostService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public PostServiceImpl(PostRepository postRepository,
                           ModelMapper modelMapper, Gson gson) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void initPosts() {
        if (this.postRepository.count() != 0) {
            return;
        }

        this.postRepository.save(new PostEntity("Offer - Apartment - Sofia",
                "Two bedroom apartment, close to metro station. Suitable for pets. Igice aprtament for free",
                "https://cf.bstatic.com/xdata/images/hotel/max1024x768/286094367.jpg?k=f45213d06327fd22fb50121d0d35fb6c33ab910a790db23fe818d4059728f434&o=&hp=1"));
        this.postRepository.save(new PostEntity("Offer - Apartment - Plovdiv",
                "I offer One bedroom apartment locate in Plovdiv. I can ccoperate for job places. ",
                "https://static.hostify.com/images/00142/listings/00103000/103356thumb.jpg"));

         this.postRepository.save(new PostEntity("Looking for Apartment in Sofia",
                 "We are two women with two kids. We are from Harkov and we don't have where to stay. We are asking politely if anyone can help us",
                 "https://pix10.agoda.net/hotelImages/66178/0/a6814d8a47dd5b7870c568bd159033d1.jpg?ca=0&ce=1&s=1024x768"));

         this.postRepository.save(new PostEntity("Offer - House",
                 "I offer a house locate in Elhovo in region Yambol. " +
                         "There is a big garden. Its a peaceful place where you can rest. There is a room for farming ",
                 "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBhtL948hr6Uy7qMuWi1sUpEzbLlnG0CdhFA&usqp=CAU"));
         this.postRepository.save(new PostEntity("Offer - House",
                 "I have a house in Varna. I would like to share one of the floor for free to our friends from Ukraine.\n" +
                         "I will cover all bills. The floor there is separate entry, there are three bedrooms and is suitable for pets",
                 "https://d2x18j2t2kugpd.cloudfront.net/324x203/filters:stretch()/filters:format(jpg)/property_images_june/836fb3cb-4e30-447a-b0fa-999a0a8448ce/15723-10264/image-0.jpg"));

    }

    @Override
    public void addPost(String post) {
        PostDto postDto = this.gson.fromJson(post, PostDto.class);
        PostEntity postEntity = this.modelMapper.map(postDto, PostEntity.class);

        this.postRepository.save(postEntity);

    }

    @Override
    public List<PostEntity> getAllPosts() {
        return this.postRepository.findAll();
    }

    @Override
    public PostEntity getById(Long id) {
        return this.postRepository.findById(id).get();
    }
}
