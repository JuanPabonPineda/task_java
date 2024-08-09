package prueba_tecnica.usuarios.consumer.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import prueba_tecnica.usuarios.domain.ResponseResult;
import prueba_tecnica.usuarios.domain.UserRequest;
import prueba_tecnica.usuarios.domain.UserResponse;
import prueba_tecnica.usuarios.services.UserService;

import java.time.LocalDate;
import java.util.List;

@ResponseBody
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/user")
public class UserApiRest {

    private final UserService userService;

    @PostMapping(path = "/save")
    public ResponseEntity<ResponseResult<UserResponse>> saveUser(@RequestBody UserRequest request ) {
        try {
            return ResponseEntity.ok(userService.saveUser(request));
        } catch (Exception exception) {
            ResponseResult responseResult = new ResponseResult(true, exception.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseResult);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseResult<UserResponse>> updateUser(@RequestBody UserRequest request) {
        try {
            return ResponseEntity.ok(userService.updateUser(request));
        } catch (Exception exception) {
            ResponseResult responseResult = new ResponseResult(true, exception.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseResult);
        }
    }

    @GetMapping(path = "/findById/{userId}")
    ResponseEntity<ResponseResult<UserResponse>> findById(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(userService.getUserById(userId));
        } catch (Exception exception) {
            ResponseResult responseResult = new ResponseResult(true, exception.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseResult);
        }
    }

    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity<ResponseResult<Integer>> deleteUser(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(userService.deleteUser(userId));
        } catch (Exception exception) {
            ResponseResult responseResult = new ResponseResult(true, exception.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseResult);
        }
    }

    @GetMapping(path = "/filterByDate")
    public ResponseEntity<ResponseResult<List<UserResponse>>> filterByDate(
            @RequestParam LocalDate starDate, @RequestParam LocalDate endDate) {
        try {
            return ResponseEntity.ok(userService.filterUserByDate(starDate, endDate));
        } catch (Exception exception) {
            ResponseResult responseResult = new ResponseResult(true, exception.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseResult);
        }
    }

}
