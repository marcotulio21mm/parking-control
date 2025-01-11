package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dtos.ResidentDto;
import com.api.parkingcontrol.models.ResidentsModel;
import com.api.parkingcontrol.repositories.ResidentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResidentsServiceTest {

    @InjectMocks
    private ResidentsService residentsService;

    @Mock
    private ResidentsRepository residentsRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldReturnResidents() {
        var residents = Collections.singletonList(mock(ResidentsModel.class));
        doReturn(residents).when(residentsRepository).findAll();
        var result = residentsService.findAll();

        assertEquals(residents, result);
    }

    @Test
    void shouldReturnResidentModel_whenEntityExistById() {
        var residentModel = mock(ResidentsModel.class);
        doReturn(Optional.of(residentModel)).when(residentsRepository).findById(Mockito.any());

        var result = residentsService.findById(1);
        assertEquals(residentModel, result);
    }

    @Test
    void shouldReturnResidentModelWithInsertDate_whenSaveSuccess(){
        var residentModel = mock(ResidentsModel.class);
        residentModel.setInsertDateResident(LocalDateTime.now(ZoneId.of("UTC")));
        doReturn(residentModel).when(residentsRepository).save(Mockito.any());

        var dto = Mockito.mock(ResidentDto.class);
        var result = residentsService.save(dto);

        assertEquals(residentModel, result);
        assertEquals(residentModel.getInsertDateResident(), result.getInsertDateResident());
    }

    @Test
    void shouldUpdateResident_whenEntityExistById() {
        var residentModel = mock(ResidentsModel.class);
        doReturn(Optional.of(residentModel)).when(residentsRepository).findById(Mockito.any());

        doReturn(residentModel).when(residentsRepository).save(Mockito.any());

        var dto = Mockito.mock(ResidentDto.class);

        var result = residentsService.update(dto, 1);
        assertEquals(residentModel, result);
    }

    @Test
    void shouldDoNothing_whenDeleteSuccess() {
        var residentModel = mock(ResidentsModel.class);
        doReturn(Optional.of(residentModel)).when(residentsRepository).findById(Mockito.any());
        doNothing().when(residentsRepository).deleteById(Mockito.any());
        residentsService.delete(1);
        verify(residentsRepository, times(1)).deleteById(Mockito.any());
    }
}