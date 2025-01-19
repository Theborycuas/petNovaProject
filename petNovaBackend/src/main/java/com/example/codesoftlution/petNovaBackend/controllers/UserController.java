package com.example.codesoftlution.petNovaBackend.controllers;


import com.example.codesoftlution.petNovaBackend.models.UserModel;
import com.example.codesoftlution.petNovaBackend.repositories.IUserRepository;
import com.example.codesoftlution.petNovaBackend.response.ListUserResponse;
import com.example.codesoftlution.petNovaBackend.response.ResponseDataUserUpdate;
import com.example.codesoftlution.petNovaBackend.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import static com.example.codesoftlution.petNovaBackend.utils.Constants.*;

@RestController
@RequestMapping("apiPetNova/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    HttpServletRequest request;

    Logger log = Logger.getLogger(UserController.class.getName());

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity userRegister(@Valid @RequestBody UserModel userModel) {
        try{
            UserModel usuarioEncontrado = userService.findUserByEmail(userModel.getEmail(), true);
            if (usuarioEncontrado == null) {
                userService.registerSetUser(userModel);
                return new ResponseEntity("USUARIO CREADO", HttpStatus.CREATED);
            } else {
                return new ResponseEntity("USUARIO NO CREADO", HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity userUpdate(@Valid @RequestBody ResponseDataUserUpdate dataUserUpdate) {
        try {
            String token = request.getHeader("PnAuthorization");

            UserModel usuarioEncontrado = userService.findUserByTokenAndActive(token, true);
            if(usuarioEncontrado != null) {
                if(dataUserUpdate.getName() != null){
                    usuarioEncontrado.setName(dataUserUpdate.getName());
                }
                if (dataUserUpdate.getEmail() != null) {
                    usuarioEncontrado.setEmail(dataUserUpdate.getEmail());
                }
                if (dataUserUpdate.getIdNumber() != null) {
                    usuarioEncontrado.setIdNumber(dataUserUpdate.getIdNumber());
                }
                if (dataUserUpdate.getPhoneNumber() != null) {
                    usuarioEncontrado.setPhoneNumber(dataUserUpdate.getPhoneNumber());
                }
                if (dataUserUpdate.getLinkPerfilPhoto() != null) {
                    usuarioEncontrado.setLinkPerfilPhoto(dataUserUpdate.getLinkPerfilPhoto());
                }
                userService.registerSetUser(usuarioEncontrado);
                return new ResponseEntity("USUARIO ACTUALIZADO", HttpStatus.OK);
            }else {
                return new ResponseEntity("USUARIO NO ENCONTRADO", HttpStatus.CONFLICT);
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getUsers() {
        try {
            List<UserModel> userModelList = userService.getUsers();

            ListUserResponse listUserResponse = new ListUserResponse();
            listUserResponse.setUserModels(userModelList);
            listUserResponse.setMessage("Lista de Usuarios");

            return new ResponseEntity<>(listUserResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteUser() {
        try{
            String token = request.getHeader("PnAuthorization");
            UserModel usuarioEncontrado = userService.findUserByTokenAndActive(token, true);
            if(usuarioEncontrado != null) {
                usuarioEncontrado.setActive(false);
                userService.registerSetUser(usuarioEncontrado);

                return new ResponseEntity("USUARIO ELIMINADO", HttpStatus.OK);
            }else{
                return new ResponseEntity("USUARIO NO ENCONTRADO", HttpStatus.CONFLICT);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/pnCifrado/{token}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String pnCifrado(@PathVariable("token") String token) {
        try {
            IvParameterSpec iv = new IvParameterSpec(PN_INIT_VECTOR.getBytes(STANDARDCHARSETS_UFT_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(PN_AES_KEY.getBytes(STANDARDCHARSETS_UFT_8), ALGORITHM_AES);
            Cipher cipher;
            cipher = Cipher.getInstance(TRANSFORMATION_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

            byte[] encrypted = cipher.doFinal(token.getBytes());

            return Base64.getUrlEncoder()
                    .encodeToString(encrypted);

        } catch (UnsupportedEncodingException | NoSuchPaddingException | NoSuchAlgorithmException |
                 IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/pnDescifrado/{tokenCifrado}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String pnDescifrado(@PathVariable("tokenCifrado") String token) {
        try {
            IvParameterSpec iv = new IvParameterSpec(PN_INIT_VECTOR.getBytes(STANDARDCHARSETS_UFT_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(PN_AES_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM_AES);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

            byte[] desencriptado = cipher.doFinal(Base64.getUrlDecoder().decode(token));
            return new String(desencriptado);
        } catch (InvalidAlgorithmParameterException | UnsupportedEncodingException | NoSuchPaddingException |
                 IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }


    @RequestMapping(value = "/getOk", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getOk() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


}
