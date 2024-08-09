package prueba_tecnica.usuarios.MockFactory;

import org.springframework.http.ResponseEntity;
import prueba_tecnica.usuarios.dataprovider.jpa.entities.StatusEntity;
import prueba_tecnica.usuarios.dataprovider.jpa.entities.UserEntity;
import prueba_tecnica.usuarios.dataprovider.jpa.entities.UserLoginEntity;
import prueba_tecnica.usuarios.domain.ResponseResult;
import prueba_tecnica.usuarios.domain.UserRequest;
import prueba_tecnica.usuarios.domain.UserResponse;

public class MockFactory {

    private MockFactory() {
    }

    public static UserRequest buildUserRequest() {
        return UserRequest.builder()
                .id(1)
                .name("JUAN")
                .lastName("PABON")
                .documentTypeID(1)
                .documentNumber("105541354")
                .phone("3155151354")
                .email("JUAN.PABON@GMAIL.COM")
                .rol(1)
                .status(1)
                .build();
    }

    public static ResponseEntity<ResponseResult<UserResponse>> buildResponseEntity() {

        ResponseResult<UserResponse> userResponse =
                new ResponseResult(false, "Successful", buildUserResponse());
        return ResponseEntity.ok(userResponse);
    }

    public static UserResponse buildUserResponse() {

        return UserResponse.builder()
                .name("JUAN")
                .lastName("PABON")
                .documentType(1)
                .documentNumber("105541354")
                .phone("3155151354")
                .email("JUAN.PABON@GMAIL.COM")
                .rol(1)
                .status(1)
                .build();
    }

    public static StatusEntity buildStatusEntity() {
        return StatusEntity.builder()
                .id(1)
                .description("ACTIVO")
                .build();
    }

    public static UserEntity buildUserEntity() {
        return UserEntity.builder()
                .id(1)
                .name("JUAN")
                .lastName("PABON")
                .documentTypeId(1)
                .documentNumber("105541354")
                .phone("3155151354")
                .email("JUAN.PABON@GMAIL.COM")
                .rolId(1)
                .statusId(1)
                .build();
    }

    public static UserLoginEntity buildUserLoginEntity() {
        return UserLoginEntity.builder()
                .id(1)
                .username("username")
                .password("password")
                .userId(1)
                .build();
    }
}
