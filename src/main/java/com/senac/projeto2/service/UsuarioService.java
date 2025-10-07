package com.senac.projeto2.service;

import com.senac.projeto2.config.SecurityConfiguration;
import com.senac.projeto2.dto.request.LoginUserDto;
import com.senac.projeto2.dto.request.UsuarioDtoRequest;
import com.senac.projeto2.dto.response.RecoveryJwtTokenDto;
import com.senac.projeto2.dto.response.UsuarioDtoResponse;
import com.senac.projeto2.entity.Role;
import com.senac.projeto2.entity.Usuario;
import com.senac.projeto2.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private SecurityConfiguration securityConfiguration;


    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios(){
        return this.usuarioRepository.listarUsuariosAtivos();
    }

    public Usuario listarUsuarioPorId(int idUsuario){
        return this.usuarioRepository.obterUsuarioAtivoPorId(idUsuario);
    }

    // Método responsável por autenticar um usuário e retornar um token JWT
    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.login(), loginUserDto.senha());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    public UsuarioDtoResponse salvar(UsuarioDtoRequest usuarioDtoRequest) {
        Role role = new Role();
        role.setName(usuarioDtoRequest.getRoleName());

        Usuario usuario = new Usuario();
        usuario.setCpf(usuarioDtoRequest.getCpf());
        usuario.setNome(usuarioDtoRequest.getNome());
        usuario.setDataNascimento(usuarioDtoRequest.getDataNascimento());
        usuario.setLogin(usuarioDtoRequest.getLogin());
        usuario.setSenha(securityConfiguration.passwordEncoder().encode(usuarioDtoRequest.getSenha()));
        usuario.setStatus(1);
        usuario.setRoles(List.of(role));

        Usuario usuarioSave = this.usuarioRepository.save(usuario);

        return modelMapper.map(usuarioSave, UsuarioDtoResponse.class);
    }

    public UsuarioDtoResponse atualizar(@Valid Integer idUsuario, UsuarioDtoRequest usuarioDtoRequest) {
        Usuario usuario = this.listarUsuarioPorId(idUsuario);
        if(usuario!= null){
            modelMapper.map(usuarioDtoRequest, usuario);
            Usuario usuarioTemp = this.usuarioRepository.save(usuario);
            return modelMapper.map(usuarioTemp, UsuarioDtoResponse.class);
        }else{
            return null;
        }
    }

    public void apagar(Integer idUsuario) {
        this.usuarioRepository.apagadoLogico(idUsuario);
    }
}
