package prueba_tecnica.usuarios.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import prueba_tecnica.usuarios.Constants.Constants;
import prueba_tecnica.usuarios.dataprovider.jpa.entities.StatusEntity;
import prueba_tecnica.usuarios.dataprovider.jpa.entities.UserEntity;
import prueba_tecnica.usuarios.dataprovider.jpa.repositories.StatusRepository;
import prueba_tecnica.usuarios.dataprovider.jpa.repositories.UsersRepository;
import prueba_tecnica.usuarios.domain.ResponseResult;
import prueba_tecnica.usuarios.domain.UserRequest;
import prueba_tecnica.usuarios.domain.UserResponse;
import prueba_tecnica.usuarios.utils.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements IUserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class.getName());

    private final UsersRepository usersRepository;
    private final StatusRepository statusRepository;

    public UserService(UsersRepository usersRepository, StatusRepository statusRepository) {
        this.usersRepository = usersRepository;
        this.statusRepository = statusRepository;
    }

    private final String STATUS_ACTIVE = "ACTIVO";

    @Override
    public ResponseResult<UserResponse> saveUser(UserRequest userRequest) throws Exception {

        UserResponse userResponse = new UserResponse();
        UserEntity userEntity = Mapper.userRequestToUserEntity(userRequest);
        setUserStatus(userEntity);

        try {
            userEntity = this.usersRepository.save(userEntity);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new Exception(Constants.MSG_ERROR_USER);
        }
        userResponse = Mapper.userEntityToUserResponse(userEntity);
        return new ResponseResult<>(false, Constants.MSG_CREATED_USER_SUCCESSFUL, userResponse);
    }

    @Override
    public ResponseResult<UserResponse> updateUser(UserRequest userRequest) throws Exception {

        UserEntity userEntity = findUserById(userRequest.getId());
        UserEntity updateUser = Mapper.userRequestToUserEntity(userRequest);
        updateUser.setId(userEntity.getId());

        try {
            userEntity = this.usersRepository.save(updateUser);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new Exception(Constants.MSG_ERROR_USER);
        }

        UserResponse response = Mapper.userEntityToUserResponse(userEntity);
        return new ResponseResult<>(false, Constants.MSG_UPDATE_USER_SUCCESSFUL, response);
    }

    @Override
    public ResponseResult<UserResponse> getUserById(Integer userId) throws Exception {

        UserEntity userEntity = findUserById(userId);
        UserResponse userResponse = Mapper.userEntityToUserResponse(userEntity);
        return new ResponseResult<>(false, Constants.MSG_SUCCESSFUL, userResponse);
    }

    @Override
    public ResponseResult<Integer> deleteUser(Integer userId) throws Exception {

        if (Objects.isNull(userId)) {
            log.error(Constants.USER_ID_NULL);
            throw new Exception(Constants.USER_ID_NULL);
        }

        this.usersRepository.delete(userId);

        return new ResponseResult<>(false, Constants.MSG_DELETE_USER_SUCCESSFUL, userId);
    }

    @Override
    public ResponseResult<List<UserResponse>> filterUserByDate(LocalDate starDate, LocalDate endDate) throws Exception {
        if (Objects.isNull(starDate) || Objects.isNull(endDate)) {
            log.error(Constants.DATES_NULL);
            throw new Exception(Constants.DATES_NULL);
        }

        List<UserEntity> userEntityList = this.usersRepository.findAllByCreationDateBetween(starDate, endDate);
        if (userEntityList.isEmpty()) {
            log.error(Constants.MSG_NOT_FOUND_USER);
            throw new Exception(Constants.MSG_NOT_FOUND_USER);
        }
        List<UserResponse> userResponse = userEntityList.stream()
                .map(Mapper::userEntityToUserResponse).toList();

        return new ResponseResult<>(false, Constants.MSG_SUCCESSFUL, userResponse);
    }

    private void setUserStatus(UserEntity userEntity) throws Exception {
        try {
            StatusEntity status = this.statusRepository.findByDescription(STATUS_ACTIVE);
            userEntity.setStatusId(status.getId());
        } catch (Exception exception) {
            log.error(String.format(Constants.MSG_ERROR_QUERY_STATUS, STATUS_ACTIVE));
            throw new Exception(String.format(Constants.MSG_ERROR_QUERY_STATUS, STATUS_ACTIVE));
        }
    }

    private UserEntity findUserById(Integer userId) throws Exception {

        if (Objects.isNull(userId) || userId == 0) {
            log.error(Constants.USER_ID_NULL);
            throw new Exception(Constants.USER_ID_NULL);
        }
        UserEntity userEntity = this.usersRepository.getById(userId);

        if (Objects.nonNull(userEntity)) {
            return userEntity;
        } else {
            log.error(Constants.USER_ID_NULL);
            throw new Exception(Constants.MSG_NOT_FOUND_USER);
        }
    }

}
