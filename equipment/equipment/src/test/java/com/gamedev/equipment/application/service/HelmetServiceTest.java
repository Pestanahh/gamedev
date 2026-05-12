package com.gamedev.equipment.application.service;

import com.gamedev.equipment.domain.exception.DuplicateNameException;
import com.gamedev.equipment.domain.exception.EmptyListException;
import com.gamedev.equipment.domain.exception.HelmetNotFoundException;
import com.gamedev.equipment.domain.model.Helmet;
import com.gamedev.equipment.domain.port.output.HelmetRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HelmetServiceTest {

    @Mock
    private HelmetRepositoryPort helmetRepositoryPort;
    @InjectMocks
    private HelmetService helmetService;

    @Test
    void getHelmetByIdOK(){
        // ARRANGE
        Long id = 9L;
        Helmet expectedHelmet = new Helmet(9L, "Golden Helm", 15, 25, 10);
        when(helmetRepositoryPort.findById(id)).thenReturn(Optional.of(expectedHelmet));

        // ACT
        Helmet actualHelmet = helmetService.getHelmetById(id);

        // ASSERT
        assertNotNull(actualHelmet);
        assertEquals(expectedHelmet, actualHelmet);
        verify(helmetRepositoryPort).findById(id);
    }

    @Test
    void getHelmetByIdKO(){
        // ARRANGE
        Long id = 9L;
        when(helmetRepositoryPort.findById(id)).thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThrows(HelmetNotFoundException.class,
                () -> helmetService.getHelmetById(id));
    }

    @Test
    void getAllHelmetsOK(){
        // ARRANGE
        List<Helmet> expectedHelmets = List.of(new Helmet(9L, "Golden Helm", 15, 25, 10));
        when(helmetRepositoryPort.findAll()).thenReturn(expectedHelmets);

        // ACT
        List<Helmet> actualHelmet = helmetService.getAllHelmets();

        // ASSERT
        assertNotNull(actualHelmet);
        assertEquals(expectedHelmets, actualHelmet);
        verify(helmetRepositoryPort).findAll();
    }

    @Test
    void getAllHelmetKO(){
        // ARRANGE
        when(helmetRepositoryPort.findAll()).thenReturn(List.of());

        // ACT & ASSERT
        assertThrows(EmptyListException.class, () -> helmetService.getAllHelmets());
        verify(helmetRepositoryPort).findAll();
    }

    @Test
    void createHelmetOK(){
        // ARRANGE
        Helmet newHelmet = new Helmet(9L, "Golden Helm", 15, 25, 10);
        when(helmetRepositoryPort.existsByName("Golden Helm")).thenReturn(false);
        when(helmetRepositoryPort.save(newHelmet)).thenReturn(newHelmet);

        // ACT
        Helmet actualValue = helmetService.createHelmet(newHelmet);

        // ASSERT
        assertNotNull(actualValue);
        assertEquals(newHelmet, actualValue);
        verify(helmetRepositoryPort).existsByName("Golden Helm");
        verify(helmetRepositoryPort).save(newHelmet);
    }

    @Test
    void createHelmetKO(){
        // ARRANGE
        Helmet newHelmet = new Helmet(9L, "Golden Helm", 15, 25, 10);
        when(helmetRepositoryPort.existsByName("Golden Helm")).thenReturn(true);

        // ACT + ASSERT
        assertThrows(DuplicateNameException.class,
                () -> helmetService.createHelmet(newHelmet));
        verify(helmetRepositoryPort).existsByName("Golden Helm");
    }

    @Test
    void updateHelmetOK(){
        // ARRANGE
        Long id= 9L;
        Helmet existingHelmet = new Helmet(9L, "Golden Helm", 15, 25, 10);
        Helmet updatedData = new Helmet(null, "Golden Helm", 15, 25, 10);
        Helmet expectedResult = new Helmet(9L, "Golden Helm", 15, 25, 10);

        when(helmetRepositoryPort.findById(id)).thenReturn(Optional.of(existingHelmet));
        when(helmetRepositoryPort.save(existingHelmet)).thenReturn(expectedResult);

        // ACT
        Helmet actualValue = helmetService.updateHelmet(updatedData, id);

        // ASSERT
        assertNotNull(actualValue);
        assertEquals(expectedResult, actualValue);
        verify(helmetRepositoryPort).findById(id);
        verify(helmetRepositoryPort).save(existingHelmet);
    }

    @Test
    void updatedHelmetKO(){
        // ARRANGE
        Long id = 9L;
        Helmet updatedData = new Helmet(null, "Golden Helm", 15, 25, 10);
        when(helmetRepositoryPort.findById(id)).thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThrows(HelmetNotFoundException.class,
                () -> helmetService.updateHelmet(updatedData, id));
        verify(helmetRepositoryPort).findById(id);
    }


}
