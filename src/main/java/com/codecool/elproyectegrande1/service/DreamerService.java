package com.codecool.elproyectegrande1.service;

import com.codecool.elproyectegrande1.dto.dream.DreamDto;
import com.codecool.elproyectegrande1.dto.dreamer.DreamerDto;
import com.codecool.elproyectegrande1.dto.dreamer.NewDreamerDto;
import com.codecool.elproyectegrande1.entity.Dream;
import com.codecool.elproyectegrande1.entity.Dreamer;
import com.codecool.elproyectegrande1.entity.Mentor;
import com.codecool.elproyectegrande1.entity.User;
import com.codecool.elproyectegrande1.mapper.DreamMapper;
import com.codecool.elproyectegrande1.mapper.NewDreamerMapper;
import com.codecool.elproyectegrande1.repository.DreamRepository;
import com.codecool.elproyectegrande1.repository.DreamerRepository;
import com.codecool.elproyectegrande1.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DreamerService {

    private final DreamerRepository dreamerRepository;
    private final DreamRepository dreamRepository;
    private final NewDreamerMapper dreamerMapper;
    private final DreamMapper dreamMapper;
    private final MentorRepository mentorRepository;

    @Autowired
    public DreamerService(DreamerRepository dreamerRepository, DreamRepository dreamRepository, NewDreamerMapper dreamerMapper, DreamMapper dreamMapper,
                          MentorRepository mentorRepository) {
        this.dreamerRepository = dreamerRepository;
        this.dreamRepository = dreamRepository;
        this.dreamerMapper = dreamerMapper;
        this.dreamMapper = dreamMapper;
        this.mentorRepository = mentorRepository;
    }

    public DreamerDto createDreamer(NewDreamerDto newDreamerDto) {
        Dreamer dreamer = dreamerMapper.mapNewDreamerDtoToEntity(newDreamerDto);
        Dreamer savedDreamer = dreamerRepository.save(dreamer);
        return dreamerMapper.mapEntityToDreamerDto(savedDreamer);
    }

    public Dreamer getDreamerWithMostFollowers() {
        return dreamerRepository.findTopByOrderByFollowersDesc();
    }

    public DreamerDto getDreamerByNickname(String nickname) {
        Dreamer dreamer = dreamerRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("Dreamer with id " + nickname + " not found"));

        return dreamerMapper.mapEntityToDreamerDto(dreamer);
    }

    public void followDreamer(String nickname, String name) {
        Dreamer toBeFollowed = dreamerRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("Dreamer with id " + nickname + " not found"));

        Optional<Dreamer> dreamer = dreamerRepository.findByNickname(name);
        Optional<Mentor> mentor = mentorRepository.findByNickname(name);
        Dreamer dreamerGoogle = null;
        if (dreamer.equals(Optional.empty()))
            dreamerGoogle = dreamerRepository.findByEmail(name);


        if (dreamer.isPresent()) {
            if (dreamer.get().getFollowed().contains(nickname)) {
                throw new IllegalArgumentException("You are already following this dreamer");
            }
            else {
                dreamer.get().getFollowed().add(nickname);
                toBeFollowed.setFollowers(toBeFollowed.getFollowers() + 1);
            }
        } else if (dreamerGoogle != null) {
            if (dreamerGoogle.getFollowed().contains(nickname)) {
                throw new IllegalArgumentException("You are already following this dreamer");
            }
            else {
                dreamerGoogle.getFollowed().add(nickname);
                toBeFollowed.setFollowers(toBeFollowed.getFollowers() + 1);
            }
        } else if (mentor.isPresent()) {
            if (mentor.get().getFollowed().contains(nickname)) {
                throw new IllegalArgumentException("You are already following this dreamer");
            } else {
                mentor.get().getFollowed().add(nickname);
                toBeFollowed.setFollowers(toBeFollowed.getFollowers() + 1);
            }
        } else {
            throw new IllegalArgumentException("Log in to follow");
        }
        dreamerRepository.save(toBeFollowed);
    }

    public void unfollowDreamer(String nickname) {
        Dreamer dreamer = dreamerRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("Dreamer with id " + nickname + " not found"));

        if (!dreamer.getFollowed().contains(nickname)) {
            throw new IllegalArgumentException("You are not following this dreamer");
        }

        dreamer.getFollowed().remove(nickname);
        dreamer.setFollowers(dreamer.getFollowers() - 1);
        dreamerRepository.save(dreamer);
    }

    public void donateDreamer(Long dreamerId, BigDecimal amount) {
        Dreamer dreamer = dreamerRepository.findById(dreamerId).orElseThrow();
        if(dreamer.getFunds() != null)
            dreamer.setFunds(amount.add(dreamer.getFunds()));
        else
            dreamer.setFunds(amount);
        dreamerRepository.save(dreamer);
    }

    public void createDreamerFromUser(User user) {
        Dreamer dreamer = dreamerMapper.mapUserToDreamer(user);
        dreamerRepository.save(dreamer);
    }

    public List<DreamDto> getAllDreamsByDreamerNickname(String nickname) {
        List<Dream> dreams = dreamRepository.findByDreamerNickname(nickname);

        List<DreamDto> dreamDtos = new ArrayList<>();

        for (int i = 0; i < 8 && i < dreams.size(); i++) {
            DreamDto dto = dreamMapper.mapEntityToDreamDto(dreams.get(i));
            dreamDtos.add(dto);
        }

        return dreamDtos;
    }

    public Dreamer findDreamerByUserId(Long userId) {
        Dreamer dreamer = dreamerRepository.getByUserId(userId);
        return dreamer;
    }
}
