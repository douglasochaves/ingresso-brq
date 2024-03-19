//package br.com.brq.brqingresso.dataprovider.services;
//
//import br.com.brq.brqingresso.dataprovider.entities.UsuarioEntity;
//import br.com.brq.brqingresso.dataprovider.repositories.UsuarioRepository;
//import br.com.brq.brqingresso.dataprovider.mappers.UsuarioMapper;
//import br.com.brq.brqingresso.usecase.domains.CepResponse;
//import br.com.brq.brqingresso.mocks.CepResponseMock;
//import br.com.brq.brqingresso.mocks.UsuarioDomainMock;
//import br.com.brq.brqingresso.mocks.UsuarioEntityMock;
//import br.com.brq.brqingresso.usecase.domains.UsuarioDomain;
//import br.com.brq.brqingresso.usecase.domains.UsuarioListaDomain;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//class UsuarioDataBaseImplTest {
//
//    @Mock
//    private UsuarioDomain usuario;
//    @Mock
//    private UsuarioEntity usuarioEntity;
//    @Mock
//    private CepResponse cepResponse;
//    @Mock
//    private UsuarioRepository usuarioRepository;
//    @Mock
//    private UsuarioMapper usuarioEntityMap;
//    @Mock
//    private br.com.brq.brqingresso.entrypoint.mappers.UsuarioMapper usuarioDomainMap;
//    @Mock
//    private UsuarioAtualizaMap usuarioAtualizaMap;
//    @Mock
//    private CepService cepService;
//    @InjectMocks UsuarioDataBaseImpl usuarioDataBase;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        this.usuario = UsuarioDomainMock.getUsuario();
//        this.usuarioEntity = UsuarioEntityMock.getUsuarioRequestMock();
//        this.cepResponse = CepResponseMock.getCepResponseMock();
//    }
//
//    @Test
//    void testSave() {
//        when(cepService.processCep(usuario.getEndereco().getCep())).thenReturn(cepResponse);
//        when(usuarioEntityMap.mapToEntity(usuario, cepResponse)).thenReturn(usuarioEntity);
//        when(usuarioDomainMap.mapToDomainComCep(usuario, usuarioEntity)).thenReturn(usuario);
//        var result = usuarioDataBase.save(usuario);
//        assertAll(
//                () -> assertNotNull(result),
//                () -> assertEquals(usuario, result)
//        );
//    }
//
//    @Test
//    void testFindAll() {
//        List<UsuarioEntity> usuarios = Arrays.asList(usuarioEntity);
//        List<UsuarioListaDomain> usuariosDomain = Arrays.asList(new UsuarioListaDomain());
//        when(usuarioRepository.findAll()).thenReturn(usuarios);
//        when(usuarioListaMap.mapToUsuarioListaDomain(usuarios)).thenReturn(usuariosDomain);
//        var result = usuarioDataBase.findAll();
//        assertAll(
//                () -> assertNotNull(result),
//                () -> assertEquals(usuariosDomain, result)
//        );
//    }
//
//    @Test
//    void testExistsByCpf() {
//        when(usuarioRepository.existsByCpf(usuario.getCpf())).thenReturn(true);
//        var result = usuarioDataBase.existsByCpf(usuario.getCpf());
//        assertAll(
//                () -> assertNotNull(result),
//                () -> assertTrue(result)
//        );
//    }
//
//    @Test
//    void testExistsByEmail() {
//        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(true);
//        var result = usuarioDataBase.existsByEmail(usuario.getEmail());
//        assertAll(
//                () -> assertNotNull(result),
//                () -> assertTrue(result)
//        );
//    }
//
//    @Test
//    void testFindById() {
//        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuarioEntity));
//        when(usuarioDomainMap.mapToDomain(usuarioEntity)).thenReturn(usuario);
//        var result = usuarioDataBase.findById(usuario.getId());
//        assertAll(
//                () -> assertNotNull(result),
//                () -> assertEquals(usuario, result)
//        );
//    }
//
//    @Test
//    void testDelete() {
//        when(usuarioEntityMap.mapToEntity(usuario)).thenReturn(usuarioEntity);
//        usuarioDataBase.delete(usuario);
//        verify(usuarioRepository).delete(usuarioEntity);
//    }
//
//    @Test
//    void testPatch() {
//        when(cepService.processCep(usuario.getEndereco().getCep())).thenReturn(cepResponse);
//        when(usuarioEntityMap.mapToEntity(usuario, cepResponse)).thenReturn(usuarioEntity);
//        when(usuarioAtualizaMap.mapUsuarioAtualiza(usuario, usuarioEntity, cepResponse)).thenReturn(usuarioEntity);
//        when(usuarioDomainMap.mapToDomain(usuarioEntity)).thenReturn(usuario);
//        var result = usuarioDataBase.patch(usuario, usuario);
//        assertAll(
//                () -> assertNotNull(result),
//                () -> assertEquals(usuario, result)
//        );
//    }
//
//    @Test
//    void testSaveHash() {
//        when(usuarioEntityMap.mapToEntity(usuario)).thenReturn(usuarioEntity);
//        when(usuarioDomainMap.mapToDomain(usuarioEntity)).thenReturn(usuario);
//        var result = usuarioDataBase.saveHash(usuario, "");
//        assertAll(
//                () -> assertNotNull(result),
//                () -> assertEquals(usuario, result)
//        );
//    }
//
//    @Test
//    void testPutSenha() {
//        when(usuarioEntityMap.mapToEntity(usuario)).thenReturn(usuarioEntity);
//        usuarioDataBase.putSenha(usuario, "");
//        verify(usuarioRepository).save(usuarioEntity);
//    }
//}
//
