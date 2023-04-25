package br.edu.ufra.integracao.sigaa.infrastructure.security.dto.response;

import br.edu.ufra.autenticationufrapp.infrastructure.dto.response.JwtResponseDTO;
import br.edu.ufra.integracao.sigaa.domain.model.User;

import java.util.List;

public class UserResponseDTO extends JwtResponseDTO {
  private User userInfo;

  public UserResponseDTO(String token, String name, String username, User userInfo, List<String> roles) {
    super(token,name,username,roles);
    this.userInfo = userInfo;
  }

  public UserResponseDTO(User userInfo, JwtResponseDTO dto) {
    super(dto.getAccessToken(),dto.getName(),dto.getUsername(),dto.getRoles());
    this.userInfo = userInfo;
  }

  public User getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(User userInfo) {
    this.userInfo = userInfo;
  }
}
