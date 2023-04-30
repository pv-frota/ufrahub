package br.edu.ufra.autenticationufrapp.infrastructure.dto.response;

import java.util.List;

public class JwtResponseDTO {
  private String token;
  private String type = "Bearer";
  private String name;
  private String username;
  private List<String> roles;

  public JwtResponseDTO() {
  }

  public JwtResponseDTO(String token, String name, String username, List<String> roles) {
    this.token = token;
    this.name = name;
    this.username = username;
    this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getRoles() {
    return roles;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
