package br.com.movie.service;

import br.com.movie.exception.BadRequestException;
import br.com.movie.model.Room;
import br.com.movie.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @InjectMocks
    private RoomService service;

    @Mock
    private RoomRepository repository;

    @Test
    void list_ReturnsAllRegisteredRooms_WhenToFindAnyOrMore() {
        when(repository.findAll()).thenReturn(List.of(createRoom("Blue"), createRoom("Red")));

        List<Room> rooms = service.list();

        assertThat(rooms.size()).isEqualTo(2);
        verify(repository).findAll();
    }

    @Test
    void list_ReturnsEmptyList_WhenNotFindAnyRoom() {
        when(repository.findAll()).thenReturn(emptyList());

        assertThat(service.list()).isEmpty();
        verify(repository).findAll();
    }

    @Test
    void findById_ReturnsRoom_WhenToFind() {
        Room green = createRoom("Green");
        when(repository.findById(any())).thenReturn(of(green));

        Room room = service.findById(1);
        assertThat(room).isEqualTo(green);
        verify(repository).findById(any());
    }

    @Test
    void findById_ThrowsBadException_WhenNotFindAnyRoom() {
        when(repository.findById(any())).thenReturn(empty());

        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> service.findById(1))
                .withMessageContaining("Room not found");
    }

    @Test
    void findByName_ReturnsRoom_WhenToFind() {
        Room green = createRoom("Green");
        when(repository.findByNameIgnoreCase(any())).thenReturn(of(green));

        Room room = service.findByName("Green");
        assertThat(room).isEqualTo(green);
        verify(repository).findByNameIgnoreCase(any());
    }

    @Test
    void findByName_ThrowsBadException_WhenNotFindAnyRoom() {
        when(repository.findByNameIgnoreCase(any())).thenReturn(empty());

        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> service.findByName("Green"))
                .withMessageContaining("Room not found");
    }

    @Test
    void save_PersistRoomAndReturnIt_WhenThereIsNoOtherRoomWithTheSameName() {
        when(repository.findByNameIgnoreCase(any())).thenReturn(empty());

        Room roomToSave = createRoom("Brown");
        service.save(roomToSave);
        verify(repository).findByNameIgnoreCase(any());
        verify(repository).save(roomToSave);
    }

    @Test
    void save_ThrowsBadException_WhenARoomWithThisNameAlreadyExists() {
        when(repository.findByNameIgnoreCase(any())).thenReturn(of(createRoom("Orange")));

        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> service.save(createRoom("Orange")))
                .withMessageContaining("There is already a room with this name");
        verify(repository).findByNameIgnoreCase(any());
        verify(repository, times(0)).save(any());
    }

    @Test
    void replace_UpdatesARoom_WhenToFindARoomWithThisId() {
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findByNameIgnoreCase(any())).thenReturn(empty());

        service.replace(createRoom("Blue"));
        verify(repository).existsById(any());
        verify(repository).save(any());
    }

    @Test
    void replace_ThrowsBadException_WhenNotFindARoomWithThisId() {
        when(repository.existsById(any())).thenReturn(false);

        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> service.replace(createRoom("Orange")))
                .withMessageContaining("Room not found");
        verify(repository).existsById(any());
        verify(repository, times(0)).findByNameIgnoreCase(any());
        verify(repository, times(0)).save(any());
    }

    @Test
    void replace_ThrowsBadException_WhenARoomWithThisNameAlreadyExists() {
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findByNameIgnoreCase(any())).thenReturn(of(createRoom("Green")));

        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> service.replace(createRoom("Orange")))
                .withMessageContaining("There is already a room with this name");
        verify(repository).existsById(any());
        verify(repository).findByNameIgnoreCase(any());
        verify(repository, times(0)).save(any());
    }

    @Test
    void deleteById_RemovesARoom_WhenToFindARoomWithThisId() {
        when(repository.existsById(any())).thenReturn(true);

        service.deleteById(1);
        verify(repository).existsById(any());
        verify(repository).deleteById(any());
    }

    @Test
    void deleteById_ThrowsBadException_WhenNotFindARoomWithThisId() {
        when(repository.existsById(any())).thenReturn(false);

        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> service.deleteById(1))
                .withMessageContaining("Room not found");
        verify(repository).existsById(any());
        verify(repository, times(0)).deleteById(any());
    }

    private Room createRoom(String name) {
        return Room.builder()
                .id(1)
                .name(name)
                .build();
    }

}