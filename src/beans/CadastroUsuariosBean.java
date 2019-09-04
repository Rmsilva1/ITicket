package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import entities.UsuarioEntity;
import retorno.UsuarioRetorno;
import service.UsuarioService;

@ManagedBean
public class CadastroUsuariosBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7140657673205336009L;
	
	private UsuarioRetorno usuarioRetorno;
	private List<UsuarioRetorno> listaUsuario;
	private UsuarioService usuarioService;
	private String nome;
	private String email;
	private String senha;
	private String senhaConfirma;
	private String telefone;
	
	@PostConstruct
	public void init(){
		usuarioService = new UsuarioService();
	}
	
	public Boolean confirmaSenha() {
		return senha.equals(senhaConfirma);
	}
	
	public void cadastrarUsuario() {
		if(confirmaSenha()) {
			UsuarioEntity usuario = new UsuarioEntity();
			usuario.setEmail(email);
			usuario.setNome(nome);
			usuario.setSenha(senha);
			usuario.setTelefone(telefone);
			usuarioService.cadastrarUsuario(usuario);
		}
	}
	
	public CadastroUsuariosBean() {}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public UsuarioRetorno getUsuarioRetorno() {
		return usuarioRetorno;
	}

	public void setUsuarioRetorno(UsuarioRetorno usuarioRetorno) {
		this.usuarioRetorno = usuarioRetorno;
	}

	public List<UsuarioRetorno> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<UsuarioRetorno> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public String getSenhaConfirma() {
		return senhaConfirma;
	}

	public void setSenhaConfirma(String senhaConfirma) {
		this.senhaConfirma = senhaConfirma;
	}
}
