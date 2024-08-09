package prueba_tecnica.usuarios.consumer.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import prueba_tecnica.usuarios.MockFactory.MockFactory;
import prueba_tecnica.usuarios.domain.ResponseResult;
import prueba_tecnica.usuarios.domain.UserResponse;
import prueba_tecnica.usuarios.services.UserService;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
class USerApiRestTest {

    UserService userService = Mockito.mock(UserService.class);

    @Autowired
    UserApiRest userApiRest = new UserApiRest(userService);

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveUserSuccessfulTest() {

        ResponseEntity<ResponseResult<UserResponse>> response =
                userApiRest.saveUser(MockFactory.buildUserRequest());

        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    public void saveUserFailedTest() throws Exception {

        Mockito.when(userService.saveUser(MockFactory.buildUserRequest())).thenThrow(Exception.class);

        ResponseEntity<ResponseResult<UserResponse>> response =
                userApiRest.saveUser(MockFactory.buildUserRequest());

        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void updateUserSuccessfulTest() {

        ResponseEntity<ResponseResult<UserResponse>> response =
                userApiRest.updateUser(MockFactory.buildUserRequest());

        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    public void updateUserFailedTest() throws Exception {

        Mockito.when(userService.updateUser(MockFactory.buildUserRequest())).thenThrow(Exception.class);

        ResponseEntity<ResponseResult<UserResponse>> response =
                userApiRest.updateUser(MockFactory.buildUserRequest());

        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void findByDateSuccessfulTest() {

        ResponseEntity<ResponseResult<List<UserResponse>>> response =
                userApiRest.filterByDate(LocalDate.now(), LocalDate.now());

        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    public void findByDateFailedTest() throws Exception {

        Mockito.when(userService.filterUserByDate(LocalDate.now(), LocalDate.now())).thenThrow(Exception.class);

        ResponseEntity<ResponseResult<List<UserResponse>>> response =
                userApiRest.filterByDate(LocalDate.now(), LocalDate.now());

        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void findByIdSuccessfulTest() {

        ResponseEntity<ResponseResult<UserResponse>> response = userApiRest.findById(1);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    public void findByIdFailedTest() throws Exception {

        Mockito.when(userService.getUserById(Mockito.anyInt())).thenThrow(Exception.class);

        ResponseEntity<ResponseResult<UserResponse>> response = userApiRest.findById(1);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void deleteUserSuccessfulTest() {

        ResponseEntity<ResponseResult<Integer>> response = userApiRest.deleteUser(1);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    public void deleteUserFailedTest() throws Exception {

        Mockito.when(userService.deleteUser(Mockito.anyInt())).thenThrow(Exception.class);

        ResponseEntity<ResponseResult<Integer>> response = userApiRest.deleteUser(1);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
    }
}
