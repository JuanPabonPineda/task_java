package prueba_tecnica.usuarios.utils;

import prueba_tecnica.usuarios.dataprovider.jpa.entities.UserEntity;
import prueba_tecnica.usuarios.domain.UserRequest;
import prueba_tecnica.usuarios.domain.UserResponse;

import java.time.LocalDate;

public class Mapper {

    public static UserEntity userRequestToUserEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .name(userRequest.getName())
                .lastName(userRequest.getLastName())
                .documentTypeId(userRequest.getDocumentTypeID())
                .documentNumber(userRequest.getDocumentNumber())
                .phone(userRequest.getPhone())
                .email(userRequest.getEmail())
                .rolId(userRequest.getRol())
                .creationDate(LocalDate.now())
                .statusId(userRequest.getStatus())
                .build();
    }

    public static UserResponse userEntityToUserResponse(UserEntity userEntity) {

        return UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .lastName(userEntity.getLastName())
                .documentType(userEntity.getDocumentTypeId())
                .documentNumber(userEntity.getDocumentNumber())
                .phone(userEntity.getPhone())
                .email(userEntity.getEmail())
                .rol(userEntity.getRolId())
                .status(userEntity.getStatusId())
                .build();
    }

}
