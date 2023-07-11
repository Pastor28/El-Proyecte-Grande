package com.codecool.elproyectegrande1.service;

import com.codecool.elproyectegrande1.dto.dream.DreamDto;
import com.codecool.elproyectegrande1.dto.dreamer.DreamerDto;
import com.codecool.elproyectegrande1.dto.mentor.MentorDto;
import com.codecool.elproyectegrande1.dto.offer.NewOfferDto;
import com.codecool.elproyectegrande1.dto.offer.OfferDto;
import com.codecool.elproyectegrande1.entity.*;
import com.codecool.elproyectegrande1.mapper.MentorMapper;
import com.codecool.elproyectegrande1.mapper.OfferMapper;
import com.codecool.elproyectegrande1.repository.ImageRepository;
import com.codecool.elproyectegrande1.repository.MentorRepository;
import com.codecool.elproyectegrande1.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentorService {

    private final MentorRepository mentorRepository;
    private final OfferRepository offerRepository;
    private final MentorMapper mentorMapper;
    private final OfferMapper offerMapper;
    private final ImageRepository imageRepository;

    @Autowired
    public MentorService(MentorRepository mentorRepository, OfferRepository offerRepository, MentorMapper mentorMapper, OfferMapper offerMapper, ImageRepository imageRepository) {
        this.mentorRepository = mentorRepository;
        this.offerRepository = offerRepository;
        this.mentorMapper = mentorMapper;
        this.offerMapper = offerMapper;
        this.imageRepository = imageRepository;
    }

    public OfferDto addOffer(String name, NewOfferDto offerDto) {
        Mentor mentor = mentorRepository.findByNickname(name)
                .orElseThrow(() -> new IllegalArgumentException("Mentor with nickname " + name + " not found"));

        Offer offer = offerMapper.mapOfferDtoToEntity(offerDto);
        offer.setMentor(mentor);

        Offer savedOffer = offerRepository.save(offer);

        List<Offer> updatedOffers = mentor.getOffers();
        updatedOffers.add(savedOffer);
        mentorRepository.save(mentor);

        return offerMapper.mapEntityToOfferDto(savedOffer);
    }

    public void createMentorFromUser(User user) {
        Mentor mentor = mentorMapper.mapUserToMentor(user);
        mentorRepository.save(mentor);
    }

    public List<MentorDto> getAllMentors() {
        List<Mentor> mentors = mentorRepository.findAll();
        return mentors.stream()
                .map(mentorMapper::mapEntityToMentorDto)
                .collect(Collectors.toList());
    }

    public MentorDto getMentor(String user_id) {
        Mentor mentor = mentorRepository.findByNickname(user_id)
                .orElseThrow(() -> new IllegalArgumentException("Mentor with nickname " + user_id + " not found"));
        System.out.println(mentor.isVerified());
        return null;
    }

    public List<OfferDto> getAllOffersByMentorNickname(String nickname) {
            List<Offer> offers = offerRepository.findByMentorNickname(nickname);

            List<OfferDto> offerDtos = new ArrayList<>();

            for (int i = 0; i < 8 && i < offers.size(); i++) {
                OfferDto dto = offerMapper.mapEntityToOfferDto(offers.get(i));
                offerDtos.add(dto);
            }

            return offerDtos;
        }

    public MentorDto getMentorByNickname(String nickname) {
            Mentor mentor = mentorRepository.findByNickname(nickname)
                    .orElseThrow(() -> new IllegalArgumentException("Dreamer with id " + nickname + " not found"));
            return mentorMapper.mapEntityToMentorDto(mentor);
    }

    public void followMentor(String nickname) {
        Mentor mentor = mentorRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("Dreamer with id " + nickname + " not found"));

        if (mentor.getFollowed().contains(nickname)) {
            throw new IllegalArgumentException("You are already following this dreamer");
        }

        mentor.getFollowed().add(nickname);
        mentor.setFollowers(mentor.getFollowers() + 1);
        mentorRepository.save(mentor);
    }

    public void unfollowMentor(String nickname) {
        Mentor mentor = mentorRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("Dreamer with id " + nickname + " not found"));

        if (!mentor.getFollowed().contains(nickname)) {
            throw new IllegalArgumentException("You are not following this dreamer");
        }

        mentor.getFollowed().remove(nickname);
        mentor.setFollowers(mentor.getFollowers() - 1);
        mentorRepository.save(mentor);
    }

    public List<MentorDto> getAllUnverifiedMentors() {
        List<Mentor> mentors = mentorRepository.findAll();
        return mentors.stream()
                .filter(mentor -> !mentor.isVerified())
                .map(mentorMapper::mapEntityToMentorDto)
                .collect(Collectors.toList());
    }
}

