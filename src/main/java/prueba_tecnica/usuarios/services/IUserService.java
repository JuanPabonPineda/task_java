package prueba_tecnica.usuarios.services;

import prueba_tecnica.usuarios.domain.ResponseResult;
import prueba_tecnica.usuarios.domain.UserRequest;
import prueba_tecnica.usuarios.domain.UserResponse;

import java.time.LocalDate;
import java.util.List;

public interface IUserService {

    ResponseResult<UserResponse> saveUser(UserRequest userRequest) throws Exception;

    ResponseResult<UserResponse> updateUser(UserRequest userRequest) throws Exception;

    ResponseResult<UserResponse> getUserById(Integer userId) throws Exception;

    ResponseResult deleteUser(Integer userId) throws Exception;

    ResponseResult<List<UserResponse>> filterUserByDate(LocalDate starDate, LocalDate endDate) throws Exception;
}
