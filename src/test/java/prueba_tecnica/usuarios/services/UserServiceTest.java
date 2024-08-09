package prueba_tecnica.usuarios.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import prueba_tecnica.usuarios.Constants.Constants;
import prueba_tecnica.usuarios.MockFactory.MockFactory;
import prueba_tecnica.usuarios.dataprovider.jpa.repositories.StatusRepository;
import prueba_tecnica.usuarios.dataprovider.jpa.repositories.UsersRepository;
import prueba_tecnica.usuarios.domain.ResponseResult;
import prueba_tecnica.usuarios.domain.UserRequest;
import prueba_tecnica.usuarios.domain.UserResponse;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

    StatusRepository statusRepository = Mockito.mock(StatusRepository.class);
    UsersRepository usersRepository = Mockito.mock(UsersRepository.class);

    @Autowired
    UserService userService = new UserService(usersRepository, statusRepository);

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void saveUser() throws Exception {

        Mockito.when(statusRepository.findByDescription(Mockito.any()))
                .thenReturn(MockFactory.buildStatusEntity());
        Mockito.when(usersRepository.save(Mockito.any()))
                .thenReturn(MockFactory.buildUserEntity());

        UserRequest userRequest = MockFactory.buildUserRequest();
        ResponseResult<UserResponse> response = userService.saveUser(userRequest);
        Assertions.assertEquals(response.isError(), false);
        Assertions.assertEquals(response.getMessage(), Constants.MSG_CREATED_USER_SUCCESSFUL);
        Assertions.assertNotEquals(null, response.getData().getId());
    }

    @Test
    public void saveUserFailedTest() {

        UserRequest userRequest = MockFactory.buildUserRequest();

        try {
            userService.saveUser(userRequest);
        } catch (Exception exception) {
            Assertions.assertEquals(exception.getMessage(),
                    String.format(Constants.MSG_ERROR_QUERY_STATUS, "ACTIVO"));
        }
    }

    @Test
    public void updateUser() throws Exception {

        Mockito.when(usersRepository.save(Mockito.any()))
                .thenReturn(MockFactory.buildUserEntity());
        Mockito.when(usersRepository.getById(Mockito.anyInt()))
                .thenReturn(MockFactory.buildUserEntity());

        UserRequest userRequest = MockFactory.buildUserRequest();
        ResponseResult<UserResponse> response = userService.updateUser(userRequest);
        Assertions.assertEquals(response.isError(), false);
        Assertions.assertEquals(response.getMessage(), Constants.MSG_UPDATE_USER_SUCCESSFUL);
        Assertions.assertEquals(response.getData().getId(), userRequest.getId());
    }

    @Test
    public void updateUserFailedTest() {

        UserRequest userRequest = MockFactory.buildUserRequest();

        Mockito.when(usersRepository.save(Mockito.any()))
                .thenReturn(MockFactory.buildUserEntity());

        try {
            userService.updateUser(userRequest);
        } catch (Exception exception) {
            Assertions.assertEquals(exception.getMessage(), Constants.MSG_NOT_FOUND_USER);
        }

    }

    @Test
    public void findByDatesTest() throws Exception {

        Mockito.when(usersRepository.findAllByCreationDateBetween(LocalDate.now(), LocalDate.now()))
                .thenReturn(Collections.singletonList(MockFactory.buildUserEntity()));

        ResponseResult<List<UserResponse>> response =
                userService.filterUserByDate(LocalDate.now(), LocalDate.now());

        Assertions.assertEquals(response.isError(), false);
        Assertions.assertEquals(response.getMessage(), Constants.MSG_SUCCESSFUL);
        Assertions.assertEquals(response.getData().size(), 1);
    }

    @Test
    public void findByDatesFailedTest() {

        try {
            userService.filterUserByDate(LocalDate.now(), LocalDate.now());
        } catch (Exception exception) {
            Assertions.assertEquals(exception.getMessage(), Constants.MSG_NOT_FOUND_USER);
        }
    }

    @Test
    public void findByDatesNullDateTest() {

        try {
            userService.filterUserByDate(null, null);
        } catch (Exception exception) {
            Assertions.assertEquals(exception.getMessage(), Constants.DATES_NULL);
        }
    }

    @Test
    public void findByIdSuccessfulTest() throws Exception {

        Mockito.when(usersRepository.getById(Mockito.anyInt()))
                .thenReturn(MockFactory.buildUserEntity());
        MockFactory.buildUserLoginEntity();
        ResponseResult<UserResponse> response = userService.getUserById(1);

        Assertions.assertEquals(response.isError(), false);
        Assertions.assertEquals(response.getMessage(), Constants.MSG_SUCCESSFUL);
        Assertions.assertEquals(response.getData().getId(), response.getData().getId());
    }

    @Test
    public void findByIdFailedTest() {

        try {
            userService.getUserById(1);
        } catch (Exception exception) {
            Assertions.assertEquals(exception.getMessage(), Constants.MSG_NOT_FOUND_USER);
        }
    }

    @Test
    public void findByIdFailedNullTest() {

        try {
            userService.getUserById(null);
        } catch (Exception exception) {
            Assertions.assertEquals(exception.getMessage(), Constants.USER_ID_NULL);
        }
    }

    @Test
    public void deleteUserSuccessfulTest() throws Exception {

        ResponseResult<Integer> response = userService.deleteUser(1);

        Assertions.assertEquals(response.isError(), false);
        Assertions.assertEquals(response.getMessage(), Constants.MSG_DELETE_USER_SUCCESSFUL);
        Assertions.assertEquals(response.getData(), 1);
    }

    @Test
    public void deleteUserFailedTest() {

        try {
            userService.deleteUser(null);
        } catch (Exception exception) {
            Assertions.assertEquals(exception.getMessage(), Constants.USER_ID_NULL);
        }
    }
}
