package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.UsuarioEntity;

public class UsuarioService {
	
    public void cadastrarUsuario(UsuarioEntity usuario){
    	
    	   try {
    	    	
    		   Connection myConnection = Conexao.connect();
               
               String sql_cmd = "INSERT INTO USUARIOS ("
            		   + "ID_USUARIO, "
                       + "NOME, "
                       + "EMAIL, "
                       + "SENHA, "
                       + "TELEFONE)"
                       + "VALUES (?, ?, ?, ?, ?)";
               PreparedStatement pstm = myConnection.prepareStatement(sql_cmd);
               
               pstm.setInt(1,1);
               pstm.setString(2, usuario.getNome());
               pstm.setString(3, usuario.getEmail());
               pstm.setString(4, usuario.getSenha());
               pstm.setString(5, usuario.getTelefone());
               pstm.executeUpdate();
               pstm.close();
               
           }catch (SQLException ex) {
               System.out.println(ex);
           }
           
           Conexao.disconnect();       
       }
    	
    }