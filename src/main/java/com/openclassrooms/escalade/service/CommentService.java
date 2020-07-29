package com.openclassrooms.escalade.service;

import com.openclassrooms.escalade.dao.SpotRepository;
import com.openclassrooms.escalade.dao.UserRepository;
import com.openclassrooms.escalade.dto.CommentDto;
import com.openclassrooms.escalade.dto.CommentSaveDto;
import com.openclassrooms.escalade.entity.Comment;
import com.openclassrooms.escalade.entity.Spot;
import com.openclassrooms.escalade.entity.User;
import com.openclassrooms.escalade.mapper.CommentMapper;
import com.openclassrooms.escalade.dao.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final SpotRepository spotRepository;
    
    public Page<CommentDto> findAllBySpotId(Long spotId, Pageable page) {
        return commentRepository.findAllBySpotId(spotId, page).map(commentMapper::toCommentDto);
    }
    
    public CommentDto findById(Long id) {
        return commentMapper.toCommentDto(commentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
    
    public CommentDto create(CommentSaveDto commentSaveDto) {
        User user = userRepository.findById(commentSaveDto.getUserId()).orElseThrow(EntityNotFoundException::new);
        Spot spot = spotRepository.findById(commentSaveDto.getSpotId()).orElseThrow(EntityNotFoundException::new);
        LocalDateTime date = LocalDateTime.now();
        Comment comment = Comment.builder()
                .user(user)
                .spot(spot)
                .content(commentSaveDto.getContent())
                .date(date)
                .build();
        return commentMapper.toCommentDto(commentRepository.save(comment));
    }
    
    public CommentDto update(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(EntityNotFoundException::new);
        comment.setContent(commentDto.getContent());
        return commentMapper.toCommentDto(commentRepository.save(comment));
    }
    
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        commentRepository.delete(comment);
    }
}