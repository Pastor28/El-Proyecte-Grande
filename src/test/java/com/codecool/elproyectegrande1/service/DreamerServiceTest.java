package com.codecool.elproyectegrande1.service;

import com.codecool.elproyectegrande1.dto.dreamer.DreamerDto;
import com.codecool.elproyectegrande1.dto.dreamer.NewDreamerDto;
import com.codecool.elproyectegrande1.entity.Dreamer;
import com.codecool.elproyectegrande1.mapper.NewDreamerMapper;
import com.codecool.elproyectegrande1.repository.DreamerRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DreamerServiceTest {

    @Mock
    private NewDreamerMapper newDreamerMapper;
    @Mock
    private DreamerRepository dreamerRepository;
    @InjectMocks
    private DreamerService dreamerService;

    @Test
    void shouldCreateDreamer() {
        NewDreamerDto newDreamerDto = Instancio.of(NewDreamerDto.class).create();
        Dreamer dreamer = Instancio.of(Dreamer.class).create();
        DreamerDto dreamerDto = Instancio.of(DreamerDto.class).create();
        when(dreamerRepository.save(dreamer)).thenReturn(dreamer);
        when(newDreamerMapper.mapNewDreamerDtoToEntity(newDreamerDto)).thenReturn(dreamer);
        when(newDreamerMapper.mapEntityToDreamerDto(dreamer)).thenReturn(dreamerDto);
        DreamerDto actual = dreamerService.createDreamer(newDreamerDto);
        Assertions.assertEquals(dreamerDto, actual);
    }

    @Test
    void followDreamer() {
        Dreamer dreamer = Instancio.of(Dreamer.class).create();
        DreamerService dreamerServiceMock = mock(DreamerService.class);
        when(dreamerRepository.findById(dreamer.getId())).thenReturn(Optional.of(dreamer));

        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);
            dreamerServiceMock.followDreamer(arg0.toString(), arg1.toString());
            return null;
        }).when(dreamerServiceMock).followDreamer(dreamer.getNickname(), dreamer.getUser().getUsername());
    }

    @Test
    void getDreamerWithMostFollowers() {
        Dreamer dreamer = Instancio.of(Dreamer.class).create();
        lenient().when(dreamerRepository.save(dreamer)).thenReturn(dreamer);
        dreamer.setFollowers(1);
        Dreamer dreamer2 = Instancio.of(Dreamer.class).create();
        dreamer2.setFollowers(2);
        lenient().when(dreamerRepository.save(dreamer2)).thenReturn(dreamer2);
        Dreamer actual = Instancio.of(Dreamer.class).create();
        actual.setFollowers(3);
        lenient().when(dreamerService.getDreamerWithMostFollowers()).thenReturn(actual);
        Assertions.assertTrue(actual.getFollowers() > dreamer.getFollowers());
        Assertions.assertTrue(actual.getFollowers() > dreamer2.getFollowers());
    }

    @Test
    void getDreamerById() {
    }

    @Test
    void unfollowDreamer() {
    }

    @Test
    void donateDreamer() {
    }

    @Test
    void createDreamerFromUser() {
    }
}