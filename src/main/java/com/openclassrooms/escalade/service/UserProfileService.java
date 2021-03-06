package com.openclassrooms.escalade.service;

import com.openclassrooms.escalade.dao.SpotRepository;
import com.openclassrooms.escalade.dao.TopoRepository;
import com.openclassrooms.escalade.dao.TopoUserRepository;
import com.openclassrooms.escalade.dao.UserRepository;
import com.openclassrooms.escalade.dto.*;
import com.openclassrooms.escalade.mapper.SpotMapper;
import com.openclassrooms.escalade.mapper.TopoMapper;
import com.openclassrooms.escalade.mapper.TopoUserMapper;
import com.openclassrooms.escalade.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to manage UserProfile
 *
 * @see UserProfileDto
 */
@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;
    private final SpotRepository spotRepository;
    private final TopoRepository topoRepository;
    private final TopoUserRepository topoUserRepository;
    private final UserMapper userMapper;
    private final SpotMapper spotMapper;
    private final TopoMapper topoMapper;
    private final TopoUserMapper topoUserMapper;

    /**
     * Method to get a UserProfile : get user's informations, spots and topos created by him, topos he booked, topos he owns
     *
     * @param userId id of the user for whom we want to get his profile
     *
     * @return UserProfileDto
     *
     * @see UserRepository#findById(Object)
     * @see SpotRepository#findAllByUserId(Long)
     * @see TopoRepository#findAllByTopoCreatorId(Long)
     * @see TopoUserRepository#findAllByOwnerId(Long)
     * @see TopoUserRepository#findAllByTenantId(Long)
     */
    public UserProfileDto findUserProfile(Long userId) {

        UserDto user = userMapper.toUserDto(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));

        List<SpotLightDto> spotsCreated = spotRepository.findAllByUserId(userId).stream().map(spotMapper::toSpotLightDto)
                .collect(Collectors.toList());

        List<TopoLightDto> toposCreated = topoRepository.findAllByTopoCreatorId(userId).stream().map(topoMapper::toTopoLightDto)
                .collect(Collectors.toList());

        List<TopoUserDto> toposOwned = topoUserRepository.findAllByOwnerId(userId).stream()
                .map(topoUserMapper::toTopoUserDto).collect(Collectors.toList());

        List<TopoUserLightDto> toposRent = topoUserRepository.findAllByTenantId(userId).stream()
                .map(topoUserMapper::toTopoUserLightDto).collect(Collectors.toList());

        return UserProfileDto.builder()
                .user(user)
                .spotsCreated(spotsCreated)
                .toposCreated(toposCreated)
                .toposOwned(toposOwned)
                .toposRent(toposRent)
                .build();
    }
}
